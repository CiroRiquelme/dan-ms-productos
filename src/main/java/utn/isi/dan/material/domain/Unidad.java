package utn.isi.dan.material.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UNIDAD", schema = "MS_MATERIAL")
public class Unidad {

    @Id
    @Column(name = "ID_UNI")
	private Integer id;
    
    @Column(name = "DESC_UNI" , unique = true, nullable = false)
	private String descripcion;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
