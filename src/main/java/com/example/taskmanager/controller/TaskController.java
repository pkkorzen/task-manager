package com.example.taskmanager.controller;

import com.example.taskmanager.dto.TaskDto;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    public String showAllTasks(Model model){
        List<TaskDto> tasks = taskService.findAllTasks();
        model.addAttribute("tasks",tasks);
        model.addAttribute("mainTitle", "All");
        return "tasks/all";
    }

    @GetMapping("/tasks/finished")
    public String showFinishedTasks(Model model){
        List<TaskDto> tasks = taskService.findAllFinishedTasks();
        model.addAttribute("tasks",tasks);
        model.addAttribute("mainTitle", "Finished");
        return "tasks/all";
    }

    @GetMapping("/tasks/unfinished")
    public String showUnfishedTasks(Model model){
        List<TaskDto> tasks = taskService.findAllUnfinishedTasks();
        model.addAttribute("tasks",tasks);
        model.addAttribute("mainTitle", "Unfinished");
        return "tasks/all";
    }

    @PostMapping("/task/save")
    public String saveTask(@ModelAttribute TaskDto task, @RequestParam(name = "pressed-button") String pushedButton){

        if ("save".equalsIgnoreCase(pushedButton)) {
            taskService.saveTask(task);
        }

        return "redirect:/tasks";
    }

    @GetMapping("/task/add")
    public String addTask(Model model){
        model.addAttribute("operationTitle", "New");
        model.addAttribute("mainParagraph", "Add new");
        TaskDto taskDto = new TaskDto();
        taskDto.setIsDone("No");
        model.addAttribute("task", taskDto);
        return "tasks/task";
    }

    @GetMapping("/task/edit/{id}")
    public String editEmployee(@PathVariable Long id, Model model) {
        model.addAttribute("operationTitle", "Edit");
        model.addAttribute("mainParagraph", "Edit");

        Optional<TaskDto> result = taskService.findTaskById(id);
        result.ifPresent(task -> model.addAttribute("task", task));

        return "tasks/task";
    }

    @GetMapping("/task/delete/{id}")
    public String deleteEmployee(@PathVariable Long id, Model model) {
        taskService.deleteTask(id);

        return "redirect:/tasks";
    }
}
