package pl.shop.commons.dao.workshopDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.shop.bike.models.model.entities.workshop.ToolsEntity;

@Repository
public interface ToolsRepository extends JpaRepository<ToolsEntity, Long> {

    ToolsEntity findByNameIgnoreCase(String name);
}
