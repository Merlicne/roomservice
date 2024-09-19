package com.example.demo.service;

import com.example.demo.model.CatalogRequest;
import com.example.demo.model.CatalogResponse;

public interface ICatalogService {
    public CatalogResponse createCatalog(CatalogRequest catalogModel);
    public CatalogResponse getCatalogById(int id);
    public Iterable<CatalogResponse> getCatalogAll();
    public CatalogResponse updateCatalog(int id, CatalogRequest catalogModel);
    public void deleteCatalog(int id);
    public Iterable<CatalogResponse> getCatalogByRoomId(int roomId);
}
