package pl.shop.bike.read.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.shop.bike.models.model.entities.accessories.BagsEntity;
import pl.shop.bike.models.model.entities.accessories.BottlesEntity;
import pl.shop.bike.models.model.entities.accessories.FendersEntity;
import pl.shop.bike.models.model.entities.accessories.PumpEntity;
import pl.shop.bike.models.model.enums.AccessoriesType;
import pl.shop.bike.models.model.request.accessoriesFilter.AccessoriesFilterRequest;
import pl.shop.commons.dao.accessoriesDAO.BagsRepository;
import pl.shop.commons.dao.accessoriesDAO.BottlesRepository;
import pl.shop.commons.dao.accessoriesDAO.FendersRepository;
import pl.shop.commons.dao.accessoriesDAO.PumpsRepository;
import pl.shop.commons.errors.exceptions.ItemNotFoundException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static pl.shop.bike.models.model.enums.AccessoriesType.*;
import static pl.shop.commons.utils.FilterNamesList.*;

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
                    return pumpList.stream()
                            .filter(pump -> !pump.isDeleted())
                            .collect(Collectors.toList());
                }
            case BOTTLE:
                List<BottlesEntity> bottlesList = bottlesRepository.findAll();

                if (!bottlesList.isEmpty()) {
                    return bottlesList.stream()
                            .filter(bottle -> !bottle.isDeleted())
                            .collect(Collectors.toList());
                }
            default:
                throw new ItemNotFoundException("Brak danych na ten temat");
        }
    }

    @PostMapping("/sort")
    public List<Set<?>> sortAccessoriesByParameter(@RequestBody AccessoriesFilterRequest request) {
        List<Set<?>> accessories = new ArrayList<>();

        Set<BagsEntity> bagsEntities = new HashSet<>();
        Set<BottlesEntity> bottlesEntities = new HashSet<>();
        Set<FendersEntity> fendersEntities = new HashSet<>();
        Set<PumpEntity> pumpEntities = new HashSet<>();

        if (!request.getMark().getTrec() && !request.getMark().getMerida() && !request.getMark().getKands()) {
            if (!request.getColor().getBlack() && !request.getColor().getWhite() &&
                    !request.getColor().getBlue() && !request.getColor().getRed() &&
                    !request.getColor().getGray() && !request.getColor().getNavy()) {
                if (!request.getType().isBags() && !request.getType().isBottles() && !request.getType().isPumps() && !request.getType().isFenders()) {
                    if (request.getPrice().getHighPrice() == 0 && request.getPrice().getLowPrice() == 0) {
                        bagsEntities.addAll(bagsRepository.findAll().stream()
                                .filter(bag -> !bag.isDeleted())
                                .collect(Collectors.toList()));
                        bottlesEntities.addAll(bottlesRepository.findAll().stream()
                                .filter(bag -> !bag.isDeleted())
                                .collect(Collectors.toList()));
                        fendersEntities.addAll(fendersRepository.findAll().stream()
                                .filter(bag -> !bag.isDeleted())
                                .collect(Collectors.toList()));
                        pumpEntities.addAll(pumpsRepository.findAll().stream()
                                .filter(bag -> !bag.isDeleted())
                                .collect(Collectors.toList()));

                        accessories.add(bagsEntities);
                        accessories.add(bottlesEntities);
                        accessories.add(fendersEntities);
                        accessories.add(pumpEntities);

                        return accessories;
                    }
                }
            }
        }

        if (request.getMark().getKands() || request.getMark().getMerida() || request.getMark().getTrec() ||
                request.getMark().getCannondale() || request.getMark().getKross() || request.getMark().getEyen() ||
                request.getMark().getSks()) {
            if (request.getMark().getKands()) {
                bagsEntities.addAll(bagsRepository.findAllByMarkIgnoreCase(KANDS).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                bottlesEntities.addAll(bottlesRepository.findAllByMarkIgnoreCase(KANDS).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                fendersEntities.addAll(fendersRepository.findAllByMarkIgnoreCase(KANDS).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                pumpEntities.addAll(pumpsRepository.findAllByMarkIgnoreCase(KANDS).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (request.getMark().getMerida()) {
                bagsEntities.addAll(bagsRepository.findAllByMarkIgnoreCase(MERIDA).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                bottlesEntities.addAll(bottlesRepository.findAllByMarkIgnoreCase(MERIDA).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                fendersEntities.addAll(fendersRepository.findAllByMarkIgnoreCase(MERIDA).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                pumpEntities.addAll(pumpsRepository.findAllByMarkIgnoreCase(MERIDA).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (request.getMark().getTrec()) {
                bagsEntities.addAll(bagsRepository.findAllByMarkIgnoreCase(BMX).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                bottlesEntities.addAll(bottlesRepository.findAllByMarkIgnoreCase(BMX).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                fendersEntities.addAll(fendersRepository.findAllByMarkIgnoreCase(BMX).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                pumpEntities.addAll(pumpsRepository.findAllByMarkIgnoreCase(BMX).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (request.getMark().getCannondale()) {
                bagsEntities.addAll(bagsRepository.findAllByMarkIgnoreCase(CANNONDALE).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                bottlesEntities.addAll(bottlesRepository.findAllByMarkIgnoreCase(CANNONDALE).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                fendersEntities.addAll(fendersRepository.findAllByMarkIgnoreCase(CANNONDALE).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                pumpEntities.addAll(pumpsRepository.findAllByMarkIgnoreCase(CANNONDALE).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (request.getMark().getKross()) {
                bagsEntities.addAll(bagsRepository.findAllByMarkIgnoreCase(CROSS).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                bottlesEntities.addAll(bottlesRepository.findAllByMarkIgnoreCase(CROSS).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                fendersEntities.addAll(fendersRepository.findAllByMarkIgnoreCase(CROSS).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                pumpEntities.addAll(pumpsRepository.findAllByMarkIgnoreCase(CROSS).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (request.getMark().getSks()) {
                bagsEntities.addAll(bagsRepository.findAllByMarkIgnoreCase(SKS).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                bottlesEntities.addAll(bottlesRepository.findAllByMarkIgnoreCase(SKS).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                fendersEntities.addAll(fendersRepository.findAllByMarkIgnoreCase(SKS).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                pumpEntities.addAll(pumpsRepository.findAllByMarkIgnoreCase(SKS).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (request.getMark().getEyen()) {
                bagsEntities.addAll(bagsRepository.findAllByMarkIgnoreCase(EYEN).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                bottlesEntities.addAll(bottlesRepository.findAllByMarkIgnoreCase(EYEN).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                fendersEntities.addAll(fendersRepository.findAllByMarkIgnoreCase(EYEN).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                pumpEntities.addAll(pumpsRepository.findAllByMarkIgnoreCase(EYEN).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
            }
        }

        if (request.getColor().getBlack() || request.getColor().getWhite() ||
                request.getColor().getBlue() || request.getColor().getRed() ||
                request.getColor().getGray() || request.getColor().getNavy()) {
            if (request.getColor().getBlack()) {
                bagsEntities.addAll(bagsRepository.findAllByColorIgnoreCase(BLACK).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                bottlesEntities.addAll(bottlesRepository.findAllByColorIgnoreCase(BLACK).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                fendersEntities.addAll(fendersRepository.findAllByColorIgnoreCase(BLACK).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                pumpEntities.addAll(pumpsRepository.findAllByColorIgnoreCase(BLACK).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (request.getColor().getWhite()) {
                bagsEntities.addAll(bagsRepository.findAllByColorIgnoreCase(WHITE).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                bottlesEntities.addAll(bottlesRepository.findAllByColorIgnoreCase(WHITE).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                fendersEntities.addAll(fendersRepository.findAllByColorIgnoreCase(WHITE).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                pumpEntities.addAll(pumpsRepository.findAllByColorIgnoreCase(WHITE).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (request.getColor().getBlue()) {
                bagsEntities.addAll(bagsRepository.findAllByColorIgnoreCase(BLUE).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                bottlesEntities.addAll(bottlesRepository.findAllByColorIgnoreCase(BLUE).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                fendersEntities.addAll(fendersRepository.findAllByColorIgnoreCase(BLUE).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                pumpEntities.addAll(pumpsRepository.findAllByColorIgnoreCase(BLUE).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (request.getColor().getRed()) {
                bagsEntities.addAll(bagsRepository.findAllByColorIgnoreCase(RED).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                bottlesEntities.addAll(bottlesRepository.findAllByColorIgnoreCase(RED).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                fendersEntities.addAll(fendersRepository.findAllByColorIgnoreCase(RED).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                pumpEntities.addAll(pumpsRepository.findAllByColorIgnoreCase(RED).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (request.getColor().getGray()) {
                bagsEntities.addAll(bagsRepository.findAllByColorIgnoreCase(GRAY).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                bottlesEntities.addAll(bottlesRepository.findAllByColorIgnoreCase(GRAY).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                fendersEntities.addAll(fendersRepository.findAllByColorIgnoreCase(GRAY).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                pumpEntities.addAll(pumpsRepository.findAllByColorIgnoreCase(GRAY).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (request.getColor().getNavy()) {
                bagsEntities.addAll(bagsRepository.findAllByColorIgnoreCase(NAVY).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                bottlesEntities.addAll(bottlesRepository.findAllByColorIgnoreCase(NAVY).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                fendersEntities.addAll(fendersRepository.findAllByColorIgnoreCase(NAVY).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
                pumpEntities.addAll(pumpsRepository.findAllByColorIgnoreCase(NAVY).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
            }

        }

        if (request.getType().isBags() || request.getType().isBottles() || request.getType().isFenders() || request.getType().isPumps()) {
            if (request.getType().isBags()) {
                bagsEntities.addAll(bagsRepository.findAllByAccessoriesType(BAGS).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (request.getType().isBottles()) {
                bottlesEntities.addAll(bottlesRepository.findAllByAccessoriesType(BOTTLE).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (request.getType().isFenders()) {
                fendersEntities.addAll(fendersRepository.findAllByAccessoriesType(FENDERS).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (request.getType().isPumps()) {
                pumpEntities.addAll(pumpsRepository.findAllByAccessoriesType(PUMP).stream()
                        .filter(bag -> !bag.isDeleted())
                        .collect(Collectors.toList()));
            }
        }

        if (request.getPrice().getHighPrice() > 0 && request.getPrice().getLowPrice() > 0) {
            bagsEntities.addAll(bagsRepository.findAllByPriceGreaterThanEqualAndPriceLessThanEqual(request.getPrice().getLowPrice(), request.getPrice().getHighPrice()).stream()
                    .filter(bag -> !bag.isDeleted())
                    .collect(Collectors.toList()));
            bottlesEntities.addAll(bottlesRepository.findAllByPriceGreaterThanEqualAndPriceLessThanEqual(request.getPrice().getLowPrice(), request.getPrice().getHighPrice()).stream()
                    .filter(bag -> !bag.isDeleted())
                    .collect(Collectors.toList()));
            fendersEntities.addAll(fendersRepository.findAllByPriceGreaterThanEqualAndPriceLessThanEqual(request.getPrice().getLowPrice(), request.getPrice().getHighPrice()).stream()
                    .filter(bag -> !bag.isDeleted())
                    .collect(Collectors.toList()));
            pumpEntities.addAll(pumpsRepository.findAllByPriceGreaterThanEqualAndPriceLessThanEqual(request.getPrice().getLowPrice(), request.getPrice().getHighPrice()).stream()
                    .filter(bag -> !bag.isDeleted())
                    .collect(Collectors.toList()));
        }

        accessories.add(bagsEntities);
        accessories.add(bottlesEntities);
        accessories.add(fendersEntities);
        accessories.add(pumpEntities);

        return accessories;
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
                        throw new ItemNotFoundException("Nie znaleziono takiego obiektu");
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
        List<PumpEntity> pumpList = pumpsRepository.findAll().stream()
                .filter(pump -> !pump.isDeleted())
                .collect(Collectors.toList());

        List<BottlesEntity> bottlesList = bottlesRepository.findAll().stream()
                .filter(bottle -> !bottle.isDeleted())
                .collect(Collectors.toList());

        List<BagsEntity> bagsList = bagsRepository.findAll().stream()
                .filter(bag -> !bag.isDeleted())
                .collect(Collectors.toList());

        List<FendersEntity> fendersList = fendersRepository.findAll().stream()
                .filter(fender -> !fender.isDeleted())
                .collect(Collectors.toList());

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

    @GetMapping("/sort/name")
    public ResponseEntity<?> sortAccessoriesByName(@RequestParam(name = "parameter") String parameter) {
        List<List<?>> filteredParts = new ArrayList<>();

        List<BagsEntity> bagsEntities = bagsRepository.findAllByNameContainsIgnoreCase(parameter).stream()
                .filter(bag -> !bag.isDeleted())
                .collect(Collectors.toList());

        List<BottlesEntity> bottlesEntities = bottlesRepository.findAllByNameContainsIgnoreCase(parameter).stream()
                .filter(bottle -> !bottle.isDeleted())
                .collect(Collectors.toList());

        List<FendersEntity> fendersEntities = fendersRepository.findAllByNameContainsIgnoreCase(parameter).stream()
                .filter(fender -> !fender.isDeleted())
                .collect(Collectors.toList());

        List<PumpEntity> pumpEntities = pumpsRepository.findAllByNameContainsIgnoreCase(parameter).stream()
                .filter(pump -> !pump.isDeleted())
                .collect(Collectors.toList());

        if (!bagsEntities.isEmpty()) {
            filteredParts.add(bagsEntities);
        }
        if (!bottlesEntities.isEmpty()) {
            filteredParts.add(bottlesEntities);
        }
        if (!fendersEntities.isEmpty()) {
            filteredParts.add(fendersEntities);
        }
        if (!pumpEntities.isEmpty()) {
            filteredParts.add(pumpEntities);
        }

        return ResponseEntity.status(HttpStatus.OK).body(filteredParts);
    }
}
