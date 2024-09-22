package com.example.demo.service;

import org.springframework.retry.annotation.Retryable;

import com.example.demo.model.JwtToken;
// import com.example.demo.entity.Room;
import com.example.demo.model.RoomModel;

public interface IRoomService {
    @Retryable(retryFor = Exception.class, maxAttempts = 5)
    public RoomModel createRoom(RoomModel roomModel, JwtToken token);

    @Retryable(retryFor = Exception.class, maxAttempts = 5)
    public RoomModel getRoomById(int id, JwtToken token);

    @Retryable(retryFor = Exception.class, maxAttempts = 5)
    public Iterable<RoomModel> getRoomAll(JwtToken token);

    @Retryable(retryFor = Exception.class, maxAttempts = 5)
    public RoomModel updateRoom(int id, RoomModel roomModel, JwtToken token);

    @Retryable(retryFor = Exception.class, maxAttempts = 5)
    public void deleteRoom(int id, JwtToken token);

    @Retryable(retryFor = Exception.class, maxAttempts = 5)
    public Iterable<RoomModel> getRoomByBuildingId(int buildingId, JwtToken token);
}
