package com.example.Project_Rishit.airBnbApp.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.math.BigDecimal;
@Data
@RequiredArgsConstructor
public class RoomRequestDto {

    private Long hotelId;
    private String type;
    private BigDecimal basePrice;
    private String[] photos;
    private String[] amenities;
    private Integer totalCount;
    private Integer Capacity; // kitne log reh skte hai

}
