package com.example.Project_Rishit.airBnbApp.repository;

import com.example.Project_Rishit.airBnbApp.entity.Booking;
import com.example.Project_Rishit.airBnbApp.entity.Hotel;
import com.example.Project_Rishit.airBnbApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {

    List<Hotel> findByOwner(User user);

}
