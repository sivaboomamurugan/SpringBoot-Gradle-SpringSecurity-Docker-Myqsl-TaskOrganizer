package com.siva.taskorganizer.repository;

import com.siva.taskorganizer.domain.UserTaskMapping;
import org.springframework.data.repository.CrudRepository;

/**
 * @author by Siva Murugan
 */
public interface UserTaskMappingRepository extends CrudRepository<UserTaskMapping, Integer> {
}
