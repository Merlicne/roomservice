package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Furniture;

@Repository
public interface FurnitureRepository extends JpaRepository<Furniture, Integer> {
    // find all furniture which deletedAt is null
    // @Query(value = "SELECT * FROM furniture WHERE deleted_at IS NULL", nativeQuery = true)
    // public Iterable<Furniture> findFurAll();

    // // find by furniture id which deletedAt is null
    // @Query(value = "SELECT * FROM furniture WHERE id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    // public Optional<Furniture> findById(int id);

    
}
