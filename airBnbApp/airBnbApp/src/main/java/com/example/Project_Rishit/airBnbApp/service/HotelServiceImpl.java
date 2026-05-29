package com.example.Project_Rishit.airBnbApp.service;

import com.example.Project_Rishit.airBnbApp.Exceptions.ResourceNotFoundException;
import com.example.Project_Rishit.airBnbApp.dto.HotelRequestDto;
import com.example.Project_Rishit.airBnbApp.dto.HotelResponseDto;
import com.example.Project_Rishit.airBnbApp.entity.Hotel;
import com.example.Project_Rishit.airBnbApp.entity.Room;
import com.example.Project_Rishit.airBnbApp.repository.HotelRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor

public class HotelServiceImpl implements HotelService{
        private final HotelRepository hotelRepository;
        private final ModelMapper modelMapper;
        private  final InventoryService inventoryService;



    @Override
    public HotelResponseDto CreateHotel(HotelRequestDto requestDtodto) {
        log.info("Creating Hotel with Name : {}",requestDtodto.getName());
        Hotel hotel = modelMapper.map(requestDtodto,Hotel.class);
        hotel=hotelRepository.save(hotel);
        log.info("Created Hotel with Name : {}",requestDtodto.getName());
        return modelMapper.map(hotel,HotelResponseDto.class);
    }

    @Override
    public HotelResponseDto getHotelById(Long id) {
        log.info("Searching the Hotel with Id : {}",id);
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel not found with id :"+id));

        return modelMapper.map(hotel,HotelResponseDto.class);
    }

    @Override
    public HotelResponseDto updateHotelById(Long id, HotelRequestDto hotelRequestDto) {
        log.info("Updating the Hotel with Id : {}",id);
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel Does not exist with Id:"+id));
        modelMapper.map(hotelRequestDto,hotel);
        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel,HotelResponseDto.class);
    }

    @Override
    @Transactional
    public void DeleteById(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel Does not Exits"));
        hotelRepository.deleteById(id);

        // Deleting Future Inventory
        for(Room room : hotel.getRooms()){
            inventoryService.deleteFutureInventories(room);
        }


    }

    @Override
    @Transactional
    public void ActivateHotel(Long id) {
        log.info("Activating the Hotel with Id : {}",id);
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel Does not Exits"));
        hotel.setActive(true);
        hotelRepository.save(hotel);

        for(Room room : hotel.getRooms()){
            inventoryService.InitializeRoomForYear(room);
        }

    }


}
