package com.example.taskmanager.converter;

import com.example.taskmanager.dto.TaskDto;
import com.example.taskmanager.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskDtoConverter  {

    public TaskDto convert (Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setName(task.getName());
        taskDto.setDescription(task.getDescription());
        if(task.isDone()){
            taskDto.setIsDone("Yes");
        } else{
            taskDto.setIsDone("No");
        }
        return taskDto;
    }

    public Task convert (TaskDto taskDto){
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        if("Yes".equals(taskDto.getIsDone())){
            task.setIsDone(true);
        } else {
            task.setIsDone(false);
        }
        return task;
    }
}
