package com.pos24.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO para autenticação de usuário.
 * Contém as credenciais necessárias para login.
 * 
 * @author POS24 Team
 * @version 1.0
 * @since 2024
 */
public class LoginDTO {
    
    /**
     * Nome de usuário para autenticação.
     */
    @NotBlank(message = "Username é obrigatório")
    private String username;
    
    /**
     * Senha do usuário para autenticação.
     */
    @NotBlank(message = "Password é obrigatório")
    private String password;

    public LoginDTO() {
    }

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
} 