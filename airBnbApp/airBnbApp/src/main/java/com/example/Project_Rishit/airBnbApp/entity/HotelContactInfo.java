package com.example.Project_Rishit.airBnbApp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class HotelContactInfo {
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String phoneNumber;
    private String Location;
    private String email;

}
