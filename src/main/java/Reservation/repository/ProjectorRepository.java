package Reservation.repository;

import Reservation.model.Projector;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectorRepository extends JpaRepository<Projector, Integer> {

//  Optional<Projector> findByProjectorId(Integer id);

}
