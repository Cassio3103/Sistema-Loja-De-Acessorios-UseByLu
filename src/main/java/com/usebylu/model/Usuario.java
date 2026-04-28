package com.usebylu.model;

import com.usebylu.auxiliar.Endereco;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario extends EntidadeBasica implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuario_id;
    private String nomeUsuario;
    private String senha;
    @Column(nullable = false, unique = true)
    private int cpfUsuario;
    private int telefoneUsuario;
    private String emailUsuario;
    @Embedded
    private Endereco enderecoUsuario;
    private LocalDate dataIngresso;

    public Usuario(){}

    public Usuario(Long usuario_id, String nomeUsuario, String senha, int cpfUsuario,
                   int telefoneUsuario, String emailUsuario, Endereco enderecoUsuario, LocalDate dataIngresso){
        this.usuario_id = usuario_id;
        this.senha = senha;
        this.telefoneUsuario = telefoneUsuario;
        this.emailUsuario = emailUsuario;
        this.enderecoUsuario = enderecoUsuario;
        this.dataIngresso = dataIngresso;
    }

    public void setCpfUsuario(int cpfUsuario) {
        if(cpfUsuario != 11){
            throw new IllegalArgumentException("O cpf não pode ser nulo!");
        }
        this.cpfUsuario = cpfUsuario;
    }

    public void setEmailUsuario(String emailUsuario){
        if(emailUsuario == null || emailUsuario.equals(" ")){
            throw new IllegalArgumentException("O email não pode ser nulo");
        }
        this.emailUsuario = emailUsuario;
    }

    public void setNomeUsuario(String nomeUsuario){
        if(nomeUsuario == null || nomeUsuario.equals(" ")){
            throw new IllegalArgumentException("O nome não pode ser nulo");
        }
        this.nomeUsuario = nomeUsuario;
    }

    public void setSenha(String senha){
        if(senha == null || senha.equals(" ")){
            throw new IllegalArgumentException("A senha não pode ser nula");
        }
        this.senha = senha;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
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
