package Reservation.controller;

import static org.junit.jupiter.api.Assertions.*;

import Reservation.service.ReservationService;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ReservationController.class)
public class ReservationControllerTest {

  private static final Logger logger = Logger.getLogger(ReservationControllerTest.class.getName());

  @Autowired private MockMvc mockMvc;
  
  @MockBean private ReservationService reservationService;

  @Test
  public void test_add_reservation_success() throws Exception {}

  @Test
  public void getReservation() throws Exception {}

  @Test
  public void removeReservation() throws Exception {}

  @Test
  public void searchReservationByDate() throws Exception {}
}
