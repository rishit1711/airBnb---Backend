package com.example.Project_Rishit.airBnbApp.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RoomResponseDto {
    private Long roomId;
    private String type;
    private Double basePrice;
    private String[] photos;
    private String[] amenities;
    private Integer capacity;


}
