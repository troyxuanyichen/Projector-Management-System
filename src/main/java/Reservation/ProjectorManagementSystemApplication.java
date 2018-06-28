package Reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication //add @Configuration(tags the class as a source of bean definitions for the application context), @EnableAutoConfiguration and @ComponentScan
public class ProjectorManagementSystemApplication {

  public static void main(String[] args) {
		SpringApplication.run(ProjectorManagementSystemApplication.class, args);
	}

/*	@Bean
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
  }*/
}
