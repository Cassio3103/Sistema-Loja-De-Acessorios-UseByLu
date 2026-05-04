package com.usebylu.dto;

import com.usebylu.model.Usuario;
import com.usebylu.model.UsuarioRole;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private LocalDate dataDeIngresso;
    private UsuarioRole role;

    public UsuarioResponseDTO(){}

    public UsuarioResponseDTO(Long id, String nome, String email, LocalDate dataDeIngresso, UsuarioRole role){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataDeIngresso = dataDeIngresso;
        this.role = role;
    }

    public UsuarioResponseDTO(Long id, String nome, String email, LocalDate dataDeIngresso){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataDeIngresso = dataDeIngresso;
    }


}
