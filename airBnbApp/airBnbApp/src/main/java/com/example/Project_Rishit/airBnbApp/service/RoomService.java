package com.example.Project_Rishit.airBnbApp.service;

import com.example.Project_Rishit.airBnbApp.dto.RoomRequestDto;
import com.example.Project_Rishit.airBnbApp.dto.RoomResponseDto;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface RoomService {

    RoomResponseDto CreateNewRoom(Long hotelId,RoomRequestDto roomRequestDto);
    List<RoomResponseDto> getAllRoomsInHotel(Long HotelId);

    RoomResponseDto GetRoomById(Long roomId);
    void DeleteRoomById(Long roomId);


   RoomResponseDto UpdateRoomById(Long roomId, Long hotelId, RoomRequestDto roomRequestDto);
}
