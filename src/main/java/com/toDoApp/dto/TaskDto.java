package com.toDoApp.dto;

import com.toDoApp.enums.Status;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TaskDto {

    private Long id;
    private String title;
    private String description;
    private Status status;
    private Date createdAt;
    private Date updatedAt;
    private Date completedAt;
    private Long userId;

}
