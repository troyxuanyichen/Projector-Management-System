package Reservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ProjectorNotFoundException extends RuntimeException {

  public ProjectorNotFoundException(String message) {
    super(message);
  }
}
