package Reservation.service;

import Reservation.model.Projector;
import Reservation.model.Reservation;
import Reservation.repository.ProjectorRepository;
import Reservation.repository.ReservationRepository;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImp implements ReservationService{

  private final ProjectorRepository projectorRepository;

  private final ReservationRepository reservationRepository;

  @Autowired
  public ReservationServiceImp(ProjectorRepository projectorRepository,
      ReservationRepository reservationRepository) {
    this.projectorRepository = projectorRepository;
    this.reservationRepository = reservationRepository;
  }

  /**
   * Add a new reservation
   *
   * @return the generated id of the reservation
   */
  public Optional<Long> insertReservation(Projector projector, Date date, Date start, Date end) {
    //todo validation
    if (checkOverlapping(projector, date, start, end)) { //overlap
      return Optional.empty();
    } else {
      Reservation reservation = reservationRepository
          .save(new Reservation(projector, date, start, end));
      return Optional.of(reservation.getId());
    }
  }

  /**
   * Find a reservation by reservation id
   *
   * @param id of the reservation
   * @return the reservation of id as input
   */
  public Optional<Reservation> searchReservation(Long id) {
    return reservationRepository.findById(id);
  }

  /**
   * Delete a reservation by reservation id
   *
   * @param id of the reservation
   * @return {@Optional.empty()} if no reservation found
   * delete message otherwise
   */
  public Optional<String> deleteReservation(Long id) {
    Optional<Reservation> reservation = reservationRepository.findById(id);
    if (reservation.isPresent()) {
      reservationRepository.delete(reservation.get());
      return Optional.of("Reservation id: " + id + " deleted.");
    } else {
      return Optional.empty();
    }
  }

  public Collection<Reservation> getAll() {
    return reservationRepository.findAll();
  }

  public Collection<Reservation> searchByDate(Date date) {
    return reservationRepository.findByDate(date);
  }

  /**
   * Check if overlapping exists if inserted.
   *
   * @return {@code true} if there is a overlapping
   * {@code false} otherwise
   */
  private boolean checkOverlapping(Projector projector, Date date, Date start, Date end) {
    Integer id = projector.getId();
    Collection<Reservation> reservationCollection = reservationRepository
        .findByProjectorIdAndDate(id, date);
    Reservation[] reservationArray =
        reservationCollection.toArray(new Reservation[reservationCollection.size()]);
    reservationCollection = null;
    Arrays.sort(reservationArray, Comparator.naturalOrder());
    int tmp = Arrays.binarySearch(reservationArray, start);
    return !end.before(reservationArray[tmp].getStart()); //no overlapping
  }
}
