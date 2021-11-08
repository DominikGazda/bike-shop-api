package pl.shop.bike.reactapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.shop.commons.clients.ReadServiceClient;

import java.util.List;

@RestController
@RequestMapping("/api/workshop")
public class WorkshopController {

    @Autowired
    private ReadServiceClient readServiceClient;

    @GetMapping
    public List<?> getAllWorkshopItems(@RequestParam(required = false) String type){
        return readServiceClient.getAllWorkshopItems(type);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getWorkshopItemByName(@PathVariable String name){
        return readServiceClient.getWorkshopItemByName(name);
    }
}
