package com.usebylu.service;

import com.usebylu.auxiliar.Categoria;
import com.usebylu.dto.ProdutoRequestDTO;
import com.usebylu.dto.ProdutoResponseDTO;
import com.usebylu.exception.NoChangeException;
import com.usebylu.exception.ProdutoInvalidoException;
import com.usebylu.mapper.ProdutoMapper;
import com.usebylu.model.Produto;
import com.usebylu.repository.ProdutoRepository;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
public class ProdutoService {

    // INJEÇÃO DE DEPENDÊNCIAS

    private ProdutoRepository produtoRepository;
    private ProdutoMapper produtoMapper;

    public ProdutoService(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper){
        super();
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }

    // C-R-U-D

    public ProdutoResponseDTO adicionarProduto(ProdutoRequestDTO produtoRequestDTO){
        Produto produto = new Produto();

        produto.setNomeProduto(produtoRequestDTO.getNomeProduto());
        produto.setCategoria(produtoRequestDTO.getCategoria());
        produto.setPrecoProduto(produtoRequestDTO.getPrecoProduto());

        produtoRepository.save(produto);

        return new ProdutoResponseDTO(
                produto.getNomeProduto(),
                produto.getCategoria(),
                produto.getPrecoProduto()
        );
    }

    public ProdutoResponseDTO alterarProduto(ProdutoRequestDTO produtoRequestDTO, Long produtoId) {
        Produto produto = produtoRepository.findById(produtoId)
                        .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

        validarDadosProduto(produtoRequestDTO.getNomeProduto(),
                            produtoRequestDTO.getPrecoProduto(),
                            produtoRequestDTO.getCategoria());

        produtoMudou(produto, produtoRequestDTO);

        produto.setNomeProduto(produtoRequestDTO.getNomeProduto());
        produto.setPrecoProduto(produtoRequestDTO.getPrecoProduto());
        produto.setCategoria(produtoRequestDTO.getCategoria());

        produtoRepository.save(produto);

        return new ProdutoResponseDTO(
                produto.getNomeProduto(),
                produto.getCategoria(),
                produto.getPrecoProduto()
        );
    }

    public  List<ProdutoResponseDTO> listarTodosOsProdutos(){
        return produtoMapper.toResponseList(produtoRepository.findAll());
    }

    //--------------------------------------------------------------------------------------------------------
    // MÉTODOS AUXILIARES

    public void validarDadosProduto(String nome, double preco, Categoria categoria){
        if(nome.isBlank()){
            throw new ProdutoInvalidoException("O produto não pode conter nome vazio!");
        }
        if(preco < 0.0){
            throw new ProdutoInvalidoException("O produto não pode ter preço negativo!");
        }
        if(!categoria.equals(Categoria.PERFUME) && !categoria.equals(Categoria.JOIA)){
            throw new ProdutoInvalidoException("Categoria de produto inválida!");
        }
    }

    public void produtoMudou(@NonNull Produto produto, @NonNull ProdutoRequestDTO dto){
        boolean naoMudou =
                produto.getNomeProduto().equals(dto.getNomeProduto())
                    &&
                produto.getPrecoProduto() != dto.getPrecoProduto();
        if(naoMudou){
            throw new NoChangeException("Não há mudanças para se atualizar!");
        }
    }

}

