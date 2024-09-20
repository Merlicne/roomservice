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

import com.example.demo.model.CatalogRequest;
import com.example.demo.model.CatalogResponse;
import com.example.demo.model.JwtToken;
import com.example.demo.model.ResponseBody;
import com.example.demo.service.implement.CatalogService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CatalogController {
    private final CatalogService catalogService;

    @GetMapping("/catalog")
    public ResponseBody<Iterable<CatalogResponse>> getCatalogAll(@RequestHeader("Authorization") String token) {
        token = token.substring(7); 
        JwtToken jwtToken = JwtToken.builder().token(token).build();

        Iterable<CatalogResponse> cats = catalogService.getCatalogAll(jwtToken);
        return new ResponseBody<>(HttpStatus.OK, "Catalog found", cats);
    }

    @GetMapping("/catalog/{id}")
    public ResponseBody<CatalogResponse> getCatalogById(@RequestHeader("Authorization") String token, @PathVariable int id) {
        token = token.substring(7); 
        JwtToken jwtToken = JwtToken.builder().token(token).build();

        CatalogResponse cat = catalogService.getCatalogById(id, jwtToken);
        return new ResponseBody<>(HttpStatus.OK, "Catalog found", cat);
    }

    @GetMapping("/catalog/room/{roomId}")
    public ResponseBody<Iterable<CatalogResponse>> getCatalogByRoomId(@RequestHeader("Authorization") String token, @PathVariable int roomId) {
        token = token.substring(7); 
        JwtToken jwtToken = JwtToken.builder().token(token).build();

        Iterable<CatalogResponse> cats = catalogService.getCatalogByRoomId(roomId, jwtToken);
        return new ResponseBody<>(HttpStatus.OK, "Catalog found", cats);
    }

    @PostMapping("/catalog")
    public ResponseBody<CatalogResponse> createCatalog(@RequestHeader("Authorization") String token, @RequestBody CatalogRequest catalogModel) {
        token = token.substring(7); 
        JwtToken jwtToken = JwtToken.builder().token(token).build();

        CatalogResponse cat = catalogService.createCatalog(catalogModel, jwtToken);
        return new ResponseBody<>(HttpStatus.CREATED, "Catalog created", cat);
    }

    @PutMapping("/catalog/{id}")
    public ResponseBody<CatalogResponse> updateCatalog(@RequestHeader("Authorization") String token, @PathVariable int id, @RequestBody CatalogRequest catalogModel) {
        token = token.substring(7); 
        JwtToken jwtToken = JwtToken.builder().token(token).build();

        CatalogResponse cat = catalogService.updateCatalog(id, catalogModel, jwtToken);
        return new ResponseBody<>(HttpStatus.OK, "Catalog updated", cat);
    }

    @DeleteMapping("/catalog/{id}")
    public ResponseBody<Void> deleteCatalog(@RequestHeader("Authorization") String token, @PathVariable int id) {
        token = token.substring(7); 
        JwtToken jwtToken = JwtToken.builder().token(token).build();

        catalogService.deleteCatalog(id, jwtToken);
        return new ResponseBody<>(HttpStatus.OK, "Catalog deleted", null);
    }

}
