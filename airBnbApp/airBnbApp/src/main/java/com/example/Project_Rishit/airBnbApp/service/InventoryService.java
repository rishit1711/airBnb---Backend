package com.example.Project_Rishit.airBnbApp.service;

import com.example.Project_Rishit.airBnbApp.dto.HotelResponseDto;
import com.example.Project_Rishit.airBnbApp.dto.HotelSearchRequest;
import com.example.Project_Rishit.airBnbApp.dto.InventoryResponse;
import com.example.Project_Rishit.airBnbApp.entity.Room;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InventoryService {
    void InitializeRoomForMonth(Room room);
    void deleteFutureInventories(Room room);

    Page<HotelResponseDto> SearchHotels(HotelSearchRequest searchRequest);

     List<InventoryResponse> getAllInventoryOfRoom(Long roomId);
}
