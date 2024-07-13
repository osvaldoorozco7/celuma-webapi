package com.celuma.webapi.persistence.mapper;

import com.celuma.webapi.domain.UserDTO;
import com.celuma.webapi.domain.response_models.UserLoginResponse;
import com.celuma.webapi.persistence.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "userType", target = "userType"),
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "email", target = "email"),
    })
    UserDTO toUserDTO(User user);
    UserLoginResponse toLoginResponse(User user);
    List<UserDTO> toUsersDTO(List<User> users);

    @InheritInverseConfiguration
    User toUser(UserDTO userDTO);
}
