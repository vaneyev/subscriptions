package test.subscriptions.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@JsonAutoDetect
@Accessors(chain = true)
@Getter
@Setter
@ToString
public class UserRequest {
    @Schema(description = "Имя пользователя")
    @NotNull
    String name;
}
