package com.toDoApp.contollers;

import com.toDoApp.dto.LoginRequest;
import com.toDoApp.dto.RegistrationRequest;
import com.toDoApp.dto.UserDto;
import com.toDoApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/new_user")
    public ResponseEntity<UserDto> register(@RequestBody RegistrationRequest request){
        return new ResponseEntity<>(userService.register(request), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto>login(@RequestBody RegistrationRequest request){
        return new ResponseEntity<>(userService.login(request.getEmail(), request.getPassword()),HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<UserDto> update(@RequestBody UserDto user){
        return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<UserDto> getUser(@RequestParam Long id){
        return new ResponseEntity<>(userService.getUser(id),HttpStatus.ACCEPTED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<UserDto> delete(@RequestParam Long id){
        return new ResponseEntity<>(userService.getUser(id),HttpStatus.ACCEPTED);
    }

}
