package com.example.demo.entity;

import java.time.Instant;
// import java.util.List;
import java.util.Date;

// import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "Buildings")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "buildingID")
    private int buildingID;

    @Column(name = "buildingName", nullable = false)
    private String buildingName;

    // ยังแก้ไม่ได้
    // @CreationTimestamp
    // @CreatedDate
    // @CreatedBy
    // @Column(name = "createdAt", updatable = false)
    // private Date createdAt;
    // private Instant createdAt;

    
    // @UpdateTimestamp
    // @Column(name = "updatedAt", nullable = false)
    // private Instant updatedAt;

    @Builder.Default
    @Column(name = "isDeleted", nullable = false)
    private boolean isDeleted = false;

}
