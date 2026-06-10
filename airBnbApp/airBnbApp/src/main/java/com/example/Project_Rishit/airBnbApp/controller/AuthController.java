package com.example.Project_Rishit.airBnbApp.controller;

import com.example.Project_Rishit.airBnbApp.Security.AuthService;
import com.example.Project_Rishit.airBnbApp.dto.LoginRequestDto;
import com.example.Project_Rishit.airBnbApp.dto.LoginResponseDto;
import com.example.Project_Rishit.airBnbApp.dto.SignUpRequestDto;
import com.example.Project_Rishit.airBnbApp.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> register(@RequestBody SignUpRequestDto requestDto){
        UserResponseDto responseDto = authService.signup(requestDto);
        return ResponseEntity.ok(responseDto);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto){
       String[] tokens = authService.signIn(requestDto);

       // store kro referesh token ko cookie me


    }

}
