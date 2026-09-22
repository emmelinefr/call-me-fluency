package dev.alexandraemmeline.call_me_fluency.Infrastructure.Mappers;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeDayDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeScheduleDomain;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.PracticeSchedule.CreatePracticeDayRequest;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.PracticeSchedule.CreatePracticeScheduleRequest;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.PracticeSchedule.PracticeScheduleResponse;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.PracticeSchedule.UpdatePracticeScheduleRequest;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.PracticeSchedule.PracticeDayEntity;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.PracticeSchedule.PracticeScheduleEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Set;

@Mapper(
        componentModel = "spring",
        uses = {
                PracticeDayMapper.class,
                UserMapper.class
        }
)
public interface PracticeScheduleMapper {

    PracticeScheduleDomain toDomain(CreatePracticeScheduleRequest createPracticeScheduleRequest);

    PracticeScheduleDomain toDomain(UpdatePracticeScheduleRequest updatePracticeScheduleRequest);


    PracticeScheduleResponse toResponse(PracticeScheduleDomain practiceScheduleDomain);


    @Mapping(source = "userDomain", target = "user")
    PracticeScheduleEntity toEntity(PracticeScheduleDomain practiceScheduleDomain);

    @Mapping(source = "user", target = "userDomain")
    @Mapping(target = "practiceDays", ignore = true)
    PracticeScheduleDomain toDomain(PracticeScheduleEntity practiceScheduleEntity);

    @AfterMapping
    default void mapPracticeDays(PracticeScheduleEntity entity, @MappingTarget PracticeScheduleDomain domain) {
        entity.getPracticeDays()
                .forEach(dayEntity -> domain.addPracticeDay(toDomain(dayEntity)));
    }
    PracticeDayDomain toDomain(PracticeDayEntity practiceDayEntity);


    Set<PracticeDayDomain> toPracticeDaysDomain(Set<CreatePracticeDayRequest> practiceDayRequestSet);
}
