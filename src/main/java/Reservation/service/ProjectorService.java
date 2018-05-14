package Reservation.service;

import Reservation.model.Projector;
import Reservation.repository.ProjectorRepository;
import Reservation.repository.ReservationRepository;
import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectorService {

  @Autowired
  private final ProjectorRepository projectorRepository;

  @Autowired
  private final ReservationRepository reservationRepository;

  public ProjectorService(ProjectorRepository projectorRepository,
      ReservationRepository reservationRepository) {
    this.projectorRepository = projectorRepository;
    this.reservationRepository = reservationRepository;
  }

  public boolean insertProjector(int projectorId) {
    Optional<Projector> projector = projectorRepository.findById(projectorId);
    if (projector.isPresent()) {
      return false;
    } else { //todo get next value
      projectorRepository.save(new Projector(projectorId));
//      System.out.println("Projector saved");
      return true;
    }
  }

  public Optional<Projector> hasProjector(Integer projectorId) {
    return projectorRepository.findById(projectorId);
  }

  public Collection<Projector> getAllProjectors() {
    return projectorRepository.findAll();
  }
}
