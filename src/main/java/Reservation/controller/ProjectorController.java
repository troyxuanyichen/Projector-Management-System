package Reservation.controller;

import Reservation.model.Projector;
import Reservation.response.ConflictException;
import Reservation.service.ProjectorService;
import com.google.gson.Gson;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projector")
public class ProjectorController {

  @Autowired
  ProjectorService projectorService;

  private static final Gson gson = new Gson();

  public ProjectorController(ProjectorService projectorService) {
    this.projectorService = projectorService;
  }

  @RequestMapping(value = "/new", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> saveProjector(int projectorId) {
    //RequestBody here prevent spring from recognize?
    //System.out.println(projectorId); //todo validation here
    if (projectorService.insertProjector(projectorId)) {
      return new ResponseEntity<>(gson.toJson("Projector id: " + projectorId + " saved"),
          HttpStatus.CREATED);
    } else {
      throw new ConflictException("Projector already exists, please try another id.");
    }
  }

  @RequestMapping(value = "/", method = RequestMethod.GET, consumes = "application/x-www-form-urlencoded", produces = MediaType.APPLICATION_JSON_VALUE)
  public Collection<Projector> getAllProjector() {
    return projectorService.getAllProjectors();
  }
}
