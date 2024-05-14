package com.yasmingv.demoparkapi.web.controller;

import com.yasmingv.demoparkapi.entity.Usuario;
import com.yasmingv.demoparkapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor 
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    private ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
      Usuario user =  usuarioService.salvar(usuario);
      return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Usuario> getById(@PathVariable Long id ) {
        Usuario user =  usuarioService.buscarPorId(id);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{id}")
    private ResponseEntity<Usuario> updatePassword(@PathVariable Long id, @RequestBody Usuario usuario ) {
        Usuario user =  usuarioService.editarSenha(id, usuario.getPassword());
        return ResponseEntity.ok(user);
    }
}