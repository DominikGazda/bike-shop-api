package pl.shop.bike.read.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.shop.bike.models.model.entities.BikeEntity;
import pl.shop.bike.models.model.enums.GenderType;
import pl.shop.bike.models.model.request.bikesFilter.BikesFilterRequest;
import pl.shop.bike.read.services.DuplicateConverter;
import pl.shop.commons.dao.BikesRepository;

import java.util.ArrayList;
import java.util.List;

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
        return bikesRepository.findAll();
    }

    @GetMapping("/{name}")
    public BikeEntity findBikeByName(@PathVariable String name) {
        return bikesRepository.findByNameIgnoreCase(name);
    }

    @GetMapping("/new")
    public List<BikeEntity> findNewestBikes() {
        return bikesRepository.findTop5ByOrderByIdDesc();
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
                            return bikesRepository.findAll();
                        }
                    }
                }
            }
        }

        if (bikesFilterRequest.getMark().getKands() || bikesFilterRequest.getMark().getMerida() ||
                bikesFilterRequest.getMark().getCross() || bikesFilterRequest.getMark().getBmx()) {
            if (bikesFilterRequest.getMark().getKands()) {
                filteredBikesList.addAll(bikesRepository.findAllByMarkIgnoreCase(KANDS));
            }
            if (bikesFilterRequest.getMark().getMerida()) {
                filteredBikesList.addAll(bikesRepository.findAllByMarkIgnoreCase(MERIDA));
            }
            if (bikesFilterRequest.getMark().getCross()) {
                filteredBikesList.addAll(bikesRepository.findAllByMarkIgnoreCase(CROSS));
            }
            if (bikesFilterRequest.getMark().getBmx()) {
                filteredBikesList.addAll(bikesRepository.findAllByMarkIgnoreCase(BMX));
            }
        }

        if (bikesFilterRequest.getColor().getBlack() || bikesFilterRequest.getColor().getWhite() ||
                bikesFilterRequest.getColor().getBlue() || bikesFilterRequest.getColor().getRed() ||
                bikesFilterRequest.getColor().getGray() || bikesFilterRequest.getColor().getNavy()) {
            if (bikesFilterRequest.getColor().getBlack()) {
                filteredBikesList.addAll(bikesRepository.findAllByColorIgnoreCase(BLACK));
            }
            if (bikesFilterRequest.getColor().getWhite()) {
                filteredBikesList.addAll(bikesRepository.findAllByColorIgnoreCase(WHITE));
            }
            if (bikesFilterRequest.getColor().getBlue()) {
                filteredBikesList.addAll(bikesRepository.findAllByColorIgnoreCase(BLUE));
            }
            if (bikesFilterRequest.getColor().getRed()) {
                filteredBikesList.addAll(bikesRepository.findAllByColorIgnoreCase(RED));
            }
            if (bikesFilterRequest.getColor().getGray()) {
                filteredBikesList.addAll(bikesRepository.findAllByColorIgnoreCase(GRAY));
            }
            if (bikesFilterRequest.getColor().getNavy()) {
                filteredBikesList.addAll(bikesRepository.findAllByColorIgnoreCase(NAVY));
            }
        }

        if (bikesFilterRequest.getFrame().getFrame26() || bikesFilterRequest.getFrame().getFrame27() ||
                bikesFilterRequest.getFrame().getFrame28() || bikesFilterRequest.getFrame().getFrame29()) {
            if (bikesFilterRequest.getFrame().getFrame26()) {
                filteredBikesList.addAll(bikesRepository.findAllBikesByFrameSize(FRAME26));
            }
            if (bikesFilterRequest.getFrame().getFrame27()) {
                filteredBikesList.addAll(bikesRepository.findAllBikesByFrameSize(FRAME27));
            }
            if (bikesFilterRequest.getFrame().getFrame28()) {
                filteredBikesList.addAll(bikesRepository.findAllBikesByFrameSize(FRAME28));
            }
            if (bikesFilterRequest.getFrame().getFrame29()) {
                filteredBikesList.addAll(bikesRepository.findAllBikesByFrameSize(FRAME29));
            }
        }

        if (bikesFilterRequest.getGender().getMan() || bikesFilterRequest.getGender().getWoman()) {
            if (bikesFilterRequest.getGender().getMan()) {
                filteredBikesList.addAll(bikesRepository.findAllByGenderType(GenderType.MALE));
            }
            if (bikesFilterRequest.getGender().getWoman()) {
                filteredBikesList.addAll(bikesRepository.findAllByGenderType(GenderType.FEMALE));
            }
        }

        if (bikesFilterRequest.getPrice().getHighPrice() > 0 && bikesFilterRequest.getPrice().getLowPrice() > 0) {
            filteredBikesList.addAll(bikesRepository.findAllByPriceGreaterThanEqualAndPriceLessThanEqual(bikesFilterRequest.getPrice().getLowPrice(), bikesFilterRequest.getPrice().getHighPrice()));
        }

        return duplicateConverter.removeDuplicatesFromList(filteredBikesList);
    }

    @GetMapping("/sort/name")
    public List<BikeEntity> sortBikesByName(@RequestParam(name = "parameter") String parameter) {
        return bikesRepository.findAllByNameContainsIgnoreCase(parameter);
    }


}
