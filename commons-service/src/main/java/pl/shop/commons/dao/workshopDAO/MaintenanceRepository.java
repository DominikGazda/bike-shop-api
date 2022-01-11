package pl.shop.commons.dao.workshopDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.shop.bike.models.model.entities.workshop.MaintenanceEntity;
import pl.shop.bike.models.model.enums.WorkshopType;

import java.util.List;

@Repository
public interface MaintenanceRepository extends JpaRepository<MaintenanceEntity, Long> {

    MaintenanceEntity findByNameIgnoreCase(String name);

    List<MaintenanceEntity> findAllByNameContainsIgnoreCase(String name);

    List<MaintenanceEntity> findAllByPriceGreaterThanEqualAndPriceLessThanEqual(Double lowPrice, Double highPrice);

    List<MaintenanceEntity> findAllByWorkshopType(WorkshopType type);

    List<MaintenanceEntity> findAllByMarkIgnoreCase(String mark);
}
