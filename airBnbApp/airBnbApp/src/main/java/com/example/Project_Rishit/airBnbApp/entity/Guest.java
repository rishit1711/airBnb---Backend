package com.example.Project_Rishit.airBnbApp.entity;

import com.example.Project_Rishit.airBnbApp.entity.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(nullable = false)
    private  String name;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer age;
}
