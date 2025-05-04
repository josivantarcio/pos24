package com.pos24.mapper;

import com.pos24.dto.ClienteDTO;
import com.pos24.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClienteMapper extends BaseMapper<ClienteDTO, Cliente> {
    
    @Override
    Cliente toEntity(ClienteDTO dto);
    
    @Override
    ClienteDTO toDTO(Cliente entity);
    
    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(ClienteDTO dto, @MappingTarget Cliente entity);
} 