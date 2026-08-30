package com.huseyn.gameserverplathform.mapper;

import com.huseyn.gameserverplathform.dto.UserGameResponseDTO;
import com.huseyn.gameserverplathform.entity.UserGame;
import org.springframework.stereotype.Component;

@Component
public class UserGameMapper {
    public UserGameResponseDTO toResponseDTO(UserGame userGame){
        return new UserGameResponseDTO(userGame.getId(), userGame.getGame().getId(), userGame.getGame().getName(), userGame.getAccountId(), userGame.getAccountUsername());
    }
}
