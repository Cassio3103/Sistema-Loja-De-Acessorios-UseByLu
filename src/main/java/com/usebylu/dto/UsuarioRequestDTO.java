package com.usebylu.dto;

import com.usebylu.auxiliar.Endereco;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UsuarioRequestDTO {

    private String nome;
    private String senha;
    private String cpf;
    private long telefone;
    private String email;
    private Endereco endereco;
    private LocalDate dataDeIngresso;

    public UsuarioRequestDTO(){}

    public UsuarioRequestDTO(String nome, String senha, String cpf, long telefone, String email,
                             Endereco endereco, LocalDate dataDeIngresso){
        this.nome = nome;
        this.senha = senha;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.dataDeIngresso = dataDeIngresso;
    }

}
