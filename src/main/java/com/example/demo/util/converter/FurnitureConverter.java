package com.example.demo.util.converter;

import java.util.List;
import java.util.ArrayList;

import com.example.demo.entity.Furniture;
import com.example.demo.model.FurnitureModel;

public class FurnitureConverter {
    public static FurnitureModel toModel(Furniture furniture) {
        return FurnitureModel.builder()
                .id(furniture.getFurID())
                .name(furniture.getName())
                .createdAt(furniture.getCreatedAt())
                .updatedAt(furniture.getUpdatedAt())
                .deletedAt(furniture.getDeletedAt())
                .build();
    }

    public static Furniture toEntity(FurnitureModel furnitureModel) {
        return Furniture.builder()
                .furID(furnitureModel.getId())
                .name(furnitureModel.getName())
                .createdAt(furnitureModel.getCreatedAt())
                .updatedAt(furnitureModel.getUpdatedAt())
                .deletedAt(furnitureModel.getDeletedAt())
                .build();
    }

    public static Iterable<FurnitureModel> toModel(Iterable<Furniture> furnitures) {
        List<FurnitureModel> furnitureModels = new ArrayList<>();
        for (Furniture f : furnitures) {
            furnitureModels.add(toModel(f));
        }

        return furnitureModels;
    }

    public static Iterable<Furniture> toEntity(Iterable<FurnitureModel> furnitureModels) {
        List<Furniture> furnitures = new ArrayList<>();
        for (FurnitureModel fm : furnitureModels) {
            furnitures.add(toEntity(fm));
        }

        return furnitures;
    }
}
