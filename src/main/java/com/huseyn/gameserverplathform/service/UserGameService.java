package com.huseyn.gameserverplathform.service;

import com.huseyn.gameserverplathform.dto.ConnectGameRequestDTO;
import com.huseyn.gameserverplathform.dto.UserGameResponseDTO;
import com.huseyn.gameserverplathform.entity.Game;
import com.huseyn.gameserverplathform.entity.User;
import com.huseyn.gameserverplathform.entity.UserGame;
import com.huseyn.gameserverplathform.exception.ResourceNotFoundException;
import com.huseyn.gameserverplathform.mapper.UserGameMapper;
import com.huseyn.gameserverplathform.repository.GameRepository;
import com.huseyn.gameserverplathform.repository.UserGameRepository;
import com.huseyn.gameserverplathform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserGameService {
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final UserGameRepository userGameRepository;
    private final UserGameMapper userGameMapper;
    public UserGameResponseDTO connectGame(String username, ConnectGameRequestDTO request){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Game game = gameRepository.findById(request.getGameId()).orElseThrow(() -> new ResourceNotFoundException("Game not found"));
        if(userGameRepository.existsByUserIdAndGameId(user.getId(), game.getId())){
            throw new RuntimeException("Game already connected");
        }
        UserGame userGame = new UserGame();
        userGame.setUser(user);
        userGame.setGame(game);
        userGame.setAccountUsername(request.getAccountUsername());
        UserGame saved = userGameRepository.save(userGame);
        return userGameMapper.toResponseDTO(saved);
    }
    public List<UserGameResponseDTO> getUserGames(String username){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return userGameRepository.findByUserId(user.getId()).stream().map(userGameMapper::toResponseDTO).toList();
    }
}
