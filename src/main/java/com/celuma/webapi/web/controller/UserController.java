package com.celuma.webapi.web.controller;

import com.celuma.webapi.domain.UserDTO;
import com.celuma.webapi.domain.request_models.UserLoginRequest;
import com.celuma.webapi.domain.request_models.UserRegistrationRequest;
import com.celuma.webapi.domain.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500/")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController() {
    }

    @GetMapping("/all")
    public List<UserDTO> getAll() {
        return userService.getAll();
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserLoginRequest request) {
        try{
            return new ResponseEntity<UserDTO>(userService.login(request), HttpStatus.OK);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity("There was a problem processing your request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegistrationRequest request) {
        
        try {
            UserDTO userDTO = new UserDTO();
            userDTO.setFirstName(request.getFirstName());
            userDTO.setLastName(request.getLastName());
            userDTO.setUsername(request.getUsername());
            userDTO.setEmail(request.getEmail());
            userDTO.setPassword(request.getPassword());
            
            userService.save(userDTO);
            return new ResponseEntity<>("User successfully registered.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("There was a problem during the registration process: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
