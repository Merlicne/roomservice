package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.entity.Catalog;
import com.example.demo.entity.Furniture;
import com.example.demo.entity.Room;
import com.example.demo.exception.BadRequestException;
import com.example.demo.middleware.JwtService;
import com.example.demo.model.CatalogRequest;
import com.example.demo.model.CatalogResponse;
import com.example.demo.model.FurnitureModel;
import com.example.demo.model.JwtToken;
import com.example.demo.model.Role;
import com.example.demo.model.RoomModel;
import com.example.demo.repository.CatalogRepository;
import com.example.demo.repository.FurnitureRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.service.implement.CatalogService;

@ExtendWith(MockitoExtension.class)
class CatalogServiceTest {
    @Mock
    private CatalogRepository catalogRepository;

    @Mock
    private FurnitureRepository furnitureRepository;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private CatalogService catalogService;

    private Catalog catalog;
    private CatalogRequest catalogRequest;
    private CatalogResponse catalogResponse;
    private Room room;
    private Furniture furniture;
    private JwtToken token;


    @BeforeEach
    void setUp() {
        catalog = Catalog.builder()
            .id(1)
            .roomID(1)
            .furnitureID(1)
            .quantity(1)
            .createdAt(null)
            .build();
        
        catalogRequest = CatalogRequest.builder()
            .roomID(1)
            .furnitureID(1)
            .quantity(1)
            .build();

        catalogResponse = CatalogResponse.builder()
            .id(1)
            .room(RoomModel.builder()
                .roomID(1)
                .roomNo("0")
                .roomPrice(0)
                .build())
            .furniture(FurnitureModel.builder()
                .id(1)
                .name("0")
                .build())
            .quantity(1)
            .createdAt(null)
            .build();

        room = Room.builder()
            .roomID(1)
            .roomNo("0")
            .roomPrice(0)
            .build();
        
        furniture = Furniture.builder()
            .furID(1)
            .name("0")
            .build();
        
        token = JwtToken.builder()
            .token("0")
            .build();
    }

    @Test
    void createCatalogTest() {

        when(jwtService.extractRole(token.getToken())).thenReturn(Role.ADMIN);
        when(roomRepository.findById(room.getRoomID())).thenReturn(Optional.of(room));
        when(furnitureRepository.findById(furniture.getFurID())).thenReturn(Optional.of(furniture));
        when(catalogRepository.save(any(Catalog.class))).thenReturn(catalog);

        CatalogResponse result = catalogService.createCatalog(catalogRequest, token);

        assertNotNull(result);
        assertEquals(catalogResponse, result);

        verify(roomRepository).findById(room.getRoomID());
        verify(furnitureRepository).findById(furniture.getFurID());
        verify(catalogRepository).save(any(Catalog.class));

    }

    @Test
    void testCreateCatalogInvalid() {
        when(jwtService.extractRole(token.getToken())).thenReturn(Role.ADMIN);
        when(roomRepository.findById(anyInt())).thenReturn(Optional.of(room));
        when(furnitureRepository.findById(anyInt())).thenReturn(Optional.of(furniture));
        



        catalogRequest.setRoomID(-1);
        assertThrows(BadRequestException.class, () -> {
            catalogService.createCatalog(catalogRequest, token);
        });
        catalogRequest.setRoomID(1);

        catalogRequest.setFurnitureID(-1);
        assertThrows(BadRequestException.class, () -> {
            catalogService.createCatalog(catalogRequest, token);
        });
        catalogRequest.setFurnitureID(1);

        catalogRequest.setQuantity(-1);
        assertThrows(BadRequestException.class, () -> {
            catalogService.createCatalog(catalogRequest, token);
        });

        verify(catalogRepository, times(0)).save(any(Catalog.class));
    }

    @Test
    void getCatalogByIdTest() {
        when(jwtService.extractRole(token.getToken())).thenReturn(Role.ADMIN);

        when(catalogRepository.findById(catalog.getId())).thenReturn(Optional.of(catalog));
        when(roomRepository.findById(room.getRoomID())).thenReturn(Optional.of(room));
        when(furnitureRepository.findById(furniture.getFurID())).thenReturn(Optional.of(furniture));

        CatalogResponse result = catalogService.getCatalogById(1, token);

        assertNotNull(result);
        assertEquals(catalogResponse, result);

        verify(catalogRepository).findById(catalog.getId());
        verify(roomRepository).findById(room.getRoomID());
        verify(furnitureRepository).findById(furniture.getFurID());
    }

    @Test
    void getCatalogAllTest() {
        when(jwtService.extractRole(token.getToken())).thenReturn(Role.ADMIN);

        when(catalogRepository.findAll()).thenReturn(java.util.List.of(catalog));
        when(roomRepository.findById(room.getRoomID())).thenReturn(Optional.of(room));
        when(furnitureRepository.findById(furniture.getFurID())).thenReturn(Optional.of(furniture));

        Iterable<CatalogResponse> result = catalogService.getCatalogAll(token);

        assertNotNull(result);
        assertEquals(java.util.List.of(catalogResponse), result);

        verify(catalogRepository).findAll();
        verify(roomRepository).findById(room.getRoomID());
        verify(furnitureRepository).findById(furniture.getFurID());
    }

    @Test
    void updateCatalogTest() {
        when(jwtService.extractRole(token.getToken())).thenReturn(Role.ADMIN);

        when(roomRepository.findById(room.getRoomID())).thenReturn(Optional.of(room));
        when(furnitureRepository.findById(furniture.getFurID())).thenReturn(Optional.of(furniture));
        when(catalogRepository.save(any(Catalog.class))).thenReturn(catalog);

        CatalogResponse result = catalogService.updateCatalog(1, catalogRequest, token);

        assertNotNull(result);
        assertEquals(catalogResponse, result);

        verify(roomRepository).findById(room.getRoomID());
        verify(furnitureRepository).findById(furniture.getFurID());
    }

    @Test
    void deleteCatalogTest() {
        when(jwtService.extractRole(token.getToken())).thenReturn(Role.ADMIN);

        when(catalogRepository.findById(catalog.getId())).thenReturn(Optional.of(catalog));

        catalogService.deleteCatalog(1, token);

        verify(catalogRepository).save(catalog);
    }

    @Test
    void getCatalogByRoomIdTest() {
        when(jwtService.extractRole(token.getToken())).thenReturn(Role.ADMIN);

        when(catalogRepository.findByRoomId(room.getRoomID())).thenReturn(java.util.List.of(catalog));
        when(roomRepository.findById(room.getRoomID())).thenReturn(Optional.of(room));
        when(furnitureRepository.findById(furniture.getFurID())).thenReturn(Optional.of(furniture));

        Iterable<CatalogResponse> result = catalogService.getCatalogByRoomId(1, token);

        assertNotNull(result);
        assertEquals(java.util.List.of(catalogResponse), result);

        verify(catalogRepository).findByRoomId(1);
        verify(roomRepository).findById(1);
        verify(furnitureRepository).findById(1);
    }

    
}
