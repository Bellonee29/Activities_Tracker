package com.toDoApp.services.Implementations;

import com.toDoApp.dto.*;
import com.toDoApp.enums.Status;
import com.toDoApp.exceptions.IllegalEntityStateException;
import com.toDoApp.model.Task;
import com.toDoApp.repository.TaskRepository;
import com.toDoApp.repository.UserRepository;
import com.toDoApp.services.TaskService;
import com.toDoApp.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    private  final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TaskDto create(TaskCreationRequest newTask) {
        return Mapper.taskToDTOMapper(taskRepository.save(
                Task.builder()
                        .title(newTask.getTitle())
                        .description(newTask.getDescription())
                        .user(userRepository.findById(newTask.getUserId()).orElseThrow(
                                ()->new EntityNotFoundException("User Not Found " + "Wrong user Id supplied")
                        ))
                        .status(Status.PENDING)
                        .build()
        ));
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }


    @Override
    public TaskDto getTask(Long id) {
        return Mapper.taskToDTOMapper(taskRepository.findById(id).orElseThrow(
                ()->new EntityNotFoundException("Task Not Found " + "No task with the ID in our record")));
    }

    public TaskDto updateStatus(StatusUpdate statusUpdate){
        Task task = findTaskById(statusUpdate.getTaskId());
        task.setStatus(statusUpdate.getStatus());
        return Mapper.taskToDTOMapper(taskRepository.saveAndFlush(task));
    }

    @Override
    public List<TaskDto> getAlltasks() {
        return taskRepository.findAll().stream()
                .map(Mapper::taskToDTOMapper)
                .collect(Collectors.toList());
    }

    //@Override
    /*public void delete(Long id) {
        taskRepository.deleteById(id);
    }*/

    @Override
    public TaskDto updateTask(TaskUpdateRequest request) {
        Task task= findTaskById(request.getTaskId());
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        return Mapper.taskToDTOMapper(taskRepository.saveAndFlush(task)); //returns equivalent dto after persisting the update
    }

    @Override
    public TaskDto setPending(Long taskId) {
        Task task=findTaskById(taskId);
        if (task.getStatus()==Status.PENDING) throw new IllegalEntityStateException("Illegal Object Update",
                "Status cannot be changed to same state");
        task.setStatus(Status.PENDING);// change the state of the object
        task.setUpdatedAt(new Date());
        task.setCompletedAt(null);
        return Mapper.taskToDTOMapper(taskRepository.saveAndFlush(task)); //returns equivalent dto after persisting the update

    }

    @Override
    public TaskDto setInProgress(Long taskId) {
        Task task=findTaskById(taskId);
        if (task.getStatus()==Status.IN_PROGRESS) throw new IllegalEntityStateException("Illegal Object Update",
                "Status cannot be changed to same state");
        task.setStatus(Status.IN_PROGRESS); // change the state of the object
        task.setUpdatedAt(new Date());
        task.setCompletedAt(null);
        return Mapper.taskToDTOMapper(taskRepository.saveAndFlush(task)); //returns equivalent dto after persisting the update

    }

    @Override
    public TaskDto setDone(Long taskId) {
        Task task=findTaskById(taskId);
        task.setStatus(Status.DONE); // change the state of the object
        task.setUpdatedAt(new Date());
        task.setCompletedAt(new Date());
        return Mapper.taskToDTOMapper(taskRepository.saveAndFlush(task)); //returns equivalent dto after persisting the update
    }
    @Override
    public List<TaskDto> viewAllPending(Long userId) {
        return viewBaseOnStatus(userId,Status.PENDING);
    }

    @Override
    public List<TaskDto> viewAllInProgress(Long userId) {
        return viewBaseOnStatus(userId,Status.IN_PROGRESS);
    }

    @Override
    public List<TaskDto> viewAllDone(Long userId) {
        return viewBaseOnStatus(userId, Status.DONE);
    }

    @Override
    public List<TaskDto> viewAllUserTask(Long userId) {
        return taskRepository.findAllUserTasks(userId).stream()
                .map(Mapper::taskToDTOMapper)
                .collect(Collectors.toList());
    }

    private List<TaskDto> viewBaseOnStatus(Long userId, Status status) {
        return taskRepository.findByUserIdAndStatus(userId, status.name()).stream()
                .map(Mapper::taskToDTOMapper)
                .collect(Collectors.toList());
    }

    private Task findTaskById(Long id){
        return taskRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Task Not Found " + "Task with id : "+id+"Not found")); // finds the task in the database or throws exception if not found
    }


}
