package com.example.Project_Rishit.airBnbApp.controller;

import com.example.Project_Rishit.airBnbApp.dto.HotelRequestDto;
import com.example.Project_Rishit.airBnbApp.dto.HotelResponseDto;
import com.example.Project_Rishit.airBnbApp.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin/hotels")
@RequiredArgsConstructor
@Slf4j
public class HotelController {
    private final HotelService hotelService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<HotelResponseDto> CreateHotel(@RequestBody HotelRequestDto requestDto){
        log.info("Attempting to Create a Hotel :"+requestDto.getName());
        HotelResponseDto responseDto = hotelService.CreateHotel(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/{HotelId}")
    public ResponseEntity<HotelResponseDto> GetHotel(@PathVariable Long HotelId){
        log.info("Attempting to Fetch a Hotel with Id:"+HotelId);
        HotelResponseDto hotelResponseDto = hotelService.getHotelById(HotelId);
        return ResponseEntity.ok(hotelResponseDto);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{HotelId}")
    public ResponseEntity<HotelResponseDto> UpdateHotelById(@PathVariable Long HotelId,@RequestBody HotelRequestDto requestDto){
        log.info("Attempting to Update a Hotel :"+requestDto.getName());
        HotelResponseDto responseDto = hotelService.updateHotelById(HotelId,requestDto);
        return ResponseEntity.ok(responseDto);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{HotelId}")
    public ResponseEntity<Void> DeleteHotelById(@PathVariable Long HotelId){
        hotelService.DeleteById(HotelId);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{HotelId}")
    public ResponseEntity<Void> ActivateHotel(@PathVariable Long HotelId){
        hotelService.ActivateHotel(HotelId);
        return ResponseEntity.noContent().build();
    }



}