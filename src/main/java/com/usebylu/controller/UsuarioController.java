package com.usebylu.controller;

import com.usebylu.dto.UsuarioRequestDTO;
import com.usebylu.dto.UsuarioResponseDTO;
import com.usebylu.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/atualizar")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable Long usuario_id, @RequestBody
                                                               UsuarioRequestDTO usuarioRequestDTO){
        return ResponseEntity
                .ok()
                .body(usuarioService.atualizarUsuario(usuario_id, usuarioRequestDTO));
    }




}
