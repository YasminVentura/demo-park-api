package com.yasmingv.demoparkapi.service;

import com.yasmingv.demoparkapi.repository.Usuariorepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final Usuariorepository usuarioRepository;
}
