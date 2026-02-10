package com.thrddqno.expense_tracker_api.auth;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.thrddqno.expense_tracker_api.auth.dto.AuthenticationResponse;
import com.thrddqno.expense_tracker_api.auth.dto.LoginRequest;
import com.thrddqno.expense_tracker_api.auth.dto.RegisterRequest;
import com.thrddqno.expense_tracker_api.auth.exceptions.EmailAlreadyExistsException;
import com.thrddqno.expense_tracker_api.auth.exceptions.InvalidCredentialsException;
import com.thrddqno.expense_tracker_api.common.security.JwtService;
import com.thrddqno.expense_tracker_api.user.Role;
import com.thrddqno.expense_tracker_api.user.User;
import com.thrddqno.expense_tracker_api.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final UserRepository userRepository;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	
	public AuthenticationResponse register(RegisterRequest request) {
		if(userRepository.findByEmail(request.getEmail()).isPresent()) {
			throw new EmailAlreadyExistsException(
					"Email: " + request.getEmail() + " already exists.",
					"BAD_USER_INPUT",
					HttpStatus.BAD_REQUEST);
		}
		
		var user = User.builder()
				.name(request.getName())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(Role.USER)
				.build();
		
		userRepository.save(user);
		var token = jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(token).build();
	}
	
	public AuthenticationResponse login(LoginRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				request.getEmail(),
				request.getPassword()));
		var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new 
				InvalidCredentialsException("Email: " + request.getEmail() + " doesn't exist or Invalid Credentials." ,
						"BAD_USER_INPUT",
						HttpStatus.BAD_REQUEST));
		var token = jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(token).build();
	}
}
