package Reservation.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;

import Reservation.model.Projector;
import Reservation.service.ReservationService;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ReservationController.class)
public class ReservationControllerTest {

  private static final Logger logger = Logger.getLogger(ReservationControllerTest.class.getName());

  private static List<Projector> projectorList =
      new ArrayList<>(Arrays.asList(new Projector(1), new Projector(2), new Projector(3)));

  @Autowired private MockMvc mockMvc;

  @MockBean private ReservationService reservationService;

  private static final Gson gson = new Gson();

  @Test
  public void test_add_reservation_success() throws Exception {
  }

  @Test
  public void getReservation() throws Exception {}

  @Test
  public void removeReservation() throws Exception {}

  @Test
  public void searchReservationByDate() throws Exception {}

  /*
   * converts a Java object into JSON representation, this is useful when sending json in request
    * body
   */
  private static String toJsonString(final Object obj) {
    return new Gson().toJson(obj);
  }
}
