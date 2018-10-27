package com.example.taskmanager.service.impl;

import com.example.taskmanager.converter.TaskDtoConverter;
import com.example.taskmanager.dto.TaskDto;
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
    private TaskDtoConverter taskDtoConverter;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, TaskDtoConverter taskDtoConverter){
        this.taskRepository = taskRepository;
        this.taskDtoConverter = taskDtoConverter;
    }

    @Override
    public List<TaskDto> findAllTasks() {
        Iterable <Task> tasks = taskRepository.findAll();
        return StreamSupport.stream(tasks.spliterator(), true).map(task -> taskDtoConverter.convert(task))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> findAllFinishedTasks() {
        Iterable <Task> tasks = taskRepository.findAllByIsDoneTrue();
        return StreamSupport.stream(tasks.spliterator(), true).map(task -> taskDtoConverter.convert(task))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> findAllUnfinishedTasks() {
        Iterable <Task> tasks = taskRepository.findAllByIsDoneFalse();
        return StreamSupport.stream(tasks.spliterator(), true).map(task -> taskDtoConverter.convert(task))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TaskDto> findTaskById(Long id) {
        Optional<Task> result = taskRepository.findById(id);
        return result.map(task -> taskDtoConverter.convert(task));
    }

    @Override
    public void saveTask(TaskDto task) {
        taskRepository.save(taskDtoConverter.convert(task));
    }

    @Override
    public void deleteTask(Long id) {
        Optional<Task> result = taskRepository.findById(id);
        result.ifPresent(task -> taskRepository.delete(task));
    }
}
