package com.example.taskmanager.service;

import com.example.taskmanager.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<Task> findAllTasks();
    Optional<Task> findTaskById(Long id);
    void saveTask(Task task);
    void deleteTask(Long id);
}
