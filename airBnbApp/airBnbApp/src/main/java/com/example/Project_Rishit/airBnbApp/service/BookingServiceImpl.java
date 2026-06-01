package com.example.Project_Rishit.airBnbApp.service;

import com.example.Project_Rishit.airBnbApp.Exceptions.ResourceNotFoundException;
import com.example.Project_Rishit.airBnbApp.dto.BookingRequestDto;
import com.example.Project_Rishit.airBnbApp.dto.BookingResponseDto;
import com.example.Project_Rishit.airBnbApp.entity.*;
import com.example.Project_Rishit.airBnbApp.entity.enums.BookingStatus;
import com.example.Project_Rishit.airBnbApp.repository.BookingRepository;
import com.example.Project_Rishit.airBnbApp.repository.HotelRepository;
import com.example.Project_Rishit.airBnbApp.repository.InventoryRepository;
import com.example.Project_Rishit.airBnbApp.repository.RoomRepository;
import jakarta.transaction.Transactional;
import jdk.jfr.Registered;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class BookingServiceImpl implements BookingService{
    private final BookingRepository bookingRepository;
    private  final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final InventoryRepository inventoryRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public BookingResponseDto initialiseBooking(BookingRequestDto requestDto) {
        log.info("Initialising Booking for Room in a hotel with HotelId{}",requestDto.getHotelId());
        Hotel hotel = hotelRepository.findById(requestDto.getHotelId()).orElseThrow(()->new ResourceNotFoundException("Hotel Does not exist with Id"+requestDto.getHotelId()));
        Room room = roomRepository.findById(requestDto.getRoomId()).orElseThrow(()->new ResourceNotFoundException("Room Does not exist with Id"+requestDto.getRoomId()));
        List<Inventory> inventoryList=inventoryRepository.findAndLockAvailableInventory(requestDto.getRoomId(), requestDto.getCheckInDate(),requestDto.getCheckOutDate(), requestDto.getRoomCount());
        long dateCount  = ChronoUnit.DAYS.between(requestDto.getCheckInDate(),requestDto.getCheckOutDate());
        if(inventoryList.size()<dateCount){
           throw  new ResourceNotFoundException("Room Not Available for all Dates");
        }
        for(Inventory inventory : inventoryList){
            inventory.setBookedCount(inventory.getBookedCount()+requestDto.getRoomCount());
        }
        inventoryRepository.saveAll(inventoryList);

        User user = new User();
        user.setId(1L);
        user.setName("Rishit");
        user.setEmail("rishit123@gmail.com");
        user.setPassword("rishit123");

        // Creating the booking
        Booking booking = Booking.builder()
                .bookingStatus(BookingStatus.RESERVED)
                .hotel(hotel)
                .room(room)
                .CheckIn(requestDto.getCheckInDate())
                .CheckOut(requestDto.getCheckOutDate())
                .totalRooms(requestDto.getRoomCount())
                .user(user)
                .Amount(BigDecimal.TEN)
                .build();


        booking=bookingRepository.save(booking);
        return modelMapper.map(booking,BookingResponseDto.class);


    }
}
