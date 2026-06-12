package com.example.Project_Rishit.airBnbApp.service;
import com.example.Project_Rishit.airBnbApp.Exceptions.ResourceNotFoundException;
import com.example.Project_Rishit.airBnbApp.Exceptions.UnauthorizedException;
import com.example.Project_Rishit.airBnbApp.dto.RoomRequestDto;
import com.example.Project_Rishit.airBnbApp.dto.RoomResponseDto;
import com.example.Project_Rishit.airBnbApp.entity.Hotel;
import com.example.Project_Rishit.airBnbApp.entity.Room;
import com.example.Project_Rishit.airBnbApp.entity.User;
import com.example.Project_Rishit.airBnbApp.repository.HotelRepository;
import com.example.Project_Rishit.airBnbApp.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService{
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final InventoryService inventoryService;
    private final ModelMapper modelMapper;


    @Override
    @PreAuthorize("hasRole('HOTEL_MANAGER')")
    public RoomResponseDto CreateNewRoom(Long hotelId,RoomRequestDto roomRequestDto) {
        log.info("Creating Room in Hotel with Id : {}",hotelId);
        Hotel hotel = hotelRepository.findById(hotelId).
                orElseThrow(()->new ResourceNotFoundException("Hotel Does not Exist with this Id"+hotelId));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.equals(hotel.getOwner())){
            throw new UnauthorizedException("You are not the owner of this hotel");
        }


        Room room = modelMapper.map(roomRequestDto,Room.class);
        room.setHotel(hotel);
        room = roomRepository.save(room);

        //Create kro inventory as soon as room is created and hotel is Active
        if(hotel.isActive()){
            inventoryService.InitializeRoomForMonth(room);

        }

        return modelMapper.map(room,RoomResponseDto.class);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('HOTEL_MANAGER')")
    public void DeleteRoomById(Long roomId) {
        log.info("Deleting Room in Hotel with Id : {}",roomId);
        Room room = roomRepository.findById(roomId).
                orElseThrow(()->new ResourceNotFoundException("Room Does not Exist with this Id"+roomId));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.equals(room.getHotel().getOwner())){
            throw new UnauthorizedException("You are not the owner of thi hotel");
        }
        inventoryService.deleteFutureInventories(room);
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
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.equals(hotel.getOwner())){
            throw new UnauthorizedException("You are not the owner of thi hotel");
        }
        return hotel.getRooms()
                .stream()
                .map((element) ->modelMapper.map(element,RoomResponseDto.class))
                .collect(Collectors.toList());
    }
}
