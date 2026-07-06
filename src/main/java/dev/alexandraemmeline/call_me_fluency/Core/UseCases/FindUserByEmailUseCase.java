package dev.alexandraemmeline.call_me_fluency.Core.UseCases;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.UserDomain;

public interface FindUserByEmailUseCase {

    UserDomain execute(String email);

}
