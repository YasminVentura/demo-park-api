package com.yasmingv.demoparkapi.repository;

import com.yasmingv.demoparkapi.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Usuariorepository extends JpaRepository<Usuario, Long> {
}
