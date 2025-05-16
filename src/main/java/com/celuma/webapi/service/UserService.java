package com.celuma.webapi.service;

import com.celuma.webapi.domain.UserDTO;
import com.celuma.webapi.domain.repository.UserDTORepository;
import com.celuma.webapi.domain.request_models.UserLoginRequest;
import com.celuma.webapi.domain.request_models.UserUpdatePasswordRequest;
import com.celuma.webapi.domain.request_models.UserUpdateRequest;
import com.celuma.webapi.domain.response_models.UserLoginResponse;
import com.celuma.webapi.middleware.GlobalExceptionHandler;
import com.celuma.webapi.persistence.entity.User;
import com.celuma.webapi.persistence.mapper.UserMapper;
import com.celuma.webapi.security.CustomUserDetailsService;
import com.celuma.webapi.utilities.JwtUtil;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public UserService(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public List<UserDTO> getAll() {
        try {
            List<User> users = userDTORepository.getAll();
            return mapper.toUsersDTO(users);
        } catch (Exception e) {
            logger.warn("Error getting all users.", e.getMessage(), e);
            throw e;
        }
    }

    public UserDTO getUser(int userId) {
        try {
            User user = userDTORepository.getById(userId);
            return  mapper.toUserDTO(user);
        } catch (Exception e) {
            logger.warn("Error getting user " + userId, e.getMessage(), e);
            throw e;
        }
    }

    public void save(UserDTO userDTO) {
        try {
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userDTO.setUserType(3); // Hardcoding user type: user
            if (userDTORepository.getUserByUsername(userDTO.getUsername()) != null) {
                throw new EntityExistsException("Username already in use.");
            }
            if (userDTORepository.getUserByEmail(userDTO.getEmail()) != null) {
                throw new EntityExistsException("Email already in use.");
            }
            userDTORepository.save(userDTO);
        } catch (Exception e) {
            logger.warn("Error saving user " + userDTO.getid(), e.getMessage(), e);
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
        } catch (Exception e) {
            logger.warn("Error loging in user " + userLoginDto.getUsername(), e.getMessage(), e);
            throw e;
        }
    }

    public boolean delete(int userId) {
        try {
            userDTORepository.delete(userId);
            return true;
        } catch (Exception e) {
            logger.warn("Error deleting user " + userId, e.getMessage(), e);
            return false;
        }
    };

    // Personal information update
    public boolean update(UserUpdateRequest userUpdateRequest) {
        try {
            User userToUpdate = userDTORepository.getById(userUpdateRequest.getUserId());
            userToUpdate.setFirstName(userUpdateRequest.getFirstName());
            userToUpdate.setLastName(userUpdateRequest.getLastName());
            userToUpdate.setEmail(userUpdateRequest.getEmail());
            userDTORepository.save(userToUpdate);
            return true;
        } catch (EntityNotFoundException e) {
            logger.warn("Error updating user " + userUpdateRequest.getUserId(), e.getMessage(), e);
            throw new EntityNotFoundException("User with id " + userUpdateRequest.getUserId() + " does not exist.");
        }
    }

    // Password update
    public boolean update(UserUpdatePasswordRequest passwordRequest) {
        try {
            User userToUpdate = userDTORepository.getById(passwordRequest.getUserId());
            if(passwordEncoder.matches(passwordRequest.getCurrentPassword(), userToUpdate.getPassword())) {
                userToUpdate.setPassword(passwordEncoder.encode(passwordRequest.getNewPassword()));
                userDTORepository.save(userToUpdate);
                return true;
            }
            return false;
        } catch (EntityNotFoundException e) {
            logger.warn("Error updating password for user " + passwordRequest.getUserId(), e.getMessage(), e);
            throw new EntityNotFoundException("User with id " + passwordRequest.getUserId() + " does not exist.");
        }
    }

}
