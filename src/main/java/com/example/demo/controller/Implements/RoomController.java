package com.example.demo.controller.Implements;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.IRoomController;
import com.example.demo.entity.Room;
import com.example.demo.service.IRoomService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class RoomController implements IRoomController {
    private final IRoomService roomService;

    @GetMapping("/room")
    public @ResponseBody Iterable<Room> getRoomAll() {
        return roomService.getRoomAll();
    }

    @GetMapping("/room/{id}")    
    public @ResponseBody Room getRoomById(@PathVariable int id) {
        return roomService.getRoomById(id);
    }

    // @GetMapping("/building/{buildingId}/room")
    // public @ResponseBody Iterable<Room> getRoomByBuildingId (@PathVariable int buildingId){
    //     return roomService.getRoomByBuildingId(buildingId);
    // }
    

    @PostMapping("/room")
    public @ResponseBody Room createRoom(@RequestBody Room room) {
        return roomService.createRoom(room);
    }

    @PutMapping("/room/{id}")
    public @ResponseBody Room updateRoom(@PathVariable int id, @RequestBody Room room) {
        return roomService.updateRoom(id, room);
    }

    @DeleteMapping("/room/{id}")
    public void deleteRoom(int id) {
        roomService.deleteRoom(id);
    }

    

}
