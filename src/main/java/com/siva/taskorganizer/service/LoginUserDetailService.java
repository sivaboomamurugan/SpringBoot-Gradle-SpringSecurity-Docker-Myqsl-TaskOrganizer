package com.siva.taskorganizer.service;

import com.siva.taskorganizer.domain.LoginUserDetails;
import com.siva.taskorganizer.domain.User;
import com.siva.taskorganizer.repository.LoginRepository;
import java.util.Optional;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = loginRepository.findByUsername(username);
        return optionalUser.map(LoginUserDetails::new)
                .orElseThrow(() ->
                new UsernameNotFoundException("User does not exist"));
    }
}
