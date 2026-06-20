package com.example.Project_Rishit.airBnbApp.service;

import com.example.Project_Rishit.airBnbApp.Exceptions.ResourceNotFoundException;
import com.example.Project_Rishit.airBnbApp.Exceptions.UnauthorizedException;
import com.example.Project_Rishit.airBnbApp.dto.HotelResponseDto;
import com.example.Project_Rishit.airBnbApp.dto.HotelSearchRequest;
import com.example.Project_Rishit.airBnbApp.dto.InventoryResponse;
import com.example.Project_Rishit.airBnbApp.entity.Hotel;
import com.example.Project_Rishit.airBnbApp.entity.Inventory;
import com.example.Project_Rishit.airBnbApp.entity.Room;
import com.example.Project_Rishit.airBnbApp.entity.User;
import com.example.Project_Rishit.airBnbApp.repository.InventoryRepository;
import com.example.Project_Rishit.airBnbApp.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService{
    private final InventoryRepository inventoryRepository;
    private final RoomRepository roomRepository;
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
    public List<InventoryResponse> getAllInventoryOfRoom(Long roomId) {

        Room room = roomRepository.findById(roomId).orElseThrow(()->new ResourceNotFoundException("Room does not exist with Id : "+roomId));

        User user  = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(room.getHotel().getOwner()!=user){
            throw new UnauthorizedException("You are not the owner of this Hotel");
        }
        List<Inventory> inventoryList = inventoryRepository.findByRoom(roomId);
        return inventoryList.stream()
                .map((element) -> modelMapper.map(element, InventoryResponse.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteFutureInventories(Room room) {
        LocalDate today = LocalDate.now();
        inventoryRepository.deleteFutureInventories(today,room);


    }
}
