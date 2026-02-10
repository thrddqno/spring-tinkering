package com.thrddqno.expense_tracker_api.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thrddqno.expense_tracker_api.auth.dto.AuthenticationResponse;
import com.thrddqno.expense_tracker_api.auth.dto.LoginRequest;
import com.thrddqno.expense_tracker_api.auth.dto.RegisterRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	
	private final AuthenticationService authenticationService;
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
		return ResponseEntity.ok(authenticationService.register(request));
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request){
		return ResponseEntity.ok(authenticationService.login(request));
	}
}
