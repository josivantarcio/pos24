package com.pos24.mapper;

import com.pos24.dto.ClienteDTO;
import com.pos24.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ClienteMapper extends BaseMapper<ClienteDTO, Cliente> {
    
    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "chamados", ignore = true)
    Cliente toEntity(ClienteDTO dto);
    
    @Override
    @Mapping(target = "chamados", ignore = true)
    ClienteDTO toDTO(Cliente entity);
    
    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "chamados", ignore = true)
    void updateEntity(ClienteDTO dto, @MappingTarget Cliente entity);
} 