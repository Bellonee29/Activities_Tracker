package com.toDoApp.utils;

import com.toDoApp.dto.TaskDto;
import com.toDoApp.dto.UserDto;
import com.toDoApp.model.Task;
import com.toDoApp.model.User;

import java.util.Date;

public class Mapper {

    public  static TaskDto taskToDTOMapper(Task task){
        return TaskDto.builder()
                .title(task.getTitle())
                .id(task.getId())
                .description(task.getDescription())
                .completedAt(task.getCompletedAt())
                .createdAt(task.getCreatedAt())
                .status(task.getStatus())
                .updatedAt(task.getUpdatedAt())
                .completedAt(task.getCompletedAt())
                .userId(task.getUser().getId())
                .build();
    }

    public static UserDto userToDTO(User user){
        return  UserDto.builder()
                .email(user.getEmail())
                .id(user.getId())
                .gender(user.getGender())
                .name(user.getName())
                .updatedAt(user.getUpdatedAt())
                .createdAt(user.getCreatedAt())
                .updatedAt(new Date())
                .build();
    }
}
