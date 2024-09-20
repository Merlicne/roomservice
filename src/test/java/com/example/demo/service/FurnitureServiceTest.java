package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.entity.Furniture;
import com.example.demo.exception.BadRequestException;
import com.example.demo.middleware.JwtService;
import com.example.demo.model.FurnitureModel;
import com.example.demo.model.JwtToken;
import com.example.demo.model.Role;
import com.example.demo.repository.FurnitureRepository;
import com.example.demo.service.implement.FurnitureService;

@ExtendWith(MockitoExtension.class)
class FurnitureServiceTest {
    @Mock
    private FurnitureRepository furnitureRepository;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private FurnitureService furnitureService;

    private Furniture furniture;
    private FurnitureModel furnitureModel;
    private JwtToken token;

    @BeforeEach
    void setUp() {
        furnitureModel = FurnitureModel.builder()
            .id(0)
            .name("Table")
            .build();

        furniture = Furniture.builder()
            .furID(0)
            .name("Table")
            .build();

        token = JwtToken.builder()
            .token("token")
            .expiresIn(1000)
            .build();
    }

    @Test
    void createFurnitureTest() {
        when(jwtService.extractRole(token.getToken())).thenReturn(Role.ADMIN);

        when(furnitureRepository.save(furniture)).thenReturn(furniture);

        FurnitureModel result = furnitureService.createFurniture(furnitureModel, token);

        assertNotNull(result);
        assertEquals(furnitureModel, result);

        verify(furnitureRepository).save(furniture);
    }

    @Test
    void getFurnitureByIdTest() {
        when(jwtService.extractRole(token.getToken())).thenReturn(Role.ADMIN);

        when(furnitureRepository.findById(furniture.getFurID())).thenReturn(java.util.Optional.of(furniture));

        FurnitureModel result = furnitureService.getFurnitureById(furniture.getFurID(), token);

        assertNotNull(result);
        assertEquals(furnitureModel, result);

        verify(furnitureRepository).findById(furniture.getFurID());
    }

    @Test
    void updateFurnitureTest() {
        when(jwtService.extractRole(token.getToken())).thenReturn(Role.ADMIN);

        when(furnitureRepository.findById(furniture.getFurID())).thenReturn(java.util.Optional.of(furniture));
        when(furnitureRepository.save(furniture)).thenReturn(furniture);

        FurnitureModel result = furnitureService.updateFurniture(furniture.getFurID(), furnitureModel, token);

        assertNotNull(result);
        assertEquals(furnitureModel, result);

        verify(furnitureRepository).findById(furniture.getFurID());
        verify(furnitureRepository).save(furniture);
    }

    @Test
    void deleteFurnitureTest() {
        when(jwtService.extractRole(token.getToken())).thenReturn(Role.ADMIN);

        when(furnitureRepository.findById(furniture.getFurID())).thenReturn(java.util.Optional.of(furniture));
        when(furnitureRepository.save(furniture)).thenReturn(furniture);

        furnitureService.deleteFurniture(furniture.getFurID(), token);

        verify(furnitureRepository).findById(furniture.getFurID());
        verify(furnitureRepository).save(furniture);
    }

    @Test
    void getFurnitureAllTest() {
        when(jwtService.extractRole(token.getToken())).thenReturn(Role.ADMIN);

        when(furnitureRepository.findFurAll()).thenReturn(java.util.List.of(furniture));

        Iterable<FurnitureModel> result = furnitureService.getFurnitureAll(token);

        assertNotNull(result);
        assertEquals(java.util.List.of(furnitureModel), result);

        verify(furnitureRepository).findFurAll();
    }


    @Test
    void testCreateFurnitureInvalid() {
        when(jwtService.extractRole(token.getToken())).thenReturn(Role.ADMIN);

        furnitureModel.setName(null);
        assertThrows(BadRequestException.class, () -> {
            furnitureService.createFurniture(furnitureModel, token);
        });
        furnitureModel.setName("Table");

        

        verify(furnitureRepository,times(0)).save(furniture);
    }
}
