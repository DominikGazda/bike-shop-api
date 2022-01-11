package pl.shop.commons.dao.workshopDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.shop.bike.models.model.entities.workshop.RacksEntity;
import pl.shop.bike.models.model.enums.WorkshopType;

import java.util.List;

@Repository
public interface RacksRepository extends JpaRepository<RacksEntity, Long> {

    RacksEntity findByNameIgnoreCase(String name);

    List<RacksEntity> findAllByNameContainsIgnoreCase(String name);

    List<RacksEntity> findAllByPriceGreaterThanEqualAndPriceLessThanEqual(Double lowPrice, Double highPrice);

    List<RacksEntity> findAllByWorkshopType(WorkshopType type);

    List<RacksEntity> findAllByMarkIgnoreCase(String mark);
}
