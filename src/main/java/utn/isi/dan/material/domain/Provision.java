package utn.isi.dan.material.domain;

import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PROVISION", schema = "MS_MATERIAL")
public class Provision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROV")
	private Integer id;
    
    
    @Column(name = "FEC_PROV")
	private Instant fechaProvision;
	
    @OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_PROV", referencedColumnName = "ID_PROV")
	private List<DetalleProvision> detalle;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Instant getFechaProvision() {
		return fechaProvision;
	}
	public void setFechaProvision(Instant fechaProvision) {
		this.fechaProvision = fechaProvision;
	}
	public List<DetalleProvision> getDetalle() {
		return detalle;
	}
	public void setDetalle(List<DetalleProvision> detalle) {
		this.detalle = detalle;
	}

	
}
