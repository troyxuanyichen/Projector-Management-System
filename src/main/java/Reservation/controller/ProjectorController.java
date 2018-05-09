package Reservation.controller;

import Reservation.exception.ConflictException;
import Reservation.model.Projector;
import Reservation.repository.ProjectorRepository;
import Reservation.repository.ReservationRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/projector")
public class ProjectorController {

  private final ProjectorRepository projectorRepository;

  private final ReservationRepository reservationRepository;

  @Autowired
  public ProjectorController(ProjectorRepository projectorRepository,
      ReservationRepository reservationRepository) {
    this.projectorRepository = projectorRepository;
    this.reservationRepository = reservationRepository;
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
  public String saveProjector(int projectorId) { //RequestBody here prevent spring from recognize?
//    System.out.println(projectorId); //todo validation here
    Optional<Projector> projector = projectorRepository.findById(projectorId);
    if (projector.isPresent()) {
      //System.out.println(projector.getClass()); //class java.util.Optional
      throw new ConflictException("Projector already exists, please try another id.");
    } else {
      projectorRepository.save(new Projector(projectorId));
      System.out.println("Projector saved.");
      return "Projector saved.";
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
