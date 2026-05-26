package com.example.Project_Rishit.airBnbApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name="hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,length = 50)
    private  String city;
    @Column(columnDefinition = "TEXT[]")
    private String[] photos;
    @Column(columnDefinition = "TEXT[]")
    private String[] amenities;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime UpdatedAt;
    @Embedded
    private HotelContactInfo contactInfo;
    @Column(nullable = false)
    private boolean isActive;
    @OneToMany(mappedBy = "hotel",fetch = FetchType.LAZY)
    private List<Room> rooms;


}
