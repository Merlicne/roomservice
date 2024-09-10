package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Room;
import java.util.List;


@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    // List<Room> findByBuildingID(int buildingID);
    List<Room> findByRoomFloor(int roomFloor);

}
