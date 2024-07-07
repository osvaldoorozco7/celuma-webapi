package com.celuma.webapi.domain.service;

import com.celuma.webapi.domain.UserDTO;
import com.celuma.webapi.domain.repository.UserDTORepository;
import com.celuma.webapi.domain.request_models.UserLoginRequest;
import com.celuma.webapi.security.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDTORepository userDTORepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private CustomUserDetailsService userDetailsService;


    public UserService(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

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
        return userDTORepository.getAll();
    }

    public UserDTO login(UserLoginRequest userLoginDto) {
        try {
            UserDetails userdetails = userDetailsService.loadUserByUsername(userLoginDto.getUsername());
            if (passwordEncoder.matches(userLoginDto.getPassword(), userdetails.getPassword())) {
                return userDTORepository.getUserByUsername(userdetails.getUsername());
            } else {
                throw new UsernameNotFoundException("Incorrect password.");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
