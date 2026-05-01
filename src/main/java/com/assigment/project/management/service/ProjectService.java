package com.assigment.project.management.service;

import com.assigment.project.management.model.Project;
import com.assigment.project.management.model.User;
import com.assigment.project.management.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public List<Project> getProjectsByUser(User user) {
        return projectRepository.findByUser(user);
    }

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
    public void deleteProjectById(Long id) {
        projectRepository.deleteById(id);
    }
}