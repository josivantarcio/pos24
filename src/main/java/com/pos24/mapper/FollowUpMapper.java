package com.pos24.mapper;

import com.pos24.dto.FollowUpDTO;
import com.pos24.model.Chamado;
import com.pos24.model.FollowUp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FollowUpMapper extends BaseMapper<FollowUpDTO, FollowUp> {
    
    @Override
    @Mapping(target = "chamado", source = "chamadoId", qualifiedByName = "chamadoFromId")
    FollowUp toEntity(FollowUpDTO dto);
    
    @Override
    @Mapping(target = "chamadoId", source = "chamado.id")
    FollowUpDTO toDTO(FollowUp entity);
    
    @Override
    @Mapping(target = "chamado", source = "chamadoId", qualifiedByName = "chamadoFromId")
    void updateEntity(FollowUpDTO dto, @MappingTarget FollowUp entity);
    
    @Named("chamadoFromId")
    default Chamado chamadoFromId(Long id) {
        if (id == null) {
            return null;
        }
        Chamado chamado = new Chamado();
        chamado.setId(id);
        return chamado;
    }
} 