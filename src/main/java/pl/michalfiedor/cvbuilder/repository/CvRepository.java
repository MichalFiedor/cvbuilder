package pl.michalfiedor.cvbuilder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.michalfiedor.cvbuilder.model.City;
import pl.michalfiedor.cvbuilder.model.Cv;

import javax.transaction.Transactional;

@Repository
public interface CvRepository extends JpaRepository<Cv, Long> {

}
