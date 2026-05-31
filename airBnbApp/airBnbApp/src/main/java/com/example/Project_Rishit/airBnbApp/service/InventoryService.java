package com.example.Project_Rishit.airBnbApp.service;

import com.example.Project_Rishit.airBnbApp.dto.HotelResponseDto;
import com.example.Project_Rishit.airBnbApp.dto.HotelSearchRequest;
import com.example.Project_Rishit.airBnbApp.entity.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {
    void InitializeRoomForMonth(Room room);
    void deleteFutureInventories(Room room);

    Page<HotelResponseDto> SearchHotels(HotelSearchRequest searchRequest);
}
