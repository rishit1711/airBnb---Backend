package com.example.Project_Rishit.airBnbApp.service;

import com.example.Project_Rishit.airBnbApp.entity.Room;

public interface InventoryService {
    void InitializeRoomForYear(Room room);
    void deleteFutureInventories(Room room);
}
