package com.example.demo.service;

import com.example.demo.model.FurnitureModel;

public interface IFurnitureService {
    public FurnitureModel createFurniture(FurnitureModel furnitureModel);
    public FurnitureModel getFurnitureById(int id);
    // public Iterable<FurnitureModel> getFurnitureAll();
    public FurnitureModel updateFurniture(int id, FurnitureModel furnitureModel);
    public void deleteFurniture(int id);
}
