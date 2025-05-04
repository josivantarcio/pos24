package com.pos24.service;

import com.pos24.dto.AuthRequest;
import com.pos24.dto.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthRequest request);
} 