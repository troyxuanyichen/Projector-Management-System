package Reservation.controller;

import Reservation.service.GreetingService;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/greeting")
@RestController
public class GreetingController {

  private final GreetingService greetingService;

  private static final Gson gson = new Gson();

  public GreetingController(GreetingService greetingService) {
    this.greetingService = greetingService;
  }

  @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE) //must add '/' in url to reach
  ResponseEntity<?> greeting() {
    return  new ResponseEntity<>(gson.toJson(greetingService.greet()),
        HttpStatus.CREATED);
  }
}
