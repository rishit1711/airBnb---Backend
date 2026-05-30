package com.example.Project_Rishit.airBnbApp.dto;

import com.example.Project_Rishit.airBnbApp.entity.HotelContactInfo;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;


@Data
@RequiredArgsConstructor
public class HotelSearchRequest {
    private  String city;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer roomsCount;

    private Integer page=0;
    private Integer size=10;






}
