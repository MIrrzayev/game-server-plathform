package com.huseyn.gameserverplathform.mapper;

import com.huseyn.gameserverplathform.dto.UserRequestDTO;
import com.huseyn.gameserverplathform.dto.UserResponseDTO;
import com.huseyn.gameserverplathform.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserRequestDTO request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return user;
    }
    public UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail());
    }
}