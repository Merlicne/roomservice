package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CatalogRequest;
import com.example.demo.model.CatalogResponse;
import com.example.demo.model.ResponseBody;
import com.example.demo.service.implement.CatalogService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CatalogController {
    private final CatalogService catalogService;

    @GetMapping("/catalog")
    public ResponseBody<Iterable<CatalogResponse>> getCatalogAll() {
        Iterable<CatalogResponse> cats = catalogService.getCatalogAll();
        return new ResponseBody<>(HttpStatus.OK, "Catalog found", cats);
    }

    @GetMapping("/catalog/{id}")
    public ResponseBody<CatalogResponse> getCatalogById(@PathVariable int id) {
        CatalogResponse cat = catalogService.getCatalogById(id);
        return new ResponseBody<>(HttpStatus.OK, "Catalog found", cat);
    }

    @GetMapping("/catalog/room/{roomId}")
    public ResponseBody<Iterable<CatalogResponse>> getCatalogByRoomId(@PathVariable int roomId) {
        Iterable<CatalogResponse> cats = catalogService.getCatalogByRoomId(roomId);
        return new ResponseBody<>(HttpStatus.OK, "Catalog found", cats);
    }

    @PostMapping("/catalog")
    public ResponseBody<CatalogResponse> createCatalog(@RequestBody CatalogRequest catalogModel) {
        CatalogResponse cat = catalogService.createCatalog(catalogModel);
        return new ResponseBody<>(HttpStatus.CREATED, "Catalog created", cat);
    }

    @PutMapping("/catalog/{id}")
    public ResponseBody<CatalogResponse> updateCatalog(@PathVariable int id, @RequestBody CatalogRequest catalogModel) {
        CatalogResponse cat = catalogService.updateCatalog(id, catalogModel);
        return new ResponseBody<>(HttpStatus.OK, "Catalog updated", cat);
    }

    @DeleteMapping("/catalog/{id}")
    public ResponseBody<Void> deleteCatalog(@PathVariable int id) {
        catalogService.deleteCatalog(id);
        return new ResponseBody<>(HttpStatus.OK, "Catalog deleted", null);
    }

}
