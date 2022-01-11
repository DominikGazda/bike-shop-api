package pl.gazda.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.shop.bike.models.model.entities.user.UserEntity;
import pl.shop.bike.models.model.request.SaveUserRequest;
import pl.shop.commons.clients.UpdateServiceClient;

import java.util.List;

@RestController
@RequestMapping("/api/adm/users")
public class UsersController {

    @Autowired
    private UpdateServiceClient updateServiceClient;

    @GetMapping("/sort")
    public List<UserEntity> getUsersByUsername(@RequestParam(name = "parameter") String parameter) {
        return updateServiceClient.getUsersByUsername(parameter);
    }

    @PostMapping("/save")
    public UserEntity updateUser(@RequestParam(name = "pointer") String pointer, @RequestBody SaveUserRequest request) {
        return updateServiceClient.updateUser(pointer, request);
    }

    @PostMapping("/modify")
    public void modifyUser(@RequestParam(name = "id") Long userId,
                           @RequestParam(name = "action") String action) {
        updateServiceClient.modifyUser(userId, action);
    }
}
