package com.pos24.controller;

import com.pos24.dto.LoginDTO;
import com.pos24.security.JwtTokenProvider;
import com.pos24.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenProvider tokenProvider;

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_WithValidCredentials_ShouldReturnToken() {
        // Arrange
        LoginDTO loginDTO = new LoginDTO("admin3", "123456");
        String expectedToken = "test-token";
        ResponseEntity<String> expectedResponse = ResponseEntity.ok(expectedToken);

        when(authService.login(any(LoginDTO.class))).thenReturn(expectedResponse);

        // Act
        ResponseEntity<?> response = authController.login(loginDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedToken, response.getBody());
    }

    @Test
    void login_WithInvalidCredentials_ShouldReturnUnauthorized() {
        // Arrange
        LoginDTO loginDTO = new LoginDTO("admin3", "wrong-password");
        ResponseEntity<String> expectedResponse = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body("Invalid username/password");

        when(authService.login(any(LoginDTO.class))).thenReturn(expectedResponse);

        // Act
        ResponseEntity<?> response = authController.login(loginDTO);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid username/password", response.getBody());
    }
} 