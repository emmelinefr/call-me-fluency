package dev.alexandraemmeline.call_me_fluency.Infrastructure.Controllers;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeDayDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeScheduleDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Domains.UserDomain;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSchedule.CreatePracticeScheduleUseCase;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.User.FindUserByEmailUseCase;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.PracticeSchedule.CreatePracticeScheduleRequest;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.PracticeSchedule.PracticeScheduleResponse;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Handler.SuccessResponse;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Mappers.PracticeScheduleMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("v1/schedule")
@RequiredArgsConstructor
public class PracticeScheduleController {

    private final PracticeScheduleMapper practiceScheduleMapper;
    private final CreatePracticeScheduleUseCase createPracticeScheduleUseCase;
    private final FindUserByEmailUseCase findUserByEmailUseCase;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping
    public ResponseEntity<SuccessResponse<PracticeScheduleResponse>> create(@Valid @RequestBody CreatePracticeScheduleRequest createPracticeScheduleRequest, Authentication authentication) {

        String email = authentication.getName();

        UserDomain user = findUserByEmailUseCase.execute(email);
        Set<PracticeDayDomain> practiceDays = practiceScheduleMapper.toPracticeDaysDomain(createPracticeScheduleRequest.practiceDays());

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

}
