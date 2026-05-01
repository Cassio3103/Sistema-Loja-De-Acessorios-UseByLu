package com.usebylu.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoPatchDTO {

    private String nomeProduto;
    private double preco;

    public ProdutoPatchDTO(String nomeProduto, double preco) {
        this.nomeProduto = nomeProduto;
        this.preco = preco;
    }
}
