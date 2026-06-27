package dev.alexandraemmeline.call_me_fluency.Infrastructure.Controllers;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.UserDomain;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.CreateUserUseCase;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.CreateUserRequest;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.UserResponse;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Handler.SuccessResponse;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final CreateUserUseCase createUserUseCase;

    @PostMapping
    public ResponseEntity<SuccessResponse<UserResponse>> create(@RequestBody CreateUserRequest createUserRequest) {

        UserDomain userToCreate = userMapper.toDomain(createUserRequest);
        UserDomain createdUser = createUserUseCase.execute(userToCreate);

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
