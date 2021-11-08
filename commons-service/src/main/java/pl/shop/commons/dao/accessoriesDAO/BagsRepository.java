package pl.shop.commons.dao.accessoriesDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.shop.bike.models.model.entities.accessories.BagsEntity;

@Repository
public interface BagsRepository extends JpaRepository<BagsEntity, Long> {

    BagsEntity findByNameIgnoreCase(String name);
}
