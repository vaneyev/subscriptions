package test.subscriptions.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.subscriptions.entity.SubscriptionSummary;
import test.subscriptions.service.SubscriptionService;

import java.util.List;

import static test.subscriptions.util.Constant.HEADER_REQUEST_ID;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
@Slf4j
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @Operation(summary = "Получение топовых подписок", description = "Возвращает первые три самые используемые подписки")
    @GetMapping("/top")
    public List<SubscriptionSummary> getTopSubscriptions(
            @Parameter(description = "Идентификатор для отслеживания запроса")
            @RequestHeader(value = HEADER_REQUEST_ID, required = false)
            String requestId
    ) {
        log.info("Request: getTopSubscriptions, request id: {}", requestId);
        List<SubscriptionSummary> response = subscriptionService.getTopSubscriptions();
        log.info("Response for request: getTopSubscriptions, request id: {}", requestId);
        return response;
    }
}
