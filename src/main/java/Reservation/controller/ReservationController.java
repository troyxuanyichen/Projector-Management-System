package Reservation.controller;

import Reservation.model.Projector;
import Reservation.model.Reservation;
import Reservation.exception.ConflictException;
import Reservation.exception.NotFoundException;
import Reservation.service.ProjectorService;
import Reservation.service.ReservationService;
import Reservation.service.ReservationServiceImp;
import Reservation.service.ProjectorServiceImpl;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
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

  private ReservationService reservationService;

  private ProjectorService projectorService;

  @Autowired
  public ReservationController(ReservationService reservationService,
      ProjectorService projectorService) {
    this.reservationService = reservationService;
    this.projectorService = projectorService;
  }
  
  /**
   * Add a new reservation
   *
   * @return id of the reservation
   */
  @RequestMapping(value = "/new", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> addReservation(Integer projectorId, Date date, Date start, Date end) {
    Optional<Projector> projector = projectorService.has(projectorId);
    if (projector.isPresent()) {
      Optional<Long> reservationId = reservationService
          .insertReservation(projector.get(), date, start, end);
      if (reservationId.isPresent()) {
        return new ResponseEntity<>(gson.toJson("Reservation ID is " + reservationId),
            HttpStatus.CREATED);
      } else {
        throw new ConflictException("The time you select is not available.");
      }
    } else {
      throw new NotFoundException("Projector not found, please try another projector id.");
    }
  }

  /**
   * Get a reservation by id in url
   *
   * @param reservationId the id of the reservation
   * @return the reservation with the specified id if found
   */
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
   * Remove a reservation by id
   *
   * @param reservationId the id of the reservation
   * @return delete message if succeed
   */
  @RequestMapping(value = "/remove", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> removeReservation(Long reservationId) {
    Optional<String> msg = reservationService.deleteReservation(reservationId);
    if (msg.isPresent()) {
      return new ResponseEntity<>(gson.toJson(msg.get()), HttpStatus.OK);
    } else {
      throw new NotFoundException("The reservation does not exist.");
    }
  }

  /**
   * Search all the reservation in a day
   *
   * @param dateString the date of reservation, should be formatted as 'mm-dd-yyyy'
   * @return list of Reservation if found
   * {@code null} if no reservation found
   */
  @RequestMapping(value = "/date", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ArrayList<Reservation> searchReservationByDate(String dateString) {
    Date date = validateDateString(dateString);
//    Collection<Reservation> reservationList = reservationService.searchByDate(date);
    ArrayList<Reservation> reservationList = new ArrayList<>(reservationService.getAll());
    Iterator<Reservation> it = reservationList.iterator();
    ArrayList<Reservation> result = new ArrayList<>();
    while (it.hasNext()) {
      Reservation tmp = it.next();
      System.out.println("---");
      System.out.println(tmp.getDate());
//      System.out.println(date);
      if (tmp.getDate().equals(date)) {
        result.add(tmp);
      }
    }
    if (result.size() == 0) {
      throw new NotFoundException("No reservation found for this day.");
    } else {
      return result;
    }
  }

  private Date validateDateString(String dateString) {
//    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
    String[] dateStringSplit = dateString.split("-");
    Calendar cal = Calendar.getInstance();
    cal.set(Integer.valueOf(dateStringSplit[0]), Integer.valueOf(dateStringSplit[1], Integer.valueOf(dateStringSplit[2])));
//    cal.set(2018, 05, 16);
    Date date = cal.getTime();
    System.out.println(date);
    return date;
//    try {
//      Date date = formatter.parse(dateString);
//      System.out.println(date);
//      return date;
//    } catch (ParseException e) {
//      e.printStackTrace();
//      throw new BadFormatOfDate("The format of date is not acceptable.");
//    }
  }
}