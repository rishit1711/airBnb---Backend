package com.example.Project_Rishit.airBnbApp.dto;


import com.example.Project_Rishit.airBnbApp.entity.enums.BookingStatus;
import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDate;


@Data


public class BookingResponseDto {

    private Long bookingId;

    private Long hotelId;
    private String hotelName;

    private Long roomId;
    private String roomType;

    private Long userId;
    private String userName;

    private Integer totalRooms;

    private LocalDate checkIn;
    private LocalDate checkOut;

    private BookingStatus bookingStatus;

    private BigDecimal amount;
}


