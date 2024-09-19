package com.example.demo.util.Validator;

import java.time.LocalDateTime;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.RoomModel;

public class RoomValidator {
    public static void validateRoomId(int id) {
        if (id < 0) {
            throw new BadRequestException("Room ID must be greater than 0");
        }
    }

    public static void validateBuildingId(int id) {
        if (id < 0) {
            throw new BadRequestException("Building ID must be greater than 0");
        }
    }


    public static void validateRoomNo(String roomNo) {
        if (roomNo == null || roomNo.isEmpty()) {
            throw new BadRequestException("Room number must not be empty");
        }
    }

    public static void validateRoomPrice(int roomPrice) {
        if (roomPrice < 0) {
            throw new BadRequestException("Room price must be greater than 0");
        }
    }

    public static void validateCreatedAt(LocalDateTime createdAt) {
        if (createdAt == null) {
            throw new BadRequestException("Created at must not be empty");
        }
    }

    public static void validateUpdatedAt(LocalDateTime updatedAt) {
        if (updatedAt == null) {
            throw new BadRequestException("Updated at must not be empty");
        }
    }

    public static void validateRoom(RoomModel room) {
        validateRoomId(room.getRoomID());
        validateBuildingId(room.getBuildingID());
        validateRoomNo(room.getRoomNo());
        validateRoomPrice(room.getRoomPrice());
        // validateCreatedAt(room.getCreatedAt());
        // validateUpdatedAt(room.getUpdatedAt());

    }
}
