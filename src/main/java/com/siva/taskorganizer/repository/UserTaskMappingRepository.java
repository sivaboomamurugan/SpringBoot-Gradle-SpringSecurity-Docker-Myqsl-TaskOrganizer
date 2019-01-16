package com.siva.taskorganizer.repository;

import com.siva.taskorganizer.domain.User;
import com.siva.taskorganizer.domain.UserTaskMapping;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * @author by Siva Murugan
 */
public interface UserTaskMappingRepository extends CrudRepository<UserTaskMapping, Integer> {

    List<UserTaskMapping> findByUser(User userid);
}
