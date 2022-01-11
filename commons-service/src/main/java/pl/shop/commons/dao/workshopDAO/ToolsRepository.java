package pl.shop.commons.dao.workshopDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.shop.bike.models.model.entities.workshop.ToolsEntity;
import pl.shop.bike.models.model.enums.WorkshopType;

import java.util.List;

@Repository
public interface ToolsRepository extends JpaRepository<ToolsEntity, Long> {

    ToolsEntity findByNameIgnoreCase(String name);

    List<ToolsEntity> findAllByNameContainsIgnoreCase(String name);

    List<ToolsEntity> findAllByPriceGreaterThanEqualAndPriceLessThanEqual(Double lowPrice, Double highPrice);

    List<ToolsEntity> findAllByWorkshopType(WorkshopType type);

    List<ToolsEntity> findAllByMarkIgnoreCase(String mark);
}
