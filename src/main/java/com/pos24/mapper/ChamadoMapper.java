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
    @Mapping(target = "descricao", source = "descricao")
    @Mapping(target = "dataAbertura", source = "dataAbertura")
    @Mapping(target = "dataFechamento", source = "dataFechamento")
    @Mapping(target = "status", source = "status")
    Chamado toEntity(ChamadoDTO dto);
    
    @Override
    @Mapping(target = "clienteId", expression = "java(entity.getCliente() != null ? entity.getCliente().getId() : null)")
    @Mapping(target = "clienteNome", expression = "java(entity.getCliente() != null ? entity.getCliente().getNome() : null)")
    @Mapping(target = "descricao", source = "descricao")
    @Mapping(target = "dataAbertura", source = "dataAbertura")
    @Mapping(target = "dataFechamento", source = "dataFechamento")
    @Mapping(target = "status", source = "status")
    ChamadoDTO toDTO(Chamado entity);
    
    @Override
    @Mapping(target = "cliente", source = "clienteId", qualifiedByName = "clienteFromId")
    @Mapping(target = "descricao", source = "descricao")
    @Mapping(target = "dataAbertura", source = "dataAbertura")
    @Mapping(target = "dataFechamento", source = "dataFechamento")
    @Mapping(target = "status", source = "status")
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