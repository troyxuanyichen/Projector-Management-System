package Reservation;

import Reservation.model.Projector;
import Reservation.model.Reservation;
import Reservation.repository.ProjectorRepository;
import Reservation.repository.ReservationRepository;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@SpringBootApplication
public class ProjectorManagementSystemApplication {

/*  @RequestMapping("/")
  String home() {
    System.out.println("hehe");
    return "Hello World!";
  }*/

	public static void main(String[] args) {
		SpringApplication.run(ProjectorManagementSystemApplication.class, args);
	}
	@Bean
  CommandLineRunner init(ProjectorRepository projectorRepository, ReservationRepository reservationRepository) {
	  return (evt) -> Arrays.asList("1,2,3,4,5,6,7".split(",")).forEach(
	      a ->{
          Projector projector = projectorRepository.save(new Projector(Integer.valueOf(a)));
          Date now = new Date();
          Calendar cal = Calendar.getInstance();
          cal.setTime(now);
          cal.add(Calendar.HOUR_OF_DAY, 1);
          reservationRepository.save(new Reservation(projector, now, now, cal.getTime()));
        }
    );
  }
}
