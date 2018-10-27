package com.example.taskmanager.service.impl;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findAllTasks() {
        Iterable <Task> tasks = taskRepository.findAll();
        return StreamSupport.stream(tasks.spliterator(), true).collect(Collectors.toList());
    }

    @Override
    public List<Task> findAllFinishedTasks() {
        Iterable <Task> tasks = taskRepository.findAllByIsDoneTrue();
        return StreamSupport.stream(tasks.spliterator(), true).collect(Collectors.toList());
    }

    @Override
    public List<Task> findAllUnfinishedTasks() {
        Iterable <Task> tasks = taskRepository.findAllByIsDoneFalse();
        return StreamSupport.stream(tasks.spliterator(), true).collect(Collectors.toList());
    }

    @Override
    public Optional<Task> findTaskById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        Optional<Task> result = findTaskById(id);
        result.ifPresent(task -> taskRepository.delete(task));
    }
}
