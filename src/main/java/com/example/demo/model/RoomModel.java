package com.example.demo.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomModel {
    private int roomID ;
    private int buildingID;
    private String roomNo;
    private int roomPrice;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private LocalDateTime updatedAt;
}
