package dev.alexandraemmeline.call_me_fluency.Infrastructure.Gateway;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeSessionDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.PracticeSessionRepositoryGateway;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Mappers.PracticeSessionMapper;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.PracticeSession.PracticeSessionEntity;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.PracticeSession.PracticeSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class PracticeSessionRepositoryGatewayImpl implements PracticeSessionRepositoryGateway {

    private final PracticeSessionRepository practiceSessionRepository;
    private final PracticeSessionMapper practiceSessionMapper;


    @Override
    public Optional<PracticeSessionDomain> findById(Long practiceSessionId) {

        return practiceSessionRepository
                .findById(practiceSessionId)
                .map(practiceSessionMapper::toDomain);

    }

    @Override
    public PracticeSessionDomain save(PracticeSessionDomain practiceSessionDomain) {

        PracticeSessionEntity practiceSessionEntity = practiceSessionMapper.toEntity(practiceSessionDomain);

        PracticeSessionEntity practiceSessionSaved = practiceSessionRepository.save(practiceSessionEntity);

        return practiceSessionMapper.toDomain(practiceSessionSaved);

    }
}
