package dev.alexandraemmeline.call_me_fluency.Infrastructure.Handler;

import java.time.LocalDateTime;

public record SuccessResponse<T>(

        boolean success,
        String message,
        T data,
        LocalDateTime timestamp

) {
}
