package com.siva.taskorganizer.api;

import com.siva.taskorganizer.domain.User;
import com.siva.taskorganizer.service.UserService;
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
public class UserController {

    @Autowired
    private UserService userService;

    /*
     * POST http://localhost:8081/api/user
     * */
    @PostMapping(value="/api/user", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes= MediaType.APPLICATION_JSON_VALUE)
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    /*
     * GET http://localhost:8081/api/user
     * */
    @GetMapping(value="/auth/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers(){
        return userService.findAll();
    }
}
