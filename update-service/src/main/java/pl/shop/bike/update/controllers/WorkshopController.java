package pl.shop.bike.update.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.shop.bike.models.model.entities.ImageEntity;
import pl.shop.bike.models.model.entities.workshop.MaintenanceEntity;
import pl.shop.bike.models.model.entities.workshop.RacksEntity;
import pl.shop.bike.models.model.entities.workshop.ToolsEntity;
import pl.shop.bike.models.model.enums.ItemType;
import pl.shop.bike.models.model.enums.WorkshopType;
import pl.shop.bike.models.model.request.SaveWorkshopRequest;
import pl.shop.bike.models.model.response.DeleteResponse;
import pl.shop.bike.models.model.response.SaveWorkshopResponse;
import pl.shop.commons.dao.workshopDAO.MaintenanceRepository;
import pl.shop.commons.dao.workshopDAO.RacksRepository;
import pl.shop.commons.dao.workshopDAO.ToolsRepository;
import pl.shop.commons.errors.exceptions.ItemNotFoundException;

import java.util.List;

import static pl.shop.bike.models.model.enums.WorkshopType.MAINTENANCE;

@RequestMapping("/api/update/workshop")
@RestController
public class WorkshopController {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Autowired
    private RacksRepository racksRepository;

    @Autowired
    private ToolsRepository toolsRepository;

    private StringBuilder stringBuilder = new StringBuilder();

