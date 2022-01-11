package pl.shop.commons.dao.accessoriesDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.shop.bike.models.model.entities.accessories.BottlesEntity;
import pl.shop.bike.models.model.enums.AccessoriesType;

import java.util.List;

@Repository
public interface BottlesRepository extends JpaRepository<BottlesEntity, Long> {

    BottlesEntity findByNameIgnoreCase(String name);

    List<BottlesEntity> findAllByNameContainsIgnoreCase(String name);

    List<BottlesEntity> findAllByMarkIgnoreCase(String mark);

    List<BottlesEntity> findAllByColorIgnoreCase(String color);

    List<BottlesEntity> findAllByPriceGreaterThanEqualAndPriceLessThanEqual(Double lowPrice, Double highPrice);

    List<BottlesEntity> findAllByAccessoriesType(AccessoriesType type);
}
