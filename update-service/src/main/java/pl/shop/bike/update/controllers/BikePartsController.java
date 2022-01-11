package pl.shop.bike.update.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.shop.bike.models.model.entities.ImageEntity;
import pl.shop.bike.models.model.entities.bikeParts.BrakeEntity;
import pl.shop.bike.models.model.entities.bikeParts.DriveEntity;
import pl.shop.bike.models.model.entities.bikeParts.FrameEntity;
import pl.shop.bike.models.model.enums.BikePartsType;
import pl.shop.bike.models.model.enums.BrakeType;
import pl.shop.bike.models.model.enums.ItemType;
import pl.shop.bike.models.model.request.SaveBikePartRequest;
import pl.shop.bike.models.model.response.DeleteResponse;
import pl.shop.bike.models.model.response.SaveBikePartResponse;
import pl.shop.bike.update.services.CloudinaryService;
import pl.shop.commons.dao.bikePartsDAO.BrakeRepository;
import pl.shop.commons.dao.bikePartsDAO.DriveRepository;
import pl.shop.commons.dao.bikePartsDAO.FrameRepository;
import pl.shop.commons.errors.exceptions.ItemNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/update/bike/part")
public class BikePartsController {

    @Autowired
    private BrakeRepository brakeRepository;

    @Autowired
    private DriveRepository driveRepository;

    @Autowired
    private FrameRepository frameRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    private StringBuilder stringBuilder = new StringBuilder();

