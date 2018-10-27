package com.example.taskmanager.service;

import com.example.taskmanager.dto.TaskDto;
import com.example.taskmanager.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<TaskDto> findAllTasks();
    List<TaskDto> findAllFinishedTasks();
    List<TaskDto> findAllUnfinishedTasks();
    Optional<TaskDto> findTaskById(Long id);
    void saveTask(TaskDto task);
    void deleteTask(Long id);
}
