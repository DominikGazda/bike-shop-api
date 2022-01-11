package pl.shop.bike.update.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.shop.bike.models.model.entities.ImageEntity;
import pl.shop.bike.models.model.entities.accessories.BagsEntity;
import pl.shop.bike.models.model.entities.accessories.BottlesEntity;
import pl.shop.bike.models.model.entities.accessories.FendersEntity;
import pl.shop.bike.models.model.entities.accessories.PumpEntity;
import pl.shop.bike.models.model.enums.AccessoriesType;
import pl.shop.bike.models.model.enums.ItemType;
import pl.shop.bike.models.model.enums.PumpType;
import pl.shop.bike.models.model.enums.ValveType;
import pl.shop.bike.models.model.request.SaveAccessoriesRequest;
import pl.shop.bike.models.model.response.DeleteResponse;
import pl.shop.bike.models.model.response.SaveAccessoriesResponse;
import pl.shop.commons.dao.accessoriesDAO.BagsRepository;
import pl.shop.commons.dao.accessoriesDAO.BottlesRepository;
import pl.shop.commons.dao.accessoriesDAO.FendersRepository;
import pl.shop.commons.dao.accessoriesDAO.PumpsRepository;
import pl.shop.commons.errors.exceptions.ItemNotFoundException;

import java.util.List;

@RequestMapping("/api/update/accessories")
@RestController
public class AccessoriesController {

    @Autowired
    private BagsRepository bagsRepository;

    @Autowired
    private BottlesRepository bottlesRepository;

    @Autowired
    private FendersRepository fendersRepository;

    @Autowired
    private PumpsRepository pumpsRepository;

    private StringBuilder stringBuilder = new StringBuilder();

