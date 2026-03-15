package com.secureauth.lab.configurations;

import java.security.Key;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.secureauth.lab.entity.Role;
import com.secureauth.lab.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	@Value("${jwt.secret}")
	private String secret;

	private Key getSigningKey() {
		return Keys.hmacShaKeyFor(secret.getBytes());
	}

	// without role
	public String generateToken(String username) {
		return Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
	}

	// with role
	public String generateToken(User user) {

		List<String> roles = user.getRoles().stream().map(Role::getRoleName).toList();

		return Jwts.builder().setSubject(user.getUsername()).claim("roles", roles).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
	}

	// extract the roles
	public List<String> extractRoles(String token) {

		Claims claims = getClaims(token);

		return claims.get("roles", List.class);
	}

	public void validateToken(String token) {
		Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
	}

	public String extractUsername(String token) {
		return getClaims(token).getSubject();
	}

	private Claims getClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
	}
}
