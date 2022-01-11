package pl.shop.bike.read.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.shop.bike.models.model.entities.BikeEntity;
import pl.shop.bike.models.model.enums.GenderType;
import pl.shop.bike.models.model.request.bikesFilter.BikesFilterRequest;
import pl.shop.bike.read.services.DuplicateConverter;
import pl.shop.commons.dao.BikesRepository;
import pl.shop.commons.errors.exceptions.ItemNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static pl.shop.commons.utils.FilterNamesList.*;

@RestController
@RequestMapping("/api/read/bikes")
public class BikesDaoController {

    @Autowired
    private BikesRepository bikesRepository;

    @Autowired
    private DuplicateConverter duplicateConverter;

    @GetMapping
    public List<BikeEntity> getAllBikes() {

        return bikesRepository.findAll().stream()
                .filter(bike -> !bike.isDeleted())
                .collect(Collectors.toList());
    }

    @GetMapping("/{name}")
    public BikeEntity findBikeByName(@PathVariable String name) {
        BikeEntity bikeEntity = bikesRepository.findByNameIgnoreCase(name);

        if (bikeEntity == null) {
            throw new ItemNotFoundException("Nie znaleziono roweru o podanej nazwie");
        }

        return bikesRepository.findByNameIgnoreCase(name);
    }

    @GetMapping("/new")
    public List<BikeEntity> findNewestBikes() {
        return bikesRepository.findTop5ByOrderByIdDesc().stream()
                .filter(bike -> !bike.isDeleted())
                .collect(Collectors.toList());
    }

