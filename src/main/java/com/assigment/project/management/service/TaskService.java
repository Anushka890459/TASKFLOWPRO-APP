
package com.assigment.project.management.service;

import com.assigment.project.management.model.Task;
import com.assigment.project.management.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getOverdueTasks() {

        return taskRepository.findByDueDateBeforeAndStatusNot(LocalDate.now(), "Completed");
    }

    public long getCountByStatus(String status) {
        return taskRepository.countByStatus(status);
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }
    public void updateStatus(Long id, String status) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setStatus(status);
        taskRepository.save(task);
    }
}