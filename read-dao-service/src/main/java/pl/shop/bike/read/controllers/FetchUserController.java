package pl.shop.bike.read.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
