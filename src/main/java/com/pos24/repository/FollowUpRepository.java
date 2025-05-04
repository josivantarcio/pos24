package com.pos24.repository;

import com.pos24.model.FollowUp;
import com.pos24.model.FollowUp.Resultado;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FollowUpRepository extends BaseRepository<FollowUp> {
    List<FollowUp> findByChamadoId(Long chamadoId);
    List<FollowUp> findByResultado(Resultado resultado);
    List<FollowUp> findByDataEnvioBetween(LocalDateTime inicio, LocalDateTime fim);
} 