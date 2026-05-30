package com.example.Project_Rishit.airBnbApp.service;

import com.example.Project_Rishit.airBnbApp.dto.HotelRequestDto;
import com.example.Project_Rishit.airBnbApp.dto.HotelResponseDto;


public interface HotelService {
    HotelResponseDto CreateHotel(HotelRequestDto dto);

    HotelResponseDto getHotelById(Long id);

    HotelResponseDto updateHotelById(Long id, HotelRequestDto hotelRequestDto);

    void DeleteById(Long id);

    void ActivateHotel(Long id);

}