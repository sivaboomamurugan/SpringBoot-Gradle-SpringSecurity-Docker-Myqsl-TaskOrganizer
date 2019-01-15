package com.siva.taskorganizer.service;

import com.siva.taskorganizer.domain.Task;
import com.siva.taskorganizer.repository.TaskRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by Siva Murugan
 */
@Service
public class TaskService {
    @Autowired
    private final TaskRepository repository;

    public TaskService(TaskRepository taskRepository) {
        this.repository = taskRepository;
    }

    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<Task>();
        repository.findAll().forEach(tasks::add);
        return tasks;
    }

    public Task addUser(Task task) {
        Task addedTask = repository.save(task);
        return addedTask;

    }
    public Task findById(Integer id) {
        Optional<Task> task = repository.findById(id);
        if (task.isPresent()) {
            return task.get();
        } else {
            return null;
        }
    }

}
