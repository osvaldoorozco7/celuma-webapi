package com.celuma.webapi.domain.repository;

import com.celuma.webapi.domain.UserDTO;

import java.util.List;

public interface UserDTORepository {
    UserDTO save(UserDTO userDTO);
    List<UserDTO> getAll();
    boolean getUserByEmail(String email, String password);
}
