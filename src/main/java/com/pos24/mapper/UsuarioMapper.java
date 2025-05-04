package com.pos24.mapper;

import com.pos24.dto.UsuarioDTO;
import com.pos24.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UsuarioMapper extends BaseMapper<UsuarioDTO, Usuario> {
    
    @Override
    @Mapping(target = "password", ignore = true)
    Usuario toEntity(UsuarioDTO dto);
    
    @Override
    UsuarioDTO toDTO(Usuario entity);
    
    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "password", ignore = true)
    void updateEntity(UsuarioDTO dto, @MappingTarget Usuario entity);
} 