    @DeleteMapping
    ResponseEntity<DeleteResponse> deleteAccessories(@RequestParam(name = "bikePartId") Long bikePartId,
                                                     @RequestParam(name = "type") BikePartsType type) {
        DeleteResponse response;

        switch (type) {
            case BRAKE:
                BrakeEntity brakeEntity = brakeRepository.findById(bikePartId)
                        .orElseThrow(() -> new ItemNotFoundException("Nie znaleziono hamulców"));

                brakeEntity.setDeleted(true);
                brakeRepository.save(brakeEntity);

                response = createDeleteResponse(brakeEntity.getName());
                return ResponseEntity.status(HttpStatus.OK).body(response);
            case DRIVE:
                DriveEntity driveEntity = driveRepository.findById(bikePartId)
                        .orElseThrow(() -> new ItemNotFoundException("Nie znaleziono napędu"));

                driveEntity.setDeleted(true);
                driveRepository.save(driveEntity);

                response = createDeleteResponse(driveEntity.getName());
                return ResponseEntity.status(HttpStatus.OK).body(response);
            case FRAME:
                FrameEntity frameEntity = frameRepository.findById(bikePartId)
                        .orElseThrow(() -> new ItemNotFoundException("Nie znaleziono ramy"));

                frameEntity.setDeleted(true);
                frameRepository.save(frameEntity);

                response = createDeleteResponse(frameEntity.getName());
                return ResponseEntity.status(HttpStatus.OK).body(response);
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping
    ResponseEntity<SaveBikePartResponse> saveBikePart(@RequestBody SaveBikePartRequest request) {

        SaveBikePartResponse response = null;

        BikePartsType bikePartsType = BikePartsType.findBikeByType(request.getBikePartsType());

        switch (bikePartsType) {
            case BRAKE:
                response = saveBrake(request, request.getImageEntityList());
                return ResponseEntity.status(HttpStatus.OK).body(response);
            case DRIVE:
                response = saveDrive(request, request.getImageEntityList());
                return ResponseEntity.status(HttpStatus.OK).body(response);
            case FRAME:
                response = saveFrame(request, request.getImageEntityList());
                return ResponseEntity.status(HttpStatus.OK).body(response);
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    private SaveBikePartResponse saveBrake(SaveBikePartRequest request, List<ImageEntity> imageEntities) {
        BrakeType type = BrakeType.findBrakeByType(request.getBrakeType());

        BrakeEntity brakeEntity = BrakeEntity.builder()
                .name(request.getName())
                .price(request.getPrice())
                .mark(request.getMark())
                .bikePartsType(BikePartsType.BRAKE)
                .color(request.getColor())
                .description(request.getDescription())
                .productCode(request.getProductCode())
                .quantity(request.getQuantity())
                .itemAmount(request.getItemAmount())
                .weight(request.getWeight())
                .itemType(ItemType.PARTS)
                .brakeType(type)
                .cableLength(request.getCableLength())
                .images(imageEntities)
                .build();

        brakeRepository.save(brakeEntity);
        return createResponse(request);
    }

    private SaveBikePartResponse saveDrive(SaveBikePartRequest request, List<ImageEntity> imageEntities) {
        DriveEntity driveEntity = DriveEntity.builder()
                .name(request.getName())
                .price(request.getPrice())
                .mark(request.getMark())
                .bikePartsType(BikePartsType.DRIVE)
                .color(request.getColor())
                .description(request.getDescription())
                .productCode(request.getProductCode())
                .quantity(request.getQuantity())
                .itemAmount(request.getItemAmount())
                .weight(request.getWeight())
                .itemType(ItemType.PARTS)
                .cassette(request.getCassette())
                .rowsCount(request.getRowsCount())
                .gradation(request.getGradation())
                .images(imageEntities)
                .build();

        driveRepository.save(driveEntity);
        return createResponse(request);
    }

    private SaveBikePartResponse saveFrame(SaveBikePartRequest request, List<ImageEntity> imageEntities) {
        FrameEntity frameEntity = FrameEntity.builder()
                .name(request.getName())
                .price(request.getPrice())
                .mark(request.getMark())
                .bikePartsType(BikePartsType.FRAME)
                .color(request.getColor())
                .description(request.getDescription())
                .productCode(request.getProductCode())
                .quantity(request.getQuantity())
                .itemAmount(request.getItemAmount())
                .weight(request.getWeight())
                .itemType(ItemType.PARTS)
                .frameSize(request.getFrameSize())
                .images(imageEntities)
                .build();

        frameRepository.save(frameEntity);
        return createResponse(request);
    }

    @PostMapping("/save")
    ResponseEntity<SaveBikePartResponse> updateBikePart(@RequestBody SaveBikePartRequest request) {
        BikePartsType bikePartsType = BikePartsType.valueOf(request.getBikePartsType());
        SaveBikePartResponse response = null;

        switch (bikePartsType) {
            case BRAKE:
                response = updateBrake(request);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            case DRIVE:
                response = updateDrive(request);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            case FRAME:
                response = updateFrame(request);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    private SaveBikePartResponse updateFrame(SaveBikePartRequest request) {
        FrameEntity frameEntity = frameRepository.findById(request.getId())
                .orElseThrow(() -> new ItemNotFoundException("Nie znaleziono ramy"));
        if (request.getName() != null) {
            frameEntity.setName(request.getName());
        }
        if (request.getPrice() != null) {
            frameEntity.setPrice(request.getPrice());
        }
        if (request.getMark() != null) {
            frameEntity.setMark(request.getMark());
        }
        if (request.getColor() != null) {
            frameEntity.setColor(request.getColor());
        }
        if (request.getDescription() != null) {
            frameEntity.setDescription(request.getDescription());
        }
        if (request.getProductCode() != null) {
            frameEntity.setProductCode(request.getProductCode());
        }
        if (request.getQuantity() != null) {
            frameEntity.setQuantity(request.getQuantity());
        }
        if (request.getItemAmount() != null) {
            frameEntity.setItemAmount(request.getItemAmount());
        }
        if (request.getWeight() != null) {
            frameEntity.setWeight(request.getWeight());
        }
        if (request.getFrameSize() != null) {
            frameEntity.setFrameSize(request.getFrameSize());
        }

        frameRepository.save(frameEntity);
        return createResponse(request);
    }

    private SaveBikePartResponse updateDrive(SaveBikePartRequest request) {
        DriveEntity drive = driveRepository.findById(request.getId())
                .orElseThrow(() -> new ItemNotFoundException("Nie znaleziono napędu"));

        if (request.getName() != null) {
            drive.setName(request.getName());
        }
        if (request.getPrice() != null) {
            drive.setPrice(request.getPrice());
        }
        if (request.getMark() != null) {
            drive.setMark(request.getMark());
        }
        if (request.getColor() != null) {
            drive.setColor(request.getColor());
        }
        if (request.getDescription() != null) {
            drive.setDescription(request.getDescription());
        }
        if (request.getProductCode() != null) {
            drive.setProductCode(request.getProductCode());
        }
        if (request.getQuantity() != null) {
            drive.setQuantity(request.getQuantity());
        }
        if (request.getItemAmount() != null) {
            drive.setItemAmount(request.getItemAmount());
        }
        if (request.getWeight() != null) {
            drive.setWeight(request.getWeight());
        }

        driveRepository.save(drive);
        return createResponse(request);
    }

    private SaveBikePartResponse updateBrake(SaveBikePartRequest request) {
        BrakeEntity brake = brakeRepository.findById(request.getId())
                .orElseThrow(() -> new ItemNotFoundException("Nie znaleziono hamulca"));

        if (request.getName() != null) {
            brake.setName(request.getName());
        }
        if (request.getPrice() != null) {
            brake.setPrice(request.getPrice());
        }
        if (request.getMark() != null) {
            brake.setMark(request.getMark());
        }
        if (request.getColor() != null) {
            brake.setColor(request.getColor());
        }
        if (request.getDescription() != null) {
            brake.setDescription(request.getDescription());
        }
        if (request.getProductCode() != null) {
            brake.setProductCode(request.getProductCode());
        }
        if (request.getQuantity() != null) {
            brake.setQuantity(request.getQuantity());
        }
        if (request.getItemAmount() != null) {
            brake.setItemAmount(request.getItemAmount());
        }
        if (request.getWeight() != null) {
            brake.setWeight(request.getWeight());
        }

        brakeRepository.save(brake);
        return createResponse(request);
    }

    private SaveBikePartResponse createResponse(SaveBikePartRequest request) {
        return SaveBikePartResponse.builder()
                .name(request.getName())
                .price(request.getPrice())
                .mark(request.getMark())
                .bikePartsType(BikePartsType.BRAKE)
                .color(request.getColor())
                .description(request.getDescription())
                .productCode(request.getProductCode())
                .quantity(request.getQuantity())
                .itemAmount(request.getItemAmount())
                .weight(request.getWeight())
                .frameSize(request.getFrameSize() == null ? null : request.getFrameSize())
                .build();
    }


    private DeleteResponse createDeleteResponse(String name) {
        return DeleteResponse.builder()
                .name(name)
                .build();
    }
}
