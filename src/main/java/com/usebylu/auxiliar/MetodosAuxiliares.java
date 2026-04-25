package com.usebylu.auxiliar;

import com.usebylu.dto.UsuarioRequestDTO;
import com.usebylu.exception.UsuarioDuplicadoException;
import com.usebylu.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDate;

@ComponentScan
public class MetodosAuxiliares {

    @Autowired
    UsuarioRepository usuarioRepository;

    public void verificarSeExisteUsuario(UsuarioRequestDTO dto){
        if(usuarioRepository.existsByemailUsuario(dto.getEmailUsuario())) {
            throw new UsuarioDuplicadoException("Usuário já cadastrado no sistema!");
        }
    }

    public void verificarDataDeIngresso(UsuarioRequestDTO dto){
        if(dto.getDataDeIngresso() == null){
            throw new IllegalArgumentException("Usuário com data de ingresso obrigatória!");
        } else if(dto.getDataDeIngresso().isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Usuário com data de ingresso inválida!");
        }
    }

}
