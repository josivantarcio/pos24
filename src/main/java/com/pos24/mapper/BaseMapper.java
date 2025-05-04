package com.pos24.mapper;

import com.pos24.dto.BaseDTO;
import com.pos24.model.BaseEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Interface base para todos os mappers da aplicação.
 * 
 * @param <D> Tipo do DTO que estende BaseDTO
 * @param <E> Tipo da Entidade que estende BaseEntity
 */
public interface BaseMapper<D extends BaseDTO, E extends BaseEntity> {
    
    /**
     * Converte um DTO para uma entidade.
     *
     * @param dto DTO a ser convertido
     * @return Entidade convertida
     */
    E toEntity(D dto);
    
    /**
     * Converte uma entidade para um DTO.
     *
     * @param entity Entidade a ser convertida
     * @return DTO convertido
     */
    D toDTO(E entity);
    
    /**
     * Atualiza uma entidade existente com os dados de um DTO.
     *
     * @param dto DTO com os novos dados
     * @param entity Entidade a ser atualizada
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(D dto, @MappingTarget E entity);
} 