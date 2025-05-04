package com.pos24.mapper;

import com.pos24.dto.BaseDTO;
import com.pos24.model.BaseEntity;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

public interface BaseMapper<D extends BaseDTO, E extends BaseEntity> {
    E toEntity(D dto);
    D toDTO(E entity);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(D dto, @MappingTarget E entity);
} 