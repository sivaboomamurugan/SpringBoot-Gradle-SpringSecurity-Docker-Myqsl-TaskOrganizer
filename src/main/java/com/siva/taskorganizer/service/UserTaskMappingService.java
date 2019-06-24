package com.siva.taskorganizer.service;

import com.siva.taskorganizer.domain.User;
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
        userTaskMapping = composeTaskMapping(userTaskMapping);

        UserTaskMapping addedUser = repository.save(userTaskMapping);
        return addedUser;

    }

    /**
     * this method is used for identifying whether we are updating a mapping or creating a new one
     * @param userTaskMapping
     */
    private UserTaskMapping composeTaskMapping(UserTaskMapping userTaskMapping) {
        List<UserTaskMapping> userTasks = findByUser(userTaskMapping.getUser());
        Optional<UserTaskMapping> optionalTaskMapping = userTasks.stream()
                .filter(userTask -> userTask.getTask().equals(userTaskMapping.getTask()))
                .findFirst();
        UserTaskMapping taskMapping = optionalTaskMapping.orElse(userTaskMapping);
        //This makes sure it updates the completed status irrespective of the task mapping we use
        taskMapping.setCompleted(userTaskMapping.isCompleted());
        return taskMapping;
    }

    public UserTaskMapping findById(Integer id) {
        Optional<UserTaskMapping> user = repository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    public List<UserTaskMapping> findByUser(User user) {
        return repository.findByUser(user);
    }
}
