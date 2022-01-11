package pl.shop.commons.dao.bikePartsDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.shop.bike.models.model.entities.bikeParts.FrameEntity;

import java.util.List;

public interface FrameRepository extends JpaRepository<FrameEntity, Long> {

    FrameEntity findByNameIgnoreCase(String name);

    List<FrameEntity> findAllByNameContainsIgnoreCase(String name);

    List<FrameEntity> findAllByColorIgnoreCase(String color);

    List<FrameEntity> findAllByMarkIgnoreCase(String mark);

    List<FrameEntity> findAllByPriceGreaterThanEqualAndPriceLessThanEqual(Double lowPrice, Double highPrice);
}
