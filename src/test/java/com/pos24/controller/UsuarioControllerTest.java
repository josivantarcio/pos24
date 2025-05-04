package com.pos24.controller;

import com.pos24.dto.UsuarioDTO;
import com.pos24.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_ShouldReturnCreatedUsuario() {
        // Arrange
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        when(usuarioService.create(any(UsuarioDTO.class))).thenReturn(usuarioDTO);

        // Act
        ResponseEntity<UsuarioDTO> response = usuarioController.create(usuarioDTO);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(usuarioService, times(1)).create(any(UsuarioDTO.class));
    }

    @Test
    void update_ShouldReturnUpdatedUsuario() {
        // Arrange
        Long id = 1L;
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        when(usuarioService.update(eq(id), any(UsuarioDTO.class))).thenReturn(usuarioDTO);

        // Act
        ResponseEntity<UsuarioDTO> response = usuarioController.update(id, usuarioDTO);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(usuarioService, times(1)).update(eq(id), any(UsuarioDTO.class));
    }

    @Test
    void delete_ShouldReturnNoContent() {
        // Arrange
        Long id = 1L;
        doNothing().when(usuarioService).delete(id);

        // Act
        ResponseEntity<Void> response = usuarioController.delete(id);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(usuarioService, times(1)).delete(id);
    }

    @Test
    void findById_ShouldReturnUsuario() {
        // Arrange
        Long id = 1L;
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        when(usuarioService.findById(id)).thenReturn(usuarioDTO);

        // Act
        ResponseEntity<UsuarioDTO> response = usuarioController.findById(id);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(usuarioService, times(1)).findById(id);
    }

    @Test
    void findAll_ShouldReturnListOfUsuarios() {
        // Arrange
        List<UsuarioDTO> usuarios = Arrays.asList(new UsuarioDTO());
        when(usuarioService.findAll()).thenReturn(usuarios);

        // Act
        ResponseEntity<List<UsuarioDTO>> response = usuarioController.findAll();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(usuarioService, times(1)).findAll();
    }

    @Test
    void findAllPageable_ShouldReturnPageOfUsuarios() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        Page<UsuarioDTO> page = new PageImpl<>(Arrays.asList(new UsuarioDTO()));
        when(usuarioService.findAll(any(Pageable.class))).thenReturn(page);

        // Act
        ResponseEntity<Page<UsuarioDTO>> response = usuarioController.findAllPageable(pageable);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getContent().size());
        verify(usuarioService, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void findByUsername_WithValidUsername_ShouldReturnUsuario() {
        // Arrange
        String username = "testuser";
        when(usuarioService.findByUsername(username)).thenReturn(new UsuarioDTO());

        // Act
        ResponseEntity<UsuarioDTO> response = usuarioController.findByUsername(username);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(usuarioService, times(1)).findByUsername(username);
    }

    @Test
    void findByEmail_WithValidEmail_ShouldReturnUsuario() {
        // Arrange
        String email = "test@example.com";
        when(usuarioService.findByEmail(email)).thenReturn(new UsuarioDTO());

        // Act
        ResponseEntity<UsuarioDTO> response = usuarioController.findByEmail(email);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(usuarioService, times(1)).findByEmail(email);
    }
} 