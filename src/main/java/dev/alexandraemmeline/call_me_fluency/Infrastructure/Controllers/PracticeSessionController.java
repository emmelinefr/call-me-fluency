package dev.alexandraemmeline.call_me_fluency.Infrastructure.Controllers;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeSessionDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Domains.UserDomain;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSession.ListPracticeSessionsUseCase;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.User.FindUserByEmailUseCase;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.PracticeSession.PracticeSessionResponse;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Handler.SuccessResponse;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Mappers.PracticeSessionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/practice-sessions")
@RequiredArgsConstructor
public class PracticeSessionController {

    private final ListPracticeSessionsUseCase listPracticeSessionsUseCase;
    private final FindUserByEmailUseCase findUserByEmailUseCase;
    private final PracticeSessionMapper practiceSessionMapper;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public ResponseEntity<SuccessResponse<Set<PracticeSessionResponse>>> findMySessions(Authentication authentication) {

        String email = authentication.getName();
        UserDomain user = findUserByEmailUseCase.execute(email);

        Set<PracticeSessionDomain> practiceSessions = listPracticeSessionsUseCase.execute(user.getId());

        Set<PracticeSessionResponse> practiceSessionResponsesSet = practiceSessions
                .stream()
                .map(practiceSessionMapper::toResponse)
                .collect(Collectors.toSet());

        SuccessResponse<Set<PracticeSessionResponse>> response = new SuccessResponse<>(
                true,
                "Practice sessions listed successfully.",
                practiceSessionResponsesSet,
                LocalDateTime.now()
        );

        return ResponseEntity.ok(response);

    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<SuccessResponse<Set<PracticeSessionResponse>>> findByUserId(@PathVariable Long userId) {

        Set<PracticeSessionDomain> practiceSessions = listPracticeSessionsUseCase.execute(userId);

        Set<PracticeSessionResponse> practiceSessionResponsesSet = practiceSessions
                .stream()
                .map(practiceSessionMapper::toResponse)
                .collect(Collectors.toSet());

        SuccessResponse<Set<PracticeSessionResponse>> response = new SuccessResponse<>(
                true,
                "Practice sessions listed successfully.",
                practiceSessionResponsesSet,
                LocalDateTime.now()
        );

        return ResponseEntity.ok(response);

    }


}
