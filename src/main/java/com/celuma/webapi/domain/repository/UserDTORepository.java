package com.celuma.webapi.domain.repository;

import com.celuma.webapi.domain.UserDTO;
import com.celuma.webapi.persistence.entity.User;

import java.util.List;

public interface UserDTORepository {
    User save(UserDTO userDTO);
    List<User> getAll();
    User getUserByUsername(String username);
}
