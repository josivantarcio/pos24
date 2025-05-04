package com.pos24.service;

import com.pos24.dto.FollowUpDTO;
import com.pos24.model.FollowUp;
import com.pos24.model.FollowUp.Resultado;

import java.time.LocalDateTime;
import java.util.List;

public interface FollowUpService extends BaseService<FollowUpDTO, FollowUp> {
    List<FollowUpDTO> findByChamadoId(Long chamadoId);
    List<FollowUpDTO> findByResultado(Resultado resultado);
    List<FollowUpDTO> findByPeriodo(LocalDateTime inicio, LocalDateTime fim);
    void registrarResultado(Long id, Resultado resultado);
} 