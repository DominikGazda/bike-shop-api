package pl.shop.bike.read.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.shop.bike.models.model.entities.workshop.MaintenanceEntity;
import pl.shop.bike.models.model.entities.workshop.RacksEntity;
import pl.shop.bike.models.model.entities.workshop.ToolsEntity;
import pl.shop.bike.models.model.enums.WorkshopType;
import pl.shop.bike.models.model.request.workshopFilter.WorkshopFilterRequest;
import pl.shop.commons.dao.workshopDAO.MaintenanceRepository;
import pl.shop.commons.dao.workshopDAO.RacksRepository;
import pl.shop.commons.dao.workshopDAO.ToolsRepository;
import pl.shop.commons.errors.exceptions.ItemNotFoundException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static pl.shop.bike.models.model.enums.WorkshopType.*;
import static pl.shop.commons.utils.FilterNamesList.*;

@RestController
@RequestMapping("/api/read/workshop")
public class WorkshopDaoController {

    private MaintenanceRepository maintenanceRepository;
    private RacksRepository racksRepository;
    private ToolsRepository toolsRepository;

    public WorkshopDaoController(MaintenanceRepository maintenanceRepository, RacksRepository racksRepository, ToolsRepository toolsRepository) {
        this.maintenanceRepository = maintenanceRepository;
        this.racksRepository = racksRepository;
        this.toolsRepository = toolsRepository;
    }

    @GetMapping
    public List<?> getAllWorkshopItems(@RequestParam(name = "type", required = false) String type) {
        if (type == null) {
            List<List<?>> allWorkshopItemsList = new ArrayList<>();

            List<MaintenanceEntity> maintenanceList = maintenanceRepository.findAll().stream()
                    .filter(brake -> !brake.isDeleted())
                    .collect(Collectors.toList());

            List<RacksEntity> rackList = racksRepository.findAll().stream()
                    .filter(brake -> !brake.isDeleted())
                    .collect(Collectors.toList());

            List<ToolsEntity> toolsList = toolsRepository.findAll().stream()
                    .filter(brake -> !brake.isDeleted())
                    .collect(Collectors.toList());

            if (!maintenanceList.isEmpty()) {
                allWorkshopItemsList.add(maintenanceList);
            }

            if (!rackList.isEmpty()) {
                allWorkshopItemsList.add(rackList);
            }

            if (!toolsList.isEmpty()) {
                allWorkshopItemsList.add(toolsList);
            }

            return allWorkshopItemsList;
        }

        WorkshopType enumType = WorkshopType.findWorkshopTypeByName(type);

        switch (enumType) {
            case MAINTENANCE:
                return maintenanceRepository.findAll();
            case TOOLS:
                return toolsRepository.findAll();
            case RACKS:
                return racksRepository.findAll();
            default:
                throw new ItemNotFoundException("Nie znaleziono workshop");
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getWorkshopItemByName(@PathVariable(name = "name") String name) {
        MaintenanceEntity maintenanceEntity = maintenanceRepository.findByNameIgnoreCase(name);
        if (maintenanceEntity == null) {
            RacksEntity racksEntity = racksRepository.findByNameIgnoreCase(name);
            if (racksEntity == null) {
                ToolsEntity toolsEntity = toolsRepository.findByNameIgnoreCase(name);
                if (toolsEntity != null) {
                    return ResponseEntity.ok(toolsEntity);
                } else {
                    throw new ItemNotFoundException("Nie znaleziono takiego obiektu w db");
                }
            } else {
                return ResponseEntity.ok(racksEntity);
            }
        } else {
            return ResponseEntity.ok(maintenanceEntity);
        }
    }

    @GetMapping("/sort/name")
    public ResponseEntity<?> sortWorkshopByName(@RequestParam String parameter) {
        List<List<?>> filteredList = new ArrayList<>();

        List<MaintenanceEntity> maintenanceEntities = maintenanceRepository.findAllByNameContainsIgnoreCase(parameter).stream()
                .filter(brake -> !brake.isDeleted())
                .collect(Collectors.toList());

        List<RacksEntity> racksEntities = racksRepository.findAllByNameContainsIgnoreCase(parameter).stream()
                .filter(brake -> !brake.isDeleted())
                .collect(Collectors.toList());

        List<ToolsEntity> toolsEntities = toolsRepository.findAllByNameContainsIgnoreCase(parameter).stream()
                .filter(brake -> !brake.isDeleted())
                .collect(Collectors.toList());

        if (!maintenanceEntities.isEmpty()) {
            filteredList.add(maintenanceEntities);
        }
        if (!racksEntities.isEmpty()) {
            filteredList.add(racksEntities);
        }
        if (!toolsEntities.isEmpty()) {
            filteredList.add(toolsEntities);
        }

        return ResponseEntity.status(HttpStatus.OK).body(filteredList);
    }

    @PostMapping("/sort")
    List<Set<?>> sortWorkshopByParameter(@RequestBody WorkshopFilterRequest request) {
        System.out.println("SORT");
        List<Set<?>> workshop = new ArrayList<>();

        Set<MaintenanceEntity> maintenanceEntities = new HashSet<>();
        Set<RacksEntity> racksEntities = new HashSet<>();
        Set<ToolsEntity> toolsEntities = new HashSet<>();

        if (!request.getMark().getTrec() && !request.getMark().getMerida() && !request.getMark().getKands()) {
            if (!request.getType().isMaintenance() && !request.getType().isRacks() && !request.getType().isTools()) {
                if (request.getPrice().getHighPrice() == 0 && request.getPrice().getLowPrice() == 0) {
                    maintenanceEntities.addAll(maintenanceRepository.findAll().stream()
                            .filter(bag -> !bag.isDeleted())
                            .collect(Collectors.toList()));

                    racksEntities.addAll(racksRepository.findAll().stream()
                            .filter(bag -> !bag.isDeleted())
                            .collect(Collectors.toList()));

                    toolsEntities.addAll(toolsRepository.findAll().stream()
                            .filter(bag -> !bag.isDeleted())
                            .collect(Collectors.toList()));

                    workshop.add(maintenanceEntities);
                    workshop.add(racksEntities);
                    workshop.add(toolsEntities);

                    return workshop;
                }
            }
        }

        if (request.getMark().getKands() || request.getMark().getMerida() || request.getMark().getTrec()) {
            if (request.getMark().getKands()) {
                maintenanceEntities.addAll(maintenanceRepository.findAllByMarkIgnoreCase(KANDS).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));

                racksEntities.addAll(racksRepository.findAllByMarkIgnoreCase(KANDS).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));

                toolsEntities.addAll(toolsRepository.findAllByMarkIgnoreCase(KANDS).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
            }
            if (request.getMark().getMerida()) {
                maintenanceEntities.addAll(maintenanceRepository.findAllByMarkIgnoreCase(MERIDA).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));

                racksEntities.addAll(racksRepository.findAllByMarkIgnoreCase(MERIDA).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));

                toolsEntities.addAll(toolsRepository.findAllByMarkIgnoreCase(MERIDA).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
            }
            if (request.getMark().getTrec()) {
                maintenanceEntities.addAll(maintenanceRepository.findAllByMarkIgnoreCase(TREC).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));

                racksEntities.addAll(racksRepository.findAllByMarkIgnoreCase(TREC).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));

                toolsEntities.addAll(toolsRepository.findAllByMarkIgnoreCase(TREC).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
            }
        }

