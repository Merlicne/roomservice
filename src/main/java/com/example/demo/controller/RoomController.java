package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

// import com.example.demo.entity.Room;
import com.example.demo.service.IRoomService;
import com.example.demo.model.JwtToken;
import com.example.demo.model.ResponseBody;
import com.example.demo.model.RoomModel;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class RoomController {
    private final IRoomService roomService;

    @GetMapping("/room/{id}")
    public ResponseBody<RoomModel> getRoomById(@RequestHeader("Authorization") String token, @PathVariable int id) {
        token = token.substring(7); 
        JwtToken jwtToken = JwtToken.builder().token(token).build();

        RoomModel r = roomService.getRoomById(id, jwtToken);
        return new ResponseBody<>(HttpStatus.OK, "Room found", r);
    }

    @GetMapping("/room")
    public ResponseBody<Iterable<RoomModel>> getRoomAll(@RequestHeader("Authorization") String token) {
        token = token.substring(7); 
        JwtToken jwtToken = JwtToken.builder().token(token).build();

        Iterable<RoomModel> r = roomService.getRoomAll(jwtToken);
        return new ResponseBody<>(HttpStatus.OK, "Room found", r);
    }

    @PostMapping("/room")
    public ResponseBody<RoomModel> createRoom(@RequestHeader("Authorization") String token,  @RequestBody RoomModel roomModel) {
        token = token.substring(7); 
        JwtToken jwtToken = JwtToken.builder().token(token).build();

        RoomModel r = roomService.createRoom(roomModel, jwtToken);
        return new ResponseBody<>(HttpStatus.CREATED, "Room created", r);
    }

    @PutMapping("/room/{id}")
    public ResponseBody<RoomModel> updateRoom(@RequestHeader("Authorization") String token, @PathVariable int id, @RequestBody RoomModel roomModel) {
        token = token.substring(7); 
        JwtToken jwtToken = JwtToken.builder().token(token).build();
        
        RoomModel r = roomService.updateRoom(id, roomModel, jwtToken);
        return new ResponseBody<>(HttpStatus.OK, "Room updated", r);
    }

    @DeleteMapping("/room/{id}")
    public ResponseBody<Void> deleteRoom(@RequestHeader("Authorization") String token, @PathVariable int id) {
        token = token.substring(7); 
        JwtToken jwtToken = JwtToken.builder().token(token).build();

        roomService.deleteRoom(id, jwtToken);
        return new ResponseBody<>(HttpStatus.OK, "Room deleted", null);
    }

    @GetMapping("/room/building/{buildingId}")
    public ResponseBody<Iterable<RoomModel>> getRoomByBuildingId(@RequestHeader("Authorization") String token, @PathVariable int buildingId) {
        token = token.substring(7); 
        JwtToken jwtToken = JwtToken.builder().token(token).build();

        Iterable<RoomModel> r = roomService.getRoomByBuildingId(buildingId, jwtToken);
        return new ResponseBody<>(HttpStatus.OK, "Room found", r);
    }

    

}
