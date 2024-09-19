package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// import com.example.demo.entity.Room;
import com.example.demo.service.IRoomService;
import com.example.demo.model.ResponseBody;
import com.example.demo.model.RoomModel;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class RoomController {
    private final IRoomService roomService;

    @GetMapping("/test")
    public ResponseBody<String> test() {
        return new ResponseBody<>(HttpStatus.OK, "Test success", "Test success");
    }

    @GetMapping("/room/{id}")
    public ResponseBody<RoomModel> getRoomById(@PathVariable int id) {
        RoomModel r = roomService.getRoomById(id);
        return new ResponseBody<>(HttpStatus.OK, "Room found", r);
    }

    @GetMapping("/room")
    public ResponseBody<Iterable<RoomModel>> getRoomAll() {
        Iterable<RoomModel> r = roomService.getRoomAll();
        return new ResponseBody<>(HttpStatus.OK, "Room found", r);
    }

    @PostMapping("/room")
    public ResponseBody<RoomModel> createRoom(@RequestBody RoomModel roomModel) {
        RoomModel r = roomService.createRoom(roomModel);
        return new ResponseBody<>(HttpStatus.CREATED, "Room created", r);
    }

    @PutMapping("/room/{id}")
    public ResponseBody<RoomModel> updateRoom(@PathVariable int id, @RequestBody RoomModel roomModel) {
        RoomModel r = roomService.updateRoom(id, roomModel);
        return new ResponseBody<>(HttpStatus.OK, "Room updated", r);
    }

    @DeleteMapping("/room/{id}")
    public ResponseBody<Void> deleteRoom(@PathVariable int id) {
        roomService.deleteRoom(id);
        return new ResponseBody<>(HttpStatus.OK, "Room deleted", null);
    }

    @GetMapping("/room/building/{buildingId}")
    public ResponseBody<Iterable<RoomModel>> getRoomByBuildingId(@PathVariable int buildingId) {
        Iterable<RoomModel> r = roomService.getRoomByBuildingId(buildingId);
        return new ResponseBody<>(HttpStatus.OK, "Room found", r);
    }

    

}
