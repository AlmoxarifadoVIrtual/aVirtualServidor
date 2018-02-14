package almoxarifadovirtual.servidor.modelo.reserva;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Reserva")
@Table(name = "tb_reserva")
public class Reserva {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(unique = true, nullable = false)
  private Long id;

  public Reserva(){}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
