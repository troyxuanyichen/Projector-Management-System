package Reservation.controller;


import static org.junit.Assert.*;

import Reservation.model.Projector;
import Reservation.model.Reservation;
import Reservation.repository.ProjectorRepository;
import Reservation.repository.ReservationRepository;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectorController.class)
//@SpringBootTest(classes = ProjectorManagementSystemApplication.class)
@WebAppConfiguration
class ProjectorControllerTest {

  private MediaType contentType = new MediaType(MediaType.APPLICATION_FORM_URLENCODED.getType(),
      MediaType.APPLICATION_FORM_URLENCODED.getSubtype(),
      Charset.forName("utf8"));

  private List<Projector> projectorList = new ArrayList<>();

  private List<Reservation> reservationList = new ArrayList<>();

  private Projector projector;

  private Integer projectorId = 7;

  private Date date = new Date();

  private MockMvc mockMvc;

  private HttpMessageConverter mappingJackson2HttpMessageConverter;

  @Autowired
  private ProjectorRepository projectorRepository;

  @Autowired
  private ReservationRepository reservationRepository;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Autowired
  void setConverters(HttpMessageConverter<?>[] converters) {

    this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
        .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
        .findAny()
        .orElse(null);

    assertNotNull("the JSON message converter must not be null",
        this.mappingJackson2HttpMessageConverter);
  }

  @Before
  public void setup() throws Exception {
    this.mockMvc = webAppContextSetup(webApplicationContext).build();

    this.reservationRepository.deleteAllInBatch();
    this.projectorRepository.deleteAllInBatch();

    this.projector = projectorRepository.save(new Projector(projectorId));
    this.reservationList
        .add(reservationRepository.save(new Reservation(projector, date, date, date)));
    this.reservationList
        .add(reservationRepository.save(new Reservation(projector, date, date, date)));
  }

  @Test
  public void projectorNotFound() throws Exception {
    mockMvc.perform(post())
  }

  @Test
  public void readSingleReservation() throws Exception {
    mockMvc.perform(get("/reservation"));
  }

  @Test
  public void saveProjector() throws Exception {
  }

  @Test
  public void getAllProjector() throws Exception {
  }
}