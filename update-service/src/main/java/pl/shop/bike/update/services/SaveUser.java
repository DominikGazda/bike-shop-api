package pl.shop.bike.update.services;

import pl.shop.bike.models.model.entities.user.UserEntity;
import pl.shop.bike.models.model.request.SaveUserRequest;

public interface SaveUser {

    UserEntity update(UserEntity userEntity, String pointer, SaveUserRequest request);

    boolean canHandle(String pointer);
}
