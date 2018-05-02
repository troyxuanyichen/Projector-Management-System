package pms.model;

import java.sql.Time;
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


@Entity
@Table(name = "reservation")
public class Reservation {

  @Id
  @Column(unique = true)
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
//  @Temporal(TemporalType.TIME)
  @Column(name = "start", columnDefinition = "TIME", nullable = false)
  private Time start;

  @Basic(fetch = FetchType.LAZY)
//  @Temporal(TemporalType.TIME)
  @Column(name = "end", columnDefinition = "TIME", nullable = false)
  private Time end;
}
