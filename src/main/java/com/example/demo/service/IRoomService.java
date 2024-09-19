package com.example.demo.service;

// import com.example.demo.entity.Room;
import com.example.demo.model.RoomModel;

public interface IRoomService {
    public RoomModel createRoom(RoomModel roomModel);
    public RoomModel getRoomById(int id);
    public Iterable<RoomModel> getRoomAll();
    public RoomModel updateRoom(int id, RoomModel roomModel);
    public void deleteRoom(int id);
    public Iterable<RoomModel> getRoomByBuildingId(int buildingId);
}
