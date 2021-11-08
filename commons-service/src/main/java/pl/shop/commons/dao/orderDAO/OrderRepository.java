package pl.shop.commons.dao.orderDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.shop.bike.models.model.entities.order.OrderEntity;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("SELECT o FROM OrderEntity  o JOIN o.user ou where ou.id = ?1")
    List<OrderEntity> findAllByUserId(Long userId);

    @Query("SELECT o FROM OrderEntity  o JOIN o.user ou where ou.username = ?1")
    List<OrderEntity> findAllByUserUsername(String username);
}
