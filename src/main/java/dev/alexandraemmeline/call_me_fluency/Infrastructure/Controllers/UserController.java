package dev.alexandraemmeline.call_me_fluency.Infrastructure.Controllers;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.UserDomain;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.CreateUserUseCase;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.DeleteUserUseCase;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.ListUsersUseCase;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.CreateUserRequest;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.DeleteUserRequest;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.UserResponse;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Handler.SuccessResponse;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final CreateUserUseCase createUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final ListUsersUseCase listUsersUseCase;

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

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody DeleteUserRequest deleteUserRequest) {
        deleteUserUseCase.execute(deleteUserRequest.email(), deleteUserRequest.password());

        return ResponseEntity.noContent()
                .build();

    }


    @GetMapping
    public ResponseEntity<SuccessResponse<List<UserResponse>>> listAll() {
        List<UserResponse> listOfUsers = listUsersUseCase.execute()
                .stream()
                .map(userMapper::toResponse)
                .toList();

        SuccessResponse<List<UserResponse>> response = new SuccessResponse<>(
                true,
                "Users listed successfully.",
                listOfUsers,
                LocalDateTime.now()
        );

        return ResponseEntity.ok(response);

    }


}
