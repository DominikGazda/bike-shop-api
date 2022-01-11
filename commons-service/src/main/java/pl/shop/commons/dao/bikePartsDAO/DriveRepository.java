package pl.shop.commons.dao.bikePartsDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.shop.bike.models.model.entities.bikeParts.DriveEntity;

import java.util.List;

public interface DriveRepository extends JpaRepository<DriveEntity, Long> {

    DriveEntity findByNameIgnoreCase(String name);

    List<DriveEntity> findAllByNameContainsIgnoreCase(String name);

    List<DriveEntity> findAllByColorIgnoreCase(String color);

    List<DriveEntity> findAllByMarkIgnoreCase(String mark);

    List<DriveEntity> findAllByPriceGreaterThanEqualAndPriceLessThanEqual(Double lowPrice, Double highPrice);
}
