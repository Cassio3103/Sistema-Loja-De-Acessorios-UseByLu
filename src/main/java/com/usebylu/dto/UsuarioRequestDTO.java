package com.usebylu.dto;

import com.usebylu.auxiliar.Endereco;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UsuarioRequestDTO {

    private String nomeUsuario;
    private String senha;
    private int cpfUsuario;
    private int telefoneUsuario;
    private String emailUsuario;
    private Endereco enderecoUsuario;
    private LocalDate dataDeIngresso;

    public UsuarioRequestDTO(String nomeUsuario, String senha, int cpfUsuario, int telefoneUsuario, String emailUsuario,
                             Endereco enderecoUsuario, LocalDate dataDeIngresso){
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.cpfUsuario = cpfUsuario;
        this.telefoneUsuario = telefoneUsuario;
        this.emailUsuario = emailUsuario;
        this.enderecoUsuario = enderecoUsuario;
        this.dataDeIngresso = dataDeIngresso;
    }

}
