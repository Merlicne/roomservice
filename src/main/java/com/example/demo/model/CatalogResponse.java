package com.example.demo.model;

import java.time.LocalDateTime;

import com.example.demo.entity.Furniture;
// import com.example.demo.entity.Room;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CatalogResponse {
    private int id;
    private Room room;

    @Data
    @Builder
    public static class Room {
        private int id;
        private String roomNo;
    }

    private Furniture furniture;

    @Data
    @Builder
    public static class Furniture {
        private int id;
        private String name;
    }

    private int quantity;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private LocalDateTime updatedAt;
}
