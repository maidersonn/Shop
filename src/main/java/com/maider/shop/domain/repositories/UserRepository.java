package com.maider.shop.domain.repositories;

import com.maider.shop.domain.entities.User;


public interface UserRepository {
    User findByUsername(String username);
    User save(User user);
}
