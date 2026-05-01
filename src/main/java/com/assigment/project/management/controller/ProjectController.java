
package com.assigment.project.management.controller;

import com.assigment.project.management.model.Project;
import com.assigment.project.management.model.User;
import com.assigment.project.management.service.ProjectService;
import com.assigment.project.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String listProjects(Model model, Principal principal) {
        String email = principal.getName();
        User user = userService.findByEmail(email);
        List<Project> projects = projectService.getProjectsByUser(user);
        model.addAttribute("projects", projects);
        return "projects";
    }

    @PostMapping("/save")
    public String saveProject(@ModelAttribute Project project, Principal principal) {
        String email = principal.getName();
        User user = userService.findByEmail(email);
        project.setUser(user);
        projectService.saveProject(project);
        return "redirect:/dashboard";
    }

    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable("id") Long id) {
        projectService.deleteProjectById(id);
        return "redirect:/dashboard";
    }
}