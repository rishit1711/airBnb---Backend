package com.example.Project_Rishit.airBnbApp.repository;

import com.example.Project_Rishit.airBnbApp.dto.BookingResponseDto;
import com.example.Project_Rishit.airBnbApp.entity.Booking;
import com.example.Project_Rishit.airBnbApp.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    Optional<Booking> findBystripeSessionId(String sessionId);

    List<Booking> findByHotelId(Long hotelId);
    List<Booking> findByHotelAndCreatedAtBetween(Hotel hotel, LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<Booking> findByUser(Long userId);
}
