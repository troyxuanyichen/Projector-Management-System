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
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

  @Autowired
  private final ProjectorRepository projectorRepository;

  @Autowired
  private final ReservationRepository reservationRepository;

  public ReservationService(ProjectorRepository projectorRepository,
      ReservationRepository reservationRepository) {
    this.projectorRepository = projectorRepository;
    this.reservationRepository = reservationRepository;
  }

  public Optional<Long> insertReservation(Projector projector, Date date, Date start, Date end) {
    //todo validation
    if (checkOverlapping(projector, date, start, end)) { //overlap
      return Optional.empty();
    } else {
      Reservation reservation = reservationRepository.save(new Reservation(projector,date,start,end));
      return Optional.of(reservation.getId());
    }
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
    if (end.before(reservationArray[tmp].getStart())) { //no overlapping
//      int sequenceId = reservationRepository.save(new Reservation(projector, date, start, end));
      return false;
    } else {
      return true;
    }
  }
}
