package com.thrddqno.todolist_api.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.thrddqno.todolist_api.domain.Roles;
import com.thrddqno.todolist_api.domain.User;
import com.thrddqno.todolist_api.domain.dto.AuthenticationResponse;
import com.thrddqno.todolist_api.domain.dto.LoginRequest;
import com.thrddqno.todolist_api.domain.dto.RegisterRequest;
import com.thrddqno.todolist_api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final UserRepository userRepository;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	
	public AuthenticationResponse register(RegisterRequest registerRequest) throws Exception {
		if(userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
			throw new Exception("Email is already registered");
		}
		
		var user = User.builder()
				.name(registerRequest.getName())
				.email(registerRequest.getEmail())
				.password(passwordEncoder.encode(registerRequest.getPassword()))
				.role(Roles.USER)
				.build();
		System.out.println(user);
		userRepository.save(user);
		var token = jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(token).build();
	}
	
	public AuthenticationResponse login(LoginRequest loginRequest) throws Exception {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new Exception("Invalid credentials"));
		var token = jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(token).build();
		
	}
}
