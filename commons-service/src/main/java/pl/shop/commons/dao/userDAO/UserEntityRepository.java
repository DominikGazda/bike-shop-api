package pl.shop.commons.dao.userDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.shop.bike.models.model.entities.user.UserEntity;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

     UserEntity findByUsername(String username);


}
