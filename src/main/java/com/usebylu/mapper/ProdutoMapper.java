package com.usebylu.mapper;

import com.usebylu.dto.ProdutoPatchDTO;
import com.usebylu.dto.ProdutoRequestDTO;
import com.usebylu.dto.ProdutoResponseDTO;
import com.usebylu.model.Produto;
import org.mapstruct.*;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    // 🔹 DTO → Entity (criação)
    Produto toEntity(ProdutoRequestDTO produtoRequestDTO);

    // 🔹 Entity → DTO (resposta)
    ProdutoResponseDTO toResponse(Produto entity);

    // 🔹 Lista de Entity → Lista de DTO
    List<ProdutoResponseDTO> toResponseList(List<Produto> entity);

    // 🔹 Atualização completa (PUT)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    void updateEntityFromPut(ProdutoRequestDTO produtoRequestDTO, @MappingTarget Produto entity);

    // 🔹 Atualização parcial (PATCH)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromPatch(ProdutoPatchDTO produtoPatchDTO, @MappingTarget Produto entity);


}
