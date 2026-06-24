package com.springboot.pmsrestservice.dtos;

import java.math.BigDecimal;

public record ProductResponse(Long id, String name, String description, BigDecimal price) {
}
