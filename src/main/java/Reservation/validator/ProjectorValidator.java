package Reservation.validator;

import Reservation.model.Projector;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ProjectorValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return Projector.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    ValidationUtils.rejectIfEmpty(errors, "id", "id.empty");
    Projector projector = (Projector) target;
    if (projector.getId() < 0) {
      errors.rejectValue("id", "negativevalue");
    }
  }
}
