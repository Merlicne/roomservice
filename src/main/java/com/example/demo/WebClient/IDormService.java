package com.example.demo.WebClient;

import java.util.Optional;

import org.springframework.retry.annotation.Retryable;

import com.example.demo.model.BuildingModel;
import com.example.demo.model.JwtToken;

public interface IDormService {

    @Retryable(retryFor  = { Exception.class }, maxAttempts = 5)
    public Optional<BuildingModel> getBuildingById(int buildingId, JwtToken token);
}
