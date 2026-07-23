package dev.alexandraemmeline.call_me_fluency.Infrastructure.Controllers;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.UserDomain;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.RegisterUserUseCase;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.CreateUserRequest;
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


    @PostMapping("/register")
    public ResponseEntity<SuccessResponse<UserResponse>> register(@RequestBody @Valid CreateUserRequest createUserRequest) {

        UserDomain userToCreate = userMapper.toDomain(createUserRequest);
        UserDomain createdUser = registerUserUseCase.execute(userToCreate);

        UserResponse userCreatedResponse = userMapper.toResponse(createdUser);

        SuccessResponse<UserResponse> response = new SuccessResponse<>(
                true,
                "User created successfully.",
                userCreatedResponse,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);

    }





}
