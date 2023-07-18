package com.toDoApp.services;

import com.toDoApp.dto.*;
import com.toDoApp.model.Task;

import java.util.List;

public interface TaskService {
    TaskDto create(TaskCreationRequest newTask);
    void deleteTask(Long taskId);
    TaskDto getTask(Long id);
  //  Task findByTaskId(String taskId);
   // Task getTaskById(Long taskId);
    TaskDto updateTask(TaskUpdateRequest request);
    TaskDto setPending(Long taskId);
    TaskDto setInProgress(Long taskId);
    TaskDto setDone(Long taskId);
    List<TaskDto> viewAllPending(Long userId);
    List<TaskDto> viewAllInProgress(Long userId);
    List<TaskDto> viewAllDone(Long userId);
    List<TaskDto> viewAllUserTask(Long userId);

    TaskDto updateStatus(StatusUpdate statusUpdate);
    List<TaskDto> getAlltasks();

}
