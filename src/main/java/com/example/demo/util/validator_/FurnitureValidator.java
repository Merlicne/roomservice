package com.example.demo.util.validator_;

import java.time.LocalDateTime;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.FurnitureModel;


public class FurnitureValidator {
    public static void validateFurnitureName(String name) {
        if (name == null || name.isEmpty()) {
            throw new BadRequestException("Furniture name cannot be empty");
        }
    }

    // validate created at
    public static void validateCreatedAt(LocalDateTime createdAt) {
        if (createdAt == null ) {
            throw new BadRequestException("Created at must not be empty");
        }
    }

    // validate updated at
    public static void validateUpdatedAt(LocalDateTime updatedAt) {
        if (updatedAt == null) {
            throw new BadRequestException("Updated at must not be empty");
        }
    }

    // validate furniture
    public static void validateFurniture(FurnitureModel furniture) {
        validateFurnitureName(furniture.getName());
        // validateCreatedAt(furniture.getCreatedAt());
        // validateUpdatedAt(furniture.getUpdatedAt());
    }
}
