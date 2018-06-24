package Reservation.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import Reservation.model.Projector;
import Reservation.repository.ProjectorRepository;
import Reservation.repository.ReservationRepository;
import Reservation.service.ProjectorService;
import Reservation.service.ReservationService;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ProjectorController.class)
public class ProjectorControllerTest { // need to be public

  private static final Logger logger = Logger.getLogger(ProjectorControllerTest.class.getName());

  private MediaType contentType =
      new MediaType(
          MediaType.APPLICATION_FORM_URLENCODED.getType(),
          MediaType.APPLICATION_FORM_URLENCODED.getSubtype(),
          Charset.forName("utf8"));

  private List<Projector> projectorList = new ArrayList<>();

  //  private List<Reservation> reservationList = new ArrayList<>();

  //  private HttpMessageConverter mappingJackson2HttpMessageConverter;

  @Autowired private MockMvc mockMvc;

  @MockBean private ProjectorService projectorService;

  @MockBean private ProjectorRepository projectorRepository;

  @MockBean private ReservationService reservationService;

  @MockBean private ReservationRepository reservationRepository;

  // need to mock reservation because init function in ProjectorManagementSystemApplication.class

  // todo
  /* @Autowired
  void setConverters(HttpMessageConverter<?>[] converters) {

    this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
        .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
        .findAny()
        .orElse(null);

    assertNotNull("the JSON message converter must not be null",
        this.mappingJackson2HttpMessageConverter);
  }*/

  @Before
  public void setup() throws Exception {
    this.projectorRepository.deleteAllInBatch();
    projectorList = new ArrayList<>();
    for (int i = 1; i <= 3; i += 1) {
      Projector projector = new Projector(i);
      projectorList.add(projector);
    }
  }

  @Test
  public void shouldInitProjector() throws Exception {
    //    when(projectorService.batchInsert(projectorList)).thenReturn(7);
    this.mockMvc
        .perform(get("/projector/count"))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)); // must be UTF8
    // here
    // actual content type = application/json;charset=UTF-8
    //        .andReturn();
  }

  @Test
  public void saveProjector() {
    when(projectorService.insert(1)).thenReturn(false);
    // todo request parameter
    //    this.mockMvc
    //        .perform(get("/projector/new"))
  }

  @Test
  public void getAllProjector() throws Exception {
    when(projectorService.getAll()).thenReturn(projectorList);
    this.mockMvc
        .perform(get("/projector/all"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[0].id", is(1)))
        .andExpect(jsonPath("$[1].id", is(2)))
        .andExpect(jsonPath("$[2].id", is(3)));
  }

  @Test
  public void countProjector() {}

  @Test
  public void getProjector() {}
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
