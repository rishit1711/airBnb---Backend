package com.example.Project_Rishit.airBnbApp.controller;

import com.example.Project_Rishit.airBnbApp.dto.BookingRequestDto;
import com.example.Project_Rishit.airBnbApp.dto.BookingResponseDto;
import com.example.Project_Rishit.airBnbApp.entity.Booking;
import com.example.Project_Rishit.airBnbApp.service.BookingService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="/booking")

public class HotelBookingController {
    private final BookingService bookingService;
    @PostMapping()
    public ResponseEntity<BookingResponseDto> initialiseBooking(@RequestBody BookingRequestDto requestDto){
        return ResponseEntity.ok(bookingService.initialiseBooking(requestDto));

    }

}
