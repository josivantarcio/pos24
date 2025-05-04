package com.pos24.config;

import com.pos24.security.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuração de segurança da aplicação.
 * Define as políticas de autenticação e autorização.
 * 
 * @author POS24 Team
 * @version 1.0
 * @since 2024
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenProvider tokenProvider;

    /**
     * Construtor que inicializa a configuração de segurança.
     *
     * @param tokenProvider Provedor de tokens JWT
     */
    public SecurityConfig(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    /**
     * Configura a cadeia de filtros de segurança.
     *
     * @param http Configuração de segurança HTTP
     * @return SecurityFilterChain configurado
     * @throws Exception em caso de erro na configuração
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated()
            );
        
        return http.build();
    }

    /**
     * Configura o gerenciador de autenticação.
     *
     * @param authConfig Configuração de autenticação
     * @return AuthenticationManager configurado
     * @throws Exception em caso de erro na configuração
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * Configura o codificador de senhas.
     *
     * @return PasswordEncoder configurado
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
} 