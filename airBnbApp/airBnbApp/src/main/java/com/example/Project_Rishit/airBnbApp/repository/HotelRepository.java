package com.example.Project_Rishit.airBnbApp.repository;

import com.example.Project_Rishit.airBnbApp.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {

}
