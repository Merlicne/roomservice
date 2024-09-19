package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.FurnitureModel;
import com.example.demo.model.ResponseBody;
import com.example.demo.service.IFurnitureService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FurnitureController {
    private final IFurnitureService furnitureService;

    // @GetMapping("/furniture")
    // public ResponseBody<Iterable<FurnitureModel>> getFurnitureAll() {
    //     Iterable<FurnitureModel> f = furnitureService.getFurnitureAll();
    //     return new ResponseBody<>(HttpStatus.OK, "Furniture found", f);
    // }

    @GetMapping("/furniture/{id}")
    public ResponseBody<FurnitureModel> getFurnitureById(@PathVariable int id) {
        FurnitureModel f = furnitureService.getFurnitureById(id);
        return new ResponseBody<>(HttpStatus.OK, "Furniture found", f);
    }

    @PostMapping("/furniture")
    public ResponseBody<FurnitureModel> createFurniture(@RequestBody FurnitureModel furnitureModel) {
        FurnitureModel f = furnitureService.createFurniture(furnitureModel);
        return new ResponseBody<>(HttpStatus.CREATED, "Furniture created", f);
    }
    
    @PutMapping("/furniture/{id}")
    public ResponseBody<FurnitureModel> updateFurniture(@PathVariable int id, @RequestBody FurnitureModel furnitureModel) {
        FurnitureModel f = furnitureService.updateFurniture(id, furnitureModel);
        return new ResponseBody<>(HttpStatus.OK, "Furniture updated", f);
    }

    @DeleteMapping("/furniture/{id}")
    public ResponseBody<Void> deleteFurniture(@PathVariable int id) {
        furnitureService.deleteFurniture(id);
        return new ResponseBody<>(HttpStatus.OK, "Furniture deleted", null);
    }
}
