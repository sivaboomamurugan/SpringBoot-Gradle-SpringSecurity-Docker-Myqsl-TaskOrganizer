package com.siva.taskorganizer.api;

import com.siva.taskorganizer.domain.Task;
import com.siva.taskorganizer.service.TaskService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by Siva Murugan
 */
@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    /*
     * POST http://localhost:8081/api/task
     * */
    @PostMapping(value="/api/task", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes= MediaType.APPLICATION_JSON_VALUE)
    public Task addUser(@RequestBody Task task){
        return taskService.addUser(task);
    }

    /*
     * GET http://localhost:8081/api/task
     * */
    @GetMapping(value="/api/task", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Task> getUsers(){
        return taskService.findAll();
    }
}
