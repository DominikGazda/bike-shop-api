package pl.gazda.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.gazda.admin.services.CloudinaryService;
import pl.shop.bike.models.model.entities.ImageEntity;
import pl.shop.bike.models.model.enums.BikePartsType;
import pl.shop.bike.models.model.request.SaveBikePartRequest;
import pl.shop.bike.models.model.response.DeleteResponse;
import pl.shop.bike.models.model.response.SaveBikePartResponse;
import pl.shop.commons.clients.UpdateServiceClient;
import pl.shop.commons.errors.ErrorsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/adm/bike/part")
public class BikePartsController {

    @Autowired
    UpdateServiceClient updateServiceClient;

    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping
    public ResponseEntity<SaveBikePartResponse> saveBikePart(HttpServletRequest servletRequest,
                                                             @Valid @ModelAttribute SaveBikePartRequest request,
                                                             BindingResult result) throws ServletException, IOException {

        if (result.hasErrors()) {
            ErrorsUtil.createErrorResponse(result, request.getItemType());
        }

        List<ImageEntity> imageEntities = new ArrayList<>();

        List<Part> partList = servletRequest.getParts().stream()
                .filter(item -> item.getName().startsWith("image"))
                .collect(Collectors.toList());

        for (Part part : partList) {
            String url = cloudinaryService.uploadPhoto(part);
            ImageEntity entity = ImageEntity.builder()
                    .imageUrl(url)
                    .build();
            imageEntities.add(entity);
        }

        request.setImageEntityList(imageEntities);

        return updateServiceClient.saveBikePart(request);
    }

    @DeleteMapping
    public ResponseEntity<DeleteResponse> deleteBikeParts(@RequestParam(name = "id") Long bikePartId,
                                                          @RequestParam(name = "type") BikePartsType type) {
        return updateServiceClient.deleteBikeParts(bikePartId, type);
    }

    @PostMapping("/save")
    public ResponseEntity<SaveBikePartResponse> updateBikePart(@RequestBody SaveBikePartRequest request) {
        return updateServiceClient.updateBikePart(request);
    }
}
