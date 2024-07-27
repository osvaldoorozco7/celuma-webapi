package com.celuma.webapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.celuma.webapi.domain.repository.UserDTORepository;
import com.celuma.webapi.web.filters.JwtRequestFilter;

@Configuration
public class WebSecurityConfig {

    @Autowired
    private final UserDTORepository userRepository;
    @Autowired
    private final JwtRequestFilter jwtRequestFilter;

    public WebSecurityConfig(UserDTORepository userRepository, JwtRequestFilter jwtRequestFilter) {
        this.userRepository = userRepository;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(requests -> requests
                    .anyRequest()
                    .permitAll());
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService () {
        return new CustomUserDetailsService(userRepository);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}