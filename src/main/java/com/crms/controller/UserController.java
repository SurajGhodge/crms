package com.crms.controller;

import com.crms.dto.UserDto;
import com.crms.entity.User;
import com.crms.repository.UserRepository;
import com.crms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@GetMapping
	public List<UserDto> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{id}")
	public UserDto getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}

	@PostMapping
	public UserDto addUser(@RequestBody UserDto dto) {
		return userService.addUser(dto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
	    return userRepository.findById(id)
	        .map(user -> {
	            user.setName(updatedUser.getName());
	            user.setEmail(updatedUser.getEmail());
	            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
	                user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
	            }
	            return ResponseEntity.ok(userRepository.save(user));
	        })
	        .orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}
}
