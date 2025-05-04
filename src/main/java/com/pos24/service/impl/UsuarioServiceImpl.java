package com.pos24.service.impl;

import com.pos24.dto.UsuarioDTO;
import com.pos24.exception.ResourceNotFoundException;
import com.pos24.mapper.UsuarioMapper;
import com.pos24.model.Usuario;
import com.pos24.repository.UsuarioRepository;
import com.pos24.service.UsuarioService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<UsuarioDTO, Usuario> implements UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper,
                            PasswordEncoder passwordEncoder) {
        super(usuarioRepository, usuarioMapper);
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    @Transactional(readOnly = true)
    public UsuarioDTO findByUsername(String username) {
        return usuarioRepository.findByUsername(username)
                .map(mapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: " + username));
    }
    
    @Override
    @Transactional(readOnly = true)
    public UsuarioDTO findByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .map(mapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com email: " + email));
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByUsername(String username) {
        return usuarioRepository.existsByUsername(username);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }
    
    @Override
    @Transactional
    public UsuarioDTO create(UsuarioDTO dto) {
        if (existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("Username já existe: " + dto.getUsername());
        }
        if (existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email já existe: " + dto.getEmail());
        }
        
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        return super.create(dto);
    }
    
    @Override
    @Transactional
    public UsuarioDTO update(Long id, UsuarioDTO dto) {
        Usuario existing = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: " + id));
        
        if (!existing.getUsername().equals(dto.getUsername()) && existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("Username já existe: " + dto.getUsername());
        }
        if (!existing.getEmail().equals(dto.getEmail()) && existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email já existe: " + dto.getEmail());
        }
        
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        } else {
            dto.setPassword(existing.getPassword());
        }
        
        return super.update(id, dto);
    }
} 