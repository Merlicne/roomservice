package com.example.demo.WebClient;

import com.example.demo.model.BuildingModel;
import com.example.demo.model.JwtToken;

public interface IDormService {
    public BuildingModel getBuildingById(int buildingId, JwtToken token);
}
