package com.siva.taskorganizer.api;

import com.siva.taskorganizer.domain.LoginUserDetails;
import com.siva.taskorganizer.domain.Task;
import com.siva.taskorganizer.domain.User;
import com.siva.taskorganizer.service.TaskService;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;

/**
 * @author by Siva Murugan
 */
@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /*
     * POST http://localhost:8081/api/task
     * */
    @PostMapping(value="/auth/api/task", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes= MediaType.APPLICATION_JSON_VALUE)
    public Task addTask(@RequestBody Task task){
        logger.debug(String.format("New task %s requested for user %s",
                task.getTaskname(), getCurrentUser().getUsername()));
        return taskService.addTask(task);
    }

    private User getCurrentUser() {
        User user = new User();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof LoginUserDetails) {
            user.setUserid(((LoginUserDetails)principal).getUserid());
        }
        return user;
    }

    /*
     * GET http://localhost:8081/api/task
     * */
    @GetMapping(value="/api/task", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Task> getTasks(){
        logger.debug("Get all task request is retrieved");
        return taskService.findAll();
    }
}
