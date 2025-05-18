package test.subscriptions.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.UUID;

@JsonAutoDetect
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class SubscriptionResponse {
    @Schema(description = "Идентификатор подписки")
    UUID id;
    @Schema(description = "Идентификатор пользователя")
    UUID userId;
    @Schema(description = "Наименование подписки")
    String name;
}
