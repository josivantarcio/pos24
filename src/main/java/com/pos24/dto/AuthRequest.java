package com.pos24.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
    
    @NotBlank(message = "Username é obrigatório")
    private String username;
    
    @NotBlank(message = "Password é obrigatório")
    private String password;
} 