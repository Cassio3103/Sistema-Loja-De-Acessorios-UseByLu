package com.usebylu.model;

public enum UsuarioRole {

    CLIENTE("CLIENTE_ROLE"),
    ADMIN("ADMINISTRADOR_ROLE");

    private final String nomeRole;

    UsuarioRole(String nomeRole){
        this.nomeRole = nomeRole;
    }



}
