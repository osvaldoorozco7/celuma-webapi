package com.celuma.webapi.persistence.mapper;

import com.celuma.webapi.domain.UserDTO;
import com.celuma.webapi.persistence.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(source = "idUser", target = "userId"),
            @Mapping(source = "typeUser", target = "userType"),
            @Mapping(source = "nameFirst", target = "firstName"),
            @Mapping(source = "nameLast", target = "lastName"),
            @Mapping(source = "nameuser", target = "username"),
            @Mapping(source = "mail", target = "email"),
    })
    UserDTO toUserDTO(User user);

    List<UserDTO> toUsersDTO(List<User> users);

    @InheritInverseConfiguration
    User toUser(UserDTO userDTO);
}
