package com.toDoApp.contollers;

import com.toDoApp.dto.*;
import com.toDoApp.model.Task;
import com.toDoApp.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping("/new-task")
    public ResponseEntity<TaskDto> create(@RequestBody TaskCreationRequest request){
        return new ResponseEntity<>(taskService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<TaskDto> update(@RequestBody TaskUpdateRequest request){
        return new ResponseEntity<>(taskService.updateTask(request), HttpStatus.ACCEPTED);
    }

    @PutMapping("/update_status")
    public ResponseEntity<TaskDto> updateS(@RequestBody StatusUpdate statusUpdate){
        return new ResponseEntity<>(taskService.updateStatus(statusUpdate), HttpStatus.ACCEPTED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskDto>> getTasks(){
        return new ResponseEntity<>(taskService.getAlltasks(),HttpStatus.ACCEPTED);
    }
    @GetMapping("/task")
    public ResponseEntity<TaskDto> getTask(@RequestParam Long taskId){
        return new ResponseEntity<>(taskService.getTask(taskId),HttpStatus.OK);
    }


    @GetMapping("/user/done")
    public ResponseEntity<List<TaskDto>> getDoneTask(@RequestParam Long taskId){
        return new ResponseEntity<>(taskService.viewAllDone(taskId),HttpStatus.OK);
    }
    @GetMapping("/user/in-progress")
    public ResponseEntity<List<TaskDto>> getInProgressTask(@RequestParam Long userId){
        return new ResponseEntity<>(taskService.viewAllInProgress(userId),HttpStatus.OK);
    }
    @GetMapping("/user/pending")
    public ResponseEntity<List<TaskDto>> getPendingTask(@RequestParam Long userId){
        return new ResponseEntity<>(taskService.viewAllPending(userId),HttpStatus.OK);
    }

    /*@PatchMapping("/user/complete")
    public ResponseEntity<TaskDto> setTaskDone(@RequestParam Long taskId){
        return new ResponseEntity<>(taskService.setDone(taskId), HttpStatus.OK);
    }

    @PatchMapping("/user/pend")
    public ResponseEntity<TaskDto> setTaskPending(@RequestParam Long taskId){
        return new ResponseEntity<>(taskService.setPending(taskId),HttpStatus.OK);
    }
    @PatchMapping("/user/progress")
    public ResponseEntity<TaskDto> setTaskInProgress(@RequestParam Long taskId){
        return new ResponseEntity<>(taskService.setInProgress(taskId),HttpStatus.OK);
    }*/

    @DeleteMapping("delete")

    public ResponseEntity<String> deletePost(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.ACCEPTED);

    /*@DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable String taskId) {
        Task task = taskService.findByTaskId(taskId);

        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }

        taskService.delete(task);
        return ResponseEntity.ok("Task deleted successfully");*/
    }



}
