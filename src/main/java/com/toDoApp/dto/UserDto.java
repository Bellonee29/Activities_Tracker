package com.toDoApp.dto;

import com.toDoApp.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public class UserDto {
        private  Long id;
        private String name;
        private String email;
        private Gender gender;
       private Date createdAt;
        private Date updatedAt;


}
