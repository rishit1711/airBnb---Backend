package com.example.Project_Rishit.airBnbApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class HotelInfoDto {
    private HotelResponseDto hotel;
    private List<RoomResponseDto> rooms;
//    private boolean available;
}
