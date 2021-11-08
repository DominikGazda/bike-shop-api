package pl.shop.bike.reactapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.shop.commons.clients.ReadServiceClient;

import java.util.List;

@RestController
@RequestMapping("/api/accessories")
public class AccessoriesController {

    private ReadServiceClient readServiceClient;

    public AccessoriesController(ReadServiceClient readServiceClient) {
        this.readServiceClient = readServiceClient;
    }

    @GetMapping
    public List<?> getAllAccessories(@RequestParam(required = false) String type) {
        return readServiceClient.getAllAccessories(type);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> findAccessoriesByName(@PathVariable String name){
        return readServiceClient.findAccessoriesByName(name);
    }
}