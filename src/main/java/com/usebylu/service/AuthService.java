package com.usebylu.service;

import org.antlr.v4.runtime.Token;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager manager;
    private final TokenService tokenService;

    public AuthService(AuthenticationManager manager, TokenService tokenService){
        this.manager = manager;
        this.tokenService = tokenService;
    }



}
