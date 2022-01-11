package pl.gazda.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.gazda.admin.services.CloudinaryService;
import pl.shop.bike.models.model.entities.ImageEntity;
import pl.shop.bike.models.model.enums.WorkshopType;
import pl.shop.bike.models.model.request.SaveWorkshopRequest;
import pl.shop.bike.models.model.response.DeleteResponse;
import pl.shop.bike.models.model.response.SaveWorkshopResponse;
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
@RequestMapping("/api/adm/workshop")
public class WorkshopController {

    @Autowired
    UpdateServiceClient updateServiceClient;

    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SaveWorkshopResponse> saveWorkshop(HttpServletRequest servletRequest,
                                                             @Valid @ModelAttribute SaveWorkshopRequest request,
                                                             BindingResult result) throws ServletException, IOException {
        if(result.hasErrors()){
            ErrorsUtil.createErrorResponse(result, request.getItemType());
        }

        List<Part> partList = servletRequest.getParts().stream()
                .filter(item -> item.getName().startsWith("image"))
                .collect(Collectors.toList());
        List<ImageEntity> imageEntities = new ArrayList<>();

        for (Part part : partList) {
            String url = cloudinaryService.uploadPhoto(part);
            ImageEntity entity = ImageEntity.builder()
                    .imageUrl(url)
                    .build();
            imageEntities.add(entity);
        }

        request.setImageEntities(imageEntities);

        return updateServiceClient.saveWorkshop(request);
    }

    @DeleteMapping
    ResponseEntity<DeleteResponse> deleteWorkshop(@RequestParam(name = "id") Long workshopId,
                                                  @RequestParam(name = "type") WorkshopType type) {
        return updateServiceClient.deleteWorkshop(workshopId, type);
    }

    @PostMapping("/save")
    ResponseEntity<SaveWorkshopResponse> updateWorkshop(@RequestBody SaveWorkshopRequest request) {
        return updateServiceClient.updateWorkshop(request);
    }
}
