package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Rooms")
@Builder
public class Rooms {
    private int roomID ;
    private String phaseID;
    private String roomNo;
    private int roomFloor;
    private int roomPrice;

}
