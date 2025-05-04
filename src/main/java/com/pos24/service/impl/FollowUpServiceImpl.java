package com.pos24.service.impl;

import com.pos24.dto.FollowUpDTO;
import com.pos24.exception.ResourceNotFoundException;
import com.pos24.mapper.FollowUpMapper;
import com.pos24.model.FollowUp;
import com.pos24.model.FollowUp.Resultado;
import com.pos24.repository.FollowUpRepository;
import com.pos24.service.ChamadoService;
import com.pos24.service.FollowUpService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowUpServiceImpl extends BaseServiceImpl<FollowUpDTO, FollowUp> implements FollowUpService {
    
    private final FollowUpRepository followUpRepository;
    private final ChamadoService chamadoService;
    
    public FollowUpServiceImpl(FollowUpRepository followUpRepository, FollowUpMapper followUpMapper,
                             ChamadoService chamadoService) {
        super(followUpRepository, followUpMapper);
        this.followUpRepository = followUpRepository;
        this.chamadoService = chamadoService;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<FollowUpDTO> findByChamadoId(Long chamadoId) {
        return followUpRepository.findByChamadoId(chamadoId).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<FollowUpDTO> findByResultado(Resultado resultado) {
        return followUpRepository.findByResultado(resultado).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<FollowUpDTO> findByPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return followUpRepository.findByDataEnvioBetween(inicio, fim).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public void registrarResultado(Long id, Resultado resultado) {
        FollowUp followUp = followUpRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FollowUp não encontrado: " + id));
        
        followUp.setResultado(resultado);
        
        if (resultado == Resultado.RESOLVIDO) {
            chamadoService.fecharChamado(followUp.getChamado().getId());
            followUp.setTempoAberturaFechamento(Duration.between(
                    followUp.getChamado().getDataAbertura(),
                    LocalDateTime.now()
            ));
        }
        
        followUpRepository.save(followUp);
    }
    
    @Override
    @Transactional
    public FollowUpDTO create(FollowUpDTO dto) {
        // Verifica se o chamado existe
        chamadoService.findById(dto.getChamadoId());
        
        return super.create(dto);
    }
} 