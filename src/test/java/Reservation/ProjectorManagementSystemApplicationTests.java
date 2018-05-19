package Reservation;

import static org.junit.jupiter.api.Assertions.*;
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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ProjectorManagementSystemApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT) //tell junit where it should get information about the Spring application under test
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//tell junit where it should get information about the Spring application under test
//@AutoConfigureMockMvc //suggested by https://spring.io/guides/gs/testing-web/
//@WebAppConfiguration //todo @WebAppConfiguration should only be used with @SpringBootTest when @SpringBootTest is configured with a mock web environment. Please remove @WebAppConfiguration or reconfigure @SpringBootTest.
//@WebMvcTest(ProjectorManagementSystemApplication.class)
@WebMvcTest
public class ProjectorManagementSystemApplicationTests {

  /* */
  /**
   * Start the application up and listen for a connection like it would do in production, and then send an HTTP request and assert the response.
   *//*
  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void greetingShouldReturnDefaultMessage() throws Exception {
    assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
        String.class)).contains("Hello World");
  }*/

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void shouldReturnDefaultMessage() throws Exception {
    this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("Hello World")));
  }

  @Test
  public void contextLoads() {
  }
}
