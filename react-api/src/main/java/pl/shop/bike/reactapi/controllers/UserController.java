package pl.shop.bike.reactapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.shop.bike.models.model.entities.service.ServiceEntity;
import pl.shop.bike.models.model.entities.user.UserEntity;
import pl.shop.bike.models.model.request.SaveServiceRequest;
import pl.shop.bike.models.model.request.SaveUserRequest;
import pl.shop.bike.models.model.security.User;
import pl.shop.commons.clients.ReadServiceClient;
import pl.shop.commons.clients.UpdateServiceClient;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private ReadServiceClient readServiceClient;

    @Autowired
    private UpdateServiceClient updateServiceClient;

    @GetMapping
    public UserEntity getUserInfo(@RequestParam(name = "username", required = false) String username,
                                  @RequestParam(name = "userId", required = false) Long id) {
        return readServiceClient.getUserInfo(username, id);
    }

    @PostMapping()
    public ResponseEntity<User> saveUser(@RequestBody UserEntity userEntity) {
        User user = updateServiceClient.saveUser(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("/save")
    public UserEntity updateUser(@RequestParam(name = "pointer") String pointer,
                                 @RequestBody SaveUserRequest request) {
        return updateServiceClient.updateUser(pointer, request);
    }

    @PostMapping("/service")
    public void saveService(@RequestBody SaveServiceRequest request) {
        System.out.println(request.toString());
        updateServiceClient.saveService(request);
    }

    @GetMapping("/service")
    List<ServiceEntity> getServicesForUser(@RequestParam(name = "userId") Long userId) {
        return readServiceClient.getServicesForUser(userId);
    }

    @DeleteMapping("service")
    void deleteService(@RequestParam(name = "id") Long serviceId) {
        updateServiceClient.deleteService(serviceId);
    }

    @GetMapping("/service/hours")
    List<String> getHoursFromDay(@RequestParam(name = "date") String date) {
        return readServiceClient.getHoursFromDay(date);
    }
}


