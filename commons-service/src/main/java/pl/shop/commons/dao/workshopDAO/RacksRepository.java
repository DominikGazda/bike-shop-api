package pl.shop.commons.dao.workshopDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.shop.bike.models.model.entities.workshop.RacksEntity;

@Repository
public interface RacksRepository extends JpaRepository<RacksEntity, Long> {

    RacksEntity findByNameIgnoreCase(String name);
}
