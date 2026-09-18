package dev.alexandraemmeline.call_me_fluency.Infrastructure.Mappers;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeScheduleDomain;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.PracticeSchedule.CreatePracticeScheduleRequest;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.PracticeSchedule.PracticeScheduleResponse;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.PracticeSchedule.UpdatePracticeScheduleRequest;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.PracticeSchedule.PracticeScheduleEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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

    @InheritInverseConfiguration
    PracticeScheduleDomain toDomain(PracticeScheduleEntity practiceScheduleEntity);

}
