package com.yasmingv.demoparkapi.web.controller;

import com.yasmingv.demoparkapi.entity.Usuario;
import com.yasmingv.demoparkapi.service.UsuarioService;
import com.yasmingv.demoparkapi.web.dto.UsuarioCreateDto;
import com.yasmingv.demoparkapi.web.dto.UsuarioResponseDto;
import com.yasmingv.demoparkapi.web.dto.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor 
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    private ResponseEntity<UsuarioResponseDto> create(@RequestBody UsuarioCreateDto createDto) {
      Usuario user =  usuarioService.salvar(UsuarioMapper.toUsuario(createDto));
      return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));
    }

    @GetMapping("/{id}")
    private ResponseEntity<UsuarioResponseDto> getById(@PathVariable Long id ) {
        Usuario user =  usuarioService.buscarPorId(id);
        return ResponseEntity.ok(UsuarioMapper.toDto(user));
    }

    @PatchMapping("/{id}")
    private ResponseEntity<Usuario> updatePassword(@PathVariable Long id, @RequestBody Usuario usuario ) {
        Usuario user =  usuarioService.editarSenha(id, usuario.getPassword());
        return ResponseEntity.ok(user);
    }

    @GetMapping
    private ResponseEntity<List<Usuario>> getAll() {
        List<Usuario> users =  usuarioService.buscarTodos();
        return ResponseEntity.ok(users);
    }
}