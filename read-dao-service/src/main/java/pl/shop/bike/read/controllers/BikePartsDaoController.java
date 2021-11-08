package pl.shop.bike.read.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.shop.bike.models.model.entities.bikeParts.BrakeEntity;
import pl.shop.bike.models.model.entities.bikeParts.DriveEntity;
import pl.shop.bike.models.model.entities.bikeParts.FrameEntity;
import pl.shop.bike.models.model.enums.BikePartsType;
import pl.shop.bike.models.model.request.bikesPartsFilter.BikesPartsFilterRequest;
import pl.shop.commons.dao.bikePartsDAO.BrakeRepository;
import pl.shop.commons.dao.bikePartsDAO.DriveRepository;
import pl.shop.commons.dao.bikePartsDAO.FrameRepository;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static pl.shop.commons.utils.FilterNamesList.*;

@RestController
@RequestMapping("/api/read/bike/parts")
public class BikePartsDaoController {

    private BrakeRepository brakeRepository;
    private DriveRepository driveRepository;
    private FrameRepository frameRepository;

    public BikePartsDaoController(BrakeRepository brakeRepository, DriveRepository driveRepository, FrameRepository frameRepository) {
        this.brakeRepository = brakeRepository;
        this.driveRepository = driveRepository;
        this.frameRepository = frameRepository;
    }

