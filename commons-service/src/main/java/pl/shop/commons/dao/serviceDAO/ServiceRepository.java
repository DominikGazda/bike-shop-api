package pl.shop.commons.dao.serviceDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.shop.bike.models.model.entities.service.ServiceEntity;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {

    //Pobieranie danych o wszystkich serwisach dla danego usera na podstawie id
    @Query("SELECT s FROM ServiceEntity  s JOIN s.user su where su.id = ?1")
    List<ServiceEntity> findAllByUser(Long id);

    /*Gotowa metoda dostarczona przez moduł Spring Data
        Zwraca wszystkie serwisy z podaną datą
     */
    List<ServiceEntity> findAllByDate(String date);
}

