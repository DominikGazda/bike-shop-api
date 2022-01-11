package pl.shop.bike.update.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.shop.bike.models.model.entities.BikeEntity;
import pl.shop.bike.models.model.entities.ImageEntity;
import pl.shop.bike.models.model.entities.bikeParts.BrakeEntity;
import pl.shop.bike.models.model.entities.bikeParts.DriveEntity;
import pl.shop.bike.models.model.entities.bikeParts.FrameEntity;
import pl.shop.bike.models.model.enums.BikeType;
import pl.shop.bike.models.model.enums.GenderType;
import pl.shop.bike.models.model.request.SaveBikeRequest;
import pl.shop.bike.models.model.request.bike.SaveBikeResponse;
import pl.shop.bike.models.model.response.DeleteResponse;
import pl.shop.bike.update.services.CloudinaryService;
import pl.shop.commons.dao.BikesRepository;
import pl.shop.commons.dao.bikePartsDAO.BrakeRepository;
import pl.shop.commons.dao.bikePartsDAO.DriveRepository;
import pl.shop.commons.dao.bikePartsDAO.FrameRepository;
import pl.shop.commons.errors.exceptions.ItemNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/update/bike")
public class BikeController {

    @Autowired
    private BikesRepository bikesRepository;

    @Autowired
    private BrakeRepository brakeRepository;

    @Autowired
    private DriveRepository driveRepository;

    @Autowired
    private FrameRepository frameRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    private StringBuilder stringBuilder = new StringBuilder();


    @DeleteMapping
    ResponseEntity<DeleteResponse> deleteBikes(@RequestParam(name = "bikesId") Long bikesId,
                                               @RequestParam(name = "type") BikeType type) {
        DeleteResponse response;

        BikeEntity bikeEntity = bikesRepository.findById(bikesId)
                .orElseThrow(() -> new ItemNotFoundException("Nie znaleziono roweru"));

        bikeEntity.setDeleted(true);
        bikesRepository.save(bikeEntity);

        response = DeleteResponse.builder()
                .name(bikeEntity.getName())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<SaveBikeResponse> saveBike(@ModelAttribute SaveBikeRequest request, HttpServletRequest servletRequest) throws IOException, ServletException {
        String line;
        SaveBikeRequest saveBikeRequest = null;
        List<ImageEntity> imageEntities = new ArrayList<>();


        for (Part part : servletRequest.getParts()) {
            if (part.getName().equals("SaveBikeJSON")) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream(), StandardCharsets.UTF_8));

                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                saveBikeRequest = new ObjectMapper().readValue(stringBuilder.toString(), SaveBikeRequest.class);
            } else if (part.getName().equals("SaveBikeRequest")) {
                continue;
            } else {
                String url = cloudinaryService.uploadPhoto(part);
                ImageEntity entity = ImageEntity.builder()
                        .imageUrl(url)
                        .build();
                imageEntities.add(entity);
            }
        }

        BrakeEntity brakeEntity = brakeRepository.findByNameIgnoreCase(saveBikeRequest.getBrake());
        DriveEntity driveEntity = driveRepository.findByNameIgnoreCase(saveBikeRequest.getDrive());
        FrameEntity frameEntity = frameRepository.findByNameIgnoreCase(saveBikeRequest.getFrame());

        BikeEntity bikeEntity = BikeEntity.builder()
                .name(saveBikeRequest.getName())
                .description(saveBikeRequest.getDescription())
                .price(saveBikeRequest.getPrice())
                .quantity(saveBikeRequest.getQuantity())
                .itemAmount(saveBikeRequest.getItemAmount())
                .mark(saveBikeRequest.getMark())
                .color(saveBikeRequest.getColor())
                .bikeType(BikeType.findBikeByType(saveBikeRequest.getBikeType()))
                .bikeCode(saveBikeRequest.getBikeCode())
                .itemType(saveBikeRequest.getItemType())
                .genderType(GenderType.findGenderByType(saveBikeRequest.getGenderType()))
                .brake(brakeEntity)
                .drive(driveEntity)
                .frame(frameEntity)
                .images(imageEntities)
                .build();

        BikeEntity savedBike = bikesRepository.save(bikeEntity);
        SaveBikeResponse response = createResponse(savedBike);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/save")
    ResponseEntity<SaveBikeResponse> updateBike(@RequestBody SaveBikeRequest request) {
        BikeEntity bikeToModify = bikesRepository.findById(request.getId())
                .orElseThrow(() -> new ItemNotFoundException("Nie znaleziono roweru"));

        if (request.getName() != null) {
            bikeToModify.setName(request.getName());
        }
        if (request.getDescription() != null) {
            bikeToModify.setDescription(request.getDescription());
        }
        if (request.getPrice() != null) {
            bikeToModify.setPrice(request.getPrice());
        }
        if (request.getQuantity() != null) {
            bikeToModify.setQuantity(request.getQuantity());
        }
        if (request.getItemAmount() != null) {
            bikeToModify.setItemAmount(request.getItemAmount());
        }
        if (request.getMark() != null) {
            bikeToModify.setMark(request.getMark());
        }
        if (request.getColor() != null) {
            bikeToModify.setColor(request.getColor());
        }

        try {
            bikeToModify.setBikeType(BikeType.findBikeByType(request.getBikeType()));
        } catch (IllegalArgumentException e) {
            log.info("Nie podano typu");
        }

        if (request.getBikeCode() != null) {
            bikeToModify.setBikeCode(request.getBikeCode());
        }
        if (request.getGenderType() != null) {
            bikeToModify.setGenderType(GenderType.findGenderByType(request.getGenderType()));
        }
        if (!(request.getBrake().length() == 1)) {
            BrakeEntity brake = brakeRepository.findByNameIgnoreCase(request.getBrake());
            bikeToModify.setBrake(brake);
        }
        if (!(request.getDrive().length() == 1)) {
            DriveEntity drive = driveRepository.findByNameIgnoreCase(request.getDrive());
            bikeToModify.setDrive(drive);
        }
        if (!(request.getFrame().length() == 1)) {
            FrameEntity frame = frameRepository.findByNameIgnoreCase(request.getFrame().trim());
            System.out.println(frame);
            bikeToModify.setFrame(frame);
        }

        BikeEntity modifiedBike = bikesRepository.save(bikeToModify);
        SaveBikeResponse response = createResponse(modifiedBike);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private SaveBikeResponse createResponse(BikeEntity bike) {
        SaveBikeResponse response = SaveBikeResponse.builder()
                .name(bike.getName())
                .description(bike.getDescription())
                .price(bike.getPrice())
                .quantity(bike.getQuantity())
                .itemAmount(bike.getItemAmount())
                .mark(bike.getMark())
                .color(bike.getColor())
                .bikeType(bike.getBikeType())
                .bikeCode(bike.getBikeCode())
                .genderType(bike.getGenderType())
                .brake(bike.getBrake().getName())
                .drive(bike.getDrive().getName())
                .frame(bike.getFrame().getName())
                .build();

        return response;
    }
}
