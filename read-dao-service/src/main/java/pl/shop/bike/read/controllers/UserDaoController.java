package pl.shop.bike.read.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.shop.bike.models.model.entities.service.ServiceEntity;
import pl.shop.bike.models.model.entities.user.UserEntity;
import pl.shop.commons.dao.serviceDAO.ServiceRepository;
import pl.shop.commons.dao.userDAO.UserEntityRepository;
import pl.shop.commons.errors.exceptions.ItemNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/read/users")
public class UserDaoController {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping
    public UserEntity getUserInfo(@RequestParam(name = "username", required = false) String username,
                                  @RequestParam(name = "userId", required = false) Long id) {
        if (username != null) {
            return userEntityRepository.findByUsername(username);
        } else {
            return userEntityRepository.findById(id)
                    .orElseThrow(() -> new ItemNotFoundException("Nie znaleziono użytkownika"));
        }
    }

    @GetMapping("/service")
    List<ServiceEntity> getServicesForUser(@RequestParam(name = "userId") Long userId) {
        return serviceRepository.findAllByUser(userId);
    }

    @GetMapping("/hours")
    List<String> getHoursFromDay(@RequestParam(name = "date") String date) {
        List<ServiceEntity> serviceEntities = serviceRepository.findAllByDate(date);
        List<String> hoursToExclude = serviceEntities.stream()
                .map(ServiceEntity::getTime)
                .collect(Collectors.toList());

        return hoursToExclude;
    }
}
