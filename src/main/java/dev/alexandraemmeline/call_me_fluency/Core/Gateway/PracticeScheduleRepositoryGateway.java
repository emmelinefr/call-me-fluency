package dev.alexandraemmeline.call_me_fluency.Core.Gateway;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeScheduleDomain;

public interface PracticeScheduleRepositoryGateway {

    PracticeScheduleDomain save(PracticeScheduleDomain practiceScheduleDomain);

}
