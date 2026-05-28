package com.example.Project_Rishit.airBnbApp.dto;

import com.example.Project_Rishit.airBnbApp.entity.HotelContactInfo;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class HotelResponseDto {
    private Long hotelId;
    private String name;
    private  String city;
    private String[] photos;
    private String[] amenities;
    private HotelContactInfo contactInfo;
    private boolean isActive;

}
