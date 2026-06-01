package com.example.Project_Rishit.airBnbApp.dto;

import com.example.Project_Rishit.airBnbApp.entity.User;
import com.example.Project_Rishit.airBnbApp.entity.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GuestDto {
//    private Long Id;
//    private User user;
    private  String name;
    private Gender gender;
    private Integer age;

}
