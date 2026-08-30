package com.huseyn.gameserverplathform.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyEmailCodeDTO {
    @NotBlank
    private String email;
    @NotBlank
    private String code;
}
