package com.example.Project_Rishit.airBnbApp.service;

import com.example.Project_Rishit.airBnbApp.dto.RoomRequestDto;
import com.example.Project_Rishit.airBnbApp.dto.RoomResponseDto;

import java.util.List;

public interface RoomService {

    RoomResponseDto CreateNewRoom(RoomRequestDto roomRequestDto);
    List<RoomResponseDto> getAllRoomsInHotel(Long HotelId);

    RoomResponseDto GetRoomById(Long roomId);
    void DeleteRoomById(Long roomId);
}
