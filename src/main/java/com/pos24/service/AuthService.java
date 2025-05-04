package com.pos24.service;

import com.pos24.dto.LoginDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<String> login(LoginDTO loginDTO);
} 