package com.siva.taskorganizer.api;

import com.siva.taskorganizer.domain.LoginUserDetails;
import com.siva.taskorganizer.domain.Task;
import com.siva.taskorganizer.domain.User;
import com.siva.taskorganizer.domain.UserTaskMapping;
import com.siva.taskorganizer.domain.UserTaskMappingModel;
import com.siva.taskorganizer.service.TaskService;
import com.siva.taskorganizer.service.UserService;
import com.siva.taskorganizer.service.UserTaskMappingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by Siva Murugan
 */
@RestController
public class UserTaskMappingController {

    @Autowired
    private UserTaskMappingService userTaskMappingService;

    @Autowired
    private TaskService taskService;

    /*
     * POST http://localhost:8081/auth/api/userTaskMapping
     * PUT http://localhost:8081/auth/api/userTaskMapping
     * */
    @RequestMapping(value="/auth/api/usertaskmapping", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes= MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.POST, RequestMethod.PUT})
    public UserTaskMapping addUserTaskMapping(@RequestBody UserTaskMappingModel userTaskMappingModel){
        UserTaskMapping userTaskMapping = getUserTaskMapping(userTaskMappingModel);
        return userTaskMappingService.addUserTaskMapping(userTaskMapping);
    }

    private Task getActualTask(Task task) {
        return taskService.findByTaskName(task);
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
     * GET http://localhost:8081/api/usertaskmapping
     * */
    @GetMapping(value="/auth/api/usertaskmapping", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserTaskMapping> getUserTaskMappings(){
        User user = getCurrentUser();
        return userTaskMappingService.findByUser(user);
    }

    /*
     * GET http://localhost:8081/api/usertaskmapping
     *
     */
    @GetMapping(value="/api/usertaskmapping/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserTaskMapping> getAllUserTaskMappings(){
        return userTaskMappingService.findAll();
    }

    private UserTaskMapping getUserTaskMapping(@RequestBody UserTaskMappingModel userTaskMappingModel) {
        User user = getCurrentUser();
        Task task = getActualTask(userTaskMappingModel.getTask());
        return new UserTaskMapping(user, task, userTaskMappingModel.isCompleted());
    }
}
