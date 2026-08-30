package com.huseyn.gameserverplathform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserGameResponseDTO {
    private Long id;
    private Long gameId;
    private String gameName;
    private String accountId;
    private String accountUsername;
}
