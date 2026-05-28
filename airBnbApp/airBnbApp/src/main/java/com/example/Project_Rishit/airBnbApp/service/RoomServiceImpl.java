package com.example.Project_Rishit.airBnbApp.service;

import com.example.Project_Rishit.airBnbApp.dto.RoomRequestDto;
import com.example.Project_Rishit.airBnbApp.dto.RoomResponseDto;
import com.example.Project_Rishit.airBnbApp.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;
    @Override
    public RoomResponseDto CreateNewRoom(RoomRequestDto dto) {
        return null;
    }

    @Override
    public void DeleteRoomById(Long roomId) {

    }

    @Override
    public RoomResponseDto GetRoomById(Long roomId) {
        return null;
    }

    @Override
    public List<RoomResponseDto> getAllRoomsInHotel(Long HotelId) {
        return List.of();
    }
}
