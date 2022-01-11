package pl.shop.commons.dao.accessoriesDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.shop.bike.models.model.entities.accessories.FendersEntity;
import pl.shop.bike.models.model.enums.AccessoriesType;

import java.util.List;

@Repository
public interface FendersRepository extends JpaRepository<FendersEntity, Long> {

    FendersEntity findByNameIgnoreCase(String name);

    List<FendersEntity> findAllByNameContainsIgnoreCase(String name);

    List<FendersEntity> findAllByMarkIgnoreCase(String mark);

    List<FendersEntity> findAllByColorIgnoreCase(String color);

    List<FendersEntity> findAllByPriceGreaterThanEqualAndPriceLessThanEqual(Double lowPrice, Double highPrice);

    List<FendersEntity> findAllByAccessoriesType(AccessoriesType type);
}
