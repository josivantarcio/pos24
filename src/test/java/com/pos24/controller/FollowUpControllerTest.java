package com.pos24.controller;

import com.pos24.dto.FollowUpDTO;
import com.pos24.service.FollowUpService;
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

class FollowUpControllerTest {

    @Mock
    private FollowUpService followUpService;

    @InjectMocks
    private FollowUpController followUpController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_ShouldReturnCreatedFollowUp() {
        // Arrange
        FollowUpDTO followUpDTO = new FollowUpDTO();
        when(followUpService.create(any(FollowUpDTO.class))).thenReturn(followUpDTO);

        // Act
        ResponseEntity<FollowUpDTO> response = followUpController.create(followUpDTO);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(followUpService, times(1)).create(any(FollowUpDTO.class));
    }

    @Test
    void update_ShouldReturnUpdatedFollowUp() {
        // Arrange
        Long id = 1L;
        FollowUpDTO followUpDTO = new FollowUpDTO();
        when(followUpService.update(eq(id), any(FollowUpDTO.class))).thenReturn(followUpDTO);

        // Act
        ResponseEntity<FollowUpDTO> response = followUpController.update(id, followUpDTO);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(followUpService, times(1)).update(eq(id), any(FollowUpDTO.class));
    }

    @Test
    void delete_ShouldReturnNoContent() {
        // Arrange
        Long id = 1L;
        doNothing().when(followUpService).delete(id);

        // Act
        ResponseEntity<Void> response = followUpController.delete(id);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(followUpService, times(1)).delete(id);
    }

    @Test
    void findById_ShouldReturnFollowUp() {
        // Arrange
        Long id = 1L;
        FollowUpDTO followUpDTO = new FollowUpDTO();
        when(followUpService.findById(id)).thenReturn(followUpDTO);

        // Act
        ResponseEntity<FollowUpDTO> response = followUpController.findById(id);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(followUpService, times(1)).findById(id);
    }
} 