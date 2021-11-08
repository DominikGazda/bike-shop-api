package pl.shop.bike.read.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.shop.bike.models.model.security.User;
import pl.shop.commons.dao.userDAO.UserRepository;

@RestController
@RequestMapping("/api/read")
public class FetchUserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{username}")
    public User getUserByUsername(@PathVariable("username") String username) {
        return userRepository.getUserByUsername(username);
    }

//    @PostMapping("/save")
//    public void saveUser(@RequestBody User user) {
//        userRepository.save(user);
//    }
}
