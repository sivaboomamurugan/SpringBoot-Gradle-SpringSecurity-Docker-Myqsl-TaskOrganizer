package com.siva.taskorganizer.service;

import com.siva.taskorganizer.domain.LoginUserDetails;
import com.siva.taskorganizer.domain.User;
import com.siva.taskorganizer.repository.LoginRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author by Siva Murugan
 */
@Service
public class LoginUserDetailService implements UserDetailsService {

    @Autowired
    LoginRepository loginRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static UsernameNotFoundException getUserNotFoundException(String username) {
        new LoginUserDetailService().logger.error(String.format("%s does not exist", username));
        return new UsernameNotFoundException(String.format("%s does not exist", username));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug(String.format("%s trying to login", username));
        Optional<User> optionalUser = loginRepository.findByUsername(username);
        return optionalUser.map(LoginUserDetails::new)
                .orElseThrow(() -> getUserNotFoundException(username));
    }
}
