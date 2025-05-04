package com.pos24.service;

import com.pos24.dto.ChamadoDTO;
import com.pos24.model.Chamado;
import com.pos24.model.Chamado.Status;

import java.time.LocalDateTime;
import java.util.List;

public interface ChamadoService extends BaseService<ChamadoDTO, Chamado> {
    List<ChamadoDTO> findByClienteId(Long clienteId);
    List<ChamadoDTO> findByStatus(Status status);
    List<ChamadoDTO> findByPeriodo(LocalDateTime inicio, LocalDateTime fim);
    Double findTempoMedioResolucao();
    void fecharChamado(Long id);
} 