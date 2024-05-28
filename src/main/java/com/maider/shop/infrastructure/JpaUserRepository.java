package com.maider.shop.infrastructure;

import com.maider.shop.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User,String>{

    User findByUsername(String username);
}
