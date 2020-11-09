package pl.michalfiedor.cvbuilder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michalfiedor.cvbuilder.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}
