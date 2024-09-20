package com.example.demo.service;

import com.example.demo.model.CatalogRequest;
import com.example.demo.model.CatalogResponse;
import com.example.demo.model.JwtToken;

public interface ICatalogService {
    public CatalogResponse createCatalog(CatalogRequest catalogModel, JwtToken token);
    public CatalogResponse getCatalogById(int id, JwtToken token);
    public Iterable<CatalogResponse> getCatalogAll(JwtToken token);
    public CatalogResponse updateCatalog(int id, CatalogRequest catalogModel, JwtToken token);
    public void deleteCatalog(int id, JwtToken token);
    public Iterable<CatalogResponse> getCatalogByRoomId(int roomId, JwtToken token);
}
