package test.subscriptions.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ApiErrorResponseDto {

    private HttpStatus status;
    private Instant timestamp;
    private String message;
    private String debugMessage;
    private List<String> stackTrace;

    private ApiErrorResponseDto() {
        timestamp = Instant.now();
    }

    public ApiErrorResponseDto(HttpStatus status) {
        this();
        this.status = status;
    }

    public ApiErrorResponseDto(HttpStatus status, Throwable ex) {
        this(status, "Unexpected error", ex);
    }

    public ApiErrorResponseDto(HttpStatus status, String message,
                               Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ExceptionUtils.getRootCauseMessage(ex);
    }
}