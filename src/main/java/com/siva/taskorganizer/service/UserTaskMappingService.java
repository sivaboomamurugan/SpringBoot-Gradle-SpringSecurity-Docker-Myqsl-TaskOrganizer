package com.siva.taskorganizer.service;

import com.siva.taskorganizer.domain.UserTaskMapping;
import com.siva.taskorganizer.repository.UserTaskMappingRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by Siva Murugan
 */
@Service
public class UserTaskMappingService {

    @Autowired
    private final UserTaskMappingRepository repository;

    public UserTaskMappingService(UserTaskMappingRepository userTaskMappingRepository) {
        this.repository = userTaskMappingRepository;
    }

    public List<UserTaskMapping> findAll() {
        List<UserTaskMapping> userTaskMappings = new ArrayList<UserTaskMapping>();
        repository.findAll().forEach(userTaskMappings::add);
        return userTaskMappings;
    }

    public UserTaskMapping addUserTaskMapping(UserTaskMapping userTaskMapping) {
        UserTaskMapping addedUser = repository.save(userTaskMapping);
        return addedUser;

    }
    public UserTaskMapping findById(Integer id) {
        Optional<UserTaskMapping> user = repository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }
}
