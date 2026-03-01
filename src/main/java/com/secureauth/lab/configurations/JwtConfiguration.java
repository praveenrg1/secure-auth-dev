package com.secureauth.lab.configurations;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtConfiguration {
	@Value("${jwt.secret}")
	private String secret;

	private Key getKey() {
		return Keys.hmacShaKeyFor(secret.getBytes());
	}

	   public String generateToken(String username){

	        return Jwts.builder()
	                .setSubject(username)
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis()+3600000))
	                .signWith(getKey(), SignatureAlgorithm.HS256)
	                .compact();
	    }

	    public String validate(String token){

	        return Jwts.parserBuilder()
	                .setSigningKey(getKey())
	                .build()
	                .parseClaimsJws(token)
	                .getBody()
	                .getSubject();
	    }
}
