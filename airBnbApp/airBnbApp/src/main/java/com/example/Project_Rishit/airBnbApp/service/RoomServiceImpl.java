package com.example.Project_Rishit.airBnbApp.service;

import com.example.Project_Rishit.airBnbApp.Exceptions.ResourceNotFoundException;
import com.example.Project_Rishit.airBnbApp.dto.RoomRequestDto;
import com.example.Project_Rishit.airBnbApp.dto.RoomResponseDto;
import com.example.Project_Rishit.airBnbApp.entity.Hotel;
import com.example.Project_Rishit.airBnbApp.entity.Room;
import com.example.Project_Rishit.airBnbApp.repository.HotelRepository;
import com.example.Project_Rishit.airBnbApp.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService{
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    @Override
    public RoomResponseDto CreateNewRoom(Long hotelId,RoomRequestDto roomRequestDto) {
        log.info("Creating Room in Hotel with Id : {}",hotelId);
        Hotel hotel = hotelRepository.findById(hotelId).
                orElseThrow(()->new ResourceNotFoundException("Hotel Does not Exist with this Id"+hotelId));


        Room room = modelMapper.map(roomRequestDto,Room.class);
        room.setHotel(hotel);
        room = roomRepository.save(room);
        System.out.println("ROOM ID = " + room.getId());

        return modelMapper.map(room,RoomResponseDto.class);
    }

    @Override
    public void DeleteRoomById(Long roomId) {
        log.info("Deleting Room in Hotel with Id : {}",roomId);
        boolean exists = roomRepository.existsById(roomId);
        if(!exists){
            throw new ResourceNotFoundException("Room Does not Exist With Id : "+roomId);
        }
        roomRepository.deleteById(roomId);



    }

    @Override
    public RoomResponseDto GetRoomById(Long roomId) {
        log.info("Getting Room in Hotel with Id : {}",roomId);
        Room room = roomRepository.findById(roomId).
                orElseThrow(()->new ResourceNotFoundException("Room Does not Exist with this Id"+roomId));

        return modelMapper.map(room,RoomResponseDto.class);
    }

    @Override
    public List<RoomResponseDto> getAllRoomsInHotel(Long HotelId) {
        log.info("Getting All Rooms in Hotel with Id : {}",HotelId);
        Hotel hotel = hotelRepository.findById(HotelId).
                orElseThrow(()->new ResourceNotFoundException("Hotel Does not Exist with this Id"+HotelId));
        return hotel.getRooms()
                .stream()
                .map((element) ->modelMapper.map(element,RoomResponseDto.class))
                .collect(Collectors.toList());


    }
}
