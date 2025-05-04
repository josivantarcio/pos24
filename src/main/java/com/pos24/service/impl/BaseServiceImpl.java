package com.pos24.service.impl;

import com.pos24.dto.BaseDTO;
import com.pos24.exception.ResourceNotFoundException;
import com.pos24.mapper.BaseMapper;
import com.pos24.model.BaseEntity;
import com.pos24.repository.BaseRepository;
import com.pos24.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseServiceImpl<D extends BaseDTO, E extends BaseEntity> implements BaseService<D, E> {
    
    protected final BaseRepository<E> repository;
    protected final BaseMapper<D, E> mapper;
    
    protected BaseServiceImpl(BaseRepository<E> repository, BaseMapper<D, E> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    
    @Override
    @Transactional
    public D create(D dto) {
        E entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDTO(entity);
    }
    
    @Override
    @Transactional
    public D update(Long id, D dto) {
        E entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado com id: " + id));
        
        mapper.updateEntity(dto, entity);
        entity = repository.save(entity);
        
        return mapper.toDTO(entity);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public D findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado com id: " + id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<D> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<D> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDTO);
    }
} 