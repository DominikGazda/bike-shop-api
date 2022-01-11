package pl.gazda.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.gazda.admin.services.CloudinaryService;
import pl.shop.bike.models.model.entities.ImageEntity;
import pl.shop.bike.models.model.enums.AccessoriesType;
import pl.shop.bike.models.model.request.SaveAccessoriesRequest;
import pl.shop.bike.models.model.response.DeleteResponse;
import pl.shop.bike.models.model.response.SaveAccessoriesResponse;
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
@RequestMapping("/api/adm/accessories")
public class AccessoriesController {

    @Autowired
    UpdateServiceClient updateServiceClient;

    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping
    public ResponseEntity<SaveAccessoriesResponse> saveAccessories(HttpServletRequest servletRequest,
                                                                   @Valid @ModelAttribute SaveAccessoriesRequest request,
                                                                   BindingResult result) throws ServletException, IOException {
        if(result.hasErrors()){
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
        return updateServiceClient.saveAccessories(request);
    }

    @DeleteMapping
    ResponseEntity<DeleteResponse> deleteAccessories(@RequestParam(name = "id") Long accessoriesId,
                                                     @RequestParam(name = "type") AccessoriesType type) {
        return updateServiceClient.deleteAccessories(accessoriesId, type);
    }

    @PostMapping("/save")
    public ResponseEntity<SaveAccessoriesResponse> updateAccessories(@RequestBody SaveAccessoriesRequest request) {
        return updateServiceClient.updateAccessories(request);
    }
}
