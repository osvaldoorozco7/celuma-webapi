package com.celuma.webapi.domain.repository;

import com.celuma.webapi.domain.UserDTO;
import com.celuma.webapi.persistence.entity.User;

import java.util.List;

public interface UserDTORepository {
    User save(UserDTO userDTO);
    void save(User user);
    List<User> getAll();
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    User getById(int id);
}
