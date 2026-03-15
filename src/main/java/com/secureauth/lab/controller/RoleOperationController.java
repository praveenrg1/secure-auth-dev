package com.secureauth.lab.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.secureauth.lab.entity.Role;
import com.secureauth.lab.service.RoleOptService;

@RestController
@RequestMapping("/role")
public class RoleOperationController {
	private RoleOptService roleService;

	public RoleOperationController(RoleOptService roleService) {
		super();
		this.roleService = roleService;
	}

	@PostMapping("/save")
	public Role createRole(@RequestBody Role role) {

		return roleService.createRole(role.getRoleName());
	}

	@PostMapping("/assign-role")
	public String assignRole(@RequestParam Long userId, @RequestParam Long roleId) {

		return roleService.assignRole(userId, roleId);
	}
}
