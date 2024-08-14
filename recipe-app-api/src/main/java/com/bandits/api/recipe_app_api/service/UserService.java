package com.bandits.api.recipe_app_api.service;

import com.bandits.api.recipe_app_api.model.AppUser;
import com.bandits.api.recipe_app_api.model.Role;
import com.bandits.api.recipe_app_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("in the user detail service");
        return userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }
}
