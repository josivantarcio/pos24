package com.pos24.mapper;

import com.pos24.dto.FollowUpDTO;
import com.pos24.model.FollowUp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface FollowUpMapper extends BaseMapper<FollowUpDTO, FollowUp> {
    
    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "chamado", source = "chamadoId")
    FollowUp toEntity(FollowUpDTO dto);
    
    @Override
    @Mapping(target = "chamadoId", source = "chamado.id")
    FollowUpDTO toDTO(FollowUp entity);
    
    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "chamado", source = "chamadoId")
    void updateEntity(FollowUpDTO dto, @MappingTarget FollowUp entity);
} 