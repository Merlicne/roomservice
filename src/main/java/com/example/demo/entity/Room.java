package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
// import org.hibernate.annotations.CreationTimestamp;
// import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "Rooms")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "roomID")
    private int roomID ;

    // @ManyToOne
    // @JoinColumn(name = "building_ID", nullable = false)
    // @JsonBackReference
    private int buildingID;

    @Column(name = "roomNo")
    private String roomNo;

    @Column(name = "roomFloor")
    private int roomFloor;

    @Column(name = "roomPrice")
    private int roomPrice;

    @Builder.Default
    @Column(name = "isDeleted")
    private boolean isDeleted = false;

// ยังแก้ไม่ได้
    // @CreationTimestamp
    // @Column(name = "createdAt")
    // private Instant createdAt;

    // @UpdateTimestamp
    // @Column(name = "updatedAt")
    // private Instant updatedAt;





}
