package Reservation.controller;

import Reservation.response.ConflictException;
import Reservation.model.Projector;
import Reservation.repository.ProjectorRepository;
import Reservation.repository.ReservationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
@RequestMapping("/projector")
public class ProjectorController {

  private final ProjectorRepository projectorRepository;

  private final ReservationRepository reservationRepository;

  private static final Gson gson = new Gson();

  @Autowired
  public ProjectorController(ProjectorRepository projectorRepository,
      ReservationRepository reservationRepository) {
    this.projectorRepository = projectorRepository;
    this.reservationRepository = reservationRepository;
  }

/*  @Autowired
  ObjectMapper mapper = new ObjectMapper();*/

  @RequestMapping(value = "/new", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> saveProjector(
      int projectorId) { //RequestBody here prevent spring from recognize?
//    System.out.println(projectorId); //todo validation here
    Optional<Projector> projector = projectorRepository.findById(projectorId);
    if (projector.isPresent()) {
      //System.out.println(projector.getClass()); //class java.util.Optional
      throw new ConflictException(
          "Projector already exists, please try another id."); //todo error message
    } else {
      projectorRepository.save(new Projector(projectorId));
      System.out.println("Projector saved");
      return new ResponseEntity<>(gson.toJson("Projector saved"), HttpStatus.CREATED);
    }
  }

  @RequestMapping(value = "/", method = RequestMethod.GET, consumes = "application/x-www-form-urlencoded", produces = "application/json")
  public Collection<Projector> getAllProjector() {
    ArrayList<Projector> projectors = new ArrayList<>(projectorRepository.findAll());
//    System.out.println(projectors.size());
    projectors.get(0).print();
    return projectors;
  }
}
