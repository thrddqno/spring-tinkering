package com.thrddqno.todolist_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.thrddqno.todolist_api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
	
	private final UserRepository userRepository;
	
	@Bean
	UserDetailsService userDetailsService() {
		return username -> userRepository.findByEmail(username).orElseThrow();
	}
	
	@Bean
	AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder);
		return authProvider;
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
