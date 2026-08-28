package com.huseyn.gameserverplathform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConnectGameRequestDTO {
    @NotNull
    private Long gameId;
    @NotBlank
    private String accountUsername;
}
