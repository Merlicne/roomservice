package com.example.demo.service;

import org.springframework.retry.annotation.Retryable;

import com.example.demo.model.CatalogRequest;
import com.example.demo.model.CatalogResponse;
import com.example.demo.model.JwtToken;
import com.example.demo.model.RoomCatalog;

public interface ICatalogService {

    @Retryable(value = { Exception.class }, maxAttempts = 5)
    public CatalogResponse createCatalog(CatalogRequest catalogModel, JwtToken token);
    
    @Retryable(value = { Exception.class }, maxAttempts = 5)
    public Iterable<RoomCatalog> getCatalogAll(JwtToken token);

    @Retryable(value = { Exception.class }, maxAttempts = 5)
    public CatalogResponse updateCatalog(int id, CatalogRequest catalogModel, JwtToken token);

    @Retryable(value = { Exception.class }, maxAttempts = 5)
    public void deleteCatalog(int id, JwtToken token);

    @Retryable(value = { Exception.class }, maxAttempts = 5)
    public RoomCatalog getCatalogByRoomId(int roomId, JwtToken token);
}
