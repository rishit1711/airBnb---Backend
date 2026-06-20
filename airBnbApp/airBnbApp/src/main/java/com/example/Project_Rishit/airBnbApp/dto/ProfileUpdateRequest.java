package com.example.Project_Rishit.airBnbApp.dto;

import com.example.Project_Rishit.airBnbApp.entity.enums.Gender;
import lombok.Data;


import java.time.LocalDate;
@Data
public class ProfileUpdateRequest {

    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;
}