    @DeleteMapping
    ResponseEntity<DeleteResponse> deleteAccessories(@RequestParam(name = "workshopId") Long workshopId,
                                                     @RequestParam(name = "type") WorkshopType type) {
        DeleteResponse response;

        switch (type) {
            case MAINTENANCE:
                MaintenanceEntity maintenanceEntity = maintenanceRepository.findById(workshopId)
                        .orElseThrow(() -> new ItemNotFoundException("Nie znaleziono"));

                maintenanceRepository.delete(maintenanceEntity);
                response = createDeleteResponse(maintenanceEntity.getName());

                return ResponseEntity.status(HttpStatus.OK).body(response);
            case TOOLS:
                ToolsEntity toolsEntity = toolsRepository.findById(workshopId)
                        .orElseThrow(() -> new ItemNotFoundException("Nie znaleziono narzędzi"));

                toolsRepository.delete(toolsEntity);
                response = createDeleteResponse(toolsEntity.getName());

                return ResponseEntity.status(HttpStatus.OK).body(response);
            case RACKS:
                RacksEntity racksEntity = racksRepository.findById(workshopId)
                        .orElseThrow(() -> new ItemNotFoundException("Nie znaleziono stojaka"));

                racksRepository.delete(racksEntity);
                response = createDeleteResponse(racksEntity.getName());

                return ResponseEntity.status(HttpStatus.OK).body(response);
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping
    ResponseEntity<SaveWorkshopResponse> saveWorkshop(@RequestBody SaveWorkshopRequest request) {

        SaveWorkshopResponse response;

        WorkshopType workshopType = WorkshopType.findWorkshopByType(request.getWorkshopType());

        switch (workshopType) {
            case RACKS:
                response = saveRacks(request, request.getImageEntities());
                return ResponseEntity.status(HttpStatus.OK).body(response);
            case TOOLS:
                response = saveTools(request, request.getImageEntities());
                return ResponseEntity.status(HttpStatus.OK).body(response);
            case MAINTENANCE:
                response = saveMaintenance(request, request.getImageEntities());
                return ResponseEntity.status(HttpStatus.OK).body(response);
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/save")
    ResponseEntity<SaveWorkshopResponse> updateWorkshop(@RequestBody SaveWorkshopRequest request) {
        WorkshopType workshopType = WorkshopType.valueOf(request.getWorkshopType());
        SaveWorkshopResponse response;

        switch (workshopType) {
            case RACKS:
                response = updateRacks(request);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            case TOOLS:
                response = updateTools(request);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            case MAINTENANCE:
                response = updateMaintenance(request);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    private SaveWorkshopResponse updateMaintenance(SaveWorkshopRequest request) {
        MaintenanceEntity maintenanceEntity = maintenanceRepository.findById(request.getId())
                .orElseThrow(() -> new ItemNotFoundException("Nie znaleziono przedmiotów do konserwacji"));

        if (request.getName() != null) {
            maintenanceEntity.setName(request.getName());
        }
        if (request.getPrice() != null) {
            maintenanceEntity.setPrice(request.getPrice());
        }
        if (request.getMark() != null) {
            maintenanceEntity.setMark(request.getMark());
        }
        if (request.getDescription() != null) {
            maintenanceEntity.setDescription(request.getDescription());
        }
        if (request.getProductCode() != null) {
            maintenanceEntity.setProductCode(request.getProductCode());
        }
        if (request.getUsages() != null) {
            maintenanceEntity.setUsages(request.getUsages());
        }
        if (request.getQuantity() != null) {
            maintenanceEntity.setQuantity(request.getQuantity());
        }
        if (request.getItemAmount() != null) {
            maintenanceEntity.setItemAmount(request.getItemAmount());
        }
        if (request.getCapacity() != null) {
            maintenanceEntity.setCapacity(request.getCapacity());
        }

        maintenanceRepository.save(maintenanceEntity);
        return createResponse(request);
    }

    private SaveWorkshopResponse updateTools(SaveWorkshopRequest request) {
        ToolsEntity toolsEntity = toolsRepository.findById(request.getId())
                .orElseThrow(() -> new ItemNotFoundException("Nie znaleziono narzędzi"));

        if (request.getName() != null) {
            toolsEntity.setName(request.getName());
        }
        if (request.getPrice() != null) {
            toolsEntity.setPrice(request.getPrice());
        }
        if (request.getMark() != null) {
            toolsEntity.setMark(request.getMark());
        }
        if (request.getDescription() != null) {
            toolsEntity.setDescription(request.getDescription());
        }
        if (request.getProductCode() != null) {
            toolsEntity.setProductCode(request.getProductCode());
        }
        if (request.getUsages() != null) {
            toolsEntity.setUsages(request.getUsages());
        }
        if (request.getQuantity() != null) {
            toolsEntity.setQuantity(request.getQuantity());
        }
        if (request.getItemAmount() != null) {
            toolsEntity.setItemAmount(request.getItemAmount());
        }
        if (request.getCapacity() != null) {
            toolsEntity.setCapacity(request.getCapacity());
        }

        toolsRepository.save(toolsEntity);
        return createResponse(request);
    }

    private SaveWorkshopResponse updateRacks(SaveWorkshopRequest request) {
        RacksEntity racksEntity = racksRepository.findById(request.getId())
                .orElseThrow(() -> new ItemNotFoundException("Nie znaleziono stojaka"));

        if (request.getName() != null) {
            racksEntity.setName(request.getName());
        }
        if (request.getPrice() != null) {
            racksEntity.setPrice(request.getPrice());
        }
        if (request.getMark() != null) {
            racksEntity.setMark(request.getMark());
        }
        if (request.getDescription() != null) {
            racksEntity.setDescription(request.getDescription());
        }
        if (request.getProductCode() != null) {
            racksEntity.setProductCode(request.getProductCode());
        }
        if (request.getUsages() != null) {
            racksEntity.setUsages(request.getUsages());
        }
        if (request.getQuantity() != null) {
            racksEntity.setQuantity(request.getQuantity());
        }
        if (request.getItemAmount() != null) {
            racksEntity.setItemAmount(request.getItemAmount());
        }
        if (request.getCapacity() != null) {
            racksEntity.setCapacity(request.getCapacity());
        }

        racksRepository.save(racksEntity);
        return createResponse(request);
    }

    private SaveWorkshopResponse saveRacks(SaveWorkshopRequest request, List<ImageEntity> imageEntities) {
        RacksEntity racksEntity = RacksEntity.builder()
                .name(request.getName())
                .price(request.getPrice())
                .mark(request.getMark())
                .description(request.getDescription())
                .productCode(request.getProductCode())
                .usages(request.getUsages())
                .quantity(request.getQuantity())
                .itemAmount(request.getItemAmount())
                .capacity(request.getCapacity())
                .workshopType(WorkshopType.RACKS)
                .itemType(ItemType.WORKSHOP)
                .images(imageEntities)
                .build();

        racksRepository.save(racksEntity);
        return createResponse(request);
    }

    private SaveWorkshopResponse saveTools(SaveWorkshopRequest request, List<ImageEntity> imageEntities) {
        ToolsEntity toolsEntity = ToolsEntity.builder()
                .name(request.getName())
                .price(request.getPrice())
                .mark(request.getMark())
                .description(request.getDescription())
                .productCode(request.getProductCode())
                .usages(request.getUsages())
                .quantity(request.getQuantity())
                .itemAmount(request.getItemAmount())
                .capacity(request.getCapacity())
                .workshopType(WorkshopType.TOOLS)
                .itemType(ItemType.WORKSHOP)
                .images(imageEntities)
                .build();

        toolsRepository.save(toolsEntity);
        return createResponse(request);
    }

    private SaveWorkshopResponse saveMaintenance(SaveWorkshopRequest request, List<ImageEntity> imageEntities) {
        MaintenanceEntity maintenanceEntity = MaintenanceEntity.builder()
                .name(request.getName())
                .price(request.getPrice())
                .mark(request.getMark())
                .description(request.getDescription())
                .productCode(request.getProductCode())
                .usages(request.getUsages())
                .quantity(request.getQuantity())
                .itemAmount(request.getItemAmount())
                .capacity(request.getCapacity())
                .workshopType(MAINTENANCE)
                .itemType(ItemType.WORKSHOP)
                .images(imageEntities)
                .build();

        maintenanceRepository.save(maintenanceEntity);
        return createResponse(request);
    }

    private SaveWorkshopResponse createResponse(SaveWorkshopRequest request) {
        return SaveWorkshopResponse.builder()
                .name(request.getName())
                .price(request.getPrice())
                .mark(request.getMark())
                .description(request.getDescription())
                .productCode(request.getProductCode())
                .usages(request.getUsages())
                .quantity(request.getQuantity())
                .itemAmount(request.getItemAmount())
                .capacity(request.getCapacity())
                .build();
    }

    private DeleteResponse createDeleteResponse(String name) {
        return DeleteResponse.builder()
                .name(name)
                .build();
    }
}