    @PostMapping("/sort")
    public List<BikeEntity> sortBikesByParameter(@RequestBody BikesFilterRequest bikesFilterRequest) {
        List<BikeEntity> filteredBikesList = new ArrayList<>();

        if (!bikesFilterRequest.getMark().getKands() && !bikesFilterRequest.getMark().getMerida() &&
                !bikesFilterRequest.getMark().getCross() && !bikesFilterRequest.getMark().getBmx()) {
            if (!bikesFilterRequest.getColor().getBlack() && !bikesFilterRequest.getColor().getWhite() &&
                    !bikesFilterRequest.getColor().getBlue() && !bikesFilterRequest.getColor().getRed() &&
                    !bikesFilterRequest.getColor().getGray() && !bikesFilterRequest.getColor().getNavy()) {
                if (!bikesFilterRequest.getFrame().getFrame26() && !bikesFilterRequest.getFrame().getFrame27() &&
                        !bikesFilterRequest.getFrame().getFrame28() && !bikesFilterRequest.getFrame().getFrame29()) {
                    if (!bikesFilterRequest.getGender().getMan() && !bikesFilterRequest.getGender().getWoman()) {
                        if (bikesFilterRequest.getPrice().getHighPrice() == 0 && bikesFilterRequest.getPrice().getLowPrice() == 0) {
                            return bikesRepository.findAll().stream()
                                    .filter(bike -> !bike.isDeleted())
                                    .collect(Collectors.toList());
                        }
                    }
                }
            }
        }

        if (bikesFilterRequest.getMark().getKands() || bikesFilterRequest.getMark().getMerida() ||
                bikesFilterRequest.getMark().getCross() || bikesFilterRequest.getMark().getBmx()) {
            if (bikesFilterRequest.getMark().getKands()) {
                filteredBikesList.addAll(bikesRepository.findAllByMarkIgnoreCase(KANDS).stream()
                        .filter(bike -> !bike.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (bikesFilterRequest.getMark().getMerida()) {
                filteredBikesList.addAll(bikesRepository.findAllByMarkIgnoreCase(MERIDA).stream()
                        .filter(bike -> !bike.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (bikesFilterRequest.getMark().getCross()) {
                filteredBikesList.addAll(bikesRepository.findAllByMarkIgnoreCase(CROSS).stream()
                        .filter(bike -> !bike.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (bikesFilterRequest.getMark().getBmx()) {
                filteredBikesList.addAll(bikesRepository.findAllByMarkIgnoreCase(BMX).stream()
                        .filter(bike -> !bike.isDeleted())
                        .collect(Collectors.toList()));
            }
        }

        if (bikesFilterRequest.getColor().getBlack() || bikesFilterRequest.getColor().getWhite() ||
                bikesFilterRequest.getColor().getBlue() || bikesFilterRequest.getColor().getRed() ||
                bikesFilterRequest.getColor().getGray() || bikesFilterRequest.getColor().getNavy()) {
            if (bikesFilterRequest.getColor().getBlack()) {
                filteredBikesList.addAll(bikesRepository.findAllByColorIgnoreCase(BLACK).stream()
                        .filter(bike -> !bike.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (bikesFilterRequest.getColor().getWhite()) {
                filteredBikesList.addAll(bikesRepository.findAllByColorIgnoreCase(WHITE).stream()
                        .filter(bike -> !bike.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (bikesFilterRequest.getColor().getBlue()) {
                filteredBikesList.addAll(bikesRepository.findAllByColorIgnoreCase(BLUE).stream()
                        .filter(bike -> !bike.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (bikesFilterRequest.getColor().getRed()) {
                filteredBikesList.addAll(bikesRepository.findAllByColorIgnoreCase(RED).stream()
                        .filter(bike -> !bike.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (bikesFilterRequest.getColor().getGray()) {
                filteredBikesList.addAll(bikesRepository.findAllByColorIgnoreCase(GRAY).stream()
                        .filter(bike -> !bike.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (bikesFilterRequest.getColor().getNavy()) {
                filteredBikesList.addAll(bikesRepository.findAllByColorIgnoreCase(NAVY).stream()
                        .filter(bike -> !bike.isDeleted())
                        .collect(Collectors.toList()));
            }
        }

        if (bikesFilterRequest.getFrame().getFrame26() || bikesFilterRequest.getFrame().getFrame27() ||
                bikesFilterRequest.getFrame().getFrame28() || bikesFilterRequest.getFrame().getFrame29()) {
            if (bikesFilterRequest.getFrame().getFrame26()) {
                filteredBikesList.addAll(bikesRepository.findAllBikesByFrameSize(FRAME26).stream()
                        .filter(bike -> !bike.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (bikesFilterRequest.getFrame().getFrame27()) {
                filteredBikesList.addAll(bikesRepository.findAllBikesByFrameSize(FRAME27).stream()
                        .filter(bike -> !bike.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (bikesFilterRequest.getFrame().getFrame28()) {
                filteredBikesList.addAll(bikesRepository.findAllBikesByFrameSize(FRAME28).stream()
                        .filter(bike -> !bike.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (bikesFilterRequest.getFrame().getFrame29()) {
                filteredBikesList.addAll(bikesRepository.findAllBikesByFrameSize(FRAME29).stream()
                        .filter(bike -> !bike.isDeleted())
                        .collect(Collectors.toList()));
            }
        }

        if (bikesFilterRequest.getGender().getMan() || bikesFilterRequest.getGender().getWoman()) {
            if (bikesFilterRequest.getGender().getMan()) {
                filteredBikesList.addAll(bikesRepository.findAllByGenderType(GenderType.MALE).stream()
                        .filter(bike -> !bike.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (bikesFilterRequest.getGender().getWoman()) {
                filteredBikesList.addAll(bikesRepository.findAllByGenderType(GenderType.FEMALE).stream()
                        .filter(bike -> !bike.isDeleted())
                        .collect(Collectors.toList()));
            }
        }

        if (bikesFilterRequest.getPrice().getHighPrice() > 0 && bikesFilterRequest.getPrice().getLowPrice() > 0) {
            filteredBikesList.addAll(bikesRepository.findAllByPriceGreaterThanEqualAndPriceLessThanEqual(bikesFilterRequest.getPrice().getLowPrice(), bikesFilterRequest.getPrice().getHighPrice()).stream()
                    .filter(bike -> !bike.isDeleted())
                    .collect(Collectors.toList()));
        }

        return duplicateConverter.removeDuplicatesFromList(filteredBikesList);
    }

    @GetMapping("/sort/name")
    public List<BikeEntity> sortBikesByName(@RequestParam(name = "parameter") String parameter) {
        return bikesRepository.findAllByNameContainsIgnoreCase(parameter).stream()
                .filter(bike -> !bike.isDeleted())
                .collect(Collectors.toList());
    }

}
