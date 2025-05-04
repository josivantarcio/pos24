package com.pos24.mapper;

import com.pos24.dto.ChamadoDTO;
import com.pos24.model.Chamado;
import com.pos24.model.Cliente;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class ChamadoMapper implements BaseMapper<ChamadoDTO, Chamado> {

    @Override
    public Chamado toEntity(ChamadoDTO dto) {
        if (dto == null) {
            return null;
        }

        Chamado chamado = Chamado.builder()
            .descricao(dto.getDescricao())
            .status(dto.getStatus())
            .dataAbertura(dto.getDataAbertura() != null ? dto.getDataAbertura() : LocalDateTime.now())
            .dataFechamento(dto.getDataFechamento())
            .build();

        if (dto.getClienteId() != null) {
            Cliente cliente = new Cliente();
            cliente.setId(dto.getClienteId());
            chamado.setCliente(cliente);
        }

        return chamado;
    }

    @Override
    public ChamadoDTO toDTO(Chamado entity) {
        if (entity == null) {
            return null;
        }

        ChamadoDTO dto = ChamadoDTO.builder()
            .id(entity.getId())
            .descricao(entity.getDescricao())
            .status(entity.getStatus())
            .dataAbertura(entity.getDataAbertura())
            .dataFechamento(entity.getDataFechamento())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())
            .build();

        if (entity.getCliente() != null) {
            dto.setClienteId(entity.getCliente().getId());
            dto.setClienteNome(entity.getCliente().getNome());
        }

        return dto;
    }

    @Override
    public void updateEntity(ChamadoDTO dto, @MappingTarget Chamado entity) {
        if (dto == null || entity == null) {
            return;
        }

        if (dto.getDescricao() != null) {
            entity.setDescricao(dto.getDescricao());
        }
        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        }
        if (dto.getDataAbertura() != null) {
            entity.setDataAbertura(dto.getDataAbertura());
        }
        if (dto.getDataFechamento() != null) {
            entity.setDataFechamento(dto.getDataFechamento());
        }
        if (dto.getClienteId() != null) {
            Cliente cliente = new Cliente();
            cliente.setId(dto.getClienteId());
            entity.setCliente(cliente);
        }
    }
} 