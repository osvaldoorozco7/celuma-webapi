package com.celuma.webapi.service;

import com.celuma.webapi.domain.UserDTO;
import com.celuma.webapi.domain.repository.UserDTORepository;
import com.celuma.webapi.domain.request_models.UserLoginRequest;
import com.celuma.webapi.domain.response_models.UserLoginResponse;
import com.celuma.webapi.persistence.entity.User;
import com.celuma.webapi.persistence.mapper.UserMapper;
import com.celuma.webapi.security.CustomUserDetailsService;
import com.celuma.webapi.utilities.JwtUtil;

import jakarta.persistence.EntityExistsException;

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
    @Autowired
    private JwtUtil jwtUtil;
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private UserMapper mapper;

    public UserService(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public void save(UserDTO userDTO) {
        try {
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userDTO.setUserType(3); // Hardcoding user type: user
            if(userDTORepository.getUserByUsername(userDTO.getUsername()) != null) {
                throw new EntityExistsException("Username already in use.");
            }
            if(userDTORepository.getUserByEmail(userDTO.getEmail()) != null) {
                throw new EntityExistsException("Email already in use.");
            } 
            userDTORepository.save(userDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<UserDTO> getAll() {
        try {
            List<User> users = userDTORepository.getAll();
            return mapper.toUsersDTO(users);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public UserLoginResponse login(UserLoginRequest userLoginDto) {
        try {
            UserDetails userdetails = userDetailsService.loadUserByUsername(userLoginDto.getUsername());
            if (passwordEncoder.matches(userLoginDto.getPassword(), userdetails.getPassword())) {
                User user = userDTORepository.getUserByUsername(userdetails.getUsername());
                UserLoginResponse autheticatedUser = mapper.toLoginResponse(user);
                String jwt = jwtUtil.generateToken(autheticatedUser.getUsername());
                autheticatedUser.setJwt(jwt);
                return autheticatedUser;
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
