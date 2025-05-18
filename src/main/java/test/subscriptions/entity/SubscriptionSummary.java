package test.subscriptions.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonAutoDetect
public class SubscriptionSummary {
    @Schema(description = "Наименование подписки")
    private String name;
    @Schema(description = "Количество использования подписки")
    private Long count;
}
