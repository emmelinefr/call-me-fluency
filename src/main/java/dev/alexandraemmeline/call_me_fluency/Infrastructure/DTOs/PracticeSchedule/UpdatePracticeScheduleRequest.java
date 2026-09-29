package dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.PracticeSchedule;

import java.util.Set;

public record UpdatePracticeScheduleRequest(

        boolean active,

        Set<CreatePracticeDayRequest> practiceDaysToAdd,

        Set<RemovePracticeDayRequest> practiceDaysToRemove

) {

        public UpdatePracticeScheduleRequest {
                practiceDaysToAdd = practiceDaysToAdd == null ? Set.of() : practiceDaysToAdd;
                practiceDaysToRemove = practiceDaysToRemove == null ? Set.of() : practiceDaysToRemove;
        }

}
