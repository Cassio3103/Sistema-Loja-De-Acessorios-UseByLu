package com.usebylu.controller;

import com.usebylu.dto.LoginRequestDTO;
import com.usebylu.dto.LoginResponseDTO;
import com.usebylu.dto.UsuarioResponseDTO;
import com.usebylu.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> logarUsuario(@RequestBody @Validated LoginRequestDTO loginRequestDTO){
        //var senhaUsuario = new UsernamePasswordAuthenticationToken(loginRequestDTO.email(), loginRequestDTO.senha());
        //var auth = authService.getManager().authenticate(senhaUsuario);

        return ResponseEntity
                .ok()
                .body(authService.login(loginRequestDTO));
    }

}
