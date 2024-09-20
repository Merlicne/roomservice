package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Room;

import java.util.Optional;


@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    
    // find by building id which deletedAt is null
    @Query(value = "SELECT * FROM rooms WHERE building_id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    public Iterable<Room> findByBuildingId(int buildingId);

    // find by room id which deletedAt is null
    @Query(value = "SELECT * FROM rooms WHERE id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    public Optional<Room> findById(int id);

    // find all room which deletedAt is null
    @Query(value = "SELECT * FROM rooms WHERE deleted_at IS NULL", nativeQuery = true)
    public Iterable<Room> findRoomAll();
}
