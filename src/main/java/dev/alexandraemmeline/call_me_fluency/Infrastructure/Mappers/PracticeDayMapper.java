package dev.alexandraemmeline.call_me_fluency.Infrastructure.Mappers;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeDayDomain;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.PracticeSchedule.CreatePracticeDayRequest;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.PracticeSchedule.PracticeDayEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface PracticeDayMapper {

    //create request -> domain
    PracticeDayDomain toDomain(
            CreatePracticeDayRequest createPracticeDayRequest
    );

    //set<createpracticedayrequest> -> set<practicedaydomain>
    Set<PracticeDayDomain> toDomain(
            Set<CreatePracticeDayRequest> createPracticeDayRequestSet
    );

    //domain -> entity
    PracticeDayEntity toEntity(
            PracticeDayDomain practiceDayDomain
    );

    //practice day -> domain
    PracticeDayDomain toDomain(
            PracticeDayEntity practiceDayEntity
    );

}
