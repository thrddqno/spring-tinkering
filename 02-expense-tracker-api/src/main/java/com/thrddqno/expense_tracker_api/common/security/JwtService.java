package com.thrddqno.expense_tracker_api.common.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.thrddqno.expense_tracker_api.user.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	@Value("${JWT_SECRET_KEY}")
	private String SECRET_KEY;
	
	public String extractEmail(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	public Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
	}
	
	private Key getSigningKey() {
		byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyByte);
	}
	
	public String generateToken(Map<String, Object> claims, User user) {
		return Jwts.builder()
				.addClaims(claims)
				.setSubject(user.getEmail())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 64 * 24)) // 24 hours
				.signWith(getSigningKey(), SignatureAlgorithm.ES256)
				.compact();
	}
	
	public String generateToken(User user) {
		return generateToken(new HashMap<>(), user);
	}
	
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String email = extractEmail(token);
		return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		return extractClaim(token, Claims::getExpiration).before(new Date());
	}
	
}

