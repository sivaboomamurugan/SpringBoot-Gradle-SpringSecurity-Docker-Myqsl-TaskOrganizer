package com.siva.taskorganizer.service;

import com.siva.taskorganizer.domain.User;
import com.siva.taskorganizer.repository.UserTaskMappingRepositoru;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by Siva Murugan
 */
@Service
public class UserService {
    @Autowired
    private final UserTaskMappingRepositoru repository;

    public UserService(UserTaskMappingRepositoru userTaskMappingRepositoru) {
        this.repository = userTaskMappingRepositoru;
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<User>();
        repository.findAll().forEach(users::add);
        return users;
    }

    public User addUser(User user) {
        User addedUser = repository.save(user);
        return addedUser;

    }
    public User findById(Integer id) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

}
