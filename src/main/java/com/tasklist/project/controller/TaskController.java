package com.tasklist.project.controller;

import com.tasklist.project.dto.GetTaskDto;
import com.tasklist.project.dto.MakeTaskDto;
import com.tasklist.project.entity.User;
import com.tasklist.project.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    @GetMapping
    public List<GetTaskDto> getAllTasks() {
        return taskService.getAllTasks();
    }
    @GetMapping("/user")
    public List<GetTaskDto> getTasksOfUser(Authentication auth) {
        User user = (User) auth.getPrincipal();
        return taskService.getTasksOfUser(user.getId());
    }
    @GetMapping("/task/{id}")
    public ResponseEntity<GetTaskDto> getTaskById(@PathVariable Long id) {
        GetTaskDto task = taskService.getTaskById(id);
        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    @GetMapping("/task")
    public ResponseEntity<GetTaskDto> getTaskOfUserById(@RequestBody GetTaskDto task,
                                                        Authentication auth) {
        User user = (User) auth.getPrincipal();
        GetTaskDto taskDto = taskService.getTaskOfUserById(task.getId(), user.getId());
        if (taskDto != null) {
            return new ResponseEntity<>(taskDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    @PostMapping("/task")
    public void createTask(@RequestBody MakeTaskDto task,
                           Authentication auth) {
        User user = (User) auth.getPrincipal();
        taskService.createTask(task, user.getId());
    }
    @PostMapping("/change-status/{taskId}")
    public ResponseEntity<String> changeTaskStatus(@PathVariable Long taskId,
                                                   Authentication auth) {
        User user = (User) auth.getPrincipal();
        return new ResponseEntity<>(taskService.changeTaskStatus(user.getId(), taskId), HttpStatus.OK);
    }
}
