package dev.alexandraemmeline.call_me_fluency.Infrastructure.Controllers;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.UserDomain;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.LoginUseCase;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.RegisterUserUseCase;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.LoginRequest;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.LoginResponse;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.RegisterUserRequest;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.UserResponse;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Handler.SuccessResponse;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Mappers.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserMapper userMapper;
    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUseCase loginUseCase;


    @PostMapping("/register")
    public ResponseEntity<SuccessResponse<UserResponse>> register(@RequestBody @Valid RegisterUserRequest registerUserRequest) {

        UserDomain userToCreate = userMapper.toDomain(registerUserRequest);
        UserDomain createdUser = registerUserUseCase.execute(userToCreate);

        UserResponse userRegisteredResponse = userMapper.toResponse(createdUser);

        SuccessResponse<UserResponse> response = new SuccessResponse<>(
                true,
                "User created successfully.",
                userRegisteredResponse,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);

    }


    //login
    @PostMapping("/login")
    public ResponseEntity<SuccessResponse<LoginResponse>> login(@RequestBody @Valid LoginRequest loginRequest) {

        String token = loginUseCase.execute(loginRequest.email(), loginRequest.password());

        LoginResponse loginResponse = new LoginResponse(token);

        SuccessResponse<LoginResponse> response = new SuccessResponse<>(
                true,
                "Login successful",
                loginResponse,
                LocalDateTime.now()
        );

        return ResponseEntity.ok(response);
    }


}
