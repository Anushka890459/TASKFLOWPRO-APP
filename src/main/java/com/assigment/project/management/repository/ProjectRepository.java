package com.assigment.project.management.repository;

import com.assigment.project.management.model.Project;
import com.assigment.project.management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {


    List<Project> findByUser(User user);
}