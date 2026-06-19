package com.example.Project_Rishit.airBnbApp.controller;

import com.example.Project_Rishit.airBnbApp.dto.BookingResponseDto;
import com.example.Project_Rishit.airBnbApp.dto.HotelReportDto;
import com.example.Project_Rishit.airBnbApp.dto.HotelRequestDto;
import com.example.Project_Rishit.airBnbApp.dto.HotelResponseDto;
import com.example.Project_Rishit.airBnbApp.service.BookingService;
import com.example.Project_Rishit.airBnbApp.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/admin/hotels")
@RequiredArgsConstructor
@Slf4j
public class HotelController {
    private final HotelService hotelService;
    private final BookingService bookingService;
    @PreAuthorize("hasRole('HOTEL_MANAGER')")
    @PostMapping
    public ResponseEntity<HotelResponseDto> CreateHotel(@RequestBody HotelRequestDto requestDto){
        log.info("Attempting to Create a Hotel :"+requestDto.getName());
        HotelResponseDto responseDto = hotelService.CreateHotel(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/{HotelId}")
    public ResponseEntity<HotelResponseDto> GetHotel(@PathVariable Long HotelId){
        log.info("Attempting to Fetch a Hotel with Id:"+HotelId);
        HotelResponseDto hotelResponseDto = hotelService.getHotelById(HotelId);
        return ResponseEntity.ok(hotelResponseDto);
    }
    @PreAuthorize("hasRole('HOTEL_MANAGER')")
    @PutMapping("/{HotelId}")
    public ResponseEntity<HotelResponseDto> UpdateHotelById(@PathVariable Long HotelId,@RequestBody HotelRequestDto requestDto){
        log.info("Attempting to Update a Hotel :"+requestDto.getName());
        HotelResponseDto responseDto = hotelService.updateHotelById(HotelId,requestDto);
        return ResponseEntity.ok(responseDto);
    }
    @PreAuthorize("hasRole('HOTEL_MANAGER')")
    @DeleteMapping("/{HotelId}")
    public ResponseEntity<Void> DeleteHotelById(@PathVariable Long HotelId){
        hotelService.DeleteById(HotelId);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasRole('HOTEL_MANAGER')")
    @PatchMapping("/{HotelId}")
    public ResponseEntity<Void> ActivateHotel(@PathVariable Long HotelId){
        hotelService.ActivateHotel(HotelId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<HotelResponseDto>> listofHotels(){
       return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @GetMapping("/{hotelId}/bookings")
    public ResponseEntity<List<BookingResponseDto>> findBookings(@PathVariable Long hotelId){
        return ResponseEntity.ok(hotelService.findBookingsOfHotel(hotelId));
    }

    @GetMapping("/{hotelId}/reports")
    public ResponseEntity<HotelReportDto> report(@PathVariable Long hotelId, @RequestParam LocalDate startDate,@RequestParam LocalDate endDate){
        return ResponseEntity.ok(bookingService.findHotelReport(hotelId,startDate,endDate));
    }



}