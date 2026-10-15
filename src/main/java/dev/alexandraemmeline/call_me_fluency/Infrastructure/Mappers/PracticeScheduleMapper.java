package dev.alexandraemmeline.call_me_fluency.Infrastructure.Mappers;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeDayDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeScheduleDomain;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.PracticeSchedule.PracticeScheduleResponse;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.PracticeSchedule.PracticeScheduleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring",
        uses = {
                PracticeDayMapper.class,
                UserMapper.class
        }
)
public abstract class PracticeScheduleMapper {

    @Autowired
    protected PracticeDayMapper practiceDayMapper;
    @Autowired
    protected UserMapper userMapper;

    //domain -> Response
    public abstract PracticeScheduleResponse toResponse(
            PracticeScheduleDomain practiceScheduleDomain
    );

    //domain -> entity
    @Mapping(source = "userDomain", target = "user")
    public abstract PracticeScheduleEntity toEntity(
            PracticeScheduleDomain practiceScheduleDomain
    );

    //entity -> domain
    public PracticeScheduleDomain toDomain(PracticeScheduleEntity practiceScheduleEntity) {

        Set<PracticeDayDomain> practiceDaysDomain =
                practiceScheduleEntity.getPracticeDays()
                        .stream()
                        .map(practiceDayMapper::toDomain)
                        .collect(Collectors.toSet());

        return PracticeScheduleDomain.reconstitute(
                practiceScheduleEntity.getId(),
                userMapper.toDomain(practiceScheduleEntity.getUser()),
                practiceDaysDomain,
                practiceScheduleEntity.isActive()
        );
    }
}