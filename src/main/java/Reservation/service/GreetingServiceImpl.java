package Reservation.service;

import java.util.logging.Logger;
import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GreetingService {

  private static final Logger logger = Logger.getLogger(GreetingServiceImpl.class.getName());

  public String greet() {
    return "Greeting";
  }

  public void greet(String str) {
    logger.info(str);
  }
}
