package com.siva.taskorganizer.api;

import com.siva.taskorganizer.domain.User;
import com.siva.taskorganizer.domain.UserTaskMapping;
import com.siva.taskorganizer.service.UserService;
import com.siva.taskorganizer.service.UserTaskMappingService;
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
public class UserTaskMappingController {

    @Autowired
    private UserTaskMappingService userTaskMappingService;

    /*
     * POST http://localhost:8081/api/userTaskMapping
     * */
    @PostMapping(value="/api/usertaskmapping", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes= MediaType.APPLICATION_JSON_VALUE)
    public UserTaskMapping addUser(@RequestBody UserTaskMapping userTaskMapping){
        return userTaskMappingService.addUserTaskMapping(userTaskMapping);
    }

    /*
     * GET http://localhost:8081/api/usertaskmapping
     * */
    @GetMapping(value="/api/usertaskmapping", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserTaskMapping> getUsers(){
        return userTaskMappingService.findAll();
    }
}
