package dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.User;


public record LoginResponse(

        String token,
        Long expiresIn

) {
}
