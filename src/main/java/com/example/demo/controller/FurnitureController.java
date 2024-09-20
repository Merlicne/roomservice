package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.FurnitureModel;
import com.example.demo.model.JwtToken;
import com.example.demo.model.ResponseBody;
import com.example.demo.service.IFurnitureService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FurnitureController {
    private final IFurnitureService furnitureService;

    @GetMapping("/furniture")
    public ResponseBody<Iterable<FurnitureModel>> getFurnitureAll(@RequestHeader("Authorization") String token) {
        token = token.substring(7); 
        JwtToken jwtToken = JwtToken.builder().token(token).build();

        Iterable<FurnitureModel> f = furnitureService.getFurnitureAll(jwtToken);
        return new ResponseBody<>(HttpStatus.OK.value(), "Furniture found", f);
    }

    @GetMapping("/furniture/{id}")
    public ResponseBody<FurnitureModel> getFurnitureById(@RequestHeader("Authorization") String token, @PathVariable int id) {
        token = token.substring(7); 
        JwtToken jwtToken = JwtToken.builder().token(token).build();

        FurnitureModel f = furnitureService.getFurnitureById(id, jwtToken);
        return new ResponseBody<>(HttpStatus.OK.value(), "Furniture found", f);
    }

    @PostMapping("/furniture")
    public ResponseBody<FurnitureModel> createFurniture(@RequestHeader("Authorization") String token, @RequestBody FurnitureModel furnitureModel) {
        token = token.substring(7); 
        JwtToken jwtToken = JwtToken.builder().token(token).build();
        
        FurnitureModel f = furnitureService.createFurniture(furnitureModel, jwtToken);
        return new ResponseBody<>(HttpStatus.CREATED.value(), "Furniture created", f);
    }
    
    @PutMapping("/furniture/{id}")
    public ResponseBody<FurnitureModel> updateFurniture(@RequestHeader("Authorization") String token, @PathVariable int id, @RequestBody FurnitureModel furnitureModel) {
        token = token.substring(7); 
        JwtToken jwtToken = JwtToken.builder().token(token).build();

        FurnitureModel f = furnitureService.updateFurniture(id, furnitureModel, jwtToken);
        return new ResponseBody<>(HttpStatus.OK.value(), "Furniture updated", f);
    }

    @DeleteMapping("/furniture/{id}")
    public ResponseBody<Void> deleteFurniture(@RequestHeader("Authorization") String token, @PathVariable int id) {
        token = token.substring(7); 
        JwtToken jwtToken = JwtToken.builder().token(token).build();

        furnitureService.deleteFurniture(id, jwtToken);
        return new ResponseBody<>(HttpStatus.OK.value(), "Furniture deleted", null);
    }
}
