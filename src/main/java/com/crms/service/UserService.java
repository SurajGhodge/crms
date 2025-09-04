package com.crms.service;

import com.crms.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    UserDto addUser(UserDto dto);
    UserDto updateUser(Long id, UserDto dto);
    void deleteUser(Long id);
}
