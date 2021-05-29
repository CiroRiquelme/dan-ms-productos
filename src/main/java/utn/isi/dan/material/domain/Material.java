package utn.isi.dan.material.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MATERIAL", schema = "MS_MATERIAL")
public class Material {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_MAT")
	private Integer id;
	
	@Column(name = "NOM_MAT", unique = true)
	private String nombre;
	
	@Column(name = "DESC_MAT")
	private String descripcion;
	
	@Column(name = "PREC_MAT")
	private Double precio;
	
	@Column(name = "STOCK_ACT_MAT")
	private Integer stockActual;
	
	@Column(name = "STOCK_MIN_MAT")
	private Integer stockMinimo;
	
	@OneToOne
	@JoinColumn(name = "ID_UNI")
	private Unidad unidad;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Integer getStockActual() {
		return stockActual;
	}
	public void setStockActual(Integer stockActual) {
		this.stockActual = stockActual;
	}
	public Integer getStockMinimo() {
		return stockMinimo;
	}
	public void setStockMinimo(Integer stockMinimo) {
		this.stockMinimo = stockMinimo;
	}
	public Unidad getUnidad() {
		return unidad;
	}
	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}
	
	
	
}
