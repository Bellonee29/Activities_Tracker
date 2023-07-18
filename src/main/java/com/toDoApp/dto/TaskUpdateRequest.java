package com.toDoApp.dto;

import com.toDoApp.enums.Status;
import lombok.*;

@Data
@RequiredArgsConstructor
public class TaskUpdateRequest {
    private Long taskId;
    private String title;
    private String description;
    //private Long userId;
    private Status status;



}
