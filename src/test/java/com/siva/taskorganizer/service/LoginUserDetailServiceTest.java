package com.siva.taskorganizer.service;

import com.siva.taskorganizer.domain.User;
import com.siva.taskorganizer.repository.LoginRepository;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * @author by Sivamurgan
 */

public class LoginUserDetailServiceTest {

    @InjectMocks
    LoginUserDetailService loginUserDetailService;

    @Mock
    LoginRepository loginRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(user.getUsername()).thenReturn("testuser");
        when(user.getPassword()).thenReturn("testuser");
        when(user.getUserid()).thenReturn(1);

    }

    @Mock
    User user;

    @Test
    public void loadUserByUsername() {
        Optional<User> optionalUser = Optional.of(new User(user));
        when(loginRepository.findByUsername("testuser")).thenReturn(optionalUser);
        Assert.assertNotNull(loginUserDetailService.loadUserByUsername("testuser"));

    }
}