    @GetMapping
    public List<?> getAllBikeParts(@RequestParam(name = "type", required = false) String type) {
        if (type == null) {
            List<List<?>> allPartsList = new ArrayList<>();
            List<BrakeEntity> brakeList = brakeRepository.findAll();
            List<DriveEntity> driveList = driveRepository.findAll();
            List<FrameEntity> frameList = frameRepository.findAll();

            if (!brakeList.isEmpty()) {
                allPartsList.add(brakeList);
            }
            if (!driveList.isEmpty()) {
                allPartsList.add(driveList);
            }
            if (!frameList.isEmpty()) {
                allPartsList.add(frameList);
            }
            return allPartsList;
        }

        BikePartsType enumType = BikePartsType.findBikePartsTypeByName(type);
        switch (enumType) {
            case BRAKE:
                return brakeRepository.findAll();
            case DRIVE:
                return driveRepository.findAll();
            case FRAME:
                return frameRepository.findAll();
            default:
                throw new IllegalArgumentException("To jest bikeparts");
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getBikePartByName(@PathVariable(name = "name") String name) {
        BrakeEntity brakeEntity = brakeRepository.findByNameIgnoreCase(name);
        if (brakeEntity == null) {
            DriveEntity driveEntity = driveRepository.findByNameIgnoreCase(name);
            if (driveEntity == null) {
                FrameEntity frameEntity = frameRepository.findByNameIgnoreCase(name);
                if (frameEntity != null) {
                    return ResponseEntity.ok(frameEntity);
                } else {
                    throw new IllegalArgumentException("Nie znaleziono obiektu w dao");
                }
            } else {
                return ResponseEntity.ok(driveEntity);
            }
        } else {
            return ResponseEntity.ok(brakeEntity);
        }
    }

    @PostMapping("/sort")
    public ResponseEntity<?> sortBikesByParameter(@RequestBody BikesPartsFilterRequest bikesPartsFilterRequest) {

        Set<BrakeEntity> filteredBrakes = new LinkedHashSet<>();
        Set<DriveEntity> filteredDrive = new LinkedHashSet<>();
        Set<FrameEntity> filteredFrame = new LinkedHashSet<>();

        if (bikesPartsFilterRequest.getType().getBrake() || bikesPartsFilterRequest.getType().getDrive() ||
                bikesPartsFilterRequest.getType().getFrame() || bikesPartsFilterRequest.getType().getTest1()) {
            if (bikesPartsFilterRequest.getType().getBrake())
                filteredBrakes.addAll(brakeRepository.findAll());
            if (bikesPartsFilterRequest.getType().getFrame())
                filteredFrame.addAll(frameRepository.findAll());
            if (bikesPartsFilterRequest.getType().getDrive())
                filteredDrive.addAll(driveRepository.findAll());
        }

        if (bikesPartsFilterRequest.getMark().getKands() || bikesPartsFilterRequest.getMark().getMerida() ||
                bikesPartsFilterRequest.getMark().getTrec() || bikesPartsFilterRequest.getMark().getTest2()) {
            if (bikesPartsFilterRequest.getMark().getKands()) {
                filteredBrakes.addAll(brakeRepository.findAllByMarkIgnoreCase(KANDS));
                filteredFrame.addAll(frameRepository.findAllByMarkIgnoreCase(KANDS));
                filteredDrive.addAll(driveRepository.findAllByMarkIgnoreCase(KANDS));
            }
            if (bikesPartsFilterRequest.getMark().getTrec()) {
                filteredBrakes.addAll(brakeRepository.findAllByMarkIgnoreCase(TREC));
                filteredFrame.addAll(frameRepository.findAllByMarkIgnoreCase(TREC));
                filteredDrive.addAll(driveRepository.findAllByMarkIgnoreCase(TREC));
            }
            if (bikesPartsFilterRequest.getMark().getMerida()) {
                filteredBrakes.addAll(brakeRepository.findAllByMarkIgnoreCase(MERIDA));
                filteredFrame.addAll(frameRepository.findAllByMarkIgnoreCase(MERIDA));
                filteredDrive.addAll(driveRepository.findAllByMarkIgnoreCase(MERIDA));
            }
            if (bikesPartsFilterRequest.getMark().getTest2()) {
                filteredBrakes.addAll(brakeRepository.findAllByMarkIgnoreCase(TEST2));
                filteredFrame.addAll(frameRepository.findAllByMarkIgnoreCase(TEST2));
                filteredDrive.addAll(driveRepository.findAllByMarkIgnoreCase(TEST2));
            }
        }

        if (bikesPartsFilterRequest.getColor().getBlack() || bikesPartsFilterRequest.getColor().getWhite() ||
                bikesPartsFilterRequest.getColor().getBlue() || bikesPartsFilterRequest.getColor().getRed() ||
                bikesPartsFilterRequest.getColor().getGray() || bikesPartsFilterRequest.getColor().getNavy()) {
            if (bikesPartsFilterRequest.getColor().getBlack()) {
                filteredBrakes.addAll(brakeRepository.findAllByColorIgnoreCase(BLACK));
                filteredDrive.addAll(driveRepository.findAllByColorIgnoreCase(BLACK));
                filteredFrame.addAll(frameRepository.findAllByColorIgnoreCase(BLACK));
            }
            if (bikesPartsFilterRequest.getColor().getWhite()) {
                filteredBrakes.addAll(brakeRepository.findAllByColorIgnoreCase(WHITE));
                filteredDrive.addAll(driveRepository.findAllByColorIgnoreCase(WHITE));
                filteredFrame.addAll(frameRepository.findAllByColorIgnoreCase(WHITE));
            }
            if (bikesPartsFilterRequest.getColor().getBlue()) {
                filteredBrakes.addAll(brakeRepository.findAllByColorIgnoreCase(BLUE));
                filteredDrive.addAll(driveRepository.findAllByColorIgnoreCase(BLUE));
                filteredFrame.addAll(frameRepository.findAllByColorIgnoreCase(BLUE));
            }
            if (bikesPartsFilterRequest.getColor().getRed()) {
                filteredBrakes.addAll(brakeRepository.findAllByColorIgnoreCase(RED));
                filteredDrive.addAll(driveRepository.findAllByColorIgnoreCase(RED));
                filteredFrame.addAll(frameRepository.findAllByColorIgnoreCase(RED));
            }
            if (bikesPartsFilterRequest.getColor().getGray()) {
                filteredBrakes.addAll(brakeRepository.findAllByColorIgnoreCase(GRAY));
                filteredDrive.addAll(driveRepository.findAllByColorIgnoreCase(GRAY));
                filteredFrame.addAll(frameRepository.findAllByColorIgnoreCase(GRAY));
            }
            if (bikesPartsFilterRequest.getColor().getNavy()) {
                filteredBrakes.addAll(brakeRepository.findAllByColorIgnoreCase(NAVY));
                filteredDrive.addAll(driveRepository.findAllByColorIgnoreCase(NAVY));
                filteredFrame.addAll(frameRepository.findAllByColorIgnoreCase(NAVY));
            }
        }

        if (bikesPartsFilterRequest.getPrice().getHighPrice() > 0 && bikesPartsFilterRequest.getPrice().getLowPrice() > 0) {
            filteredBrakes.addAll(brakeRepository.findAllByPriceGreaterThanEqualAndPriceLessThanEqual(bikesPartsFilterRequest.getPrice().getLowPrice(), bikesPartsFilterRequest.getPrice().getHighPrice()));
            filteredDrive.addAll(driveRepository.findAllByPriceGreaterThanEqualAndPriceLessThanEqual(bikesPartsFilterRequest.getPrice().getLowPrice(), bikesPartsFilterRequest.getPrice().getHighPrice()));
            filteredFrame.addAll(frameRepository.findAllByPriceGreaterThanEqualAndPriceLessThanEqual(bikesPartsFilterRequest.getPrice().getLowPrice(), bikesPartsFilterRequest.getPrice().getHighPrice()));
        }

        List<Set<?>> filteredParts = new ArrayList<>();

        if (!filteredBrakes.isEmpty())
            filteredParts.add(filteredBrakes);
        if (!filteredFrame.isEmpty())
            filteredParts.add(filteredFrame);
        if (!filteredDrive.isEmpty())
            filteredParts.add(filteredDrive);

        return ResponseEntity.ok(filteredParts);
    }

    @GetMapping("/sort/name")
    public ResponseEntity<?> sortPartsByName(@RequestParam(name = "parameter") String parameter) {
        List<List<?>> filteredParts = new ArrayList<>();

        List<BrakeEntity> brakes = brakeRepository.findAllByNameContainsIgnoreCase(parameter);
        List<DriveEntity> drives = driveRepository.findAllByNameContainsIgnoreCase(parameter);
        List<FrameEntity> frames = frameRepository.findAllByNameContainsIgnoreCase(parameter);

        if (!brakes.isEmpty()) {
            filteredParts.add(brakes);
        }
        if (!drives.isEmpty()) {
            filteredParts.add(drives);
        }
        if (!frames.isEmpty()) {
            filteredParts.add(frames);
        }
        return ResponseEntity.ok(filteredParts);
    }

}
