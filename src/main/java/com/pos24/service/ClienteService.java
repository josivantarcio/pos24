package com.pos24.service;

import com.pos24.dto.ClienteDTO;
import com.pos24.model.Cliente;

import java.util.List;

public interface ClienteService extends BaseService<ClienteDTO, Cliente> {
    List<ClienteDTO> findByNomeContaining(String nome);
    ClienteDTO findByEmail(String email);
    boolean existsByEmail(String email);
} 