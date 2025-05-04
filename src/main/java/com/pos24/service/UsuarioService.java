package com.pos24.service;

import com.pos24.dto.UsuarioDTO;
import com.pos24.model.Usuario;

public interface UsuarioService extends BaseService<UsuarioDTO, Usuario> {
    UsuarioDTO findByUsername(String username);
    UsuarioDTO findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
} 