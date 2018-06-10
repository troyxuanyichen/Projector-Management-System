package Reservation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.criteria.CriteriaBuilder.In; //todo what is this

@Entity
@Table(name = "projector")
public class Projector {

  @Id
  @Column(name = "id", unique = true)
  private Integer id;

  @Basic(fetch = FetchType.LAZY)
  @OneToMany(mappedBy = "projector")
  @JsonIgnore
  private Set<Reservation> reservations = new HashSet<>();

  private Projector() { //missing will cause no default constructor for reservation
  }

  /**
   * about why use final here, see
   * http://web.archive.org/web/20110623124823/http://renaud.waldura.com/doc/java/final-keyword.shtml
   */
  @Autowired
  public Projector(final Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public Set<Reservation> getReservations() {
    return reservations;
  }

  public void print() {
    ObjectMapper mapper = new ObjectMapper();
    try {
      System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }
}
