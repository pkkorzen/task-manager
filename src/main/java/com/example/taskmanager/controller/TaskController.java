package com.example.taskmanager.controller;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class TaskController {

    private TaskService taskService;

    @GetMapping("/tasks")
    public String showAllTasks(Model model){
        List<Task> tasks = taskService.findAllTasks();
        model.addAttribute(tasks);
        return "tasks/all";
    }

    @PostMapping("/task/save")
    public String saveTask(@ModelAttribute Task task, @RequestParam(name = "pressed-button") String pushedButton){

        if ("save".equalsIgnoreCase(pushedButton)) {
            taskService.saveTask(task);
        }

        return "redirect:/tasks";
    }

    @GetMapping("/task/add")
    public String addTask(Model model){
        model.addAttribute("operationTitle", "New");
        model.addAttribute("mainParagraph", "Add new");
        model.addAttribute("employee", new Task());
        return "tasks/task";
    }

    @GetMapping("/task/edit/{id}")
    public String editEmployee(@PathVariable Long id, Model model) {
        model.addAttribute("operationTitle", "Edit");
        model.addAttribute("mainParagraph", "Edit");

        Optional<Task> result = taskService.findTaskById(id);
        result.ifPresent(task -> model.addAttribute("task", task));

        return "tasks/task";
    }

    @GetMapping("/task/delete/{id}")
    public String deleteEmployee(@PathVariable Long id, Model model) {
        taskService.deleteTask(id);

        return "redirect:/tasks";
    }
}
