package pl.shop.bike.reactapi.controllers;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.shop.bike.models.model.entities.user.UserEntity;
import pl.shop.bike.models.model.security.User;
import pl.shop.commons.clients.ReadServiceClient;
import pl.shop.commons.clients.UpdateServiceClient;
import pl.shop.commons.dao.addressDao.AddressRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private ReadServiceClient readServiceClient;

    @Autowired
    private UpdateServiceClient updateServiceClient;

    @GetMapping
    public UserEntity getUserInfo(@RequestParam(name = "username") String username) {
        return readServiceClient.getUserInfo(username);
    }

    @PostMapping()
    public ResponseEntity<User> saveUser(@RequestBody UserEntity userEntity) {
        User user = updateServiceClient.saveUser(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

//    @GetMapping("/test")
//    public String test() {
//       return addressRepository.findById(4L).orElseThrow(NoClassDefFoundError::new).toString();
//    }
}


