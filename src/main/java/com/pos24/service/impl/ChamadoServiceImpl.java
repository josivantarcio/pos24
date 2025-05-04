package com.pos24.service.impl;

import com.pos24.dto.ChamadoDTO;
import com.pos24.exception.ResourceNotFoundException;
import com.pos24.mapper.ChamadoMapper;
import com.pos24.model.Chamado;
import com.pos24.model.Chamado.Status;
import com.pos24.repository.ChamadoRepository;
import com.pos24.service.ChamadoService;
import com.pos24.service.ClienteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChamadoServiceImpl extends BaseServiceImpl<ChamadoDTO, Chamado> implements ChamadoService {
    
    private final ChamadoRepository chamadoRepository;
    private final ClienteService clienteService;
    
    public ChamadoServiceImpl(ChamadoRepository chamadoRepository, ChamadoMapper chamadoMapper,
                            ClienteService clienteService) {
        super(chamadoRepository, chamadoMapper);
        this.chamadoRepository = chamadoRepository;
        this.clienteService = clienteService;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ChamadoDTO> findByClienteId(Long clienteId) {
        return chamadoRepository.findByClienteId(clienteId).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ChamadoDTO> findByStatus(Status status) {
        return chamadoRepository.findByStatus(status).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ChamadoDTO> findByPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return chamadoRepository.findByPeriodo(inicio, fim).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Double findTempoMedioResolucao() {
        return chamadoRepository.findTempoMedioResolucao();
    }
    
    @Override
    @Transactional
    public void fecharChamado(Long id) {
        Chamado chamado = chamadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Chamado não encontrado: " + id));
        
        chamado.setStatus(Chamado.Status.FECHADO);
        chamadoRepository.save(chamado);
    }
    
    @Override
    @Transactional
    public ChamadoDTO create(ChamadoDTO dto) {
        // Verifica se o cliente existe
        clienteService.findById(dto.getClienteId());
        
        return super.create(dto);
    }
} 