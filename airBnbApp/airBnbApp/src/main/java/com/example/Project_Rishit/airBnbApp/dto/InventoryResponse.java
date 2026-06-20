package com.example.Project_Rishit.airBnbApp.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@Builder
public class InventoryResponse {
    private Long inventoryId;

    private Long roomId;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer availableCount;

    private BigDecimal price;

    private LocalDateTime updatedAt;
}