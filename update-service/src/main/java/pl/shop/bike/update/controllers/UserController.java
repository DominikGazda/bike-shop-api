package pl.shop.bike.update.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.shop.bike.models.model.entities.service.ServiceEntity;
import pl.shop.bike.models.model.entities.user.UserEntity;
import pl.shop.bike.models.model.request.SaveServiceRequest;
import pl.shop.bike.models.model.request.SaveUserRequest;
import pl.shop.bike.models.model.security.User;
import pl.shop.bike.update.services.UserService;
import pl.shop.commons.dao.serviceDAO.ServiceRepository;
import pl.shop.commons.dao.userDAO.UserEntityRepository;
import pl.shop.commons.dao.userDAO.UserRepository;
import pl.shop.commons.errors.exceptions.ItemNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/update/user")
public class UserController {

    private final UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public User saveUser(@RequestBody UserEntity userEntity) {
        return userService.saveUser(userEntity);
    }

    @PostMapping("/save")
    public UserEntity updateUser(@RequestParam(name = "pointer") String pointer, @RequestBody SaveUserRequest request) {
        return userService.updateUser(pointer, request);
    }

    @GetMapping("/sort")
    List<UserEntity> getUsersByUsername(@RequestParam(name = "parameter") String parameter) {
        return userEntityRepository.findByUsernameContainsIgnoreCase(parameter).stream()
                .filter(user -> !user.isDeleted())
                .collect(Collectors.toList());
    }

    @PostMapping("/service")
    void saveService(@RequestBody SaveServiceRequest request) {
        UserEntity user = userEntityRepository.findById(request.getUserId())
                .orElseThrow(() -> new ItemNotFoundException("Nie znaleziono użytkownika"));

        ServiceEntity service = ServiceEntity.builder()
                .user(user)
                .date(request.getDate())
                .time(request.getTime())
                .build();

        serviceRepository.save(service);
    }

    @DeleteMapping("/service")
    void deleteService(@RequestParam(name = "id") Long serviceId) {
        ServiceEntity service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new ItemNotFoundException("Nie znaleziono danego spotania"));

        service.setUser(null);
        serviceRepository.save(service);
        serviceRepository.delete(service);
    }

    @PostMapping("/modify")
    public void modifyUser(@RequestParam(name = "id") Long userId,
                           @RequestParam(name = "action") String action) {
        UserEntity userEntity = userEntityRepository.findById(userId)
                .orElseThrow(() -> new ItemNotFoundException("Nie znaleziono danego użytkownika"));
        User user = userRepository.findByUsername(userEntity.getUsername());

        switch (action) {
            case "block":
                user.setAccountNonExpired(!user.isAccountNonExpired());
                userEntity.setBlocked(!userEntity.isBlocked());
                userRepository.save(user);
                userEntityRepository.save(userEntity);
                break;
            case "delete":
                userRepository.delete(user);
                break;
        }
    }
}
