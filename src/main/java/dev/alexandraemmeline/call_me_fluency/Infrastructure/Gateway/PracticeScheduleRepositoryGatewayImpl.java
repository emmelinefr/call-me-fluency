package dev.alexandraemmeline.call_me_fluency.Infrastructure.Gateway;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeScheduleDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.PracticeScheduleRepositoryGateway;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Mappers.PracticeScheduleMapper;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Mappers.UserMapper;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.PracticeSchedule.PracticeScheduleEntity;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.PracticeSchedule.PracticeScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PracticeScheduleRepositoryGatewayImpl implements PracticeScheduleRepositoryGateway {

    private final PracticeScheduleRepository practiceScheduleRepository;
    private final PracticeScheduleMapper practiceScheduleMapper;
    private final UserMapper userMapper;

    @Override
    public PracticeScheduleDomain save(PracticeScheduleDomain practiceScheduleDomain) {

        PracticeScheduleEntity practiceScheduleSaved = practiceScheduleRepository
                .save(practiceScheduleMapper.toEntity(practiceScheduleDomain));

        return practiceScheduleMapper.toDomain(practiceScheduleSaved);

    }

    @Override
    public boolean existsByUserId(Long id) {
        return practiceScheduleRepository.existsByUserId(id);
    }

    @Override
    public Optional<PracticeScheduleDomain> findByUserId(Long id) {

        return practiceScheduleRepository
                .findByUserId(id)
                .map(practiceScheduleMapper::toDomain);
    }
}
