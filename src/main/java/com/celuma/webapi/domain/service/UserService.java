package com.celuma.webapi.domain.service;

import com.celuma.webapi.domain.UserDTO;
import com.celuma.webapi.domain.repository.UserDTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDTORepository userDTORepository;


    public UserDTO save(UserDTO userDTO) {
         return userDTORepository.save(userDTO);
    }

    public List<UserDTO> getAll() {
        return  userDTORepository.getAll();
    }
}
