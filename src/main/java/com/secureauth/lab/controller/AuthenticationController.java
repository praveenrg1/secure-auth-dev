package com.secureauth.lab.controller;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secureauth.lab.entity.User;
import com.secureauth.lab.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	private UserRepository userRepo;
	private PasswordEncoder encoder;

	public AuthenticationController(UserRepository userRepo, PasswordEncoder encoder) {
		super();
		this.userRepo = userRepo;
		this.encoder = encoder;
	}

	@GetMapping("/")
	public String healthCheck() {
		return "I am fine..!";
	}

	@PostMapping("/register")
	public String register(@RequestBody User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		userRepo.save(user);
		return "User registered";
	}

	@PostMapping("/login")
	public String login(@RequestBody User user) {

//		List<User> users = userRepo.login(user.getUsername(), user.getPassword());
		List<User> users = userRepo.login(user.getUsername());
		if (users.isEmpty())
			return "Invalid credentials";

		User sgUser = users.get(0);
		System.out.println(sgUser.getPassword());
		System.out.println(encoder.encode(user.getPassword()));
		if (sgUser == null || !encoder.matches(user.getPassword(), sgUser.getPassword()))
			return "Invalid credentials";
		return "Login success";
	}
}
