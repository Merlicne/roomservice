package com.example.demo.service.implement;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Furniture;
import com.example.demo.exception.NotFoundException;
import com.example.demo.middleware.JwtService;
import com.example.demo.model.FurnitureModel;
import com.example.demo.model.JwtToken;
import com.example.demo.model.Role;
import com.example.demo.repository.FurnitureRepository;
import com.example.demo.service.IFurnitureService;
import com.example.demo.util.converter.FurnitureConverter;
import com.example.demo.util.validator_.FurnitureValidator;
import com.example.demo.util.validator_.RoleValidation;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FurnitureService implements IFurnitureService {
    private final FurnitureRepository furnitureRepository;
    private final JwtService jwtService;

    public FurnitureModel createFurniture(FurnitureModel furnitureModel, JwtToken token) {
        Role role = jwtService.extractRole(token.getToken());
        RoleValidation.allowRoles(role, Role.ADMIN);

        FurnitureValidator.validateFurniture(furnitureModel);
        Furniture f = furnitureRepository.save(FurnitureConverter.toEntity(furnitureModel));
        return FurnitureConverter.toModel(f);
    }

    public FurnitureModel getFurnitureById(int id, JwtToken token) {
        Role role = jwtService.extractRole(token.getToken());
        RoleValidation.allowRoles(role, Role.ADMIN);

        Furniture f = furnitureRepository.findById(id).orElseThrow(() -> new NotFoundException("Furniture not found"));
        return FurnitureConverter.toModel(f);
    }

    public Iterable<FurnitureModel> getFurnitureAll(JwtToken token){
        Role role = jwtService.extractRole(token.getToken());
        RoleValidation.allowRoles(role, Role.ADMIN);

        Iterable<Furniture> furs= furnitureRepository.findFurAll();
        return FurnitureConverter.toModel(furs);
    }

    public FurnitureModel updateFurniture(int id, FurnitureModel furnitureModel, JwtToken token) {
        Role role = jwtService.extractRole(token.getToken());
        RoleValidation.allowRoles(role, Role.ADMIN);

        Furniture f = furnitureRepository.findById(id).orElseThrow(() -> new NotFoundException("Furniture not found"));
        Furniture fur = FurnitureConverter.toEntity(furnitureModel);
        fur.setCreatedAt(f.getCreatedAt());
        
        FurnitureValidator.validateFurniture(furnitureModel);
        fur = furnitureRepository.save(fur);
        return FurnitureConverter.toModel(fur);
    }
     
    public void deleteFurniture(int id, JwtToken token) {
        Role role = jwtService.extractRole(token.getToken());
        RoleValidation.allowRoles(role, Role.ADMIN);
        
        Furniture f = furnitureRepository.findById(id).orElseThrow(() -> new NotFoundException("Furniture not found"));
        f.setDeletedAt(LocalDateTime.now());
        furnitureRepository.save(f);
    }

    

}
