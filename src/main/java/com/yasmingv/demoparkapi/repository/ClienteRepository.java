package com.yasmingv.demoparkapi.repository;

import com.yasmingv.demoparkapi.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
