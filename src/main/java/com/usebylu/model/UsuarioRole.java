package com.usebylu.model;

public enum UsuarioRole {

    CLIENTE("CLIENTE"),
    ADMIN("ADMINISTRADOR");

    private final String nomeRole;

    UsuarioRole(String nomeRole){
        this.nomeRole = nomeRole;
    }



}
