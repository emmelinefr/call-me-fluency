package dev.alexandraemmeline.call_me_fluency.Core.UseCases;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.UserDomain;
import java.util.List;

public interface ListUsersUseCase {

    List<UserDomain> execute();

}
