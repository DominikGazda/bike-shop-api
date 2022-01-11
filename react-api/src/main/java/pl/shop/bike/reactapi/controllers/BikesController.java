package pl.shop.bike.reactapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.shop.bike.models.model.entities.BikeEntity;
import pl.shop.bike.models.model.request.bikesFilter.BikesFilterRequest;
import pl.shop.commons.clients.ReadServiceClient;
import pl.shop.commons.clients.UpdateServiceClient;

import java.util.List;

@RestController
@RequestMapping("/api/bikes")
public class BikesController {

    @Autowired
    private ReadServiceClient readServiceClient;

    @Autowired
    private UpdateServiceClient updateServiceClient;

    public BikesController(ReadServiceClient readServiceClient) {
        this.readServiceClient = readServiceClient;
    }

    @GetMapping
    public List<BikeEntity> getAllBikes() {
        return readServiceClient.getAllBikes();
    }

    @GetMapping("/{name}")
    public BikeEntity findBikeByName(@PathVariable String name) {
        return readServiceClient.findBikeByName(name);
    }

    @GetMapping("/new")
    public List<BikeEntity> findNewestBikes() {
        return readServiceClient.findNewestBikes();
    }

    @PostMapping("/sort")
    public List<BikeEntity> sortBikesByParameter(@RequestBody BikesFilterRequest bikesFilterRequest) {
        return readServiceClient.sortBikesByParameter(bikesFilterRequest);
    }

    @GetMapping("/sort/name")
    public List<BikeEntity> sortBikesByName(@RequestParam(name = "parameter") String parameter) {
        return readServiceClient.sortBikesByName(parameter);
    }
}
