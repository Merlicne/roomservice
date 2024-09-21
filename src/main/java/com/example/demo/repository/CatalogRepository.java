package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Catalog;

import java.util.Optional;


@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Integer> {
    // find by id which deletedAt is null
    @Query(value = "SELECT * FROM catalog WHERE id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    public Optional<Catalog> findById(int id);

    // find all catalog which deletedAt is null
    @Query(value = "SELECT * FROM catalog WHERE deleted_at IS NULL", nativeQuery = true)
    public Iterable<Catalog> findRentAll();

    // findByRoomId which deletedAt is null
    @Query(value = "SELECT * FROM catalog WHERE roomID = ?1 AND deleted_at IS NULL", nativeQuery = true)
    public Iterable<Catalog> findByRoomId(int roomId);


}
