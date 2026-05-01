package com.assigment.project.management.repository;

import com.assigment.project.management.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    long countByStatus(String status);
    List<Task> findByDueDateBeforeAndStatusNot(LocalDate date, String status);
}