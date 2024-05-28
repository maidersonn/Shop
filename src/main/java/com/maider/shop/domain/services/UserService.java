package com.maider.shop.domain.services;

import com.maider.shop.domain.entities.User;
import com.maider.shop.domain.repositories.UserRepository;
import com.maider.shop.result.Failure;
import com.maider.shop.result.Result;
import com.maider.shop.result.Success;
import com.maider.shop.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByUsername(username);
            return UserDetailsImpl.build(user);
        } catch (Exception e){
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
    }
    public Result<User, String> createUser (User user) {
        try {
            String passwordEncrypted =  passwordEncoder.encode(user.getPassword());
            User newUser = new User(user.getUsername(), passwordEncrypted);
            User userCreated = userRepository.save(newUser);
            return new Success<>(userCreated);
        } catch (Exception e) {
            return new Failure<>("Error relationed with database");
        }
    }
}
