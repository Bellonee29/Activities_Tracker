package com.toDoApp.dto;

import com.toDoApp.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationRequest {
    private String name;
    private String email;
    private Gender gender;
    private String password;
}