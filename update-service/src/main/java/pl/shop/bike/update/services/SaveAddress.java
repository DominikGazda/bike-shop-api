package pl.shop.bike.update.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.shop.bike.models.model.entities.address.AddressEntity;
import pl.shop.bike.models.model.entities.user.UserEntity;
import pl.shop.bike.models.model.request.SaveUserRequest;
import pl.shop.commons.dao.userDAO.UserEntityRepository;

@Service
public class SaveAddress implements SaveUser {

    @Autowired
    UserEntityRepository userRepository;

    @Override
    public UserEntity update(UserEntity userEntity, String pointer, SaveUserRequest request) {
        UserEntity user = userRepository.findByUsername(request.getUsername());

        AddressEntity addressEntity = AddressEntity.builder()
                .name(request.getAddress().getName())
                .surname(request.getAddress().getSurname())
                .houseNumber(request.getAddress().getHouseNumber())
                .localNumber(request.getAddress().getLocalNumber())
                .zipCode(request.getAddress().getZipCode())
                .city(request.getAddress().getCity())
                .phone(request.getAddress().getPhone())
                .email(request.getAddress().getEmail())
                .street(request.getAddress().getStreet())
                .build();

        user.setAddress(addressEntity);
        UserEntity userAfterUpdate = userRepository.save(user);

        return userAfterUpdate;
    }

    @Override
    public boolean canHandle(String pointer) {
        return pointer.startsWith("/address");
    }
}
