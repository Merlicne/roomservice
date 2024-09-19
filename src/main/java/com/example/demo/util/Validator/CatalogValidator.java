package com.example.demo.util.Validator;

// import com.example.demo.entity.Furniture;
import com.example.demo.exception.BadRequestException;
import com.example.demo.model.CatalogRequest;
import com.example.demo.repository.FurnitureRepository;
import com.example.demo.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CatalogValidator {
    public final RoomRepository roomRepository;
    public final FurnitureRepository furnitureRepository;

    public static void validateid(CatalogRequest catalog) {
        if (catalog.getId() < 0) {
            throw new BadRequestException("Catalog ID must be greater than 0");
        }
    }

    public static void validateRoomId(CatalogRequest catalog) {
        if (catalog.getRoomID() == 0) {
            throw new BadRequestException("Room ID cannot be empty");
        } 
    }

    public static void validateFurnitureId(CatalogRequest catalog) {
        if (catalog.getFurnitureID() == 0) {
            throw new BadRequestException("Furniture ID cannot be empty");
        } 
    }

    public static void validateQuantity(CatalogRequest catalog) {
        if (catalog.getQuantity() < 0) {
            throw new BadRequestException("Quantity must be greater than 0");
        }
    }

    public static void validateCatalog(CatalogRequest catalog) {
        
        validateRoomId(catalog);
        validateFurnitureId(catalog);
        validateQuantity(catalog);
    }
    
}
