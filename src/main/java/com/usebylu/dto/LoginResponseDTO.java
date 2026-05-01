package com.usebylu.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {

    private Long id;
    private String email;
    private String token;

    public LoginResponseDTO(Long id, String email, String token){
        this.id = id;
        this.email = email;
        this.token = token;
    }

}
