package pl.michalfiedor.cvbuilder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.michalfiedor.cvbuilder.model.City;

public interface CityRepository extends JpaRepository<City, Long> {

}
