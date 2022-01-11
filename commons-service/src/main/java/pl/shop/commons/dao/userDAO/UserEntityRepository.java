package pl.shop.commons.dao.userDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.shop.bike.models.model.entities.user.UserEntity;

import java.util.List;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

    List<UserEntity> findByUsernameContainsIgnoreCase(String username);
}
