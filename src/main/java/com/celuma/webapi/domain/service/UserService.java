package com.celuma.webapi.domain.service;

import com.celuma.webapi.domain.UserDTO;
import com.celuma.webapi.domain.repository.UserDTORepository;
import com.celuma.webapi.domain.request_models.UserLoginRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDTORepository userDTORepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(UserDTO userDTO) {

        try {
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userDTO.setUserType(3); // Hardcoding user type: user
            userDTORepository.save(userDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<UserDTO> getAll() {
        return  userDTORepository.getAll();
    }

    public boolean login(UserLoginRequest userLoginDto) {
        // Decode password
        String password = userLoginDto.getPassword();
        //return userDTORepository.getUserByEmail(userLoginDto.getEmail(), password);
        return true;
    }

    public boolean passwordCheck(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(encodedPassword, encodedPassword);
    }
}
