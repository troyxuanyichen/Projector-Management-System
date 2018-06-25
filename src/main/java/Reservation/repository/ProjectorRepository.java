package Reservation.repository;

import Reservation.model.Projector;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectorRepository extends JpaRepository<Projector, Integer> {

  Optional<Projector> findById(Integer id);
}
