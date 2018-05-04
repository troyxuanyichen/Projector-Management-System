package Reservation.controller;

import Reservation.model.Projector;
import Reservation.repository.ProjectorRepository;
import Reservation.repository.ReservationRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
//@EnableAutoConfiguration
//@RequestMapping("/projector")
public class ProjectorController {

  private final ProjectorRepository projectorRepository;

  private final ReservationRepository reservationRepository;

  @Autowired
  public ProjectorController(ProjectorRepository projectorRepository,
      ReservationRepository reservationRepository) {
    this.projectorRepository = projectorRepository;
    this.reservationRepository = reservationRepository;
  }

  @ResponseBody
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public String saveProjector(@RequestBody int projectorId) {
    Optional<Projector> projector = projectorRepository.findById(projectorId);
//    T Projector projector = projectorRepository.findById(projectorId);
    if (projector != null) {
      return "Projector already exists, please try another id.";
    } else {
      projectorRepository.save(new Projector(projectorId));
      return "Projector saved.";
    }
  }

  @ResponseBody
  @RequestMapping(value = "/findall",method = RequestMethod.GET)
  public List<Projector> getAllProjector() {
    List<Projector> projectors = projectorRepository.findAll();
    return projectors;
  }
}
