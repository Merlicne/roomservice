package com.example.demo.model;

import java.time.LocalDateTime;

import com.example.demo.enumerate.RentStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomModel {
    private int roomID ;
    private int buildingID;
    private String roomNo;
    private int roomPrice;
    private RentStatus isRent;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private LocalDateTime updatedAt;
}
