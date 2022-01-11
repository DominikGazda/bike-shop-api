package pl.shop.bike.update.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.shop.bike.models.model.entities.user.UserEntity;
import pl.shop.bike.models.model.request.SaveUserRequest;
import pl.shop.bike.models.model.security.User;
import pl.shop.commons.dao.userDAO.UserEntityRepository;
import pl.shop.commons.dao.userDAO.UserRepository;

@Service
public class SavePassword implements SaveUser {

    @Autowired
    UserEntityRepository userRepository;

    @Autowired
    UserRepository userSecurityRepository;

    @Override
    public UserEntity update(UserEntity userEntity, String pointer, SaveUserRequest request) {
        User user = userSecurityRepository.findByUsername(request.getUsername());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (!user.getUserEntity().getPassword().equals(request.getOldPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Podano błędne hasło do konta");
        }

        user.getUserEntity().setPassword(request.getPassword());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User userAfterUpdate = userSecurityRepository.save(user);

        return userAfterUpdate.getUserEntity();
    }

    @Override
    public boolean canHandle(String pointer) {
        return pointer.startsWith("/password");
    }
}
