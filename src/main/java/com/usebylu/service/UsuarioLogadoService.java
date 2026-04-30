package com.usebylu.service;

import com.usebylu.exception.UsuarioNaoEncontradoException;
import com.usebylu.model.Usuario;
import com.usebylu.repository.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/* CLASS PARA PEGAR USUÁRIO QUE JÁ ESTÁ LOGADO NO BANCO*/

public abstract class UsuarioLogadoService {

    private final UsuarioRepository usuarioRepository;

    protected UsuarioLogadoService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    protected Usuario getUsuarioLogado(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth == null){
            throw new RuntimeException("Usuário não autenticado");
        }

        if(auth.getPrincipal() instanceof Usuario){
            return (Usuario) auth.getPrincipal();
        }


        return usuarioRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado!"));
    }


}
