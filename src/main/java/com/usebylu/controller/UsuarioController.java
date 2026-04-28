package com.usebylu.controller;

import com.usebylu.dto.UsuarioRequestDTO;
import com.usebylu.dto.UsuarioResponseDTO;
import com.usebylu.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO){
        return ResponseEntity
                .ok()
                .body(usuarioService.cadastrarUsuario(usuarioRequestDTO));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable Long usuario_id, @RequestBody
                                                               UsuarioRequestDTO usuarioRequestDTO)
                                                                throws AccessDeniedException {
        return ResponseEntity
                .ok()
                .body(usuarioService.atualizarUsuario(usuario_id, usuarioRequestDTO));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuario(@PathVariable Long usuario_id)
                                                            throws AccessDeniedException {
        return ResponseEntity
                .ok()
                .body(usuarioService.buscarUsuario(usuario_id));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long usuario_id)
                                                                throws AccessDeniedException{
        usuarioService.deletarUsuario(usuario_id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/ativar/{id}")
    public ResponseEntity<Void> ativarUsuario(@PathVariable Long usuario_id){
        usuarioService.ativarUsuario(usuario_id);
        return ResponseEntity
                .noContent()
                .build();
    }




}
