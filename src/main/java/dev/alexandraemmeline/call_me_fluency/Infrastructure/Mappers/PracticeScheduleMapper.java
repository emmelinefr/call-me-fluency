package dev.alexandraemmeline.call_me_fluency.Infrastructure.Mappers;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeDayDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeScheduleDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Domains.UserDomain;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.PracticeSchedule.CreatePracticeScheduleRequest;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.PracticeSchedule.PracticeScheduleResponse;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.PracticeSchedule.UpdatePracticeScheduleRequest;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.PracticeSchedule.PracticeDayEntity;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.PracticeSchedule.PracticeScheduleEntity;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.User.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring",
        uses = {
                PracticeDayMapper.class,
                UserMapper.class
        }
)
public interface PracticeScheduleMapper {

    //create request -> domain
    PracticeScheduleDomain toDomain(
            CreatePracticeScheduleRequest createPracticeScheduleRequest
    );

    //update request -> domain
    PracticeScheduleDomain toDomain(
            UpdatePracticeScheduleRequest updatePracticeScheduleRequest
    );

    //domain -> Response
    PracticeScheduleResponse toResponse(
            PracticeScheduleDomain practiceScheduleDomain
    );

    //domain -> entity
    @Mapping(source = "userDomain", target = "user")
    PracticeScheduleEntity toEntity(PracticeScheduleDomain practiceScheduleDomain);

    //entity -> domain
    default PracticeScheduleDomain toDomain(
            PracticeScheduleEntity practiceScheduleEntity
    ) {

        Set<PracticeDayDomain> practiceDaysDomain =
                practiceScheduleEntity.getPracticeDays()
                    .stream()
                    .map(this::toDomain)
                    .collect(Collectors.toSet());

        return PracticeScheduleDomain.reconstitute(
                practiceScheduleEntity.getId(),
                toDomain(practiceScheduleEntity.getUser()),
                practiceDaysDomain,
                practiceScheduleEntity.isActive()
        );

    }

    //auxiliaries
    PracticeDayDomain toDomain(PracticeDayEntity practiceDayEntity);

    UserDomain toDomain(UserEntity userEntity);


}
