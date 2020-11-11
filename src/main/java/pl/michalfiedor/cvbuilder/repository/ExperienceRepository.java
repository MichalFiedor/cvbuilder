package pl.michalfiedor.cvbuilder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.michalfiedor.cvbuilder.model.Experience;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}
