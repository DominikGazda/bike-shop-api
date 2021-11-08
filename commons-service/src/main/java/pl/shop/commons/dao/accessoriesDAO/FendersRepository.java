package pl.shop.commons.dao.accessoriesDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.shop.bike.models.model.entities.accessories.BagsEntity;
import pl.shop.bike.models.model.entities.accessories.FendersEntity;

@Repository
public interface FendersRepository extends JpaRepository<FendersEntity, Long> {

    FendersEntity findByNameIgnoreCase(String name);
}
