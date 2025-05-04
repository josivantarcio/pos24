package com.pos24.controller;

import com.pos24.dto.ClienteDTO;
import com.pos24.service.ClienteService;
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

class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    private ClienteDTO clienteDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clienteDTO = new ClienteDTO();
    }

    @Test
    void create_ShouldReturnCreatedCliente() {
        // Arrange
        when(clienteService.create(any(ClienteDTO.class))).thenReturn(clienteDTO);

        // Act
        ResponseEntity<ClienteDTO> response = clienteController.create(clienteDTO);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(clienteService, times(1)).create(any(ClienteDTO.class));
    }

    @Test
    void update_ShouldReturnUpdatedCliente() {
        // Arrange
        Long id = 1L;
        when(clienteService.update(eq(id), any(ClienteDTO.class))).thenReturn(clienteDTO);

        // Act
        ResponseEntity<ClienteDTO> response = clienteController.update(id, clienteDTO);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(clienteService, times(1)).update(eq(id), any(ClienteDTO.class));
    }

    @Test
    void delete_ShouldReturnNoContent() {
        // Arrange
        Long id = 1L;
        doNothing().when(clienteService).delete(id);

        // Act
        ResponseEntity<Void> response = clienteController.delete(id);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(clienteService, times(1)).delete(id);
    }

    @Test
    void findById_ShouldReturnCliente() {
        // Arrange
        Long id = 1L;
        when(clienteService.findById(id)).thenReturn(clienteDTO);

        // Act
        ResponseEntity<ClienteDTO> response = clienteController.findById(id);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(clienteService, times(1)).findById(id);
    }
} 