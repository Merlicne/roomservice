package com.example.demo.service;

import org.springframework.retry.annotation.Retryable;

import com.example.demo.model.FurnitureModel;
import com.example.demo.model.JwtToken;

public interface IFurnitureService {
    @Retryable(retryFor = Exception.class, maxAttempts = 5)
    public FurnitureModel createFurniture(FurnitureModel furnitureModel, JwtToken token);

    @Retryable(retryFor = Exception.class, maxAttempts = 5)
    public FurnitureModel getFurnitureById(int id, JwtToken token);

    @Retryable(retryFor = Exception.class, maxAttempts = 5)
    public Iterable<FurnitureModel> getFurnitureAll(JwtToken token);

    @Retryable(retryFor = Exception.class, maxAttempts = 5)
    public FurnitureModel updateFurniture(int id, FurnitureModel furnitureModel, JwtToken token);

    @Retryable(retryFor = Exception.class, maxAttempts = 5)
    public void deleteFurniture(int id, JwtToken token);
}
