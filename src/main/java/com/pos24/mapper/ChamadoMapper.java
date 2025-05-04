package com.pos24.mapper;

import com.pos24.dto.ChamadoDTO;
import com.pos24.model.Chamado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ChamadoMapper extends BaseMapper<ChamadoDTO, Chamado> {
    
    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "followUps", ignore = true)
    @Mapping(target = "cliente", source = "clienteId")
    Chamado toEntity(ChamadoDTO dto);
    
    @Override
    @Mapping(target = "followUps", ignore = true)
    @Mapping(target = "clienteId", source = "cliente.id")
    @Mapping(target = "clienteNome", source = "cliente.nome")
    ChamadoDTO toDTO(Chamado entity);
    
    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "followUps", ignore = true)
    @Mapping(target = "cliente", source = "clienteId")
    void updateEntity(ChamadoDTO dto, @MappingTarget Chamado entity);
} 