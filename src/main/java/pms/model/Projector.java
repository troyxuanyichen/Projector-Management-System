package pms.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "projector")
public class Projector {

  @Id
  @Column(name = "id", unique = true)
  private int id;

  @Basic(fetch = FetchType.LAZY)
  @OneToMany(mappedBy = "projector")
  private Set<Reservation> reservations = new HashSet<>();

  private Projector() {
  }

  /**
   * about why use final here, see
   * http://web.archive.org/web/20110623124823/http://renaud.waldura.com/doc/java/final-keyword.shtml
   */
  public Projector(final int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public Set<Reservation> getReservations() {
    return reservations;
  }
}
