package com.siva.taskorganizer.service;

import com.siva.taskorganizer.domain.Task;
import com.siva.taskorganizer.repository.TaskRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * @author by ${user}
 */
public class TaskServiceTest {

    @InjectMocks
    TaskService service;

    @Mock
    TaskRepository repository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAll() {
        when(repository.findAll()).thenReturn(new ArrayList<Task>(){{add(new Task());}});
        assertEquals(1, service.findAll().size());

    }

    @Test
    public void addTask() {
        Task task = new Task();
        when(repository.save(task)).thenReturn(task);
        assertEquals(task, service.addTask(task));
    }

    @Test
    public void findById() {
        Optional<Task> task = Optional.of(new Task());
        when(repository.findById(1)).thenReturn(task);
        assertEquals(task.get(), service.findById(1));
    }

    @Test
    public void findByTaskName() {
        Task task1 = new Task();
        task1.setTaskname("Office");
        Optional<Task> task = Optional.of(task1);
        when(repository.findByTaskname("Office")).thenReturn(task);
        assertEquals(task.get(), service.findByTaskName(task1));
    }
}