package com.thrddqno.todolist_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thrddqno.todolist_api.domain.dto.AuthenticationResponse;
import com.thrddqno.todolist_api.domain.dto.LoginRequest;
import com.thrddqno.todolist_api.domain.dto.RegisterRequest;
import com.thrddqno.todolist_api.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	
	public final AuthenticationService authenticationService;
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) throws Exception{
		return ResponseEntity.ok(authenticationService.register(request));
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request) throws Exception{
		return ResponseEntity.ok(authenticationService.login(request));
	}
}
