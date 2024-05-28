package com.maider.shop.controllers.mapper;

import com.maider.shop.controllers.dto.UserCrationDTO;
import com.maider.shop.controllers.dto.UserDTO;
import com.maider.shop.domain.entities.User;

public class UserMapper {
    public static User toUser(UserCrationDTO userDTO) {
        return new User(userDTO.getUsername(), userDTO.getPassword());
    }
    public static UserDTO toUserDTO(User user) {
        return new UserDTO(user.getUsername(), user.getId());
    }
}
