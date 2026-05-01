package com.usebylu.controller;

import com.usebylu.dto.ProdutoRequestDTO;
import com.usebylu.dto.ProdutoResponseDTO;
import com.usebylu.service.ProdutoService;
import org.apache.coyote.Response;
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
    public ResponseEntity<ProdutoResponseDTO> adicionarProduto(@RequestBody ProdutoRequestDTO produtoResponseDTO){
        return ResponseEntity
                .ok()
                .body(produtoService.adicionarProduto(produtoResponseDTO));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ProdutoResponseDTO>> listarTodosOsProdutos(){
        return ResponseEntity.ok().body(produtoService.listarTodosOsProdutos());
    }


}
