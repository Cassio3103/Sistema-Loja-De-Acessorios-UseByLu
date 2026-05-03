package com.usebylu.service;

import com.usebylu.dto.LoginRequestDTO;
import com.usebylu.dto.LoginResponseDTO;
import com.usebylu.model.Usuario;
import com.usebylu.repository.UsuarioRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class AuthService{

    @Autowired
    UsuarioRepository usuarioRepository;

    private final AuthenticationManager manager;
    private final TokenService tokenService;

    public AuthService(AuthenticationManager manager, TokenService tokenService){
        this.manager = manager;
        this.tokenService = tokenService;
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Authentication tokenAuthentication = new UsernamePasswordAuthenticationToken(
                loginRequestDTO.email(), loginRequestDTO.senha()
        );
        Authentication authentication = manager.authenticate(tokenAuthentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Usuario usuario = (Usuario) authentication.getPrincipal();

        assert usuario != null;
        return new LoginResponseDTO(usuario.getId(), usuario.getEmail(), tokenService.generateToken(usuario));
    }
}
