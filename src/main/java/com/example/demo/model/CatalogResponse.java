package com.example.demo.model;

import java.time.LocalDateTime;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CatalogResponse {
    private int id;
    private RoomModel room;
    private FurnitureModel furniture;
    private int quantity;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private LocalDateTime updatedAt;
}
