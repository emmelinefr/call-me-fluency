package dev.alexandraemmeline.call_me_fluency.Infrastructure.Controllers;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.UserDomain;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.*;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.ChangePasswordRequest;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.CreateUserRequest;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.DeleteUserRequest;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.UserResponse;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Handler.SuccessResponse;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Mappers.UserMapper;
import jakarta.validation.Valid;
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
    private final DeleteUserUseCase deleteUserUseCase;
    private final ListUsersUseCase listUsersUseCase;
    private final FindUserByEmailUseCase findUserByEmailUseCase;
    private final ChangePasswordUseCase changePasswordUseCase;



    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody @Valid DeleteUserRequest deleteUserRequest) {
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

    @GetMapping("/email/{email}")
    public ResponseEntity<SuccessResponse<UserResponse>> findByEmail(@PathVariable String email) {

        UserDomain user = findUserByEmailUseCase.execute(email);

        UserResponse userResponse = userMapper.toResponse(user);

        SuccessResponse<UserResponse> response = new SuccessResponse<>(
                true,
                "User successfully found.",
                userResponse,
                LocalDateTime.now()
        );

        return ResponseEntity.ok(response);

    }

    @PatchMapping("/me/password")
    public ResponseEntity<SuccessResponse<Void>> changePassword(@RequestBody @Valid ChangePasswordRequest changePasswordRequest) {

        changePasswordUseCase.execute(changePasswordRequest.email(), changePasswordRequest.currentPassword(), changePasswordRequest.newPassword());

        SuccessResponse<Void> response = new SuccessResponse<>(
                true,
                "Password changed successfully.",
                null,
                LocalDateTime.now()
        );

        return ResponseEntity.ok(response);
    }


}
