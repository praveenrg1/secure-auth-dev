package com.secureauth.lab.configurations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.startsWith("/auth/") || path.startsWith("/h2") || path.startsWith("/role")|| path.startsWith("/user/register")|| path.startsWith("/user/login")) {
			filterChain.doFilter(request, response);
			return;
		}
		String authHeader = request.getHeader("Authorization");

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		String token = authHeader.substring(7);

		try {

			jwtService.validateToken(token);

			String username = jwtService.extractUsername(token);

			List<String> roles = jwtService.extractRoles(token);

			List<SimpleGrantedAuthority> authorities = roles.stream()
					.map(role -> new SimpleGrantedAuthority("ROLE_" + role)).toList();

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null,
					authorities);

			SecurityContextHolder.getContext().setAuthentication(authentication);

		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		filterChain.doFilter(request, response);

	}
}
