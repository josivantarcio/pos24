package com.pos24.mapper;

import com.pos24.dto.ClienteDTO;
import com.pos24.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClienteMapper extends BaseMapper<ClienteDTO, Cliente> {
    
    @Override
    Cliente toEntity(ClienteDTO dto);
    
    @Override
    ClienteDTO toDTO(Cliente entity);
    
    @Override
    void updateEntity(ClienteDTO dto, @MappingTarget Cliente entity);
} 