package com.example.demo.WebClient;

import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.model.BuildingModel;
import com.example.demo.model.JwtToken;
import com.example.demo.model.ResponseBody;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DormServiceWebClient implements IDormService {
    private final WebClient webClient;

    
    public Optional<BuildingModel> getBuildingById(int buildingId, JwtToken token) {
    
        ResponseBody<BuildingModel> response = webClient.get()
                .uri("/building/{buildingId}", buildingId)
                .headers(headers -> headers.setBearerAuth(token.getToken()))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ResponseBody<BuildingModel>>() {})
                .block();
        if ((response.getStatus() != 200 && response.getStatus() != 201) || response.getData() == null) {
            return Optional.empty();
        }
        return Optional.of(response.getData());
    }
}
