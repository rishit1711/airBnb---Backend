package com.example.Project_Rishit.airBnbApp.service;

import com.example.Project_Rishit.airBnbApp.dto.BookingRequestDto;
import com.example.Project_Rishit.airBnbApp.dto.BookingResponseDto;
import com.example.Project_Rishit.airBnbApp.dto.GuestDto;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface BookingService {

     BookingResponseDto initialiseBooking(BookingRequestDto requestDto);

    @Nullable BookingResponseDto addGuest(Long bookingId, List<GuestDto> guestDtoList);

    String initiatePayment(Long bookingId) throws StripeException;

    void capturePayment(Event event);

     void CancelBooking(Long bookingId) throws StripeException;
}
