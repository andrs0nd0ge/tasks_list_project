package com.tasklist.project.service;

import com.tasklist.project.dao.TaskDao;
import com.tasklist.project.dto.GetTaskDto;
import com.tasklist.project.dto.MakeTaskDto;
import com.tasklist.project.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskDao taskDao;
    public List<GetTaskDto> getAllTasks() {
        List<Task> tasks = taskDao.getAllTasks();
        return tasks.stream()
                .map(GetTaskDto::from)
                .collect(Collectors.toList());
    }
    public List<GetTaskDto> getTasksOfUser(Long userId) {
        List<Task> userTasks = taskDao.getTasksOfUser(userId);
        return userTasks.stream()
                .map(GetTaskDto::from)
                .collect(Collectors.toList());
    }
    public GetTaskDto getTaskById(Long id) {
        Task task = taskDao.getTaskById(id).orElse(null);
        if (task != null) {
            return GetTaskDto.from(task);
        }
        return null;
    }
    public GetTaskDto getTaskOfUserById(Long taskId, Long userId) {
        Task task = taskDao.getTaskOfUserById(taskId, userId).orElse(null);
        if (task != null) {
            return GetTaskDto.from(task);
        }
        return null;
    }
    public void createTask(MakeTaskDto task, Long userId) {
        taskDao.createTask(task, userId);
    }
    public String changeTaskStatus(Long userId, Long taskId) {
        Task task = taskDao.getTaskById(taskId).orElse(null);
        if (task != null) {
            if (task.getUserId().equals(userId)) {
                if (task.getStatusId().equals(1L)) {
                    taskDao.changeTaskStatus(2L, userId, task.getId());
                    return "Status of the task has been changed to \"In Progress\"";
                } else if (task.getStatusId().equals(2L)) {
                    taskDao.changeTaskStatus(3L, userId, task.getId());
                    return "Status of the task has been changed to \"Done\"";
                } else if (task.getStatusId().equals(3L)) {
                    return "Task is already completed, unable to change the status";
                }
            } else {
                return "You cannot change status of someone else's task";
            }
        }
        return "Task with given ID does not exist";
    }
}
