package com.pos24.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BaseDTO {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 