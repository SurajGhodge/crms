package com.crms.mapper;

import com.crms.dto.UserDto;
import com.crms.entity.User;

public class UserMapper {

	public static UserDto toDTO(User user) {
		if (user == null)
			return null;
		return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getRole());
	}

	public static User toEntity(UserDto dto) {
		if (dto == null)
			return null;
		User user = new User();
		user.setId(dto.getId());
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setRole(dto.getRole());
		return user;
	}
}