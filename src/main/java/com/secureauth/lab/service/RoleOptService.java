package com.secureauth.lab.service;

import org.springframework.stereotype.Service;

import com.secureauth.lab.entity.Role;
import com.secureauth.lab.entity.User;
import com.secureauth.lab.repository.RoleRepository;
import com.secureauth.lab.repository.UserRepository;

@Service
public class RoleOptService {

	private RoleRepository roleRepository;
	private UserRepository userRepository;

	public RoleOptService(RoleRepository roleRepository, UserRepository userRepository) {
		super();
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;

	}

	public Role createRole(String roleName) {

		Role role = new Role();
		role.setRoleName(roleName);

		return roleRepository.save(role);
	}

	public String assignRole(Long userId, Long roleId) {

		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));

		user.getRoles().add(role);

		userRepository.save(user);

		return "Role assigned successfully";
	}
}