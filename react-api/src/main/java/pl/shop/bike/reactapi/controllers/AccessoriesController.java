package pl.shop.bike.reactapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.shop.bike.models.model.request.accessoriesFilter.AccessoriesFilterRequest;
import pl.shop.commons.clients.ReadServiceClient;
import pl.shop.commons.clients.UpdateServiceClient;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/accessories")
public class AccessoriesController {

    @Autowired
    private ReadServiceClient readServiceClient;

    @Autowired
    private UpdateServiceClient updateServiceClient;

    public AccessoriesController(ReadServiceClient readServiceClient) {
        this.readServiceClient = readServiceClient;
    }

    @GetMapping
    public List<?> getAllAccessories(@RequestParam(required = false) String type) {
        return readServiceClient.getAllAccessories(type);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> findAccessoriesByName(@PathVariable String name) {
        return readServiceClient.findAccessoriesByName(name);
    }

    @GetMapping("/sort/name")
    public ResponseEntity<?> sortAccessoriesByName(@RequestParam String parameter) {
        return readServiceClient.sortAccessoriesByName(parameter);
    }

    @PostMapping("/sort")
    public List<Set<?>> sortAccessoriesByParameter(@RequestBody AccessoriesFilterRequest request) {
        return readServiceClient.sortAccessoriesByParameter(request);
    }
}
