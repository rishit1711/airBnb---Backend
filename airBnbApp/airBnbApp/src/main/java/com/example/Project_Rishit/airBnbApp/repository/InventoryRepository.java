package com.example.Project_Rishit.airBnbApp.repository;

import com.example.Project_Rishit.airBnbApp.entity.Hotel;
import com.example.Project_Rishit.airBnbApp.entity.Inventory;
import com.example.Project_Rishit.airBnbApp.entity.Room;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {

    void deleteByDateAfter(LocalDate date, Room room);
    @Query("""
SELECT DISTINCT i.hotel
FROM Inventory i
WHERE i.city = :city
AND i.date BETWEEN :startDate AND :endDate
AND i.closed = false
AND (i.totalCount - i.bookedCount) >= :roomsCount
GROUP BY i.hotel, i.room
HAVING COUNT(i.date) = :dateCount
""")
    Page<Hotel> findHotelsWithAvailableInventory(
            @Param("city") String city,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param(("roomsCount"))Integer roomsCount,
            @Param(("dateCount")) Long dateCount,
            Pageable pageable
    );
    @Query("""
            SELECT i
            FROM Inventory i
            WHERE i.room.id =: roomId
            AND i.date BETWEEN :startDate AND :endDate
            AND (i.totalCount - i.bookedCount) >= :roomsCount
            AND i.closed = false
            
      
            
            """)
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Inventory> findAndLockAvailableInventory(
            @Param("roomId") Long roomID,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param(("roomsCount"))Integer roomsCount

    );
}
