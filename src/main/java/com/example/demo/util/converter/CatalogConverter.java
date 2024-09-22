package com.example.demo.util.converter;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Catalog;
import com.example.demo.entity.Furniture;
import com.example.demo.entity.Room;
import com.example.demo.model.CatalogRequest;
import com.example.demo.model.CatalogResponse;
import com.example.demo.model.FurnitureModel;
import com.example.demo.model.RoomModel;

// @RequiredArgsConstructor
public class CatalogConverter {
    
    // save
    public static Catalog toEntity(CatalogRequest catalogModel) {
        return Catalog.builder()
                .id(catalogModel.getId())
                .furnitureID(catalogModel.getFurnitureID())
                .roomID(catalogModel.getRoomID())
                .quantity(catalogModel.getQuantity())
                .createdAt(catalogModel.getCreatedAt())
                .updatedAt(catalogModel.getUpdatedAt())
                .deletedAt(catalogModel.getDeletedAt())
                .build();
    }

    // show result
    public static CatalogResponse toModel(Catalog catalog, Room room, Furniture furniture) {
        return CatalogResponse.builder()
                .id(catalog.getId())
                .room(RoomModel.builder()
                        .roomID(room.getRoomID())
                        .roomNo(room.getRoomNo())
                        .roomPrice(room.getRoomPrice())
                        .build())
                // .room(CatalogResponse.Room.builder()
                //         .id(room.getRoomID())
                //         .roomNo(room.getRoomNo())
                //         .build())
                .furniture(FurnitureModel.builder()
                        .id(furniture.getFurID())
                        .name(furniture.getName())
                        .build())
                .quantity(catalog.getQuantity())
                .createdAt(catalog.getCreatedAt())
                .updatedAt(catalog.getUpdatedAt())
                .deletedAt(catalog.getDeletedAt())
                .build();
    }

//     public static List<CatalogResponse> toModels(Catalog catalog) {
//         List<CatalogResponse> catalogResponses = new ArrayList<>();
//         for (Catalog c : catalog) {
//                 Room room = roomRepository.findById(c.getRoomID()).orElseThrow(() -> new NotFoundException("Room not found"));
//                 Furniture furniture = furnitureRepository.findById(c.getFurnitureID()).orElseThrow(() -> new NotFoundException("Furniture not found"));
//                 catalogResponses.add(toModel(c, room, furniture));
//         }
//         return catalogResponses;
//     }

    
}
