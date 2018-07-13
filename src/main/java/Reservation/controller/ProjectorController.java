package Reservation.controller;

import Reservation.exception.ConflictException;
import Reservation.model.Projector;
import Reservation.service.ProjectorService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.Collection;
import java.util.Optional;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projector")
public class ProjectorController {

  private static final Logger logger = Logger.getLogger(ProjectorController.class.getName());

  private final ProjectorService projectorService;

  private static final Gson gson = new Gson();

  @Autowired
  public ProjectorController(
      ProjectorService projectorService) {
    this.projectorService = projectorService;
  }

  /**
   * Get a projector by its projectorId
   *
   * @param projectorId id of the projector to be insert
   * @return the projector with projectorId {@code null} if no projector found
   */
  @RequestMapping(
      value = "/{pId}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<?> getProjector(@PathVariable(value = "pId") int projectorId) {
    Optional<Projector> projector = projectorService.get(projectorId);
    if (projector.isPresent()) {
      return new ResponseEntity<>(projector, HttpStatus.FOUND);
    } else {
      JsonObject responseObj = new JsonObject();
      responseObj.addProperty("message", "Projector of id: " + projectorId + " not found!");
      return new ResponseEntity<>(gson.toJson(responseObj), HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(
      value = "/new",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<?> saveProjector(int projectorId) {
    /*
    RequestBody here prevent spring from recognize application/x-www-form-urlencoded
    System.out.println(projectorId); //todo validation here
    */
    if (projectorService.insert(projectorId)) {
      return new ResponseEntity<>(
          gson.toJson("Projector id: " + projectorId + " saved"), HttpStatus.CREATED);
    } else {
      throw new ConflictException("Projector already exists, please try another id.");
    }
  }

  /**
   * Get all projectors
   *
   * @return the collection of projectors
   */
  @RequestMapping(
      value = "/all",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<?> getAllProjector() {
    Collection<Projector> projectors = projectorService.getAll();
    //todo 5xx code
    if (projectors == null || projectors.size() == 0) {
      logger.info("No projector found!");
      return new ResponseEntity<Collection<Projector>>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(projectors, HttpStatus.OK);
    }
  }

  /**
   * Count the number of projectors
   *
   * @return number of projector
   */
  @RequestMapping(
      value = "/count",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<?> countProjector() {
    JsonObject responseObj = new JsonObject();
    responseObj.addProperty("Projector Count", projectorService.count());
    return new ResponseEntity<>(gson.toJson(responseObj), HttpStatus.OK);
  }

  /*  @RequestMapping(
      value = "/reservation/{pId}",
      method = RequestMethod.GET,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Reservation> getProjectorReservation() {
    List<Reservation> result;
    return result;
  }*/
}
