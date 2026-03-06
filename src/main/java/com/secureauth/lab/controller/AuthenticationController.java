package com.secureauth.lab.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secureauth.lab.configurations.JwtService;
import com.secureauth.lab.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class AuthenticationController {
	private UserRepository userRepo;
	private PasswordEncoder encoder;
	private JwtService jwtService;

	public AuthenticationController(UserRepository userRepo, PasswordEncoder encoder, JwtService jwtService) {
		super();
		this.userRepo = userRepo;
		this.encoder = encoder;
		this.jwtService = jwtService;
	}

	@PostMapping("/jwt-test")
	public String testJwt(@RequestHeader String authorization) {
		return "jwt validated";
	}
}
