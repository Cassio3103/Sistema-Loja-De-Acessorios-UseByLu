package com.usebylu.dto;

import com.usebylu.auxiliar.Endereco;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioPatchDTO {

    private String nome;
    private String senha;
    private Long telefone;
    private String email;
    private Endereco endereco;

    public UsuarioPatchDTO(){}

    public UsuarioPatchDTO(String nome, String senha, Long telefone, String email, Endereco endereco) {
        this.nome = nome;
        this.senha = senha;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }
}
