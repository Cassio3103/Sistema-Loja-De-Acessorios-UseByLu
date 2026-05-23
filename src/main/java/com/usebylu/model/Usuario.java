package com.usebylu.model;

import com.usebylu.auxiliar.Endereco;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Data
@Entity
@Table(name = "usuario")
public class Usuario extends EntidadeBasica implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String senha;
    @Column(nullable = false, unique = true)
    private String cpf;
    private long telefone;
    @Column(nullable = false, unique = true)
    private String email;
    @Embedded
    private Endereco endereco;
    private LocalDate dataIngresso;
    private UsuarioRole role;

    public Usuario(){}

    public Usuario(Long id, String nome, String senha, String cpf,
                   long telefone, String email, Endereco endereco, LocalDate dataDeIngresso, UsuarioRole role){
        this.id = id;
        this.senha = senha;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.dataIngresso = dataDeIngresso;
        this.role = role;
    }

    public void setCpf(String cpf) {
        if(cpf.length() != 11){
            throw new IllegalArgumentException("O CPF está inválido!");
        }

        this.cpf = cpf;
    }

    public void setEmail(String email){
        if(email == null || email.equals(" ")){
            throw new IllegalArgumentException("O email não pode ser nulo");
        }
        this.email = email;
    }

    public void setNome(String nome){
        if(nome == null || nome.equals(" ")){
            throw new IllegalArgumentException("O nome não pode ser nulo");
        }
        this.nome = nome;
    }

    public void setSenha(String senha){
        if(senha == null || senha.equals(" ")){
            throw new IllegalArgumentException("A senha não pode ser nula");
        }
        this.senha = senha;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if(this.role == UsuarioRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ADMINISTRADOR_ROLE"),
                    new SimpleGrantedAuthority("USUARIO_ROLE"));
        } else {
            return List.of(new SimpleGrantedAuthority("USUARIO_ROLE"));
        }
    }

    @Override
    public @Nullable String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }
}
