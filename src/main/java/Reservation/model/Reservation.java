package Reservation.model;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * https://memorynotfound.com/hibernate-date-time-datetime-mapping/
 */

@Entity
@Table(name = "reservation")
public class Reservation implements Comparable<Reservation> {

  @Id
  @Column(name = "reservation_id", unique = true)
  //use hibernate naming strategy in application.properties to fix the name
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Basic(fetch = FetchType.LAZY)
  @ManyToOne
  private Projector projector;

  @Basic(fetch = FetchType.LAZY)
  @Temporal(TemporalType.DATE)
  @Column(name = "date", columnDefinition = "DATE", nullable = false)
  private Date date;

  @Basic(fetch = FetchType.LAZY)
  @Column(name = "start", columnDefinition = "TIME", nullable = false)
  private Date start;

  @Basic(fetch = FetchType.LAZY)
  @Column(name = "end", columnDefinition = "TIME", nullable = false)
  private Date end;

  @Autowired
  public Reservation(final Projector projector, final Date date, final Date start, final Date end) {
    this.projector = projector;
    this.date = date;
    this.start = start;
    this.end = end;
  }

  public Long getId() {
    return id;
  }

  public Projector getProjector() {
    return projector;
  }

  public Date getDate() {
    return date;
  }

  public Date getStart() {
    return start;
  }

  public Date getEnd() {
    return end;
  }

  @Override
  public int compareTo(Reservation reservation) {
    return start.before(reservation.getStart()) ? 1 : start.equals(reservation) ? 0 : -1;
  }
}
