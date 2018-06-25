package Reservation.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import java.util.Optional;
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

  // todo
  private MediaType contentType =
      new MediaType(
          MediaType.APPLICATION_FORM_URLENCODED.getType(),
          MediaType.APPLICATION_FORM_URLENCODED.getSubtype(),
          Charset.forName("utf8"));

  private List<Projector> projectorList = new ArrayList<>();

  //  private HttpMessageConverter mappingJackson2HttpMessageConverter;

  @Autowired private MockMvc mockMvc;

  @MockBean private ProjectorService projectorService;

  // need to mock reservation because init function in ProjectorManagementSystemApplication.class
  // and projector has list of reservation
  //todo decouple projector and reservation

  @MockBean private ProjectorRepository projectorRepository;

  @MockBean private ReservationService reservationService;

  @MockBean private ReservationRepository reservationRepository;

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
  public void setup() {
    logger.info("---- setup ----");
    projectorList = new ArrayList<>();
    for (int i = 1; i <= 3; i += 1) {
      Projector projector = new Projector(i);
      projectorList.add(projector);
    }
    logger.info("---- setup finish ----");
  }

  @Test
  public void test_save_projector_success() throws Exception {
    logger.info("---- test save projector success ----");
    when(projectorService.insert(1)).thenReturn(true);
    this.mockMvc
        .perform(post("/projector/new").param("projectorId", "1"))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    /**
     * Verify that the findById() method of the ProjectorService is invoked exactly once.
     */
    verify(projectorService, times(1)).insert(1);
    /**
     * Verify that after the response, no more interactions are made to the ProjectorService
     */
    verifyNoMoreInteractions(projectorService);
  }

  @Test
  public void test_save_projector_fail_409_conflict() throws Exception {
    logger.info("---- test save projector fail 409 conflict ----");
    when(projectorService.insert(1)).thenReturn(false);
    this.mockMvc
        .perform(post("/projector/new").param("projectorId", "1"))
        .andDo(print())
        .andExpect(status().isConflict());
    verify(projectorService, times(1)).insert(1);
    verifyNoMoreInteractions(projectorService);
  }

  @Test
  public void test_get_all_projector_success() throws Exception {
    logger.info("---- test get all projector success ----");
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
    verify(projectorService, times(1)).getAll();
    verifyNoMoreInteractions(projectorService);
  }

  @Test
  public void test_get_all_projector_success_204_no_content() throws Exception {
    logger.info("---- test get all projector 204 no content ----");
    when(projectorService.getAll()).thenReturn(null);
    this.mockMvc.perform(get("/projector/all")).andDo(print()).andExpect(status().isNoContent());
    verify(projectorService, times(1)).getAll();
    verifyNoMoreInteractions(projectorService);
  }

  @Test
  public void test_count_projector_success() throws Exception {
    logger.info("---- test count projector success ----");
    when(projectorService.count()).thenReturn(7);
    this.mockMvc
        .perform(get("/projector/count"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json("{'Projector Count':7}")); // do not use '' on int value
    verify(projectorService, times(1)).count();
    verifyNoMoreInteractions(projectorService);
  }

  @Test
  public void test_get_projector_success() throws Exception {
    logger.info("---- test get projector success ----");
    when(projectorService.get(1)).thenReturn(Optional.of(new Projector(1)));
    this.mockMvc
        .perform(get("/projector/{pId}", 1))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.id", is(1)));
    verify(projectorService, times(1)).get(1);
    verifyNoMoreInteractions(projectorService);
  }

  @Test
  public void test_get_projector_fail_404_not_found() throws Exception {
    logger.info("---- test get all projector 404 not found ----");
    when(projectorService.get(1)).thenReturn(Optional.empty());
    this.mockMvc
        .perform(get("/projector/{pId}", 1))
        .andDo(print())
        .andExpect(status().isNotFound())
        .andExpect(content().json("{'message':'Projector of id: 1 not found!'}"));
    verify(projectorService, times(1)).get(1);
    verifyNoMoreInteractions(projectorService);
  }
}
