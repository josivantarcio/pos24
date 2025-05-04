package com.pos24.repository;

import com.pos24.model.Cliente;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends BaseRepository<Cliente> {
    List<Cliente> findByNomeContaining(String nome);
    Optional<Cliente> findByEmail(String email);
    boolean existsByEmail(String email);
} 