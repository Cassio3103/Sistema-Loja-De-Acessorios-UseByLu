package com.usebylu.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UsuarioResponseDTO {

    private Long id;
    private String nomeUsuario;
    private String emailUsuario;
    private LocalDate dataDeIngresso;

    public UsuarioResponseDTO(Long id, String nomeUsuario, String emailUsuario, LocalDate dataDeIngresso){
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.emailUsuario = emailUsuario;
        this.dataDeIngresso = dataDeIngresso;
    }



}
