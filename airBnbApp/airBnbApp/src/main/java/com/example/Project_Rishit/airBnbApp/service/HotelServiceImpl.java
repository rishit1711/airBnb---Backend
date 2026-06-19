package com.example.Project_Rishit.airBnbApp.service;

import com.example.Project_Rishit.airBnbApp.Exceptions.ResourceNotFoundException;
import com.example.Project_Rishit.airBnbApp.Exceptions.UnauthorizedException;
import com.example.Project_Rishit.airBnbApp.dto.*;
import com.example.Project_Rishit.airBnbApp.entity.Booking;
import com.example.Project_Rishit.airBnbApp.entity.Hotel;
import com.example.Project_Rishit.airBnbApp.entity.Room;
import com.example.Project_Rishit.airBnbApp.entity.User;
import com.example.Project_Rishit.airBnbApp.entity.enums.Role;
import com.example.Project_Rishit.airBnbApp.repository.BookingRepository;
import com.example.Project_Rishit.airBnbApp.repository.HotelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor

public class HotelServiceImpl implements HotelService{
        private final HotelRepository hotelRepository;
        private final ModelMapper modelMapper;
        private  final InventoryService inventoryService;
        private final BookingRepository bookingRepository;



    @Override
    @PreAuthorize("hasRole('HOTEL_MANAGER')")
    public HotelResponseDto CreateHotel(HotelRequestDto requestDtodto) {
        log.info("Creating Hotel with Name : {}",requestDtodto.getName());
        Hotel hotel = modelMapper.map(requestDtodto,Hotel.class);
        hotel.setActive(false);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        hotel.setOwner(user);
        hotel=hotelRepository.save(hotel);
        log.info("Created Hotel with Name : {}",requestDtodto.getName());
        return modelMapper.map(hotel,HotelResponseDto.class);
    }

    @Override
    public HotelResponseDto getHotelById(Long id) {
        log.info("Searching the Hotel with Id : {}",id);
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel not found with id :"+id));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.equals(hotel.getOwner())){
            throw new UnauthorizedException("You are not the owner of this hotel");
        }

        return modelMapper.map(hotel,HotelResponseDto.class);
    }

    @Override
    @PreAuthorize("hasRole('HOTEL_MANAGER')")
    public HotelResponseDto updateHotelById(Long id, HotelRequestDto hotelRequestDto) {
        log.info("Updating the Hotel with Id : {}",id);
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel Does not exist with Id:"+id));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.equals(hotel.getOwner())){
            throw new UnauthorizedException("You are not the owner of this hotel");
        }
        modelMapper.map(hotelRequestDto,hotel);


        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel,HotelResponseDto.class);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('HOTEL_MANAGER')")
    public void DeleteById(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel Does not Exits"));

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.equals(hotel.getOwner())){
            throw new UnauthorizedException("You are not the owner of this hotel");
        }
        hotelRepository.deleteById(id);

        // Deleting Future Inventory
        for(Room room : hotel.getRooms()){
            inventoryService.deleteFutureInventories(room);
        }


    }

    @Override
    @Transactional
    public void ActivateHotel(Long id) {
        log.info("Activating the Hotel with Id : {}",id);
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel Does not Exits"));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!user.getRoles().contains(Role.HOTEL_MANAGER)){
            throw new UnauthorizedException("You are not the Hotel Manager");

        }

        if(!hotel.getOwner().getId().equals(user.getId())){
            throw new UnauthorizedException("You are not the owner of this hotel");
        }
//



        hotel.setActive(true);
        hotelRepository.save(hotel);

        for(Room room : hotel.getRooms()){
            inventoryService.InitializeRoomForMonth(room);
        }

    }

    @Override
    public  HotelInfoDto GetInfoAboutHotel(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("Hotel Does not exist with id"+hotelId));
        List<RoomResponseDto> rooms = hotel.getRooms().stream().map((element) ->modelMapper.map(element,RoomResponseDto.class)).toList();
        return new HotelInfoDto(modelMapper.map(hotel,HotelResponseDto.class),rooms);


    }

    @Override
    public List<HotelResponseDto> getAllHotels() {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("Getting all Hotels for the admin with Id: {}",user.getId());
        List<Hotel> hotels  = hotelRepository.findByOwner(user);
        log.info("Converting list of hotels into response dto using Mapper");
        return hotels.stream()
                .map(element -> modelMapper.map(element, HotelResponseDto.class))
                .toList();


    }

    @Override
    public List<BookingResponseDto> findBookingsOfHotel(Long hotelId) {
        Hotel hotel =  hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel does not exist with Id: "+hotelId));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(hotel.getOwner().getId()!= user.getId()){
            throw new AccessDeniedException("You are not the Owner of this hotel");
        }
        List<Booking> bookings=bookingRepository.findByHotelId(hotelId);
        return bookings.stream()
                .map(element -> modelMapper.map(element,BookingResponseDto.class))
                .toList();

    }


}
