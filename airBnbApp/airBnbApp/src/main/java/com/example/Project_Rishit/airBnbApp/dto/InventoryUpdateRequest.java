package com.example.Project_Rishit.airBnbApp.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data


public class InventoryUpdateRequest {

    private Long inventoryId;

    private LocalDate startDate;
    private LocalDate endDate;

    private Integer availableCount;

    private BigDecimal price;
}
