package com.example.demo.service;

import com.example.demo.model.CatalogRequest;
import com.example.demo.model.CatalogResponse;
import com.example.demo.model.JwtToken;
import com.example.demo.model.RoomCatalog;

public interface ICatalogService {
    public CatalogResponse createCatalog(CatalogRequest catalogModel, JwtToken token);
    public RoomCatalog getCatalogById(int id, JwtToken token);
    public Iterable<RoomCatalog> getCatalogAll(JwtToken token);
    public CatalogResponse updateCatalog(int id, CatalogRequest catalogModel, JwtToken token);
    public void deleteCatalog(int id, JwtToken token);
    public RoomCatalog getCatalogByRoomId(int roomId, JwtToken token);
}
