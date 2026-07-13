package dev.alexandraemmeline.call_me_fluency.Core.UseCases;

public interface ChangePasswordUseCase {

    void execute(String email, String currentPassword, String newPassword);

}
