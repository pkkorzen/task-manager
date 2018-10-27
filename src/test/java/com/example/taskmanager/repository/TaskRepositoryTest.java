package com.example.taskmanager.repository;

import com.example.taskmanager.entity.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void shouldFindAllFinishedTasks() {
        Iterable<Task> tasksIterable = taskRepository.findAllByIsDoneTrue();
        List<Task> tasks = StreamSupport.stream(tasksIterable.spliterator(), true).collect(Collectors.toList());
        assertEquals("shopping", tasks.get(0).getName());

    }

    @Test
    public void shouldFindAllUnfinishedTasks() {
        Iterable<Task> tasksIterable = taskRepository.findAllByIsDoneFalse();
        List<Task> tasks = StreamSupport.stream(tasksIterable.spliterator(), true).collect(Collectors.toList());
        assertEquals(2, tasks.size());
    }
}