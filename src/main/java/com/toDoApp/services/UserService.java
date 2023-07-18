package com.toDoApp.services;


import com.toDoApp.dto.RegistrationRequest;
import com.toDoApp.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto login(String email, String password);
    UserDto register(RegistrationRequest newUser);
    UserDto update(UserDto userDto);
    UserDto getUser(Long id);
    List<UserDto> getAllUsers();


}
