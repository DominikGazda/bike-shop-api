package pl.shop.commons.dao.workshopDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.shop.bike.models.model.entities.workshop.MaintenanceEntity;

@Repository
public interface MaintenanceRepository extends JpaRepository<MaintenanceEntity, Long> {

    MaintenanceEntity findByNameIgnoreCase(String name);
}
