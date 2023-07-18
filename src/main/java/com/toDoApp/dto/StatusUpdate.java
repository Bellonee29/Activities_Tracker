package com.toDoApp.dto;

import com.toDoApp.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusUpdate {
    private Long taskId;
    private Status status;
}
