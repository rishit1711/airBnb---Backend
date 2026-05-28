package com.example.Project_Rishit.airBnbApp.repository;

import com.example.Project_Rishit.airBnbApp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
}
