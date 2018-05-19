package Reservation;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

//import org.junit.jupiter.api.Test; //should not be this, otherwise No runnable methods error
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ProjectorManagementSystemApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT) //tell junit where it should get information about the Spring application under test
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) //tell junit where it should get information about the Spring application under test
//@WebAppConfiguration //todo @WebAppConfiguration should only be used with @SpringBootTest when @SpringBootTest is configured with a mock web environment. Please remove @WebAppConfiguration or reconfigure @SpringBootTest.
public class ProjectorManagementSystemApplicationTests {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

//  private ProjectorManagementSystemApplication projectorManagementSystemApplication;

 /* @Test
  public void greetingShouldReturnDefaultMessage() throws Exception {
    assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
        String.class)).contains("Hello World");
  }*/

  @Test
  public void contextLoads() {
  }
}
