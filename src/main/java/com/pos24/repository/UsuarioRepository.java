package com.pos24.repository;

import com.pos24.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario> {
    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
} 