package com.example.demo.service;

import com.example.demo.entity.Room;

public interface IRoomService {
    public Room createRoom(Room room);
    public Room getRoomById(int id);
    public Iterable<Room> getRoomAll();
    public Room updateRoom(int id, Room room);
    public void deleteRoom(int id);

    // public Iterable<Room> getRoomByBuildingId(int buildingId);
}
