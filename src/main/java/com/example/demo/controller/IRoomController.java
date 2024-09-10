package com.example.demo.controller;

import com.example.demo.entity.Room;

public interface IRoomController {
    public Iterable<Room> getRoomAll ();
    public Room getRoomById (int id);
    public Room createRoom (Room room);
    public Room updateRoom (int id, Room room);
    public void deleteRoom (int id);

    // public Iterable<Room> getRoomByBuildingId (int buildingId);
}
