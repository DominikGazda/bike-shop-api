package pl.shop.bike.update.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.shop.bike.models.model.entities.user.UserEntity;
import pl.shop.bike.models.model.security.User;
import pl.shop.bike.update.services.UserService;

@RestController
@RequestMapping("/api/update/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public User saveUser(@RequestBody UserEntity userEntity) {
      return userService.saveUser(userEntity);
    }
}
