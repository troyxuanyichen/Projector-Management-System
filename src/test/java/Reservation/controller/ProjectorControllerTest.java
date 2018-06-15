package Reservation.controller;


import static org.junit.Assert.*;

import Reservation.model.Projector;
import Reservation.repository.ProjectorRepository;
import Reservation.repository.ReservationRepository;
import Reservation.service.ProjectorService;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProjectorController.class)
//@SpringBootTest
//@WebAppConfiguration
//@WebMvcTest
public class ProjectorControllerTest { //need to be public

  private MediaType contentType = new MediaType(MediaType.APPLICATION_FORM_URLENCODED.getType(),
      MediaType.APPLICATION_FORM_URLENCODED.getSubtype(),
      Charset.forName("utf8"));

  private List<Projector> projectorList = new ArrayList<>();

//  private List<Reservation> reservationList = new ArrayList<>();

  private Projector projector;

  private Integer projectorId = 7;

  private Date date = new Date();

  @Autowired
  private MockMvc mockMvc;

  private HttpMessageConverter mappingJackson2HttpMessageConverter;

//  @Autowired
  @MockBean
  private ProjectorRepository projectorRepository;

//  @Autowired
  @MockBean
  private ProjectorService projectorService;

  //need to mock reservation probably because list of reservation in projector model
  @MockBean
  private ReservationRepository reservationRepository;

  @MockBean
  private WebApplicationContext webApplicationContext;

  //todo
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
//    this.mockMvc = webAppContextSetup(webApplicationContext).build();

//    this.reservationRepository.deleteAllInBatch();
    this.projectorRepository.deleteAllInBatch();

    this.projector = projectorRepository.save(new Projector(projectorId));
//    this.reservationList
//        .add(reservationRepository.save(new Reservation(projector, date, date, date)));
//    this.reservationList
//        .add(reservationRepository.save(new Reservation(projector, date, date, date)));
  }

  @Test
  public void shouldInitProjector() throws Exception {
    List<Projector> projectorList = new ArrayList<>();
    for (int i = 0; i < 7; i++) {
      Projector projector = new Projector(i);
      projectorList.add(projector);
    }
//    when(projectorService.batchInsert(projectorList)).thenReturn(7);
    this.mockMvc.perform(get("/projector/count"))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    //actual content type = application/json;charset=UTF-8
//        .andReturn();
  }
 /* @Test
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
  }*/
}