package com.example.demo.WebClient;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.model.BuildingModel;
import com.example.demo.model.JwtToken;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DormServiceWebClient implements IDormService {
    private final WebClient webClient;

    
    public BuildingModel getBuildingById(int buildingId, JwtToken token) {
    
        return webClient.get()
                .uri("/building/{buildingId}", buildingId)
                .header("Authorization", token.getToken())
                .retrieve()
                .bodyToMono(BuildingModel.class)
                .block();
    }
}
