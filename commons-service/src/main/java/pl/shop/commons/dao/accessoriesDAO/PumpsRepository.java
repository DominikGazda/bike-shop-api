package pl.shop.commons.dao.accessoriesDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.shop.bike.models.model.entities.accessories.PumpEntity;
import pl.shop.bike.models.model.enums.AccessoriesType;

import java.util.List;

@Repository
public interface PumpsRepository extends JpaRepository<PumpEntity, Long> {

    PumpEntity findByNameIgnoreCase(String name);

    List<PumpEntity> findAllByNameContainsIgnoreCase(String name);

    List<PumpEntity> findAllByMarkIgnoreCase(String mark);

    List<PumpEntity> findAllByColorIgnoreCase(String color);

    List<PumpEntity> findAllByPriceGreaterThanEqualAndPriceLessThanEqual(Double lowPrice, Double highPrice);

    List<PumpEntity> findAllByAccessoriesType(AccessoriesType type);
}
