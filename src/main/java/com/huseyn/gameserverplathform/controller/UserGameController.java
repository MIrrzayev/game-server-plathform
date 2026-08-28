package com.huseyn.gameserverplathform.controller;

import com.huseyn.gameserverplathform.dto.ConnectGameRequestDTO;
import com.huseyn.gameserverplathform.dto.UserGameResponseDTO;
import com.huseyn.gameserverplathform.service.UserGameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-games")
@RequiredArgsConstructor
public class UserGameController {
    private final UserGameService userGameService;
    @PostMapping
    public UserGameResponseDTO connectGame(Authentication authentication, @Valid @RequestBody ConnectGameRequestDTO request){
        return userGameService.connectGame(authentication.getName(), request);
    }
    @GetMapping
    public List<UserGameResponseDTO> getGames(Authentication authentication){
        return userGameService.getUserGames(authentication.getName());
    }
}
