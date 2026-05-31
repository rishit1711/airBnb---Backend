package com.example.Project_Rishit.airBnbApp.controller;


import com.example.Project_Rishit.airBnbApp.dto.HotelInfoDto;
import com.example.Project_Rishit.airBnbApp.dto.HotelResponseDto;
import com.example.Project_Rishit.airBnbApp.dto.HotelSearchRequest;
import com.example.Project_Rishit.airBnbApp.service.HotelService;
import com.example.Project_Rishit.airBnbApp.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelBrowseController {
    private final InventoryService inventoryService;
    private final HotelService hotelService;
    @GetMapping("/search")
    public ResponseEntity<Page<HotelResponseDto>> searchingHotels(@RequestBody HotelSearchRequest searchRequest){
        Page<HotelResponseDto> page = inventoryService.SearchHotels(searchRequest);
        return ResponseEntity.ok(page);

    }
    @GetMapping("/{hotelId}/info")
    public ResponseEntity<HotelInfoDto> getHotelInfo(@PathVariable Long hotelId){
        return ResponseEntity.ok(hotelService.GetInfoAboutHotel(hotelId));

    }
}
