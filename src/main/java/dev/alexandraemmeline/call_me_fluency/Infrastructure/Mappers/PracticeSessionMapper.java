package dev.alexandraemmeline.call_me_fluency.Infrastructure.Mappers;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeSessionDomain;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.PracticeSession.PracticeSessionResponse;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.PracticeSession.PracticeSessionEntity;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = "spring",
        uses = {
                UserMapper.class,
                PracticeScheduleMapper.class
        }
)
public abstract class PracticeSessionMapper {

    @Autowired
    protected UserMapper userMapper;
    @Autowired
    protected PracticeScheduleMapper practiceScheduleMapper;

    // entity -> domain
    public PracticeSessionDomain toDomain(PracticeSessionEntity practiceSessionEntity) {
        return PracticeSessionDomain.reconstitute(
                practiceSessionEntity.getId(),
                userMapper.toDomain(practiceSessionEntity.getUser()),
                practiceSessionEntity.getScheduledAt(),
                practiceSessionEntity.getStartedAt(),
                practiceSessionEntity.getEndedAt(),
                practiceSessionEntity.getStatus(),
                practiceScheduleMapper.toDomain(practiceSessionEntity.getPracticeSchedule())
        );
    }

    // domain -> entity
    public abstract PracticeSessionEntity toEntity(
            PracticeSessionDomain practiceSessionDomain
    );

    // domain -> response
    public abstract PracticeSessionResponse toResponse(
            PracticeSessionDomain practiceSessionDomain
    );
}