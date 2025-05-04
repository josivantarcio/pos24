package com.pos24.service;

import com.pos24.dto.BaseDTO;
import com.pos24.model.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseService<D extends BaseDTO, E extends BaseEntity> {
    D create(D dto);
    D update(Long id, D dto);
    void delete(Long id);
    D findById(Long id);
    List<D> findAll();
    Page<D> findAll(Pageable pageable);
} 