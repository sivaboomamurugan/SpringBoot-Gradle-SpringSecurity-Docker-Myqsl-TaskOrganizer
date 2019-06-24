package com.siva.taskorganizer.api;

import com.siva.taskorganizer.domain.LoginUserDetails;
import com.siva.taskorganizer.domain.User;
import com.siva.taskorganizer.service.UserService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /*
     * POST http://localhost:8081/api/user
     * */
    @PostMapping(value="/api/user", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes= MediaType.APPLICATION_JSON_VALUE)
    public User addUser(@RequestBody User user){
        logger.debug(String.format("New user reqistration reuqest for %s", user.getUsername()));
        return userService.addUser(user);
    }

    private User getCurrentUser() {
        User user = new User();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof LoginUserDetails) {
            user.setUserid(((LoginUserDetails)principal).getUserid());
            user.setUsername(((LoginUserDetails)principal).getUsername());
        }
        return user;
    }

    /*
     * GET http://localhost:8081/api/user
     * */
    @GetMapping(value="/auth/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers(){
        System.out.println(getCurrentUser());
        logger.debug(String.format("Get all users requested by user %s", getCurrentUser().getUsername()));
        return userService.findAll();
    }
}
