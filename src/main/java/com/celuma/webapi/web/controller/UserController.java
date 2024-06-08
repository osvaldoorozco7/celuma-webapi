package com.celuma.webapi.web.controller;

import com.celuma.webapi.domain.UserDTO;
import com.celuma.webapi.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500/")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<UserDTO> getAll() {
        return userService.getAll();
    }

    @PostMapping("/save")
    public UserDTO save(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO.getFirstName());
        System.out.println(userDTO.getLastName());
        System.out.println(userDTO.getEmail());
        System.out.println(userDTO.getUserType());
        return userService.save(userDTO);
    }
}
