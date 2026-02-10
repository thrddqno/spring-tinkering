package com.thrddqno.expense_tracker_api.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.thrddqno.expense_tracker_api.common.security.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final AuthenticationProvider authenticationProvider;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
		httpSecurity.csrf(csrf -> csrf
				.disable())
		.authorizeHttpRequests(requests -> requests
				.requestMatchers(
						"/v3/api-docs/**",
						"/auth/**",
						"/swagger-ui.html",
						"/swagger-ui/**"
						)
				.permitAll().anyRequest()
				.authenticated())
		.sessionManagement(management -> management
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		return httpSecurity.build();
		}

}
