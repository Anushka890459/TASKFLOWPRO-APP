package com.assigment.project.management.controller;

import com.assigment.project.management.model.Project;
import com.assigment.project.management.model.Task;
import com.assigment.project.management.model.User;
import com.assigment.project.management.repository.TaskRepository;
import com.assigment.project.management.service.ProjectService;
import com.assigment.project.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class dashboardcontroller {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/dashboard")
    public String showDashboard(Model model, Principal principal) {
        String email = principal.getName();
        User user = userService.findByEmail(email);

        List<Project> projects = projectService.getProjectsByUser(user);

        long pendingCount = taskRepository.countByStatus("Pending");
        long completedCount = taskRepository.countByStatus("Completed");

        List<Task> overdueTasks = taskRepository.findByDueDateBeforeAndStatusNot(LocalDate.now(), "Completed");

        model.addAttribute("user", user);
        model.addAttribute("projects", projects);
        model.addAttribute("pendingCount", pendingCount);
        model.addAttribute("completedCount", completedCount);
        model.addAttribute("overdueTasks", overdueTasks);
        model.addAttribute("userName", user.getName());

        return "dashboard";
    }
}

