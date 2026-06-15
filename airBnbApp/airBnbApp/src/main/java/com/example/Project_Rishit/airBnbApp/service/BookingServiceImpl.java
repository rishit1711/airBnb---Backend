package com.example.Project_Rishit.airBnbApp.service;

import com.example.Project_Rishit.airBnbApp.Exceptions.ResourceNotFoundException;
import com.example.Project_Rishit.airBnbApp.Exceptions.UnauthorizedException;
import com.example.Project_Rishit.airBnbApp.dto.BookingRequestDto;
import com.example.Project_Rishit.airBnbApp.dto.BookingResponseDto;
import com.example.Project_Rishit.airBnbApp.dto.GuestDto;
import com.example.Project_Rishit.airBnbApp.entity.*;
import com.example.Project_Rishit.airBnbApp.entity.enums.BookingStatus;
import com.example.Project_Rishit.airBnbApp.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor

public class BookingServiceImpl implements BookingService{
    private final BookingRepository bookingRepository;
    private  final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final InventoryRepository inventoryRepository;
    private final GuestRepository guestRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

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
            inventory.setReservedCount(inventory.getReservedCount()+requestDto.getRoomCount());
        }
        inventoryRepository.saveAll(inventoryList);


        // Creating the booking
        Booking booking = Booking.builder()
                .bookingStatus(BookingStatus.RESERVED)
                .hotel(hotel)
                .room(room)
                .CheckIn(requestDto.getCheckInDate())
                .CheckOut(requestDto.getCheckOutDate())
                .totalRooms(requestDto.getRoomCount())
                .user(getUser())
                .Amount(BigDecimal.TEN)
                .build();


        booking=bookingRepository.save(booking);
        return modelMapper.map(booking,BookingResponseDto.class);


    }

    @Override
    @Transactional
    public  BookingResponseDto addGuest(Long bookingId, List<GuestDto> guestDtoList) {
        log.info("Adding Guest in a Booking with Booking ID : {}",bookingId);
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(()->new ResourceNotFoundException("Booking with this Id Not found"));

        User user = getUser();
        if(!user.equals(booking.getUser())){
            throw new UnauthorizedException("Booking Guest does not macth the Logged in Guest :"+user.getEmail());
        }

        if(isBookingExpired(booking)){
            throw new IllegalStateException("Booking has been Expired");
        }
        if(booking.getBookingStatus() !=BookingStatus.RESERVED){
            throw new IllegalStateException("Booking is not in the Reserved State");

        }
        for(GuestDto guestDto : guestDtoList){
            Guest guest = modelMapper.map(guestDto,Guest.class);
            guest.setUser(booking.getUser());  // bad me spring security se authencticated user aa jayega
            guestRepository.save(guest);
            booking.getGuest().add(guest);


        }
        booking.setBookingStatus(BookingStatus.GUESTS_ADDED);
        booking=bookingRepository.save(booking);


        return modelMapper.map(booking,BookingResponseDto.class);
    }

    @Override
    public String initiatePayment(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(()->new ResourceNotFoundException("Booking Does not Exist with Id : "+bookingId ));
        User user = getUser();

        if(!user.equals(booking.getUser())){
            throw new UnauthorizedException("Booking Guest does not match the Logged in Guest :"+user.getEmail());
        }

        if(isBookingExpired(booking)){
            throw new IllegalStateException("Booking has been Expired");
        }
    }

    public boolean isBookingExpired(Booking booking){
        return booking.getCreatedAt().plusMinutes(10).isBefore(LocalDateTime.now());

    }
    public User getUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
