package com.example.demo.service.implement;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Furniture;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.FurnitureModel;
import com.example.demo.repository.FurnitureRepository;
import com.example.demo.service.IFurnitureService;
import com.example.demo.util.converter.FurnitureConverter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FurnitureService implements IFurnitureService {
    private final FurnitureRepository furnitureRepository;

    public FurnitureModel createFurniture(FurnitureModel furnitureModel) {
        Furniture f = furnitureRepository.save(FurnitureConverter.toEntity(furnitureModel));
        return FurnitureConverter.toModel(f);
    }

    public FurnitureModel getFurnitureById(int id) {
        Furniture f = furnitureRepository.findById(id).orElseThrow(() -> new NotFoundException("Furniture not found"));
        return FurnitureConverter.toModel(f);
    }

    // public Iterable<FurnitureModel> getFurnitureAll(){
    //     Iterable<Furniture> furs= furnitureRepository.findFurAll();
    //     return FurnitureConverter.toModel(furs);
    // }

    public FurnitureModel updateFurniture(int id, FurnitureModel furnitureModel) {
        Furniture f = furnitureRepository.findById(id).orElseThrow(() -> new NotFoundException("Furniture not found"));
        Furniture fur = FurnitureConverter.toEntity(furnitureModel);
        fur.setCreatedAt(f.getCreatedAt());
        fur = furnitureRepository.save(fur);
        return FurnitureConverter.toModel(fur);
    }
     
    public void deleteFurniture(int id) {
        Furniture f = furnitureRepository.findById(id).orElseThrow(() -> new NotFoundException("Furniture not found"));
        f.setDeletedAt(LocalDateTime.now());
        furnitureRepository.save(f);
    }

    

}
