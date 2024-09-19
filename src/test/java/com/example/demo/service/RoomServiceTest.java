package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.WebClient.DormServiceWebClient;
import com.example.demo.entity.Room;
import com.example.demo.exception.BadRequestException;
import com.example.demo.middleware.JwtService;
import com.example.demo.model.BuildingModel;
import com.example.demo.model.JwtToken;
import com.example.demo.model.Role;
import com.example.demo.model.RoomModel;
import com.example.demo.repository.RoomRepository;
import com.example.demo.service.implement.RoomService;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private DormServiceWebClient dormService;
    
    @InjectMocks
    private RoomService roomService;

    @Mock
    private JwtService jwtService;

    
    private JwtToken token;
    private Room room;
    private RoomModel roomModel;
    private BuildingModel buildingModel;

    @BeforeEach
    void setUp() {
        roomModel = RoomModel.builder()
            .buildingID(1)
            .roomID(0)
            .roomNo("105")
            .roomPrice(4500)
            .build();

        room = Room.builder()
            .buildingID(1)
            .roomID(0)
            .roomNo("105")
            .roomPrice(4500)
            .build();

        token = JwtToken.builder()
            .token("token")
            .expiresIn(1000)
            .build();

        buildingModel = BuildingModel.builder()
            .buildingID(1)
            .buildingName("A")
            .elecPrice(8)
            .waterPrice(150)
            .build();
        
    }

    @Test
    void testCreateRoom() {
        when(jwtService.extractRole(token.getToken())).thenReturn(Role.ADMIN);
        when(roomRepository.save(room)).thenReturn(room);
        when(dormService.getBuildingById(1, token)).thenReturn(buildingModel);

        RoomModel rm = roomService.createRoom(roomModel, token);

        assertNotNull(rm);
        assertEquals(roomModel, rm);
    }

    @Test
    void testCreateRoomInvalid(){
        when(dormService.getBuildingById(1, token)).thenReturn(buildingModel);
        when(jwtService.extractRole(token.getToken())).thenReturn(Role.ADMIN);

        roomModel.setRoomNo(null);
        assertThrows(BadRequestException.class, () -> {
            roomService.createRoom(roomModel, token);
        });
        roomModel.setRoomNo("105");

        roomModel.setRoomPrice(-1);
        assertThrows(BadRequestException.class, () -> {
            roomService.createRoom(roomModel, token);
        });
        roomModel.setRoomPrice(4500);

        roomModel.setBuildingID(-1);
        assertThrows(BadRequestException.class, () -> {
            roomService.createRoom(roomModel, token);
        });

        verify(roomRepository, times(0)).save(room);

    }

    @Test
    void testGetRoomById() {
        
        when(jwtService.extractRole(token.getToken())).thenReturn(Role.ADMIN);
        when(roomRepository.findById(0)).thenReturn(java.util.Optional.of(room));

        RoomModel rm = roomService.getRoomById(0, token);

        assertNotNull(rm);
        assertEquals(roomModel, rm);
    }

    @Test
    void testGetRoomAll() {
        
        when(jwtService.extractRole(token.getToken())).thenReturn(Role.ADMIN);
        when(roomRepository.findAll()).thenReturn(java.util.List.of(room));

        Iterable<RoomModel> rms = roomService.getRoomAll(token);

        assertNotNull(rms);
        assertEquals(java.util.List.of(roomModel), rms);
    }

    @Test
    void testUpdateRoom() {
        
        when(jwtService.extractRole(token.getToken())).thenReturn(Role.ADMIN);
        when(roomRepository.findById(0)).thenReturn(java.util.Optional.of(room));
        when(roomRepository.save(room)).thenReturn(room);

        RoomModel rm = roomService.updateRoom(0, roomModel, token);

        assertNotNull(rm);
        assertEquals(roomModel, rm);
    }

    @Test
    void testDeleteRoom() {
        
        when(jwtService.extractRole(token.getToken())).thenReturn(Role.ADMIN);
        when(roomRepository.findById(0)).thenReturn(java.util.Optional.of(room));

        roomService.deleteRoom(0, token);

        verify(roomRepository).save(room);
    }

    @Test
    void testGetRoomByBuildingId() {
        
        when(jwtService.extractRole(token.getToken())).thenReturn(Role.ADMIN);
        when(roomRepository.findByBuildingId(1)).thenReturn(java.util.List.of(room));

        Iterable<RoomModel> rms = roomService.getRoomByBuildingId(1, token);

        assertNotNull(rms);
        assertEquals(java.util.List.of(roomModel), rms);
    }

    @Test
    void testGetRoomByBuildingIdNotFound() {
        
        when(jwtService.extractRole(token.getToken())).thenReturn(Role.ADMIN);
        when(roomRepository.findByBuildingId(1)).thenReturn(java.util.List.of());

        Iterable<RoomModel> rms = roomService.getRoomByBuildingId(1, token);

        assertNotNull(rms);
        assertEquals(java.util.List.of(), rms);
    }
}
