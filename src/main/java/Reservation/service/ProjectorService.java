package Reservation.service;

import Reservation.model.Projector;
import Reservation.repository.ProjectorRepository;
import Reservation.repository.ReservationRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectorService {

  private final ProjectorRepository projectorRepository;

  private final ReservationRepository reservationRepository;

  @Autowired
  public ProjectorService(ProjectorRepository projectorRepository,
      ReservationRepository reservationRepository) {
    this.projectorRepository = projectorRepository;
    this.reservationRepository = reservationRepository;
  }

  /**
   * Insert a project using the id.
   * @param projectorId id of the projector to be insert
   * @return true if insert success, false otherwise
   */
  public boolean insert(int projectorId) {
    Optional<Projector> projector = projectorRepository.findById(projectorId);
    if (projector.isPresent()) {
      return false;
    } else { //todo get next value
      projectorRepository.save(new Projector(projectorId));
      return true;
    }
  }

 /* public int batchInsert(List<Projector> projectorList) {
    //transaction
  }*/

  public Optional<Projector> has(Integer projectorId) {
    return projectorRepository.findById(projectorId);
  }

  public Collection<Projector> getAll() {
    return projectorRepository.findAll();
  }

  public int count() {
    return (int) projectorRepository.count();
  }
}
