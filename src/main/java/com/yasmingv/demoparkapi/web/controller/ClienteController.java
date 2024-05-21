package com.yasmingv.demoparkapi.web.controller;

import com.yasmingv.demoparkapi.entity.Cliente;
import com.yasmingv.demoparkapi.jwt.JwtUserDetails;
import com.yasmingv.demoparkapi.service.ClienteService;
import com.yasmingv.demoparkapi.service.UsuarioService;
import com.yasmingv.demoparkapi.web.dto.ClienteCreateDto;
import com.yasmingv.demoparkapi.web.dto.ClienteResponseDto;
import com.yasmingv.demoparkapi.web.dto.mapper.ClienteMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Clientes", description = "Contém todas as opereções relativas ao recurso de um cliente")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final UsuarioService usuarioService;

    @PostMapping
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<ClienteResponseDto> create(@RequestBody @Valid ClienteCreateDto dto, @AuthenticationPrincipal JwtUserDetails userDetails) {
        Cliente cliente = ClienteMapper.toCliente(dto);
        cliente.setUsuario(usuarioService.buscarPorId(userDetails.getId()));
        clienteService.salvar(cliente);
        return ResponseEntity.status(201).body(ClienteMapper.toDto(cliente));
    }
}