package com.pos24.mapper;

import com.pos24.dto.FollowUpDTO;
import com.pos24.model.FollowUp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FollowUpMapper extends BaseMapper<FollowUpDTO, FollowUp> {
    
    @Override
    FollowUp toEntity(FollowUpDTO dto);
    
    @Override
    FollowUpDTO toDTO(FollowUp entity);
    
    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(FollowUpDTO dto, @MappingTarget FollowUp entity);
} 