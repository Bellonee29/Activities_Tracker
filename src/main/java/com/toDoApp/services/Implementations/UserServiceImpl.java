package com.toDoApp.services.Implementations;

import com.toDoApp.dto.RegistrationRequest;
import com.toDoApp.dto.UserDto;
import com.toDoApp.exceptions.InvalidUserDetailsException;
import com.toDoApp.model.Task;
import com.toDoApp.model.User;
import com.toDoApp.repository.TaskRepository;
import com.toDoApp.repository.UserRepository;
import com.toDoApp.services.UserService;
import com.toDoApp.utils.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private  final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto login(String email, String password) {
        return Mapper.userToDTO(
                userRepository.findByEmailAndPassword(email,password)
                        .orElseThrow(()-> new EntityNotFoundException("User not found " + "Enter a valid email and password")));
    }

    @Override
    public UserDto register(RegistrationRequest newUser) {

        if(newUser.getEmail()==null|| newUser.getPassword()==null||
                newUser.getName()==null|| newUser.getGender()==null) throw new InvalidUserDetailsException("Invalid Details",
                "Email, Name, Password, and Gender cannot be empty");
        return Mapper.userToDTO( userRepository.save(
                User.builder() // create user entity
                        .name(newUser.getName())
                        .email(newUser.getEmail())
                        .gender(newUser.getGender())
                        .password(newUser.getPassword())
                        .build())); // persist user and return its DTO value
    }

    @Override
    public UserDto update(UserDto userDto) {
        User user=userRepository.findByEmail(userDto.getEmail());
        if(user==null){
            throw new EntityNotFoundException("User Email update attempted " + "You cannot mutate user email");
        }
        user.setName(userDto.getName());
        user.setGender(userDto.getGender());

        user.setUpdatedAt(new Date()); // update last modified date

        userRepository.saveAndFlush(user); // update the user in the database

        BeanUtils.copyProperties(user, userDto); // map saved user to user dto
        return userDto;
    }

    @Override
    public UserDto getUser(Long id) {
        return Mapper.userToDTO(userRepository.findById(id).orElseThrow(
                ()->new EntityNotFoundException("User not Found " + "Provide valid user id")));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(Mapper::userToDTO)
                .collect(Collectors.toList());
    }



}
