package Reservation.advice;

import Reservation.exception.ProjectorNotFoundException;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ProjectorControllerAdvice {
  @ResponseBody
  @ExceptionHandler(ProjectorNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  VndErrors projectorNotFoundExceptionHandler(ProjectorNotFoundException ex) {
    return new VndErrors("error", ex.getMessage());
  }


}
