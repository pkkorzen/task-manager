package com.example.taskmanager.service.impl;

import com.example.taskmanager.dto.TaskDto;
import com.example.taskmanager.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TaskServiceImplTest {

    @Autowired
    TaskService taskService;
    @Test
    public void shouldFindAllTasksDto() {
        List<TaskDto> tasks = taskService.findAllTasks();
        assertEquals(3, tasks.size());

    }

    @Test
    public void shouldFindAllFinishedTasksDto() {
        List<TaskDto> tasks = taskService.findAllFinishedTasks();
        assertEquals(1, tasks.size());
    }

    @Test
    public void shouldFindAllUnfinishedTasksDto() {
        List<TaskDto> tasks = taskService.findAllUnfinishedTasks();
        assertEquals("garbage", tasks.get(0).getName());
    }

    @Test
    public void shouldFindTaskDtoById() {
        Optional<TaskDto> taskDto = taskService.findTaskById(1L);
        assertTrue(taskDto.isPresent());
    }

    @Test
    public void shouldSaveTaskDto() {
        List<TaskDto> tasksBefore = taskService.findAllTasks();
        TaskDto task = new TaskDto();
        task.setName("laundry");
        task.setDescription("do the laundry");
        task.setIsDone("No");
        taskService.saveTask(task);
        List<TaskDto> tasks = taskService.findAllTasks();
        assertEquals(tasksBefore.size()+1, tasks.size());
    }

    @Test
    public void shouldDeleteTaskDto() {
        List<TaskDto> tasksBefore = taskService.findAllTasks();
        taskService.deleteTask((long)(tasksBefore.size()-1));
        List<TaskDto> tasksAfter = taskService.findAllTasks();
        assertEquals(tasksBefore.size()-1, tasksAfter.size());
    }
}