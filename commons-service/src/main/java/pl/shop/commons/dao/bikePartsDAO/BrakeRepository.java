package pl.shop.commons.dao.bikePartsDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.shop.bike.models.model.entities.BikeEntity;
import pl.shop.bike.models.model.entities.bikeParts.BrakeEntity;
import pl.shop.bike.models.model.enums.BikePartsType;

import java.util.List;

public interface BrakeRepository extends JpaRepository<BrakeEntity, Long> {

    BrakeEntity findByNameIgnoreCase(String name);

    List<BrakeEntity> findAllByNameContainsIgnoreCase(String name);

    List<BrakeEntity> findAllByColorIgnoreCase(String color);

    List<BrakeEntity> findAllByMarkIgnoreCase(String mark);

    List<BrakeEntity> findAllByPriceGreaterThanEqualAndPriceLessThanEqual(Double lowPrice, Double highPrice);

}
