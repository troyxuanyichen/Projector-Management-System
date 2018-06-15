package Reservation.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

import Reservation.service.GreetingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(GreetingController.class)
//@AutoConfigureMockMvc(ProjectorManagementSystemApplication.class) //todo ?
//@AutoConfigureMockMvc
public class GreetingControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private GreetingService greetingService;

  //todo
  @Test
  public void contextLoad() {
    assertThat(greetingService).isNotNull();
  }

  @Test
  public void shouldReturnDefaultMessage() throws Exception {
    when(greetingService.greet()).thenReturn("Hello Mock");
    this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("Hello Mock")));
  }
}
