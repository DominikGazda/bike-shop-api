package pl.shop.bike.read.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.shop.bike.models.model.entities.accessories.BagsEntity;
import pl.shop.bike.models.model.entities.accessories.BottlesEntity;
import pl.shop.bike.models.model.entities.accessories.FendersEntity;
import pl.shop.bike.models.model.entities.accessories.PumpEntity;
import pl.shop.bike.models.model.enums.AccessoriesType;
import pl.shop.commons.dao.accessoriesDAO.BagsRepository;
import pl.shop.commons.dao.accessoriesDAO.BottlesRepository;
import pl.shop.commons.dao.accessoriesDAO.FendersRepository;
import pl.shop.commons.dao.accessoriesDAO.PumpsRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/read/accessories")
public class AccessoriesDaoController {

    private BagsRepository bagsRepository;
    private BottlesRepository bottlesRepository;
    private FendersRepository fendersRepository;
    private PumpsRepository pumpsRepository;

    public AccessoriesDaoController(BagsRepository bagsRepository,
                                    BottlesRepository bottlesRepository,
                                    FendersRepository fendersRepository,
                                    PumpsRepository pumpsRepository) {
        this.bagsRepository = bagsRepository;
        this.bottlesRepository = bottlesRepository;
        this.fendersRepository = fendersRepository;
        this.pumpsRepository = pumpsRepository;
    }

    @GetMapping
    public List<?> getAllAccessories(@RequestParam(required = false) String type) {
        if (type == null) {
            List<?> allAccessoriesList = getAllAccessories();
            return allAccessoriesList;
        }

        AccessoriesType enumType = AccessoriesType.findAccessoriesTypeByName(type);
        switch (enumType) {
            case PUMP:
                List<PumpEntity> pumpList = pumpsRepository.findAll();
                if (!pumpList.isEmpty()) {
                    return pumpList;
                }
            case BOTTLE:
                List<BottlesEntity> bottlesList = bottlesRepository.findAll();
                if (!bottlesList.isEmpty()) {
                    return bottlesList;
                }
            default:
                throw new IllegalArgumentException("Brak danych na ten temat");
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> findAccessoriesByName(@PathVariable String name) {
        BagsEntity bagsEntity = bagsRepository.findByNameIgnoreCase(name);
        if (bagsEntity == null) {
            BottlesEntity bottlesEntity = bottlesRepository.findByNameIgnoreCase(name);
            if (bottlesEntity == null) {
                FendersEntity fendersEntity = fendersRepository.findByNameIgnoreCase(name);
                if (fendersEntity == null) {
                    PumpEntity pumpEntity = pumpsRepository.findByNameIgnoreCase(name);
                    if (pumpEntity != null) {
                        return ResponseEntity.ok(pumpEntity);
                    } else {
                        throw new IllegalArgumentException("Nie znaleziono takiego obiektu");
                    }
                } else {
                    return ResponseEntity.ok(fendersEntity);
                }
            } else {
                return ResponseEntity.ok(bottlesEntity);
            }
        } else {
            return ResponseEntity.ok(bagsEntity);
        }
    }

    private List<?> getAllAccessories() {
        List<List<?>> allAccessoriesList = new ArrayList<>();
        List<PumpEntity> pumpList = pumpsRepository.findAll();
        List<BottlesEntity> bottlesList = bottlesRepository.findAll();
        List<BagsEntity> bagsList = bagsRepository.findAll();
        List<FendersEntity> fendersList = fendersRepository.findAll();

        if (!pumpList.isEmpty()) {
            allAccessoriesList.add(pumpList);
        }
        if (!bottlesList.isEmpty()) {
            allAccessoriesList.add(bottlesList);
        }
        if (!bagsList.isEmpty()) {
            allAccessoriesList.add(bagsList);
        }
        if (!fendersList.isEmpty()) {
            allAccessoriesList.add(fendersList);
        }
        return allAccessoriesList;
    }
}
