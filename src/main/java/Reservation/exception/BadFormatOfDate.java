package Reservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadFormatOfDate extends RuntimeException {

  public BadFormatOfDate(String message) {
    super(message);
  }
}
