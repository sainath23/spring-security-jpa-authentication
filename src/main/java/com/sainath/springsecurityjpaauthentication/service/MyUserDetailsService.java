package com.sainath.springsecurityjpaauthentication.service;

import com.sainath.springsecurityjpaauthentication.entity.MyUserDetails;
import com.sainath.springsecurityjpaauthentication.entity.User;
import com.sainath.springsecurityjpaauthentication.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Provided username " + username + " not found!"));
        // LOGGER.info("User: {}", user.toString());
        //return new MyUserDetails(user);
        Optional<User> optionalUser = userRepository.findByUsername(username);
        //return optionalUser.map(user -> new MyUserDetails(user)).get();
        return optionalUser.map(MyUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("Provided username " + username + " not found!"));
    }
}
