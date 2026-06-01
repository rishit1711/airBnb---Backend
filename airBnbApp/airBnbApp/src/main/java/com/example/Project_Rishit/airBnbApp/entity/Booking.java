package com.example.Project_Rishit.airBnbApp.entity;

import com.example.Project_Rishit.airBnbApp.entity.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    private LocalDate CheckIn;
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDate CheckOut;
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
    @OneToMany(fetch = FetchType.LAZY)
    private Set<Guest> guest;
    @Column(nullable = false,precision = 10,scale = 2)
    private BigDecimal Amount;
    private LocalDateTime createdAt;


}
