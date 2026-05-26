package com.example.Project_Rishit.airBnbApp.entity;

import com.example.Project_Rishit.airBnbApp.entity.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="hotel_Id",nullable = false)
    private Hotel hotel;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="room_id",nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="booked_user",nullable = false)
    private User user;
    @Column(nullable = false)
    private Integer totalRooms;
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime CheckIn;
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime CheckOut;
    @OneToOne(fetch = FetchType.LAZY)

    private Payment payment;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus bookingStatus;

}
