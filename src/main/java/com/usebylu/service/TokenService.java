package com.usebylu.service;

import com.usebylu.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService extends {

    @Value(
        "${api.security.tokem.secret}"
    )
    private String tokenSecret;



    public String gerarToken(Usuario usuario){
        try{
            Algorithm
        }
    }
}
