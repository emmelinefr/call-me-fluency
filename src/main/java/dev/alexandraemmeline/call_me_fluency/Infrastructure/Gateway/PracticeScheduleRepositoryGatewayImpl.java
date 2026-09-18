package dev.alexandraemmeline.call_me_fluency.Infrastructure.Gateway;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeScheduleDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.PracticeScheduleRepositoryGateway;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Mappers.PracticeScheduleMapper;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.PracticeSchedule.PracticeScheduleEntity;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.PracticeSchedule.PracticeScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PracticeScheduleRepositoryGatewayImpl implements PracticeScheduleRepositoryGateway {

    private final PracticeScheduleRepository practiceScheduleRepository;
    private final PracticeScheduleMapper practiceScheduleMapper;

    @Override
    public PracticeScheduleDomain save(PracticeScheduleDomain practiceScheduleDomain) {

        //salva
        PracticeScheduleEntity practiceScheduleSaved = practiceScheduleRepository
                .save(practiceScheduleMapper.toEntity(practiceScheduleDomain));

        //retorna pro gateway em domain
        return practiceScheduleMapper.toDomain(practiceScheduleSaved);
    }
}
