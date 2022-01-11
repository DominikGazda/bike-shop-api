package pl.shop.bike.reactapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.shop.bike.models.model.request.bikesPartsFilter.BikesPartsFilterRequest;
import pl.shop.bike.models.model.response.BikePartsNamesResponse;
import pl.shop.commons.clients.ReadServiceClient;
import pl.shop.commons.clients.UpdateServiceClient;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", allowedHeaders = "*")

@RestController
@RequestMapping("/api/bike/parts")
public class BikePartsController {

    @Autowired
    private ReadServiceClient readServiceClient;

    @Autowired
    private UpdateServiceClient updateServiceClient;

    @GetMapping
    public List<?> getAllBikeParts(@RequestParam(required = false) String type) {
        return readServiceClient.getAllBikeParts(type);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getBikePartByName(@PathVariable String name) {
        return readServiceClient.getBikePartByName(name);
    }

    @GetMapping("/sort/name")
    public ResponseEntity<?> sortPartsByName(@RequestParam String parameter) {
        return readServiceClient.sortPartsByName(parameter);
    }

    @GetMapping("/names")
    public BikePartsNamesResponse getBikePartsNames() {
        return readServiceClient.getBikePartsNames();
    }

    @PostMapping("/sort")
    public List<Set<?>> sortBikePartsByParameter(@RequestBody BikesPartsFilterRequest bikesPartsFilterRequest) {
        return readServiceClient.sortBikePartsByParameter(bikesPartsFilterRequest);
    }

}
