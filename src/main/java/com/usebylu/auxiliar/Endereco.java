package com.usebylu.auxiliar;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Endereco {

    private String cidade;
    private String bairro;
    private String rua;
    private int numeroCasa;
    private Estado estado;

    public Endereco(){}

    public Endereco(String cidade, String bairro, String rua, int numeroCasa, Estado estado){
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        setNumeroCasa(numeroCasa);
        this.estado = estado;
    }


    public void setNumeroCasa(int numeroCasa){
        if(numeroCasa < 0){
            throw new IllegalArgumentException("O número da sua casa não pode ser negativo!");
        }
        this.numeroCasa= numeroCasa;
    }



}
