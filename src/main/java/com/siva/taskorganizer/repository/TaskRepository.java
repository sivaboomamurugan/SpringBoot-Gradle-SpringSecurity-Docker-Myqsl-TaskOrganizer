package com.siva.taskorganizer.repository;

import com.siva.taskorganizer.domain.Task;
import com.siva.taskorganizer.domain.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author by Siva Murugan
 */
@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
    Optional<Task> findByTaskname(String taskname);
}
