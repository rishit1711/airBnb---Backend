package com.example.Project_Rishit.airBnbApp.controller;

import com.example.Project_Rishit.airBnbApp.dto.UserResponseDto;
import com.example.Project_Rishit.airBnbApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class HostController {
    private final UserService userService;
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/becomehost")
    public void becomeManager(){
        userService.becomeHost();

    }
}
