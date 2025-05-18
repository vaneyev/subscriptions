package test.subscriptions.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.UUID;

@JsonAutoDetect
@Accessors(chain = true)
@Getter
@Setter
@ToString
public class UserResponse {
    @Schema(description = "Идентификатор пользователя")
    private UUID userId;
    @Schema(description = "Имя пользователя")
    private String name;
}
