package com.assigment.project.management.controller;

import com.assigment.project.management.model.Project;
import com.assigment.project.management.model.Task;
import com.assigment.project.management.service.ProjectService;
import com.assigment.project.management.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ProjectService projectService;

    @PostMapping("/add/{projectId}")
    public String createTask(@PathVariable Long projectId, @ModelAttribute("task") Task task) {
        Project project = projectService.getProjectById(projectId);
        task.setProject(project);

        if (task.getStatus() == null) {
            task.setStatus("Pending");
        }

        taskService.saveTask(task);
        return "redirect:/dashboard";
    }

    @PostMapping("/update-status/{id}")
    public String updateTaskStatus(@PathVariable Long id, @RequestParam String status) {
        taskService.updateStatus(id, status);
        return "redirect:/dashboard";
    }
}