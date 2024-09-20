package com.example.demo.service.implement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Catalog;
import com.example.demo.entity.Furniture;
import com.example.demo.entity.Room;
import com.example.demo.exception.NotFoundException;
import com.example.demo.middleware.JwtService;
import com.example.demo.model.CatalogRequest;
import com.example.demo.model.CatalogResponse;
import com.example.demo.model.JwtToken;
import com.example.demo.model.Role;
import com.example.demo.repository.CatalogRepository;
import com.example.demo.repository.FurnitureRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.service.ICatalogService;
import com.example.demo.util.Validator.CatalogValidator;
import com.example.demo.util.Validator.RoleValidation;
import com.example.demo.util.converter.CatalogConverter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CatalogService implements ICatalogService {
    private final CatalogRepository catalogRepository;
    private final FurnitureRepository furnitureRepository;
    private final RoomRepository roomRepository;
    private final JwtService jwtService;

    public CatalogResponse createCatalog(CatalogRequest catalogModel, JwtToken token) {
        Role role = jwtService.extractRole(token.getToken());
        RoleValidation.allowRoles(role, Role.ADMIN);

        Room room = roomRepository.findById(catalogModel.getRoomID()).orElseThrow(() -> new NotFoundException("Room not found"));
        Furniture furniture = furnitureRepository.findById(catalogModel.getFurnitureID()).orElseThrow(() -> new NotFoundException("Furniture not found"));
        
        CatalogValidator.validateCatalog(catalogModel);
        
        
        Catalog c = catalogRepository.save(CatalogConverter.toEntity(catalogModel));
        
        return CatalogConverter.toModel(c, room, furniture);
    }
    
    public CatalogResponse getCatalogById(int id, JwtToken token) {
        Role role = jwtService.extractRole(token.getToken());
        RoleValidation.allowRoles(role, Role.ADMIN);

        Catalog c = catalogRepository.findById(id).orElseThrow(() -> new NotFoundException("Catalog not found"));
        Room room = roomRepository.findById(c.getRoomID()).orElseThrow(() -> new NotFoundException("Room not found"));
        Furniture furniture = furnitureRepository.findById(c.getFurnitureID()).orElseThrow(() -> new NotFoundException("Furniture not found"));
        return CatalogConverter.toModel(c, room, furniture);
    }

    public Iterable<CatalogResponse> getCatalogAll(JwtToken token) {
        Role role = jwtService.extractRole(token.getToken());
        RoleValidation.allowRoles(role, Role.ADMIN);

        Iterable<Catalog> cats = catalogRepository.findAll();
        List<CatalogResponse> catalogResponse = new ArrayList<>();

        for (Catalog c : cats) {
            Room room = roomRepository.findById(c.getRoomID()).orElseThrow(() -> new NotFoundException("Room not found"));
            Furniture furniture = furnitureRepository.findById(c.getFurnitureID()).orElseThrow(() -> new NotFoundException("Furniture not found"));
            catalogResponse.add(CatalogConverter.toModel(c, room, furniture));
        }
        return catalogResponse;
    }

    public CatalogResponse updateCatalog(int id, CatalogRequest catalogModel, JwtToken token) {
        Role role = jwtService.extractRole(token.getToken());
        RoleValidation.allowRoles(role, Role.ADMIN);

        Room room = roomRepository.findById(catalogModel.getRoomID()).orElseThrow(() -> new NotFoundException("Room not found"));
        Furniture furniture = furnitureRepository.findById(catalogModel.getFurnitureID()).orElseThrow(() -> new NotFoundException("Furniture not found"));
        
        CatalogValidator.validateCatalog(catalogModel);
        Catalog c = catalogRepository.save(CatalogConverter.toEntity(catalogModel));
        return CatalogConverter.toModel(c, room, furniture);
    }

    public void deleteCatalog(int id, JwtToken token) {
        Role role = jwtService.extractRole(token.getToken());
        RoleValidation.allowRoles(role, Role.ADMIN);

        Catalog c = catalogRepository.findById(id).orElseThrow(() -> new NotFoundException("Catalog not found"));
        c.setDeletedAt(LocalDateTime.now());
        catalogRepository.save(c);
    }

    public Iterable<CatalogResponse> getCatalogByRoomId(int roomId, JwtToken token) {
        Role role = jwtService.extractRole(token.getToken());
        RoleValidation.allowRoles(role, Role.ADMIN);
        
        Iterable<Catalog> cats = catalogRepository.findByRoomId(roomId);
        List<CatalogResponse> catalogResponse = new ArrayList<>();

        for (Catalog c : cats) {
            Room room = roomRepository.findById(c.getRoomID()).orElseThrow(() -> new NotFoundException("Room not found"));
            Furniture furniture = furnitureRepository.findById(c.getFurnitureID()).orElseThrow(() -> new NotFoundException("Furniture not found"));
            catalogResponse.add(CatalogConverter.toModel(c, room, furniture));
        }
        return catalogResponse;
    }
    
}
