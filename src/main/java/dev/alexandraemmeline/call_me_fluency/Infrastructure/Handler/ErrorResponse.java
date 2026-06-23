package dev.alexandraemmeline.call_me_fluency.Infrastructure.Handler;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse (

        boolean success,
        String message,
        List<String> erros,
        LocalDateTime timestamp

){}
