package com.crms.security;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
		u.setRole(body.get("role"));
		userRepo.save(u);
		return ResponseEntity.ok("User created");
	}
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> req) {
	    Authentication auth = authManager.authenticate(
	        new UsernamePasswordAuthenticationToken(
	            req.get("email"),
	            req.get("password")
	        )
	    );

	    String username = ((UserDetails) auth.getPrincipal()).getUsername();
	    String token = jwtUtil.generateToken(username);

	    // ✅ Find full user entity
	    User user = userRepo.findByEmail(username)
	        .orElseThrow(() -> new RuntimeException("User not found"));

	    String role = auth.getAuthorities().stream()
	        .findFirst()
	        .map(GrantedAuthority::getAuthority)
	        .orElse("ROLE_USER");

	    // ✅ Return id, name, email, role, and token
	    return ResponseEntity.ok(Map.of(
	        "token", token,
	        "role", role,
	        "id", user.getId(),       // <--- ADDED
	        "name", user.getName(),
	        "email", user.getEmail()
	    ));
	}

}
