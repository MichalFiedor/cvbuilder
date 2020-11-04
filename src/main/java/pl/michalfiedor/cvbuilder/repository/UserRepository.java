package pl.michalfiedor.cvbuilder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michalfiedor.cvbuilder.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
