package com.siva.taskorganizer.repository;

import com.siva.taskorganizer.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author by Siva Murugan
 */
public interface LoginRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