        if (request.getType().isMaintenance() || request.getType().isRacks() || request.getType().isTools()) {
            if (request.getType().isMaintenance()) {
                maintenanceEntities.addAll(maintenanceRepository.findAllByWorkshopType(MAINTENANCE).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (request.getType().isTools()) {
                toolsEntities.addAll(toolsRepository.findAllByWorkshopType(TOOLS).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (request.getType().isRacks()) {
                racksEntities.addAll(racksRepository.findAllByWorkshopType(RACKS).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
            }
        }

        if (request.getPrice().getHighPrice() > 0 && request.getPrice().getLowPrice() > 0) {
            maintenanceEntities.addAll(maintenanceRepository.findAllByPriceGreaterThanEqualAndPriceLessThanEqual(request.getPrice().getLowPrice(), request.getPrice().getHighPrice()).stream()
                    .filter(bag -> !bag.isDeleted())
                    .collect(Collectors.toList()));

            racksEntities.addAll(racksRepository.findAllByPriceGreaterThanEqualAndPriceLessThanEqual(request.getPrice().getLowPrice(), request.getPrice().getHighPrice()).stream()
                    .filter(bag -> !bag.isDeleted())
                    .collect(Collectors.toList()));

            toolsEntities.addAll(toolsRepository.findAllByPriceGreaterThanEqualAndPriceLessThanEqual(request.getPrice().getLowPrice(), request.getPrice().getHighPrice()).stream()
                    .filter(bag -> !bag.isDeleted())
                    .collect(Collectors.toList()));
        }

        workshop.add(maintenanceEntities);
        workshop.add(toolsEntities);
        workshop.add(racksEntities);

        return workshop;
    }
}
