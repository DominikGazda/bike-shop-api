package pl.shop.bike.reactapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.shop.bike.models.model.request.workshopFilter.WorkshopFilterRequest;
import pl.shop.commons.clients.ReadServiceClient;
import pl.shop.commons.clients.UpdateServiceClient;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/workshop")
public class WorkshopController {

    @Autowired
    private ReadServiceClient readServiceClient;

    @Autowired
    private UpdateServiceClient updateServiceClient;

    @GetMapping
    public List<?> getAllWorkshopItems(@RequestParam(required = false) String type) {
        return readServiceClient.getAllWorkshopItems(type);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getWorkshopItemByName(@PathVariable String name) {
        return readServiceClient.getWorkshopItemByName(name);
    }

    @GetMapping("/sort/name")
    public ResponseEntity<?> sortWorkshopByName(@RequestParam String parameter) {
        return readServiceClient.sortWorkshopByName(parameter);
    }

    @PostMapping("/sort")
    public List<Set<?>> sortWorkshopByParameter(@RequestBody WorkshopFilterRequest workshopFilterRequest) {
        return readServiceClient.sortWorkshopByParameter(workshopFilterRequest);
    }
}
