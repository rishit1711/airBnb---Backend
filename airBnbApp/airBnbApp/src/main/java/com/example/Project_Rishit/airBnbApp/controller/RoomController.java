package com.example.Project_Rishit.airBnbApp.controller;

import com.example.Project_Rishit.airBnbApp.dto.RoomRequestDto;
import com.example.Project_Rishit.airBnbApp.dto.RoomResponseDto;
import com.example.Project_Rishit.airBnbApp.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin/hotels/{hotelId}/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomResponseDto> CreateRoomInHotel(@PathVariable Long hotelId,@RequestBody RoomRequestDto roomRequestDto){
        RoomResponseDto responseDto = roomService.CreateNewRoom(hotelId,roomRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);

    }
    @GetMapping
    public ResponseEntity<List<RoomResponseDto>> GetAllRooms(@PathVariable Long hotelId){
        List<RoomResponseDto> responseDto=roomService.getAllRoomsInHotel(hotelId);
        return ResponseEntity.ok(responseDto);
    }
    @GetMapping(path = "/{roomId}")
    public ResponseEntity<RoomResponseDto> GetRoomById(@PathVariable Long roomId){
       RoomResponseDto responseDto= roomService.GetRoomById(roomId);
       return ResponseEntity.ok(responseDto);
    }
    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> DeleteRoom(@PathVariable Long roomId){
        roomService.DeleteRoomById(roomId);
        return ResponseEntity.noContent().build();
    }

}
