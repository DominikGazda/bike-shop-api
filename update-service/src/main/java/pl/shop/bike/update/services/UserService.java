package pl.shop.bike.update.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.shop.bike.models.model.entities.user.UserEntity;
import pl.shop.bike.models.model.security.Role;
import pl.shop.bike.models.model.security.User;
import pl.shop.commons.dao.userDAO.UserEntityRepository;
import pl.shop.commons.dao.userDAO.UserRepository;

@Service
public class UserService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private UserEntityRepository userEntityRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, UserEntityRepository userEntityRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userEntityRepository = userEntityRepository;
    }

    public User saveUser(UserEntity userEntity){
        User user = createNewUser(userEntity);
        UserEntity savedUserEntity = userEntityRepository.save(userEntity);
        user.setUserEntity(savedUserEntity);
        return userRepository.save(user);
    }

    public User createNewUser(UserEntity userEntity) {
        System.out.println(userEntity.getName());
        System.out.println(userEntity.getPassword());
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
