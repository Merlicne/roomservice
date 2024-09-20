package com.example.demo.service;

import com.example.demo.model.FurnitureModel;
import com.example.demo.model.JwtToken;

public interface IFurnitureService {
    public FurnitureModel createFurniture(FurnitureModel furnitureModel, JwtToken token);
    public FurnitureModel getFurnitureById(int id, JwtToken token);
    public Iterable<FurnitureModel> getFurnitureAll(JwtToken token);
    public FurnitureModel updateFurniture(int id, FurnitureModel furnitureModel, JwtToken token);
    public void deleteFurniture(int id, JwtToken token);
}
