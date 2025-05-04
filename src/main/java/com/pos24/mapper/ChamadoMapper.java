package com.pos24.mapper;

import com.pos24.dto.ChamadoDTO;
import com.pos24.model.Chamado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ChamadoMapper extends BaseMapper<ChamadoDTO, Chamado> {
    
    @Override
    Chamado toEntity(ChamadoDTO dto);
    
    @Override
    ChamadoDTO toDTO(Chamado entity);
    
    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(ChamadoDTO dto, @MappingTarget Chamado entity);
} 