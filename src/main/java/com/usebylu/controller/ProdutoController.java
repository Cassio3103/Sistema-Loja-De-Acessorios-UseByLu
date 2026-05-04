package com.usebylu.controller;

import com.usebylu.dto.ProdutoRequestDTO;
import com.usebylu.dto.ProdutoResponseDTO;
import com.usebylu.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @PostMapping("/adicionar")
    public ResponseEntity<ProdutoResponseDTO> adicionarProduto(@RequestBody ProdutoRequestDTO produtoRequestDTO){
        return ResponseEntity
                .ok()
                .body(produtoService.adicionarProduto(produtoRequestDTO));
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<ProdutoResponseDTO> alterarProduto(@RequestBody ProdutoRequestDTO produtoRequestDTO,
                                                             @PathVariable Long produtoId){
        return ResponseEntity
                .ok()
                .body(produtoService.alterarProduto(produtoRequestDTO, produtoId));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ProdutoResponseDTO>> listarTodosOsProdutos(){
        return ResponseEntity.ok().body(produtoService.listarTodosOsProdutos());
    }




}
