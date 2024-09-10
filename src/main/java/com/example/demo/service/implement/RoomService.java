package com.example.demo.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Room;
import com.example.demo.repository.RoomRepository;
import com.example.demo.service.IRoomService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomService implements IRoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room createRoom(Room room){
        return roomRepository.save(room);
    }

    @Override
    public Room getRoomById(int id){
        return roomRepository.findById(id).get();
    }

    @Override
    public Iterable<Room> getRoomAll(){
        return roomRepository.findAll();
    }

    @Override
    public Room updateRoom(int id, Room room){
        Room roomUpdate = roomRepository.findById(id).get();
        return roomRepository.save(roomUpdate);
    }

    @Override
    public void deleteRoom(int id){
        // roomRepository.deleteById(id);
        Room room = roomRepository.findById(id).get();
        room.setDeleted(true);
    }

    // @Override
    // public Iterable<Room> getRoomByBuildingId(int buildingId){
    //     return roomRepository.findByBuildingID(buildingId);
    // }

}
