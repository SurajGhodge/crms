package com.crms.service.impl;

import com.crms.dto.UserDto;
import com.crms.entity.User;
import com.crms.mapper.UserMapper;
import com.crms.repository.UserRepository;
import com.crms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<UserDto> getAllUsers() {
		return userRepository.findAll().stream().map(UserMapper::toDTO).toList();
	}

	@Override
	public UserDto getUserById(Long id) {
		return userRepository.findById(id).map(UserMapper::toDTO).orElse(null);
	}

	@Override
	public UserDto addUser(UserDto dto) {
		User user = UserMapper.toEntity(dto);
		User saved = userRepository.save(user);
		return UserMapper.toDTO(saved);
	}

	@Override
	public UserDto updateUser(Long id, UserDto dto) {
		return userRepository.findById(id).map(existing -> {
			existing.setName(dto.getName());
			existing.setEmail(dto.getEmail());
			existing.setRole(dto.getRole());
			User updated = userRepository.save(existing);
			return UserMapper.toDTO(updated);
		}).orElse(null);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
}
