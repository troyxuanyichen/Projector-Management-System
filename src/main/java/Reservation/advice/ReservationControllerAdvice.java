package Reservation.advice;

import Reservation.exception.ReservationConflictException;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ReservationControllerAdvice {

  @ResponseBody
  @ExceptionHandler(ReservationConflictException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  VndErrors reservationConflictExceptionHandler(ReservationConflictException ex) {
    return new VndErrors("error", ex.getMessage());
  }
}
