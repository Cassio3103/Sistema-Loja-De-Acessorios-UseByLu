package com.usebylu.service;

import com.usebylu.dto.ProdutoRequestDTO;
import com.usebylu.dto.ProdutoResponseDTO;
import com.usebylu.mapper.ProdutoMapper;
import com.usebylu.model.Produto;
import com.usebylu.repository.ProdutoRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
public class ProdutoService {

    // INJEÇÃO DE DEPENDÊNCIAS

    private ProdutoRepository produtoRepository;
    private ProdutoMapper produtoMapper;

    public ProdutoService(ProdutoRepository produtoRepository){
        super();
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }

    // C-R-U-D

    public ProdutoResponseDTO adicionarProduto(ProdutoRequestDTO produtoRequestDTO){
        Produto produto = new Produto();

        produto.setNomeProduto(produtoRequestDTO.getNomeProduto());
        produto.setCategoria(produtoRequestDTO.getCategoria());
        produto.setEstoque(produtoRequestDTO.getEstoque());
        produto.setPrecoProduto(produtoRequestDTO.getPrecoProduto());

        produtoRepository.save(produto);

        return new ProdutoResponseDTO(
                produto.getNomeProduto(),
                produto.getCategoria(),
                produto.getEstoque(),
                produto.getPrecoProduto()
                );
    }


    public  List<ProdutoResponseDTO> listarTodosOsProdutos(){
        return produtoMapper.toResponseList(produtoRepository.findAll());
    }


}
