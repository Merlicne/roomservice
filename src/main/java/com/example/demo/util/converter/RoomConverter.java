package com.example.demo.util.converter;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Room;
import com.example.demo.model.RoomModel;

public class RoomConverter {

    public static RoomModel toModel(Room entity) {
        return RoomModel.builder()
                .roomID(entity.getRoomID())
                .buildingID(entity.getBuildingID())
                .roomNo(entity.getRoomNo())
                .roomPrice(entity.getRoomPrice())
                .isRent(entity.getIsRent())
                .createdAt(entity.getCreatedAt())
                .deletedAt(entity.getDeletedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public static Room toEntity(RoomModel model) {
        return Room.builder()
                .roomID(model.getRoomID())
                .buildingID(model.getBuildingID())
                .roomNo(model.getRoomNo())
                .roomPrice(model.getRoomPrice())
                .isRent(model.getIsRent())
                .createdAt(model.getCreatedAt())
                .deletedAt(model.getDeletedAt())
                .updatedAt(model.getUpdatedAt())
                .build();
    }

    public static Iterable<RoomModel> toModel(Iterable<Room> rooms) {
        List<RoomModel> roomModels = new ArrayList<>();
        for (Room room : rooms) {
            roomModels.add(toModel(room));
        }
        return roomModels;
    }

    public static Iterable<Room> toEntity(Iterable<RoomModel> roomModels) {
        List<Room> rooms = new ArrayList<>();
        for (RoomModel roomModel : roomModels) {
            rooms.add(toEntity(roomModel));
        }
        return rooms;
    }
}
