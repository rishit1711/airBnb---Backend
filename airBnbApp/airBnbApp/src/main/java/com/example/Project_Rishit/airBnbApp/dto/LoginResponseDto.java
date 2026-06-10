package com.example.Project_Rishit.airBnbApp.dto;

import lombok.Data;

@Data
public class LoginResponseDto {
    private String accessToken;
    private String refreshToken;
}
