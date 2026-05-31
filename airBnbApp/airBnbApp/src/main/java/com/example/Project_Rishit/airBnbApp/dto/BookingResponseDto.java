package com.example.Project_Rishit.airBnbApp.dto;

import com.example.Project_Rishit.airBnbApp.entity.Guest;
import com.example.Project_Rishit.airBnbApp.entity.Hotel;
import com.example.Project_Rishit.airBnbApp.entity.Room;
import com.example.Project_Rishit.airBnbApp.entity.User;
import com.example.Project_Rishit.airBnbApp.entity.enums.BookingStatus;


import java.time.LocalDateTime;
import java.util.Set;

public class BookingResponseDto {
    private Long Id;
    private Hotel hotel;
    private Room room;
    private User user;
    private Integer totalRooms;
    private LocalDateTime CheckIn;
    private LocalDateTime CheckOut;
    private BookingStatus bookingStatus;

    private Set<Guest> guest;

}
