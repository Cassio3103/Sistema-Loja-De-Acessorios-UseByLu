package com.usebylu.dto;

import com.usebylu.auxiliar.Categoria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoResponseDTO {

    private String nomeProduto;
    private Categoria categoria;
    private double precoProduto;

    public ProdutoResponseDTO(String nomeProduto, Categoria categoria, double precoProduto) {
        this.nomeProduto = nomeProduto;
        this.categoria = categoria;
        this.precoProduto = precoProduto;
    }


}
