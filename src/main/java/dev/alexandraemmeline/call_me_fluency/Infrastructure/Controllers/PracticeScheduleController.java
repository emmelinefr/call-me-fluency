package dev.alexandraemmeline.call_me_fluency.Infrastructure.Controllers;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeDayDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeDayKey;
import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeScheduleDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Domains.UserDomain;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSchedule.CreatePracticeScheduleUseCase;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSchedule.DeletePracticeScheduleUseCase;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSchedule.FindPracticeScheduleByUserIdUseCase;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSchedule.UpdatePracticeScheduleUseCase;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.User.FindUserByEmailUseCase;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.PracticeSchedule.CreatePracticeScheduleRequest;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.PracticeSchedule.PracticeScheduleResponse;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.PracticeSchedule.UpdatePracticeScheduleRequest;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Handler.SuccessResponse;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Mappers.PracticeDayMapper;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Mappers.PracticeScheduleMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/schedule")
@RequiredArgsConstructor
public class PracticeScheduleController {

    private final PracticeScheduleMapper practiceScheduleMapper;
    private final PracticeDayMapper practiceDayMapper;
    private final CreatePracticeScheduleUseCase createPracticeScheduleUseCase;
    private final FindUserByEmailUseCase findUserByEmailUseCase;
    private final FindPracticeScheduleByUserIdUseCase findPracticeScheduleByUserIdUseCase;
    private final UpdatePracticeScheduleUseCase updatePracticeScheduleUseCase;
    private final DeletePracticeScheduleUseCase deletePracticeScheduleUseCase;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Transactional
    @PostMapping
    public ResponseEntity<SuccessResponse<PracticeScheduleResponse>> create(@Valid @RequestBody CreatePracticeScheduleRequest createPracticeScheduleRequest, Authentication authentication) {

        String email = authentication.getName();

        UserDomain user = findUserByEmailUseCase.execute(email);

        Set<PracticeDayDomain> practiceDays = practiceDayMapper.toDomain(createPracticeScheduleRequest.practiceDays());

        PracticeScheduleDomain practiceScheduleDomain = createPracticeScheduleUseCase.execute(user, practiceDays);

        PracticeScheduleResponse practiceScheduleResponse = practiceScheduleMapper.toResponse(practiceScheduleDomain);

        SuccessResponse<PracticeScheduleResponse> response = new SuccessResponse<>(
                true,
                "Practice Schedule successfully created.",
                practiceScheduleResponse,
                LocalDateTime.now()
        );

        return ResponseEntity.ok(response);
    }


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<SuccessResponse<PracticeScheduleResponse>> getMySchedule(Authentication authentication) {

        String email = authentication.getName();

        UserDomain user = findUserByEmailUseCase.execute(email);

        PracticeScheduleDomain practiceScheduleDomain = findPracticeScheduleByUserIdUseCase.execute(user.getId());

        PracticeScheduleResponse practiceScheduleResponse = practiceScheduleMapper.toResponse(practiceScheduleDomain);

        SuccessResponse<PracticeScheduleResponse> response = new SuccessResponse<>(
                true,
                "Practice Schedule successfully found.",
                practiceScheduleResponse,
                LocalDateTime.now()
        );

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{userId}")
    public ResponseEntity<SuccessResponse<PracticeScheduleResponse>> getPracticeScheduleByUserId(@PathVariable Long userId) {

        PracticeScheduleDomain practiceScheduleDomain = findPracticeScheduleByUserIdUseCase.execute(userId);

        PracticeScheduleResponse practiceScheduleResponse = practiceScheduleMapper.toResponse(practiceScheduleDomain);

        SuccessResponse<PracticeScheduleResponse> response = new SuccessResponse<>(
                true,
                "Practice Schedule sucessfully found.",
                practiceScheduleResponse,
                LocalDateTime.now()
        );

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Transactional
    @PatchMapping
    public ResponseEntity<SuccessResponse<PracticeScheduleResponse>> update(@Valid @RequestBody UpdatePracticeScheduleRequest updatePracticeScheduleRequest, Authentication authentication) {

        String email = authentication.getName();
        UserDomain user = findUserByEmailUseCase.execute(email);


        Set<PracticeDayDomain> daysToAdd =
                updatePracticeScheduleRequest.practiceDaysToAdd()
                        .stream()
                        .map(practiceDayMapper::toDomain)
                        .collect(Collectors.toSet());

        Set<PracticeDayKey> daysToRemove =
                updatePracticeScheduleRequest.practiceDaysToRemove()
                        .stream()
                        .map(practiceDayMapper::toKey)
                        .collect(Collectors.toSet());


        PracticeScheduleDomain practiceScheduleDomain =
                updatePracticeScheduleUseCase.execute(
                    user.getId(),
                    updatePracticeScheduleRequest.active(),
                    daysToAdd,
                    daysToRemove
        );


        PracticeScheduleResponse practiceScheduleResponse = practiceScheduleMapper.toResponse(practiceScheduleDomain);

        SuccessResponse<PracticeScheduleResponse> response = new SuccessResponse<>(
                true,
                "Practice Schedule successfully updated.",
                practiceScheduleResponse,
                LocalDateTime.now()
        );

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @PatchMapping("/{userId}")
    public ResponseEntity<SuccessResponse<PracticeScheduleResponse>> update(@Valid @PathVariable Long userId, @RequestBody UpdatePracticeScheduleRequest updatePracticeScheduleRequest) {

        Set<PracticeDayDomain> daysToAdd =
                updatePracticeScheduleRequest.practiceDaysToAdd()
                        .stream()
                        .map(practiceDayMapper::toDomain)
                        .collect(Collectors.toSet());

        Set<PracticeDayKey> daysToRemove =
                updatePracticeScheduleRequest.practiceDaysToRemove()
                        .stream()
                        .map(practiceDayMapper::toKey)
                        .collect(Collectors.toSet());

        PracticeScheduleDomain practiceScheduleDomain =
                updatePracticeScheduleUseCase.execute(
                        userId,
                        updatePracticeScheduleRequest.active(),
                        daysToAdd,
                        daysToRemove
                );

        PracticeScheduleResponse practiceScheduleResponse =
                practiceScheduleMapper.toResponse(practiceScheduleDomain);


        SuccessResponse response = new SuccessResponse<>(
                true,
                "Practice Schedule successfully updated.",
                practiceScheduleResponse,
                LocalDateTime.now()
        );

        return ResponseEntity.ok(response);

    }


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Transactional
    @DeleteMapping
    public ResponseEntity<SuccessResponse> delete(Authentication authentication) {

        String email = authentication.getName();
        UserDomain user = findUserByEmailUseCase.execute(email);

        deletePracticeScheduleUseCase.execute(user.getId());

        SuccessResponse response = new SuccessResponse<>(
                true,
                "Practice Schedule successfully deleted.",
                null,
                LocalDateTime.now()
        );

        return ResponseEntity.ok(response);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @DeleteMapping("/{userId}")
    public ResponseEntity<SuccessResponse> delete(@PathVariable Long userId) {

        deletePracticeScheduleUseCase.execute(userId);

        SuccessResponse response = new SuccessResponse<>(
                true,
                "Practice Schedule successfully deleted.",
                null,
                LocalDateTime.now()
        );

        return ResponseEntity.ok(response);
    }

}
