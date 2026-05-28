package com.usebylu.mapper;

import com.usebylu.auxiliar.Categoria;
import com.usebylu.dto.ProdutoPatchDTO;
import com.usebylu.dto.ProdutoRequestDTO;
import com.usebylu.dto.ProdutoResponseDTO;
import com.usebylu.model.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-26T12:12:15-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.11 (Oracle Corporation)"
)
@Component
public class ProdutoMapperImpl implements ProdutoMapper {

    @Override
    public Produto toEntity(ProdutoRequestDTO produtoRequestDTO) {
        if ( produtoRequestDTO == null ) {
            return null;
        }

        Produto produto = new Produto();

        produto.setNomeProduto( produtoRequestDTO.getNomeProduto() );
        produto.setCategoria( produtoRequestDTO.getCategoria() );
        produto.setPrecoProduto( produtoRequestDTO.getPrecoProduto() );

        return produto;
    }

    @Override
    public ProdutoResponseDTO toResponse(Produto entity) {
        if ( entity == null ) {
            return null;
        }

        String nomeProduto = null;
        Categoria categoria = null;
        double precoProduto = 0.0d;

        nomeProduto = entity.getNomeProduto();
        categoria = entity.getCategoria();
        precoProduto = entity.getPrecoProduto();

        ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO( nomeProduto, categoria, precoProduto );

        return produtoResponseDTO;
    }

    @Override
    public List<ProdutoResponseDTO> toResponseList(List<Produto> entity) {
        if ( entity == null ) {
            return null;
        }

        List<ProdutoResponseDTO> list = new ArrayList<ProdutoResponseDTO>( entity.size() );
        for ( Produto produto : entity ) {
            list.add( toResponse( produto ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromPut(ProdutoRequestDTO produtoRequestDTO, Produto entity) {
        if ( produtoRequestDTO == null ) {
            return;
        }

        entity.setNomeProduto( produtoRequestDTO.getNomeProduto() );
        entity.setCategoria( produtoRequestDTO.getCategoria() );
        entity.setPrecoProduto( produtoRequestDTO.getPrecoProduto() );
    }

    @Override
    public void updateEntityFromPatch(ProdutoPatchDTO produtoPatchDTO, Produto entity) {
        if ( produtoPatchDTO == null ) {
            return;
        }

        if ( produtoPatchDTO.getNomeProduto() != null ) {
            entity.setNomeProduto( produtoPatchDTO.getNomeProduto() );
        }
    }
}