    @DeleteMapping
    ResponseEntity<DeleteResponse> deleteAccessories(@RequestParam(name = "accessoriesId") Long accessoriesId,
                                                     @RequestParam(name = "type") AccessoriesType type) {
        DeleteResponse response;

        switch (type) {
            case FENDERS:
                FendersEntity fendersEntity = fendersRepository.findById(accessoriesId)
                        .orElseThrow(() -> new ItemNotFoundException("Nie znaleziono takiej ramy"));

                fendersEntity.setDeleted(true);
                fendersRepository.save(fendersEntity);

                response = createDeleteResponse(fendersEntity.getName());
                return ResponseEntity.status(HttpStatus.OK).body(response);
            case PUMP:
                PumpEntity pumpEntity = pumpsRepository.findById(accessoriesId)
                        .orElseThrow(() -> new ItemNotFoundException("Nie znaleziono takiej pompki"));

                pumpEntity.setDeleted(true);
                pumpsRepository.save(pumpEntity);

                response = createDeleteResponse(pumpEntity.getName());
                return ResponseEntity.status(HttpStatus.OK).body(response);
            case BAGS:
                BagsEntity bagsEntity = bagsRepository.findById(accessoriesId)
                        .orElseThrow(() -> new ItemNotFoundException("Nie znaleziono takie torby"));

                bagsEntity.setDeleted(true);
                bagsRepository.save(bagsEntity);

                response = createDeleteResponse(bagsEntity.getName());
                return ResponseEntity.status(HttpStatus.OK).body(response);
            case BOTTLE:
                BottlesEntity bottlesEntity = bottlesRepository.findById(accessoriesId)
                        .orElseThrow(() -> new ItemNotFoundException("Nie znaleziono takich bidonów"));

                bottlesEntity.setDeleted(true);
                bottlesRepository.save(bottlesEntity);

                response = createDeleteResponse(bottlesEntity.getName());
                return ResponseEntity.status(HttpStatus.OK).body(response);
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping
    ResponseEntity<SaveAccessoriesResponse> saveAccessories(@RequestBody SaveAccessoriesRequest request) {
        SaveAccessoriesResponse response;

        AccessoriesType accessoriesType = AccessoriesType.findAccessoriesByType(request.getAccessoriesType());

        switch (accessoriesType) {
            case BAGS:
                response = saveBag(request, request.getImageEntityList());
                return ResponseEntity.status(HttpStatus.OK).body(response);
            case PUMP:
                response = savePump(request, request.getImageEntityList());
                return ResponseEntity.status(HttpStatus.OK).body(response);
            case BOTTLE:
                response = saveBottle(request, request.getImageEntityList());
                return ResponseEntity.status(HttpStatus.OK).body(response);
            case FENDERS:
                response = saveFenders(request, request.getImageEntityList());
                return ResponseEntity.status(HttpStatus.OK).body(response);
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    private SaveAccessoriesResponse saveBag(SaveAccessoriesRequest request, List<ImageEntity> imageEntities) {
        BagsEntity bagsEntity = BagsEntity.builder()
                .name(request.getName())
                .price(request.getPrice())
                .mark(request.getMark())
                .accessoriesType(AccessoriesType.BAGS)
                .itemAmount(request.getItemAmount())
                .color(request.getColor())
                .description(request.getDescription())
                .productCode(request.getProductCode())
                .quantity(request.getQuantity())
                .weight(request.getWeight())
                .itemType(ItemType.ACCESSORIES)
                .capacity(request.getCapacity())
                .installation(request.getInstallation())
                .fastMontage(request.isFastMontage())
                .waterproof(request.isWaterproof())
                .dimensions(request.getDimensions())
                .images(imageEntities)
                .build();

        bagsRepository.save(bagsEntity);
        SaveAccessoriesResponse response = createResponse(request);
        return response;
    }

    private SaveAccessoriesResponse savePump(SaveAccessoriesRequest request, List<ImageEntity> imageEntities) {
        PumpEntity pumpEntity = PumpEntity.builder()
                .name(request.getName())
                .price(request.getPrice())
                .mark(request.getMark())
                .accessoriesType(AccessoriesType.PUMP)
                .itemAmount(request.getItemAmount())
                .color(request.getColor())
                .description(request.getDescription())
                .productCode(request.getProductCode())
                .quantity(request.getQuantity())
                .weight(request.getWeight())
                .itemType(ItemType.ACCESSORIES)
                .maxPressure(request.getMaxPressure())
                .manometer(request.isManometer())
                .isCatridge(request.isCatridge())
                .pumpType(PumpType.findPumpByType(request.getPumpType()))
                .valveType(ValveType.findValveByType(request.getValveType()))
                .images(imageEntities)
                .build();

        pumpsRepository.save(pumpEntity);
        return createResponse(request);
    }


    private SaveAccessoriesResponse saveBottle(SaveAccessoriesRequest request, List<ImageEntity> imageEntities) {
        BottlesEntity bottlesEntity = BottlesEntity.builder()
                .name(request.getName())
                .price(request.getPrice())
                .mark(request.getMark())
                .accessoriesType(AccessoriesType.BOTTLE)
                .itemAmount(request.getItemAmount())
                .color(request.getColor())
                .description(request.getDescription())
                .productCode(request.getProductCode())
                .quantity(request.getQuantity())
                .weight(request.getWeight())
                .itemType(ItemType.ACCESSORIES)
                .material(request.getMaterial())
                .capacity(request.getCapacity())
                .thermal(request.isThermal())
                .images(imageEntities)
                .build();

        bottlesRepository.save(bottlesEntity);
        return createResponse(request);
    }

    private SaveAccessoriesResponse saveFenders(SaveAccessoriesRequest request, List<ImageEntity> imageEntities) {
        FendersEntity fendersEntity = FendersEntity.builder()
                .name(request.getName())
                .price(request.getPrice())
                .mark(request.getMark())
                .accessoriesType(AccessoriesType.FENDERS)
                .itemAmount(request.getItemAmount())
                .color(request.getColor())
                .description(request.getDescription())
                .productCode(request.getProductCode())
                .quantity(request.getQuantity())
                .weight(request.getWeight())
                .itemType(ItemType.ACCESSORIES)
                .additionalItems(request.getAdditionalItems())
                .material(request.getMaterial())
                .montage(request.getMontage())
                .wheelSize(request.getWheelSize())
                .fenderSize(request.getFenderSize())
                .fastMontage(request.isFastMontage())
                .images(imageEntities)
                .build();

        fendersRepository.save(fendersEntity);
        return createResponse(request);
    }

    @PostMapping("/save")
    ResponseEntity<SaveAccessoriesResponse> updateAccessories(@RequestBody SaveAccessoriesRequest request) {
        AccessoriesType accessoriesType = AccessoriesType.valueOf(request.getAccessoriesType());
        SaveAccessoriesResponse response;

        switch (accessoriesType) {
            case BAGS:
                response = updateBag(request);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            case PUMP:
                response = updatePump(request);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            case BOTTLE:
                response = updateBottle(request);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            case FENDERS:
                response = updateFenders(request);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    private SaveAccessoriesResponse updateBag(SaveAccessoriesRequest request) {
        BagsEntity bagsEntity = bagsRepository.findById(request.getId())
                .orElseThrow(() -> new ItemNotFoundException("Nie znaleziono torby lub sakwy"));

        if (request.getName() != null) {
            bagsEntity.setName(request.getName());
        }
        if (request.getPrice() != null) {
            bagsEntity.setPrice(request.getPrice());
        }
        if (request.getMark() != null) {
            bagsEntity.setMark(request.getMark());
        }
        if (request.getItemAmount() != null) {
            bagsEntity.setItemAmount(request.getItemAmount());
        }
        if (request.getColor() != null) {
            bagsEntity.setColor(request.getColor());
        }
        if (request.getDescription() != null) {
            bagsEntity.setDescription(request.getDescription());
        }
        if (request.getProductCode() != null) {
            bagsEntity.setProductCode(request.getProductCode());
        }
        if (request.getQuantity() != null) {
            bagsEntity.setQuantity(request.getQuantity());
        }
        if (request.getWeight() != null) {
            bagsEntity.setWeight(request.getWeight());
        }
        if (request.getCapacity() != null) {
            bagsEntity.setCapacity(request.getCapacity());
        }
        if (request.getInstallation() != null) {
            bagsEntity.setInstallation(request.getInstallation());
        }
        if (request.isFastMontage() != bagsEntity.isFastMontage()) {
            bagsEntity.setFastMontage(request.isFastMontage());
        }
        if (request.isWaterproof() != bagsEntity.isWaterproof()) {
            bagsEntity.setWaterproof(request.isWaterproof());
        }
        if (request.getDimensions() != null) {
            bagsEntity.setDimensions(request.getDimensions());
        }

        bagsRepository.save(bagsEntity);
        return createResponse(request);
    }

    private SaveAccessoriesResponse updatePump(SaveAccessoriesRequest request) {
        PumpEntity pumpEntity = pumpsRepository.findById(request.getId())
                .orElseThrow(() -> new ItemNotFoundException("Nie znaleziono takiej pompki"));

        if (request.getName() != null) {
            pumpEntity.setName(request.getName());
        }
        if (request.getPrice() != null) {
            pumpEntity.setPrice(request.getPrice());
        }
        if (request.getMark() != null) {
            pumpEntity.setMark(request.getMark());
        }
        if (request.getItemAmount() != null) {
            pumpEntity.setItemAmount(request.getItemAmount());
        }
        if (request.getColor() != null) {
            pumpEntity.setColor(request.getColor());
        }
        if (request.getDescription() != null) {
            pumpEntity.setDescription(request.getDescription());
        }
        if (request.getProductCode() != null) {
            pumpEntity.setProductCode(request.getProductCode());
        }
        if (request.getQuantity() != null) {
            pumpEntity.setQuantity(request.getQuantity());
        }
        if (request.getWeight() != null) {
            pumpEntity.setWeight(request.getWeight());
        }
        if (request.getMaxPressure() != null) {
            pumpEntity.setMaxPressure(request.getMaxPressure());
        }
        if (request.isManometer() != pumpEntity.isManometer()) {
            pumpEntity.setManometer(request.isManometer());
        }
        if (request.isCatridge() != pumpEntity.isCatridge()) {
            pumpEntity.setCatridge(request.isCatridge());
        }
        if (request.getPumpType() != null) {
            pumpEntity.setPumpType(PumpType.findPumpByType(request.getPumpType()));
        }
        if (request.getValveType() != null) {
            pumpEntity.setValveType(ValveType.findValveByType(request.getValveType()));
        }

        pumpsRepository.save(pumpEntity);
        return createResponse(request);
    }

    private SaveAccessoriesResponse updateBottle(SaveAccessoriesRequest request) {
        BottlesEntity bottlesEntity = bottlesRepository.findById(request.getId())
                .orElseThrow(() -> new ItemNotFoundException("Nie znaleziono bidonu"));

        if (request.getName() != null) {
            bottlesEntity.setName(request.getName());
        }
        if (request.getPrice() != null) {
            bottlesEntity.setPrice(request.getPrice());
        }
        if (request.getMark() != null) {
            bottlesEntity.setMark(request.getMark());
        }
        if (request.getItemAmount() != null) {
            bottlesEntity.setItemAmount(request.getItemAmount());
        }
        if (request.getColor() != null) {
            bottlesEntity.setColor(request.getColor());
        }
        if (request.getDescription() != null) {
            bottlesEntity.setDescription(request.getDescription());
        }
        if (request.getProductCode() != null) {
            bottlesEntity.setProductCode(request.getProductCode());
        }
        if (request.getQuantity() != null) {
            bottlesEntity.setQuantity(request.getQuantity());
        }
        if (request.getWeight() != null) {
            bottlesEntity.setWeight(request.getWeight());
        }
        if (request.getMaterial() != null) {
            bottlesEntity.setMaterial(request.getMaterial());
        }
        if (request.getCapacity() != null) {
            bottlesEntity.setCapacity(request.getCapacity());
        }
        if (request.isThermal() != bottlesEntity.isThermal()) {
            bottlesEntity.setThermal(request.isThermal());
        }

        bottlesRepository.save(bottlesEntity);
        return createResponse(request);
    }

    private SaveAccessoriesResponse updateFenders(SaveAccessoriesRequest request) {
        FendersEntity fendersEntity = fendersRepository.findById(request.getId())
                .orElseThrow(() -> new ItemNotFoundException("Nie znaleziono podanej ramy"));

        if (request.getName() != null) {
            fendersEntity.setName(request.getName());
        }
        if (request.getPrice() != null) {
            fendersEntity.setPrice(request.getPrice());
        }
        if (request.getMark() != null) {
            fendersEntity.setMark(request.getMark());
        }
        if (request.getItemAmount() != null) {
            fendersEntity.setItemAmount(request.getItemAmount());
        }
        if (request.getColor() != null) {
            fendersEntity.setColor(request.getColor());
        }
        if (request.getDescription() != null) {
            fendersEntity.setDescription(request.getDescription());
        }
        if (request.getProductCode() != null) {
            fendersEntity.setProductCode(request.getProductCode());
        }
        if (request.getQuantity() != null) {
            fendersEntity.setQuantity(request.getQuantity());
        }
        if (request.getWeight() != null) {
            fendersEntity.setWeight(request.getWeight());
        }
        if (request.getAdditionalItems() != null) {
            fendersEntity.setAdditionalItems(request.getAdditionalItems());
        }
        if (request.getMaterial() != null) {
            fendersEntity.setMaterial(request.getMaterial());
        }
        if (request.getMontage() != null) {
            fendersEntity.setMontage(request.getMontage());
        }
        if (request.getWheelSize() != null) {
            fendersEntity.setWheelSize(request.getWheelSize());
        }
        if (request.getFenderSize() != null) {
            fendersEntity.setFenderSize(request.getFenderSize());
        }
        if (request.isFastMontage() != fendersEntity.isFastMontage()) {
            fendersEntity.setFastMontage(request.isFastMontage());
        }

        fendersRepository.save(fendersEntity);
        return createResponse(request);
    }


    private SaveAccessoriesResponse createResponse(SaveAccessoriesRequest request) {
        return SaveAccessoriesResponse.builder()
                .name(request.getName())
                .price(request.getPrice())
                .mark(request.getMark())
                .accessoriesType(AccessoriesType.BAGS)
                .itemAmount(request.getItemAmount())
                .color(request.getColor())
                .description(request.getDescription())
                .productCode(request.getProductCode())
                .quantity(request.getQuantity())
                .weight(request.getWeight())
                .build();
    }

    private DeleteResponse createDeleteResponse(String name) {
        return DeleteResponse.builder()
                .name(name)
                .build();
    }
}
