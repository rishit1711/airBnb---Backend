package com.example.Project_Rishit.airBnbApp.service;

import com.example.Project_Rishit.airBnbApp.dto.HotelInfoDto;
import com.example.Project_Rishit.airBnbApp.dto.HotelRequestDto;
import com.example.Project_Rishit.airBnbApp.dto.HotelResponseDto;
import org.jspecify.annotations.Nullable;


public interface HotelService {
    HotelResponseDto CreateHotel(HotelRequestDto dto);

    HotelResponseDto getHotelById(Long id);

    HotelResponseDto updateHotelById(Long id, HotelRequestDto hotelRequestDto);

    void DeleteById(Long id);

    void ActivateHotel(Long id);

     HotelInfoDto GetInfoAboutHotel(Long hotelId);
}