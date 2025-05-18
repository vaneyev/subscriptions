package test.subscriptions.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.subscriptions.dto.SubscriptionRequest;
import test.subscriptions.dto.SubscriptionResponse;
import test.subscriptions.dto.UserRequest;
import test.subscriptions.dto.UserResponse;
import test.subscriptions.service.SubscriptionService;
import test.subscriptions.service.UserService;

import java.util.List;
import java.util.UUID;

import static test.subscriptions.util.Constant.HEADER_REQUEST_ID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final SubscriptionService subscriptionService;

    @Operation(summary = "Получение пользователя", description = "Возвращает пользователя по id")
    @GetMapping( value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public UserResponse getUser(
            @Parameter(description = "Идентификатор для отслеживания запроса")
            @RequestHeader(value = HEADER_REQUEST_ID, required = false)
            String requestId,
            @Parameter(description = "Идентификатор пользователя")
            @PathVariable
            String id
    ) {
        log.info("Request: getUser, request id: {}, user id: {}", requestId, id);
        UserResponse response = userService.getUser(UUID.fromString(id));
        log.info("Response for request: getUser, request id: {}, response: {}", requestId, response);
        return response;
    }

    @Operation(summary = "Удаление пользователя", description = "Удаляет пользователя по id")
    @DeleteMapping(value = "/{id}")
    public void deleteUser(
            @Parameter(description = "Идентификатор для отслеживания запроса")
            @RequestHeader(value = HEADER_REQUEST_ID, required = false)
            String requestId,
            @Parameter(description = "Идентификатор пользователя")
            @PathVariable
            String id
    ) {
        log.info("Request: deleteUser, request id: {}, user id: {}", requestId, id);
        userService.deleteUser(UUID.fromString(id));
        log.info("Response for request: deleteUser, request id: {}, user id: {}", requestId, id);
    }

    @Operation(summary = "Создание пользователя", description = "Создает пользователя с указанным именем и возвращает его id")
    @PostMapping
    public UserResponse postUser(
            @Parameter(description = "Идентификатор для отслеживания запроса")
            @RequestHeader(value = HEADER_REQUEST_ID, required = false)
            String requestId,
            @Parameter(description = "Параметры пользователя")
            @RequestBody
            UserRequest request
    ) {
        log.info("Request: postUser, request id: {}, request body: {}", requestId, request);
        UserResponse response = userService.postUser(request);
        log.info("Response for request: postUser, request id: {}, response: {}", requestId, response);
        return response;
    }

    @Operation(summary = "Изменение пользователя", description = "Изменяет имя пользователя по id")
    @PutMapping(value = "/{id}")
    public UserResponse putUser(
            @Parameter(description = "Идентификатор для отслеживания запроса")
            @RequestHeader(value = HEADER_REQUEST_ID, required = false)
            String requestId,
            @Parameter(description = "Идентификатор пользователя")
            @PathVariable
            String id,
            @Parameter(description = "Параметры пользователя")
            @RequestBody
            UserRequest request
    ) {
        log.info("Request: putUser, request id: {}, user id: {}, request body: {}", requestId, id, request);
        UserResponse response = userService.putUser(UUID.fromString(id), request);
        log.info("Response for request: putUser, request id: {}, user id: {}", requestId, id);
        return response;
    }

    @Operation(summary = "Получение подписок пользователя", description = "Возвращает подписки пользователя по его id")
    @GetMapping(value = "/{id}/subscriptions")
    public List<SubscriptionResponse> getSubscriptions(
            @Parameter(description = "Идентификатор для отслеживания запроса")
            @RequestHeader(value = HEADER_REQUEST_ID, required = false)
            String requestId,
            @Parameter(description = "Идентификатор пользователя")
            @PathVariable
            String id
    ) {
        log.info("Request: getSubscriptions, request id: {}, user id: {}", requestId, id);
        List<SubscriptionResponse> response = subscriptionService.getSubscriptions(UUID.fromString(id));
        log.info("Response for request: getSubscriptions, request id: {}, user id: {}, response: {}", requestId, id, response);
        return response;
    }

    @Operation(summary = "Создание подписки", description = "Создает подписку для пользователя")
    @PostMapping(value = "/{id}/subscriptions")
    public SubscriptionResponse postSubscriptions(
            @Parameter(description = "Идентификатор для отслеживания запроса")
            @RequestHeader(value = HEADER_REQUEST_ID, required = false)
            String requestId,
            @Parameter(description = "Идентификатор пользователя")
            @PathVariable
            String id,
            @Parameter(description = "Параметры подписки")
            @RequestBody
            SubscriptionRequest request
    ) {
        log.info("Request: postSubscriptions, request id: {}, user id: {}, request: {}", requestId, id, request);
        SubscriptionResponse response = subscriptionService.postSubscription(UUID.fromString(id), request);
        log.info("Response for request: postSubscriptions, request id: {}, user id: {}, response: {}", requestId, id, response);
        return response;
    }

    @Operation(summary = "Удаление подписки", description = "Удаляет подписку пользователя")
    @DeleteMapping(value = "/{id}/subscriptions/{sub_id}")
    public void deleteSubscriptions(
            @Parameter(description = "Идентификатор для отслеживания запроса")
            @RequestHeader(value = HEADER_REQUEST_ID, required = false)
            String requestId,
            @Parameter(description = "Идентификатор пользователя")
            @PathVariable
            String id,
            @Parameter(description = "Идентификатор подписки")
            @PathVariable
            String sub_id
    ) {
        log.info("Request: deleteSubscriptions, request id: {}, user id: {}, subscription id: {}", requestId, id, sub_id);
        subscriptionService.deleteSubscription(UUID.fromString(id),  UUID.fromString(sub_id));
        log.info("Response for request: deleteSubscriptions, request id: {}, user id: {}, subscription id: {}", requestId, id, sub_id);
    }
}
