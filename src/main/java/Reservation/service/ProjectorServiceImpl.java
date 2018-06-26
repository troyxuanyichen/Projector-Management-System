package Reservation.service;

import Reservation.model.Projector;
import Reservation.repository.ProjectorRepository;
import Reservation.repository.ReservationRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectorServiceImpl implements ProjectorService{

  private static final Logger logger = Logger.getLogger(ProjectorServiceImpl.class.getName());

  private final ProjectorRepository projectorRepository;

  private final ReservationRepository reservationRepository;

  @Autowired
  public ProjectorServiceImpl(
      ProjectorRepository projectorRepository, ReservationRepository reservationRepository) {
    this.projectorRepository = projectorRepository;
    this.reservationRepository = reservationRepository;
  }

  /**
   * Insert a project using the id.
   *
   * @param projectorId id of the projector to be insert
   * @return true if insert success, false otherwise
   */
  public boolean insert(int projectorId) {
    Optional<Projector> projector = projectorRepository.findById(projectorId);
    if (projector.isPresent()) {
      return false;
    } else { // todo get next value
      projectorRepository.save(new Projector(projectorId));
      return true;
    }
  }

  @Transactional
  public int batchInsert(List<Projector> projectorList) { // both methods have same erasure
    logger.info("Batch insert projector");
    if (projectorList.size() == 0) return 0;
    for (Projector projector : projectorList) {
      projectorRepository.save(projector);
    }
    return projectorList.size();
  }

  //  @Transactional
  //  public int batchInsert(<Projector> projectorList) {
  //    logger.info("Batch insert projector");
  //    if (projectorList.size() == 0) return 0;
  //    for (int id: projectorList) {
  //      projectorRepository.save(new Projector(id));
  //    }
  //    return projectorList.size();
  //  }

  /**
   * Check if the projector of id: {@param projectorId} exists
   *
   * @param projectorId id of the projector
   * @return {@code true} if projector exist {@code false} otherwise
   */
  public Optional<Projector> has(Integer projectorId) {
    return projectorRepository.findById(projectorId);
  }

  /**
   * Get the projector of id projectorId
   *
   * @param projectorId
   * @return the projector of id: projectorId if found; {@code Optional.empty} otherwise
   */
  public Optional<Projector> get(Integer projectorId) {
    return projectorRepository.findById(projectorId);
  }

  /**
   * Get all the projector
   *
   * @return collection of all the projectors
   */
  public Collection<Projector> getAll() {
    return projectorRepository.findAll();
  }

  /**
   * Count the number of projectors
   *
   * @return number of projectors
   */
  public Integer count() {
    return (int) projectorRepository.count();
  }
}
