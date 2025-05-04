package com.pos24.repository;

import com.pos24.model.Chamado;
import com.pos24.model.Chamado.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ChamadoRepository extends BaseRepository<Chamado> {
    List<Chamado> findByClienteId(Long clienteId);
    List<Chamado> findByStatus(Status status);
    
    @Query("SELECT c FROM Chamado c WHERE c.dataAbertura BETWEEN :inicio AND :fim")
    List<Chamado> findByPeriodo(LocalDateTime inicio, LocalDateTime fim);
    
    @Query("SELECT AVG(TIMESTAMPDIFF(HOUR, c.dataAbertura, c.dataFechamento)) FROM Chamado c WHERE c.status = 'FECHADO'")
    Double findTempoMedioResolucao();
} 