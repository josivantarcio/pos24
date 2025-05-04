package com.pos24.controller;

import com.pos24.dto.ChamadoDTO;
import com.pos24.service.ChamadoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ChamadoControllerTest {

    @Mock
    private ChamadoService chamadoService;

    @InjectMocks
    private ChamadoController chamadoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_ShouldReturnCreatedChamado() {
        // Arrange
        ChamadoDTO chamadoDTO = new ChamadoDTO();
        when(chamadoService.create(any(ChamadoDTO.class))).thenReturn(chamadoDTO);

        // Act
        ResponseEntity<ChamadoDTO> response = chamadoController.create(chamadoDTO);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(chamadoService, times(1)).create(any(ChamadoDTO.class));
    }

    @Test
    void update_ShouldReturnUpdatedChamado() {
        // Arrange
        Long id = 1L;
        ChamadoDTO chamadoDTO = new ChamadoDTO();
        when(chamadoService.update(eq(id), any(ChamadoDTO.class))).thenReturn(chamadoDTO);

        // Act
        ResponseEntity<ChamadoDTO> response = chamadoController.update(id, chamadoDTO);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(chamadoService, times(1)).update(eq(id), any(ChamadoDTO.class));
    }

    @Test
    void delete_ShouldReturnNoContent() {
        // Arrange
        Long id = 1L;
        doNothing().when(chamadoService).delete(id);

        // Act
        ResponseEntity<Void> response = chamadoController.delete(id);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(chamadoService, times(1)).delete(id);
    }

    @Test
    void findById_ShouldReturnChamado() {
        // Arrange
        Long id = 1L;
        ChamadoDTO chamadoDTO = new ChamadoDTO();
        when(chamadoService.findById(id)).thenReturn(chamadoDTO);

        // Act
        ResponseEntity<ChamadoDTO> response = chamadoController.findById(id);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(chamadoService, times(1)).findById(id);
    }
} 