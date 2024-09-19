package com.example.demo.service;

import com.example.demo.model.JwtToken;
// import com.example.demo.entity.Room;
import com.example.demo.model.RoomModel;

public interface IRoomService {
    public RoomModel createRoom(RoomModel roomModel, JwtToken token);
    public RoomModel getRoomById(int id, JwtToken token);
    public Iterable<RoomModel> getRoomAll(JwtToken token);
    public RoomModel updateRoom(int id, RoomModel roomModel, JwtToken token);
    public void deleteRoom(int id, JwtToken token);
    public Iterable<RoomModel> getRoomByBuildingId(int buildingId, JwtToken token);
}
