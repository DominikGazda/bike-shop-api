package pl.shop.bike.update.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.shop.bike.models.model.entities.user.UserEntity;
import pl.shop.bike.models.model.request.SaveUserRequest;
import pl.shop.bike.models.model.security.Role;
import pl.shop.bike.models.model.security.User;
import pl.shop.commons.dao.userDAO.UserEntityRepository;
import pl.shop.commons.dao.userDAO.UserRepository;
import pl.shop.commons.utils.UserContextService;

@Service
public class UserService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private UserEntityRepository userEntityRepository;
    private SaveInvoker saveInvoker;
    private UserContextService userContextService;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, UserEntityRepository userEntityRepository, SaveInvoker saveInvoker, UserContextService userContextService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userEntityRepository = userEntityRepository;
        this.saveInvoker = saveInvoker;
        this.userContextService = userContextService;
    }

    public User saveUser(UserEntity userEntity) {
        User user = createNewUser(userEntity);
        UserEntity savedUserEntity = userEntityRepository.save(userEntity);
        user.setUserEntity(savedUserEntity);

        return userRepository.save(user);
    }

    public UserEntity updateUser(String pointer, SaveUserRequest request) {
        UserEntity userBeforeChange = userEntityRepository.findByUsername(request.getUsername());
        UserEntity updatedUser = saveInvoker.lookUp(pointer)
                .update(userBeforeChange, pointer, request);

        return updatedUser;
    }

    public User createNewUser(UserEntity userEntity) {
        return User.builder()
                .password(passwordEncoder.encode(userEntity.getPassword()))
                .username(userEntity.getUsername())
                .surname(userEntity.getSurname())
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .role(Role.USER)
                .build();
    }
}
