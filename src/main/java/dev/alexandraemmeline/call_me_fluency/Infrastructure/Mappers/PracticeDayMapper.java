package dev.alexandraemmeline.call_me_fluency.Infrastructure.Mappers;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeDayDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeDayKey;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.PracticeSchedule.CreatePracticeDayRequest;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.PracticeSchedule.RemovePracticeDayRequest;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.PracticeSchedule.PracticeDayEntity;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface PracticeDayMapper {

    // create request -> domain
    PracticeDayDomain toDomain(
            CreatePracticeDayRequest createPracticeDayRequest
    );

    //remove request -> practice day key
    PracticeDayKey toKey(
            RemovePracticeDayRequest removePracticeDayRequest
    );

    //Set<CreatePracticeDayRequest> -> Set<PracticeDayDomain>
    Set<PracticeDayDomain> toDomain(
            Set<CreatePracticeDayRequest> requests
    );


    //domain -> entity
    PracticeDayEntity toEntity(
            PracticeDayDomain practiceDayDomain
    );

    //entity -> domain
    PracticeDayDomain toDomain(
            PracticeDayEntity practiceDayEntity
    );
}
