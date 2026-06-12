package com.example.Project_Rishit.airBnbApp.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class RoomResponseDto {
    private Long roomId;
    private String type;
    private BigDecimal basePrice;
    private String[] photos;
    private String[] amenities;
    private Integer capacity;
    private Integer reservedCount;


}
