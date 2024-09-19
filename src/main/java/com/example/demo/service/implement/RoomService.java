package com.example.demo.service.implement;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import com.example.demo.entity.Room;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.RoomModel;
import com.example.demo.repository.RoomRepository;
import com.example.demo.service.IRoomService;
import com.example.demo.util.Validator.RoomValidator;
import com.example.demo.util.converter.RoomConverter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomService implements IRoomService {

    private final RoomRepository roomRepository;

    public RoomModel createRoom(RoomModel roomModel) {
        Room r = roomRepository.save(RoomConverter.toEntity(roomModel));
        return RoomConverter.toModel(r);
    }

    public RoomModel getRoomById(int id) {
        Room r = roomRepository.findById(id).orElseThrow(() -> new NotFoundException("Room not found"));
        return RoomConverter.toModel(r);
    }

    public Iterable<RoomModel> getRoomAll(){
        return RoomConverter.toModel(roomRepository.findRentAll());
    }

    public RoomModel updateRoom(int id, RoomModel roomModel) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new NotFoundException("Room not found"));
        RoomValidator.validateRoom(roomModel);
        Room r = RoomConverter.toEntity(roomModel);
        r.setCreatedAt(room.getCreatedAt());
        r = roomRepository.save(r);

        return RoomConverter.toModel(r);
    }
     
    public void deleteRoom(int id) {
        Room r = roomRepository.findById(id).orElseThrow(() -> new NotFoundException("Room not found"));
        r.setDeletedAt(LocalDateTime.now());
        roomRepository.save(r);
    }

    public Iterable<RoomModel> getRoomByBuildingId(int buildingId) {
        Iterable<Room> rooms = roomRepository.findByBuildingId(buildingId);
        return RoomConverter.toModel(rooms);
    }
    

}
