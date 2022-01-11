package pl.shop.bike.update.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.shop.bike.models.model.entities.user.UserEntity;
import pl.shop.bike.models.model.request.SaveUserRequest;
import pl.shop.bike.models.model.security.User;
import pl.shop.commons.dao.userDAO.UserRepository;

@Service
public class SaveUserDetails implements SaveUser {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserEntity update(UserEntity userEntity, String pointer, SaveUserRequest request) {
        User user = userRepository.findByUsername(request.getUsername());

        user.setSurname(request.getSurname());
        user.getUserEntity().setName(request.getName());
        user.getUserEntity().setSurname(request.getSurname());

        User userAfterUpdate = userRepository.save(user);
        return userAfterUpdate.getUserEntity();
    }

    @Override
    public boolean canHandle(String pointer) {
        return pointer.startsWith("/details");
    }
}
