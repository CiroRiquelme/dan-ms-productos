package utn.isi.dan.material.domain;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MOVIMIENTO_STOCK", schema = "MS_MATERIAL")
public class MovimientosStock {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_MOV_STOCK")
	private Integer id;
    
    @OneToOne
    @JoinColumn(name = "ID_DET_PED")
	private DetallePedido detallePedido;
	
    @OneToOne
    @JoinColumn(name = "ID_DET_PROV")
	private DetalleProvision detalleProvision;
    
    @OneToOne
    @JoinColumn(name = "ID_MAT")
	private Material material;
	
    @Column(name = "CANT_ENT_MOV_STOCK")
   	private Integer cantidadEntrada;
	
    @Column(name = "CANT_SAL_MOV_STOCK")
	private Integer cantidadSalida;
	
    @Column(name = "FEC_MOV_STOCK")
	private Instant fecha;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public DetallePedido getDetallePedido() {
		return detallePedido;
	}
	public void setDetallePedido(DetallePedido detallePedido) {
		this.detallePedido = detallePedido;
	}
	public DetalleProvision getDetalleProvision() {
		return detalleProvision;
	}
	public void setDetalleProvision(DetalleProvision detalleProvision) {
		this.detalleProvision = detalleProvision;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	public Integer getCantidadEntrada() {
		return cantidadEntrada;
	}
	public void setCantidadEntrada(Integer cantidadEntrada) {
		this.cantidadEntrada = cantidadEntrada;
	}
	public Integer getCantidadSalida() {
		return cantidadSalida;
	}
	public void setCantidadSalida(Integer cantidadSalida) {
		this.cantidadSalida = cantidadSalida;
	}
	public Instant getFecha() {
		return fecha;
	}
	public void setFecha(Instant fecha) {
		this.fecha = fecha;
	}
	
	
}
