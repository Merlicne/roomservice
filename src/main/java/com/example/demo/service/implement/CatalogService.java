package com.example.demo.service.implement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Catalog;
import com.example.demo.entity.Furniture;
import com.example.demo.entity.Room;
import com.example.demo.exception.NotFoundException;
import com.example.demo.middleware.JwtService;
import com.example.demo.model.CatalogRequest;
import com.example.demo.model.CatalogResponse;
import com.example.demo.model.JwtToken;
import com.example.demo.model.Role;
import com.example.demo.model.RoomCatalog;
import com.example.demo.repository.CatalogRepository;
import com.example.demo.repository.FurnitureRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.service.ICatalogService;
import com.example.demo.util.converter.CatalogConverter;
import com.example.demo.util.converter.RoomConverter;
import com.example.demo.util.validator.CatalogValidator;
import com.example.demo.util.validator.RoleValidation;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CatalogService implements ICatalogService {
    private final CatalogRepository catalogRepository;
    private final FurnitureRepository furnitureRepository;
    private final RoomRepository roomRepository;
    private final JwtService jwtService;

    @Transactional
    public CatalogResponse createCatalog(CatalogRequest catalogModel, JwtToken token) {
        Role role = jwtService.extractRole(token.getToken());
        RoleValidation.allowRoles(role, Role.ADMIN);

        Room room = roomRepository.findById(catalogModel.getRoomID()).orElseThrow(() -> new NotFoundException("Room not found"));
        Furniture furniture = furnitureRepository.findById(catalogModel.getFurnitureID()).orElseThrow(() -> new NotFoundException("Furniture not found"));
        
        CatalogValidator.validateCatalog(catalogModel);
        
        
        Catalog c = catalogRepository.save(CatalogConverter.toEntity(catalogModel));
        
        return CatalogConverter.toModel(c, room, furniture);
    }
    

    public Iterable<RoomCatalog> getCatalogAll(JwtToken token) {
        Role role = jwtService.extractRole(token.getToken());
        RoleValidation.allowRoles(role, Role.ADMIN);

        Iterable<Room> rooms = roomRepository.findRoomAll();

        List<RoomCatalog> rcs = new ArrayList<>();
        
        for (Room r : rooms) {
            List<Catalog> cats = catalogRepository.findByRoomId(r.getRoomID());
            
            List<CatalogResponse> catalogResponses = new ArrayList<>();
            for (Catalog c : cats) {
                Furniture furniture = furnitureRepository.findById(c.getFurnitureID()).orElseThrow(() -> new NotFoundException("Furniture not found"));
                catalogResponses.add(CatalogConverter.toModel_RoomCatalog(c, furniture));
            }

            RoomCatalog rc = RoomCatalog.builder()
                                .room(RoomConverter.toModel(r))
                                .catalog(catalogResponses)
                                .build();
            rcs.add(rc);
            
        }
        return rcs;
    }

    @Transactional
    public CatalogResponse updateCatalog(int id, CatalogRequest catalogModel, JwtToken token) {
        Role role = jwtService.extractRole(token.getToken());
        RoleValidation.allowRoles(role, Role.ADMIN);

        Room room = roomRepository.findById(catalogModel.getRoomID()).orElseThrow(() -> new NotFoundException("Room not found"));
        Furniture furniture = furnitureRepository.findById(catalogModel.getFurnitureID()).orElseThrow(() -> new NotFoundException("Furniture not found"));
        
        CatalogValidator.validateCatalog(catalogModel);
        Catalog c = catalogRepository.save(CatalogConverter.toEntity(catalogModel));
        return CatalogConverter.toModel(c, room, furniture);
    }

    @Transactional
    public void deleteCatalog(int id, JwtToken token) {
        Role role = jwtService.extractRole(token.getToken());
        RoleValidation.allowRoles(role, Role.ADMIN);

        Catalog c = catalogRepository.findById(id).orElseThrow(() -> new NotFoundException("Catalog not found"));
        c.setDeletedAt(LocalDateTime.now());
        catalogRepository.save(c);
    }

    public RoomCatalog getCatalogByRoomId(int roomId, JwtToken token) {
        Role role = jwtService.extractRole(token.getToken());
        RoleValidation.allowRoles(role, Role.ADMIN);
        
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new NotFoundException("Room not found"));
        Iterable<Catalog> cats = catalogRepository.findByRoomId(roomId);

        List<CatalogResponse> catalogResponse = new ArrayList<>();
        
        for (Catalog c : cats) {
            Furniture furniture = furnitureRepository.findById(c.getFurnitureID()).orElseThrow(() -> new NotFoundException("Furniture not found"));
            catalogResponse.add(CatalogConverter.toModel_RoomCatalog(c, furniture));
            
        }

        return RoomCatalog.builder()
            .room(RoomConverter.toModel(room))
            .catalog(catalogResponse)
            .build();

    }
    
}
