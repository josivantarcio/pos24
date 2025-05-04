package com.pos24.mapper;

import com.pos24.dto.ChamadoDTO;
import com.pos24.model.Chamado;
import com.pos24.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ChamadoMapper extends BaseMapper<ChamadoDTO, Chamado> {
    
    @Override
    @Mapping(target = "cliente", source = "clienteId", qualifiedByName = "clienteFromId")
    Chamado toEntity(ChamadoDTO dto);
    
    @Override
    @Mapping(target = "clienteId", source = "cliente.id")
    @Mapping(target = "clienteNome", source = "cliente.nome")
    ChamadoDTO toDTO(Chamado entity);
    
    @Override
    @Mapping(target = "cliente", source = "clienteId", qualifiedByName = "clienteFromId")
    void updateEntity(ChamadoDTO dto, @MappingTarget Chamado entity);
    
    @Named("clienteFromId")
    default Cliente clienteFromId(Long id) {
        if (id == null) {
            return null;
        }
        Cliente cliente = new Cliente();
        cliente.setId(id);
        return cliente;
    }
} 