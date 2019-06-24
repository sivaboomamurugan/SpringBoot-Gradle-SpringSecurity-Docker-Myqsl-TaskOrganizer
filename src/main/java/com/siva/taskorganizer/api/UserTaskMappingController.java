package com.siva.taskorganizer.api;

import com.siva.taskorganizer.domain.LoginUserDetails;
import com.siva.taskorganizer.domain.Task;
import com.siva.taskorganizer.domain.User;
import com.siva.taskorganizer.domain.UserTaskMapping;
import com.siva.taskorganizer.domain.UserTaskMappingModel;
import com.siva.taskorganizer.service.TaskService;
import com.siva.taskorganizer.service.UserTaskMappingService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /*
     * POST http://localhost:8081/auth/api/userTaskMapping
     * PUT http://localhost:8081/auth/api/userTaskMapping
     * */
    @RequestMapping(value="/auth/api/usertaskmapping", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes= MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.POST, RequestMethod.PUT})
    public UserTaskMapping addUserTaskMapping(@RequestBody UserTaskMappingModel userTaskMappingModel){
        logger.debug(String.format("%s task mapping requested for user %s",
                userTaskMappingModel.getTask().getTaskname(), getCurrentUser().getUsername()));
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
        logger.debug(String.format("Get task mapping requested for user %s", getCurrentUser().getUsername()));
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
        logger.debug(String.format("Get all user task mapping requested by user %s", getCurrentUser().getUsername()));
        User user = getCurrentUser();
        Task task = getActualTask(userTaskMappingModel.getTask());
        return new UserTaskMapping(user, task, userTaskMappingModel.isCompleted());
    }
}
