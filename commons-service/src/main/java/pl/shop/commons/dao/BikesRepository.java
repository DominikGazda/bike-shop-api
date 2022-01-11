package pl.shop.commons.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.shop.bike.models.model.entities.BikeEntity;
import pl.shop.bike.models.model.enums.GenderType;

import java.util.List;

@Repository
public interface BikesRepository extends JpaRepository<BikeEntity, Long> {

    BikeEntity findByNameIgnoreCase(String name);

    List<BikeEntity> findTop5ByOrderByIdDesc();

    List<BikeEntity> findAllByMarkIgnoreCase(String mark);

    List<BikeEntity> findAllByColorIgnoreCase(String color);

    List<BikeEntity> findAllByPriceGreaterThanEqualAndPriceLessThanEqual(Double lowPrice, Double highPrice);

    List<BikeEntity> findAllByNameContainsIgnoreCase(String name);

    List<BikeEntity> findAllByGenderType(GenderType genderType);

    @Query("SELECT b FROM BikeEntity  b JOIN b.frame bf where bf.frameSize = ?1")
    List<BikeEntity> findAllBikesByFrameSize(Long frameSize);
}
