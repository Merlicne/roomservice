package com.example.demo.service.implement;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.WebClient.DormServiceWebClient;
import com.example.demo.entity.Room;
import com.example.demo.enumerate.RoomStatus;
import com.example.demo.exception.NotFoundException;
import com.example.demo.middleware.JwtService;
import com.example.demo.model.JwtToken;
import com.example.demo.model.Role;
import com.example.demo.model.RoomModel;
import com.example.demo.repository.RoomRepository;
import com.example.demo.service.IRoomService;
import com.example.demo.util.converter.RoomConverter;
import com.example.demo.util.validator.RoleValidation;
import com.example.demo.util.validator.RoomValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService implements IRoomService {

    private final RoomRepository roomRepository;
    private final DormServiceWebClient dormService;
    private final JwtService jwtService;

    public RoomModel createRoom(RoomModel roomModel, JwtToken token) {
        Role role = jwtService.extractRole(token.getToken());
        RoleValidation.allowRoles(role, Role.ADMIN);

        RoomValidator.validateRoom(roomModel);
        dormService.getBuildingById(roomModel.getBuildingID(), token).orElseThrow(() -> new NotFoundException("Building not found"));
        Room room = RoomConverter.toEntity(roomModel);
        room.setRoomStatus(RoomStatus.NOT_RENTED);
        Room r = roomRepository.save(room);
        return RoomConverter.toModel(r);
    }

    public RoomModel getRoomById(int id, JwtToken token) {
        Role role = jwtService.extractRole(token.getToken());
        RoleValidation.allowRoles(role, Role.ADMIN, Role.TENANT);

        Room r = roomRepository.findById(id).orElseThrow(() -> new NotFoundException("Room not found"));
        return RoomConverter.toModel(r);
    }

    public Iterable<RoomModel> getRoomAll(JwtToken token) {
        Role role = jwtService.extractRole(token.getToken());
        RoleValidation.allowRoles(role, Role.ADMIN);

        return RoomConverter.toModel(roomRepository.findRoomAll());
    }

    public RoomModel updateRoom(int id, RoomModel roomModel, JwtToken token) {
        Role role = jwtService.extractRole(token.getToken());
        RoleValidation.allowRoles(role, Role.ADMIN);

        Room room = roomRepository.findById(id).orElseThrow(() -> new NotFoundException("Room not found"));
        roomModel.setRoomID(id);
        RoomValidator.validateRoom(roomModel);
        Room r = RoomConverter.toEntity(roomModel);
        r.setCreatedAt(room.getCreatedAt());
        if (r.getRoomStatus() == null) {
            r.setRoomStatus(room.getRoomStatus());
        }
        r = roomRepository.save(r);

        return RoomConverter.toModel(r);
    }

    public void deleteRoom(int id, JwtToken token) {
        Role role = jwtService.extractRole(token.getToken());
        RoleValidation.allowRoles(role, Role.ADMIN);

        Room r = roomRepository.findById(id).orElseThrow(() -> new NotFoundException("Room not found"));
        r.setDeletedAt(LocalDateTime.now());
        roomRepository.save(r);
    }

    public Iterable<RoomModel> getRoomByBuildingId(int buildingId, JwtToken token) {
        Role role = jwtService.extractRole(token.getToken());
        RoleValidation.allowRoles(role, Role.ADMIN);

        Iterable<Room> rooms = roomRepository.findByBuildingId(buildingId);
        return RoomConverter.toModel(rooms);
    }
    

}
