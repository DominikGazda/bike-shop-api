package pl.shop.bike.read.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.shop.bike.models.model.entities.user.UserEntity;
import pl.shop.bike.models.model.security.User;
import pl.shop.commons.dao.userDAO.UserEntityRepository;
import pl.shop.commons.dao.userDAO.UserRepository;

import java.util.InputMismatchException;

@RestController
@RequestMapping("/api/read/users")
public class UserDaoController {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @GetMapping
    public UserEntity getUserInfo(@RequestParam(name = "username") String username){
        return userEntityRepository.findByUsername(username);
    }
}
