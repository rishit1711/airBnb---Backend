package com.example.Project_Rishit.airBnbApp.service;

import com.example.Project_Rishit.airBnbApp.entity.Inventory;
import com.example.Project_Rishit.airBnbApp.entity.Room;
import com.example.Project_Rishit.airBnbApp.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService{
    private final InventoryRepository inventoryRepository;



    @Override
    public void InitializeRoomForYear(Room room) {
        LocalDate today =LocalDate.now();
        LocalDate endDate = today.plusYears(1);
        while (!today.isAfter(endDate)) {
            Inventory inventory = Inventory.builder()
                    .hotel(room.getHotel() )
                    .room(room)
                    .bookedCount(0)
                    .city(room.getHotel().getCity())
                    .date(today)
                    .price(room.getBasePrice())
                    .surgeFactor(BigDecimal.ONE)
                    .totalCount(room.getTotalCount())
                    .closed(false)
                    .build();

            inventoryRepository.save(inventory);
            today = today.plusDays(1);
        }

    }
    @Override
    public void deleteFutureInventories(Room room) {
        LocalDate today = LocalDate.now();
        inventoryRepository.deleteByDateAfter(today,room);


    }
}
