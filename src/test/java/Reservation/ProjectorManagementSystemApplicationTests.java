package Reservation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import Reservation.repository.ProjectorRepository;
import Reservation.repository.ReservationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc //the full Spring application context is started
public class ProjectorManagementSystemApplicationTests {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ProjectorRepository projectorRepository;

  @MockBean
  private ReservationRepository reservationRepository;

  @Test
  public void shouldReturnDefaultMessage() throws Exception {
    this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("Hello World")));
  }

  @Test
  public void shouldInitProjector() throws Exception {
//    this.mockMvc.perform(get("/projector/count")).andDo(print()).andExpect(status().isOk())
//        .andExpect(content().string(containsString("7")));
    this.mockMvc.perform(get("/projector/count")).andDo(print()).andExpect(status().isOk()).andReturn();

  }
}
