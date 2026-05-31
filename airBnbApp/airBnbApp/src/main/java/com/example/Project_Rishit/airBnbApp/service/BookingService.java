package com.example.Project_Rishit.airBnbApp.service;

import com.example.Project_Rishit.airBnbApp.dto.BookingRequestDto;
import com.example.Project_Rishit.airBnbApp.dto.BookingResponseDto;
import com.example.Project_Rishit.airBnbApp.entity.Booking;
import com.example.Project_Rishit.airBnbApp.entity.enums.BookingStatus;
import org.jspecify.annotations.Nullable;

public interface BookingService {

     BookingResponseDto initialiseBooking(BookingRequestDto requestDto);
}
