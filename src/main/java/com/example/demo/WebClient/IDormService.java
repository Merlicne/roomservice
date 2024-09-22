package com.example.demo.WebClient;

import org.springframework.retry.annotation.Retryable;

import com.example.demo.model.BuildingModel;
import com.example.demo.model.JwtToken;

public interface IDormService {

    @Retryable(value = { Exception.class }, maxAttempts = 5)
    public BuildingModel getBuildingById(int buildingId, JwtToken token);
}
