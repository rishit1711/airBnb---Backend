package com.example.Project_Rishit.airBnbApp.service;

import com.example.Project_Rishit.airBnbApp.dto.HotelResponseDto;
import com.example.Project_Rishit.airBnbApp.dto.HotelSearchRequest;
import com.example.Project_Rishit.airBnbApp.dto.InventoryResponse;
import com.example.Project_Rishit.airBnbApp.entity.Hotel;
import com.example.Project_Rishit.airBnbApp.entity.Inventory;
import com.example.Project_Rishit.airBnbApp.entity.Room;
import com.example.Project_Rishit.airBnbApp.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService{
    private final InventoryRepository inventoryRepository;
    private  final ModelMapper modelMapper;



    @Override
    public void InitializeRoomForMonth(Room room) {
        LocalDate today =LocalDate.now();
        LocalDate endDate = today.plusMonths(1);
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
                    .reservedCount(0)
                            .build();

            inventoryRepository.save(inventory);
            today = today.plusDays(1);
        }

    }

    @Override
    public Page<HotelResponseDto> SearchHotels(HotelSearchRequest searchRequest) {
        Pageable pageable = PageRequest.of(searchRequest.getPage(), searchRequest.getSize());
        long dateCount = 1 + ChronoUnit.DAYS.between(searchRequest.getStartDate(), searchRequest.getEndDate()); //kitne din ke liye hotel chahiye
        Page<Hotel> hotels = inventoryRepository.findHotelsWithAvailableInventory(searchRequest.getCity(), searchRequest.getStartDate(), searchRequest.getEndDate(), searchRequest.getRoomsCount(), dateCount, pageable);

        return hotels.map((element) ->modelMapper.map(element,HotelResponseDto.class));
    }

    @Override
    public InventoryResponse getAllInventoryOfRoom(Long roomId) {
        return null;
    }

    @Override
    public void deleteFutureInventories(Room room) {
        LocalDate today = LocalDate.now();
        inventoryRepository.deleteFutureInventories(today,room);


    }
}
