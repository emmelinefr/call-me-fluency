package dev.alexandraemmeline.call_me_fluency.Infrastructure.Mappers;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.UserDomain;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.CreateUserRequest;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.UserResponse;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDomain toDomain(CreateUserRequest createUserRequest);

    UserDomain toDomain(UserEntity userEntity);

    UserEntity toEntity(UserDomain userDomain);

    UserResponse toResponse(UserDomain userDomain);

    List<UserResponse> toUserResponseList(List<UserEntity> userEntityList);

}
