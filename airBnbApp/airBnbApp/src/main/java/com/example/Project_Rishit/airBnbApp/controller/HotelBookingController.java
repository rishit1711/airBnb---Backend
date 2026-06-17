package com.example.Project_Rishit.airBnbApp.controller;

import com.example.Project_Rishit.airBnbApp.dto.BookingRequestDto;
import com.example.Project_Rishit.airBnbApp.dto.BookingResponseDto;
import com.example.Project_Rishit.airBnbApp.dto.GuestDto;
import com.example.Project_Rishit.airBnbApp.entity.Booking;
import com.example.Project_Rishit.airBnbApp.entity.Guest;
import com.example.Project_Rishit.airBnbApp.service.BookingService;
import com.stripe.exception.StripeException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="/booking")

public class HotelBookingController {
    private final BookingService bookingService;
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/init")
    public ResponseEntity<BookingResponseDto> initialiseBooking(@RequestBody BookingRequestDto requestDto){
        return ResponseEntity.ok(bookingService.initialiseBooking(requestDto));

    }
    @PostMapping("/{bookingId}/addGuests")
    public ResponseEntity<BookingResponseDto> addGuest(@PathVariable Long bookingId, @RequestBody List<GuestDto> guestDtoList){
        return ResponseEntity.ok(bookingService.addGuest(bookingId,guestDtoList));
    }
    @PostMapping("/{bookingId}/initiatePayments")
    public ResponseEntity<Map<String,String>> initiatePayments(@PathVariable Long bookingId) throws StripeException {
        String sessionUrl=bookingService.initiatePayment(bookingId);
        return ResponseEntity.ok(Map.of("SessionUrl",sessionUrl));

    }


}
