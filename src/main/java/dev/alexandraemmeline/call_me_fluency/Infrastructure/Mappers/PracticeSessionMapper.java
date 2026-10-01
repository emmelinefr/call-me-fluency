package dev.alexandraemmeline.call_me_fluency.Infrastructure.Mappers;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeScheduleDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeSessionDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Domains.UserDomain;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.PracticeSchedule.PracticeScheduleEntity;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.PracticeSession.PracticeSessionEntity;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.User.UserEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {
                UserMapper.class,
                PracticeScheduleMapper.class
        }
)
public interface PracticeSessionMapper {

    // entity -> domain
    default PracticeSessionDomain toDomain(
            PracticeSessionEntity practiceSessionEntity
    ) {

        return PracticeSessionDomain.reconstitute(
                practiceSessionEntity.getId(),
                toDomain(practiceSessionEntity.getUser()),
                practiceSessionEntity.getScheduledAt(),
                practiceSessionEntity.getStartedAt(),
                practiceSessionEntity.getEndedAt(),
                practiceSessionEntity.getStatus(),
                toDomain(practiceSessionEntity.getPracticeSchedule())
        );
    }

    // domain -> entity
    PracticeSessionEntity toEntity(
            PracticeSessionDomain practiceSessionDomain
    );

    // auxiliaries
    UserDomain toDomain(UserEntity userEntity);

    PracticeScheduleDomain toDomain(
            PracticeScheduleEntity practiceScheduleEntity
    );
}