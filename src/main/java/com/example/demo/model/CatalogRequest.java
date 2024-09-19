package com.example.demo.model;

import java.time.LocalDateTime;

import com.example.demo.entity.Furniture;
import com.example.demo.entity.Room;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CatalogRequest {
    private int id;
    private int roomID;
    private int furnitureID;
    private int quantity;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private LocalDateTime updatedAt;
}
