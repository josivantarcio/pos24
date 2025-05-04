package com.pos24.service.impl;

import com.pos24.dto.ClienteDTO;
import com.pos24.exception.ResourceNotFoundException;
import com.pos24.mapper.ClienteMapper;
import com.pos24.model.Cliente;
import com.pos24.repository.ClienteRepository;
import com.pos24.service.ClienteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl extends BaseServiceImpl<ClienteDTO, Cliente> implements ClienteService {
    
    private final ClienteRepository clienteRepository;
    
    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        super(clienteRepository, clienteMapper);
        this.clienteRepository = clienteRepository;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ClienteDTO> findByNomeContaining(String nome) {
        return clienteRepository.findByNomeContaining(nome).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public ClienteDTO findByEmail(String email) {
        return clienteRepository.findByEmail(email)
                .map(mapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com email: " + email));
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return clienteRepository.existsByEmail(email);
    }
} 