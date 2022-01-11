package pl.shop.commons.dao.accessoriesDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.shop.bike.models.model.entities.accessories.BagsEntity;
import pl.shop.bike.models.model.enums.AccessoriesType;

import java.util.List;

@Repository
public interface BagsRepository extends JpaRepository<BagsEntity, Long> {

    BagsEntity findByNameIgnoreCase(String name);

    List<BagsEntity> findAllByMarkIgnoreCase(String mark);

    List<BagsEntity> findAllByNameContainsIgnoreCase(String name);

    List<BagsEntity> findAllByColorIgnoreCase(String color);

    List<BagsEntity> findAllByPriceGreaterThanEqualAndPriceLessThanEqual(Double lowPrice, Double highPrice);

    List<BagsEntity> findAllByAccessoriesType(AccessoriesType type);
}
