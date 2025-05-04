package com.pos24.mapper;

import com.pos24.dto.UsuarioDTO;
import com.pos24.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuarioMapper extends BaseMapper<UsuarioDTO, Usuario> {
    
    @Override
    Usuario toEntity(UsuarioDTO dto);
    
    @Override
    UsuarioDTO toDTO(Usuario entity);
    
    @Override
    void updateEntity(UsuarioDTO dto, @MappingTarget Usuario entity);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Usuario toEntityWithPassword(UsuarioDTO dto);
} 