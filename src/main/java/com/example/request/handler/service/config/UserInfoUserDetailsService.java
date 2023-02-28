package com.example.request.handler.service.config;

import com.example.request.handler.service.repository.User;
import com.example.request.handler.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> userInfo = repository.findByName(username);
        if(userInfo==null || userInfo.isEmpty())
            throw new UsernameNotFoundException("user not found " + username);
        return new UserInfoUserDetails(userInfo.get(0));
    }
}
