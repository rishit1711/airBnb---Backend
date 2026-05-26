package com.example.Project_Rishit.airBnbApp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(name = "unique_hotel_room_data",columnNames = {
        "hotel_Id","room_id","date"}
))
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="hotel_Id",nullable = false)
    private Hotel hotel;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="room_id",nullable = false)
    private Room room;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private Integer bookedCount;
    @Column(nullable = false)
    private Integer totalCount;
    @Column(nullable = false,precision = 2,scale = 2)
    private BigDecimal surgeFactor;
    @Column(nullable = false,precision = 10,scale = 2)
    private BigDecimal price; // BASE PRICE * SURGE FACTOR

    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private boolean closed;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime UpdatedAt;
}
