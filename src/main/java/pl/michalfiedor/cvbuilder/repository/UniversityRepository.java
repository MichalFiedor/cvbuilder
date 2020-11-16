package pl.michalfiedor.cvbuilder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.michalfiedor.cvbuilder.model.University;

import java.util.List;

public interface UniversityRepository extends JpaRepository<University, Long> {
//    @Query("select u from University u where u.city.id=?1")
    List<University> findAllByCityId(long id);
}
