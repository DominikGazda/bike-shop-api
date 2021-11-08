package pl.shop.bike.read.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.shop.bike.models.model.entities.workshop.MaintenanceEntity;
import pl.shop.bike.models.model.entities.workshop.RacksEntity;
import pl.shop.bike.models.model.entities.workshop.ToolsEntity;
import pl.shop.bike.models.model.enums.WorkshopType;
import pl.shop.commons.dao.workshopDAO.MaintenanceRepository;
import pl.shop.commons.dao.workshopDAO.RacksRepository;
import pl.shop.commons.dao.workshopDAO.ToolsRepository;

import java.util.ArrayList;
import java.util.List;

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
            List<MaintenanceEntity> maintenanceList = maintenanceRepository.findAll();
            List<RacksEntity> rackList = racksRepository.findAll();
            List<ToolsEntity> toolsList = toolsRepository.findAll();

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
        switch (enumType){
            case MAINTENANCE:
                return maintenanceRepository.findAll();
            case TOOLS:
                return toolsRepository.findAll();
            case RACKS:
                return racksRepository.findAll();
            default:
                throw new IllegalArgumentException("Nie znaleziono workshop");
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getWorkshopItemByName(@PathVariable(name="name") String name){
        MaintenanceEntity maintenanceEntity = maintenanceRepository.findByNameIgnoreCase(name);
        if(maintenanceEntity == null){
            RacksEntity racksEntity = racksRepository.findByNameIgnoreCase(name);
            if(racksEntity == null){
                ToolsEntity toolsEntity = toolsRepository.findByNameIgnoreCase(name);
                if(toolsEntity != null){
                   return ResponseEntity.ok(toolsEntity);
                } else {
                    throw new IllegalArgumentException("Nie znaleziono takiego obiektu w db");
                }
            } else {
                return ResponseEntity.ok(racksEntity);
            }
        } else {
            return ResponseEntity.ok(maintenanceEntity);
        }
    }
}
