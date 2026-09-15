package dev.alexandraemmeline.call_me_fluency.Infrastructure.Mappers;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.UserDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Enums.RoleName;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.User.LoginRequest;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.User.RegisterUserRequest;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.User.UserResponse;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.User.RoleEntity;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.User.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "passwordHash", source = "password")
    UserDomain toDomain(RegisterUserRequest registerUserRequest);

    @Mapping(target = "passwordHash", source = "password")
    UserDomain toDomain(LoginRequest loginRequest);

    UserDomain toDomain(UserEntity userEntity);

    UserEntity toEntity(UserDomain userDomain);

    UserResponse toResponse(UserDomain userDomain);

    List<UserResponse> toUserResponseList(List<UserEntity> userEntityList);

    default RoleName mapRoleEntityToRoleName(RoleEntity roleEntity) {
        return roleEntity.getName();
    }

}
