package com.example.Project_Rishit.airBnbApp.service;

import com.example.Project_Rishit.airBnbApp.dto.BookingRequestDto;
import com.example.Project_Rishit.airBnbApp.dto.BookingResponseDto;
import com.example.Project_Rishit.airBnbApp.dto.GuestDto;
import com.example.Project_Rishit.airBnbApp.entity.Booking;
import com.example.Project_Rishit.airBnbApp.entity.enums.BookingStatus;
import com.stripe.exception.StripeException;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface BookingService {

     BookingResponseDto initialiseBooking(BookingRequestDto requestDto);

    @Nullable BookingResponseDto addGuest(Long bookingId, List<GuestDto> guestDtoList);

    String initiatePayment(Long bookingId) throws StripeException;
}
