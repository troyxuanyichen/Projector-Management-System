package Reservation.controller;

import Reservation.model.Projector;
import Reservation.model.Reservation;
import Reservation.exception.ConflictException;
import Reservation.exception.NotFoundException;
import Reservation.service.ReservationService;
import Reservation.service.ProjectorService;
import com.google.gson.Gson;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

  private static final Gson gson = new Gson();

  @Autowired
  private ReservationService reservationService;

  @Autowired
  private ProjectorService projectorService;

  @Autowired
  public ReservationController(ReservationService reservationService, ProjectorService projectorService) {
    this.reservationService = reservationService;
    this.projectorService = projectorService;
  }

  /**
   * Add a new reservation
   *
   * @return id of the reservation
   */
  @RequestMapping(value = "/new", method = RequestMethod.PUT, consumes = "application/x-www-form-urlencoded", produces = "application/json")
  public ResponseEntity<?> addReservation(Integer projectorId, Date date, Date start, Date end) {
    Optional<Projector> projector = projectorService.hasProjector(projectorId);
    if (projector.isPresent()) {
      Optional<Long> reservationId = reservationService.insertReservation(projector.get(), date, start, end);
      if (reservationId.isPresent()) {
        return new ResponseEntity<>(gson.toJson("Reservation ID is " + reservationId),
            HttpStatus.CREATED);
      } else {
        throw new ConflictException("The time you select is not available.");
      }
    }
    else {
      throw new NotFoundException("Projector not found, please try another projector id.");
    }
  }

  @RequestMapping(value = "/{reservationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public Reservation getReservation(@PathVariable Long reservationId) {
    Optional<Reservation> reservation = reservationService.searchReservation(reservationId);
    if (reservation.isPresent()) {
      return reservation.get();
    } else {
      throw new NotFoundException("Reservation id not recognized.");
    }
  }

  /**
   * Search all the reservation in a day
   * @param date
   * @return
   */
 /* @RequestMapping(value = "/date", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
  public String searchByDate(Date date) {

  }*/
}