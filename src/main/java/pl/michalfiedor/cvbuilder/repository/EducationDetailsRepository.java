package pl.michalfiedor.cvbuilder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.michalfiedor.cvbuilder.model.EducationDetails;

public interface EducationDetailsRepository extends JpaRepository<EducationDetails, Long> {

}
