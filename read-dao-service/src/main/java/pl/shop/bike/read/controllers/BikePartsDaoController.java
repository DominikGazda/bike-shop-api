package pl.shop.bike.read.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.shop.bike.models.model.entities.bikeParts.BrakeEntity;
import pl.shop.bike.models.model.entities.bikeParts.DriveEntity;
import pl.shop.bike.models.model.entities.bikeParts.FrameEntity;
import pl.shop.bike.models.model.enums.BikePartsType;
import pl.shop.bike.models.model.request.bikesPartsFilter.BikesPartsFilterRequest;
import pl.shop.bike.models.model.response.BikePartsNamesResponse;
import pl.shop.commons.dao.bikePartsDAO.BrakeRepository;
import pl.shop.commons.dao.bikePartsDAO.DriveRepository;
import pl.shop.commons.dao.bikePartsDAO.FrameRepository;
import pl.shop.commons.errors.exceptions.ItemNotFoundException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
            List<BrakeEntity> brakeList = brakeRepository.findAll().stream()
                    .filter(brake -> !brake.isDeleted())
                    .collect(Collectors.toList());

            List<DriveEntity> driveList = driveRepository.findAll().stream()
                    .filter(drive -> !drive.isDeleted())
                    .collect(Collectors.toList());

            List<FrameEntity> frameList = frameRepository.findAll().stream()
                    .filter(frame -> !frame.isDeleted())
                    .collect(Collectors.toList());

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
                return brakeRepository.findAll().stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList());
            case DRIVE:
                return driveRepository.findAll().stream()
                        .filter(drive -> !drive.isDeleted())
                        .collect(Collectors.toList());
            case FRAME:
                return frameRepository.findAll().stream()
                        .filter(frame -> !frame.isDeleted())
                        .collect(Collectors.toList());
            default:
                throw new ItemNotFoundException("Nie znaleziono takiego przedmiotu");
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getBikePartByName(@PathVariable(name = "name") String name) {
        System.out.println(name);
        BrakeEntity brakeEntity = brakeRepository.findByNameIgnoreCase(name);
        if (brakeEntity == null) {
            DriveEntity driveEntity = driveRepository.findByNameIgnoreCase(name);
            if (driveEntity == null) {
                FrameEntity frameEntity = frameRepository.findByNameIgnoreCase(name);
                if (frameEntity != null) {
                    return ResponseEntity.ok(frameEntity);
                } else {
                    throw new ItemNotFoundException("Nie znaleziono takiego przedmiotu");
                }
            } else {
                return ResponseEntity.ok(driveEntity);
            }
        } else {
            return ResponseEntity.ok(brakeEntity);
        }
    }

    @PostMapping("/sort")
    public List<Set<?>> sortBikesByParameter(@RequestBody BikesPartsFilterRequest request) {
        List<Set<?>> parts = new ArrayList<>();

        Set<BrakeEntity> filteredBrakes = new HashSet<>();
        Set<DriveEntity> filteredDrive = new HashSet<>();
        Set<FrameEntity> filteredFrame = new HashSet<>();

        if (!request.getType().getBrake() && !request.getType().getFrame() && !request.getType().getDrive()) {
            if (!request.getColor().getBlack() && !request.getColor().getWhite() &&
                    !request.getColor().getBlue() && !request.getColor().getRed() &&
                    !request.getColor().getGray() && !request.getColor().getNavy()) {
                if (!request.getMark().getTrec() && !request.getMark().getMerida() && !request.getMark().getKands()) {
                    if (request.getPrice().getHighPrice() == 0 && request.getPrice().getLowPrice() == 0) {
                        filteredBrakes.addAll(brakeRepository.findAll().stream()
                                .filter(brake -> !brake.isDeleted())
                                .collect(Collectors.toList()));

                        filteredDrive.addAll(driveRepository.findAll().stream()
                                .filter(brake -> !brake.isDeleted())
                                .collect(Collectors.toList()));

                        filteredFrame.addAll(frameRepository.findAll().stream()
                                .filter(brake -> !brake.isDeleted())
                                .collect(Collectors.toList()));

                        parts.add(filteredBrakes);
                        parts.add(filteredFrame);
                        parts.add(filteredDrive);

                        return parts;
                    }
                }
            }
        }


        if (request.getType().getBrake() || request.getType().getDrive() || request.getType().getFrame()) {
            if (request.getType().getBrake())
                filteredBrakes.addAll(brakeRepository.findAll().stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));

            if (request.getType().getFrame())
                filteredFrame.addAll(frameRepository.findAll().stream()
                        .filter(frame -> !frame.isDeleted())
                        .collect(Collectors.toList()));

            if (request.getType().getDrive())
                filteredDrive.addAll(driveRepository.findAll().stream()
                        .filter(drive -> !drive.isDeleted())
                        .collect(Collectors.toList()));
        }

        if (request.getMark().getKands() || request.getMark().getMerida() || request.getMark().getTrec() || request.getMark().getSram() || request.getMark().getShimano()) {
            if (request.getMark().getKands()) {
                filteredBrakes.addAll(brakeRepository.findAllByMarkIgnoreCase(KANDS).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));

                filteredFrame.addAll(frameRepository.findAllByMarkIgnoreCase(KANDS).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));

                filteredDrive.addAll(driveRepository.findAllByMarkIgnoreCase(KANDS).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (request.getMark().getTrec()) {
                filteredBrakes.addAll(brakeRepository.findAllByMarkIgnoreCase(TREC).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));

                filteredFrame.addAll(frameRepository.findAllByMarkIgnoreCase(TREC).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));

                filteredDrive.addAll(driveRepository.findAllByMarkIgnoreCase(TREC).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (request.getMark().getMerida()) {
                filteredBrakes.addAll(brakeRepository.findAllByMarkIgnoreCase(MERIDA).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));

                filteredFrame.addAll(frameRepository.findAllByMarkIgnoreCase(MERIDA).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));

                filteredDrive.addAll(driveRepository.findAllByMarkIgnoreCase(MERIDA).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (request.getMark().getShimano()) {
                filteredBrakes.addAll(brakeRepository.findAllByMarkIgnoreCase(SHIMANO).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));

                filteredFrame.addAll(frameRepository.findAllByMarkIgnoreCase(SHIMANO).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));

                filteredDrive.addAll(driveRepository.findAllByMarkIgnoreCase(SHIMANO).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (request.getMark().getSram()) {
                filteredBrakes.addAll(brakeRepository.findAllByMarkIgnoreCase(SRAM).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));

                filteredFrame.addAll(frameRepository.findAllByMarkIgnoreCase(SRAM).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));

                filteredDrive.addAll(driveRepository.findAllByMarkIgnoreCase(SRAM).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));
            }
        }

        if (request.getColor().getBlack() || request.getColor().getWhite() ||
                request.getColor().getBlue() || request.getColor().getRed() ||
                request.getColor().getGray() || request.getColor().getNavy()) {
            if (request.getColor().getBlack()) {
                filteredBrakes.addAll(brakeRepository.findAllByColorIgnoreCase(BLACK).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));

                filteredDrive.addAll(driveRepository.findAllByColorIgnoreCase(BLACK).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));

                filteredFrame.addAll(frameRepository.findAllByColorIgnoreCase(BLACK).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (request.getColor().getWhite()) {
                filteredBrakes.addAll(brakeRepository.findAllByColorIgnoreCase(WHITE).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));

                filteredDrive.addAll(driveRepository.findAllByColorIgnoreCase(WHITE).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));

                filteredFrame.addAll(frameRepository.findAllByColorIgnoreCase(WHITE).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (request.getColor().getBlue()) {
                filteredBrakes.addAll(brakeRepository.findAllByColorIgnoreCase(BLUE).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));

                filteredDrive.addAll(driveRepository.findAllByColorIgnoreCase(BLUE).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));

                filteredFrame.addAll(frameRepository.findAllByColorIgnoreCase(BLUE).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (request.getColor().getRed()) {
                filteredBrakes.addAll(brakeRepository.findAllByColorIgnoreCase(RED).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));

                filteredDrive.addAll(driveRepository.findAllByColorIgnoreCase(RED).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));

                filteredFrame.addAll(frameRepository.findAllByColorIgnoreCase(RED).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (request.getColor().getGray()) {
                filteredBrakes.addAll(brakeRepository.findAllByColorIgnoreCase(GRAY).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));

                filteredDrive.addAll(driveRepository.findAllByColorIgnoreCase(GRAY).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));

                filteredFrame.addAll(frameRepository.findAllByColorIgnoreCase(GRAY).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));
            }

            if (request.getColor().getNavy()) {
                filteredBrakes.addAll(brakeRepository.findAllByColorIgnoreCase(NAVY).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));

                filteredDrive.addAll(driveRepository.findAllByColorIgnoreCase(NAVY).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));

                filteredFrame.addAll(frameRepository.findAllByColorIgnoreCase(NAVY).stream()
                        .filter(brake -> !brake.isDeleted())
                        .collect(Collectors.toList()));
            }
        }

        if (request.getPrice().getHighPrice() > 0 && request.getPrice().getLowPrice() > 0) {
            filteredBrakes.addAll(brakeRepository.findAllByPriceGreaterThanEqualAndPriceLessThanEqual(request.getPrice().getLowPrice(), request.getPrice().getHighPrice()).stream()
                    .filter(brake -> !brake.isDeleted())
                    .collect(Collectors.toList()));

            filteredDrive.addAll(driveRepository.findAllByPriceGreaterThanEqualAndPriceLessThanEqual(request.getPrice().getLowPrice(), request.getPrice().getHighPrice()).stream()
                    .filter(brake -> !brake.isDeleted())
                    .collect(Collectors.toList()));

            filteredFrame.addAll(frameRepository.findAllByPriceGreaterThanEqualAndPriceLessThanEqual(request.getPrice().getLowPrice(), request.getPrice().getHighPrice()).stream()
                    .filter(brake -> !brake.isDeleted())
                    .collect(Collectors.toList()));
        }

        parts.add(filteredBrakes);
        parts.add(filteredDrive);
        parts.add(filteredFrame);

        return parts;
    }

    @GetMapping("/sort/name")
    public ResponseEntity<?> sortPartsByName(@RequestParam(name = "parameter") String parameter) {
        List<List<?>> filteredParts = new ArrayList<>();

        List<BrakeEntity> brakes = brakeRepository.findAllByNameContainsIgnoreCase(parameter).stream()
                .filter(brake -> !brake.isDeleted())
                .collect(Collectors.toList());

        List<DriveEntity> drives = driveRepository.findAllByNameContainsIgnoreCase(parameter).stream()
                .filter(brake -> !brake.isDeleted())
                .collect(Collectors.toList());

        List<FrameEntity> frames = frameRepository.findAllByNameContainsIgnoreCase(parameter).stream()
                .filter(brake -> !brake.isDeleted())
                .collect(Collectors.toList());

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

    @GetMapping("/names")
    public BikePartsNamesResponse getBikePartsNames() {
        List<BrakeEntity> brakes = brakeRepository.findAll().stream()
                .filter(brake -> !brake.isDeleted())
                .collect(Collectors.toList());

        List<DriveEntity> drives = driveRepository.findAll().stream()
                .filter(brake -> !brake.isDeleted())
                .collect(Collectors.toList());

        List<FrameEntity> frames = frameRepository.findAll().stream()
                .filter(brake -> !brake.isDeleted())
                .collect(Collectors.toList());

        List<String> brakeNames = brakes.stream()
                .map(brake -> brake.getName()).
                        collect(Collectors.toList());

        List<String> drivesNames = drives.stream()
                .map(drive -> drive.getName())
                .collect(Collectors.toList());

        List<String> framesNames = frames.stream()
                .map(frame -> frame.getName())
                .collect(Collectors.toList());

        return BikePartsNamesResponse.builder()
                .brakeNames(brakeNames)
                .driveNames(drivesNames)
                .frameNames(framesNames)
                .build();
    }
}
