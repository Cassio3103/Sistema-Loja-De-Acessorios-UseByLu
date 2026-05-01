package com.usebylu.model;

import com.usebylu.auxiliar.Categoria;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long produtoId;
    private String nomeProduto;
    private Categoria categoria;
    private int estoque;
    private double precoProduto;

    public Produto(Long produtoId, String nomeProduto, Categoria categoria, int estoque, double precoProduto){
        this.produtoId = produtoId;
        this.nomeProduto = nomeProduto;
        this.categoria = categoria;
        this.estoque = estoque;
        this.precoProduto = precoProduto;
    }


}
