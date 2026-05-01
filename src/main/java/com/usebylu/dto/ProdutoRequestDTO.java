package com.usebylu.dto;

import com.usebylu.auxiliar.Categoria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoRequestDTO {

    private String nomeProduto;
    private Categoria categoria;
    private int estoque;
    private double precoProduto;

    public ProdutoRequestDTO(String nomeProduto, Categoria categoria, int estoque, double precoProduto) {
        this.nomeProduto = nomeProduto;
        this.categoria = categoria;
        this.estoque = estoque;
        this.precoProduto = precoProduto;
    }


}
