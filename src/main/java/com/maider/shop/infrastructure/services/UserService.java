package com.maider.shop.infrastructure.services;

import com.maider.shop.domain.entities.User;
import com.maider.shop.domain.repositories.UserRepository;
import com.maider.shop.result.Failure;
import com.maider.shop.result.Result;
import com.maider.shop.result.Success;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

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
