package dev.alexandraemmeline.call_me_fluency.Infrastructure.Mappers;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeDayDomain;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.PracticeSchedule.CreatePracticeDayRequest;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.PracticeSchedule.PracticeDayEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PracticeDayMapper {

    PracticeDayDomain toDomain(CreatePracticeDayRequest createPracticeDayRequest);

    PracticeDayEntity toEntity(PracticeDayDomain practiceDayDomain);

    @InheritInverseConfiguration
    PracticeDayDomain toDomain(PracticeDayEntity practiceDayEntity);

}
