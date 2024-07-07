package com.celuma.webapi.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.celuma.webapi.domain.UserDTO;
import com.celuma.webapi.domain.repository.UserDTORepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    private final UserDTORepository userRepository;

    public CustomUserDetailsService(UserDTORepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = userRepository.getUserByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Username not found.");
        }

        return User.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .build();
    }
}
