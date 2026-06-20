package com.example.Project_Rishit.airBnbApp.controller;

import com.example.Project_Rishit.airBnbApp.dto.BookingResponseDto;
import com.example.Project_Rishit.airBnbApp.dto.ProfileUpdateRequest;
import com.example.Project_Rishit.airBnbApp.service.BookingService;
import com.example.Project_Rishit.airBnbApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final BookingService bookingService;

    @PutMapping("/profile")
    public ResponseEntity<String> UpdateProfile(@RequestBody ProfileUpdateRequest updateRequest){
        userService.UpdateProfileDetails(updateRequest);
        return ResponseEntity.ok("Profile Updated Successfully");
    }

    @GetMapping("/{userId}/Mybookings")
    public ResponseEntity<List<BookingResponseDto>> bookings(@PathVariable Long userId){
        return ResponseEntity.ok(bookingService.getMyBookings(userId));

    }

}
