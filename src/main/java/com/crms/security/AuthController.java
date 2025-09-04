package com.crms.security;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crms.entity.User;
import com.crms.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody Map<String, String> body) {
		User u = new User();
		u.setName(body.get("name"));
		u.setEmail(body.get("email"));
		u.setPassword(passwordEncoder.encode(body.get("password")));
		u.setRole(body.getOrDefault("role", "SALES"));
		userRepo.save(u);
		return ResponseEntity.ok("User created");
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> req) {
		Authentication auth = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(req.get("email"), req.get("password")));
		String token = jwtUtil.generateToken(((UserDetails) auth.getPrincipal()).getUsername());
		return ResponseEntity.ok(Map.of("token", token));
	}
}
