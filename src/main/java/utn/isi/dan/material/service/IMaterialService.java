package utn.isi.dan.material.service;

import java.util.List;
import java.util.Optional;

import utn.isi.dan.material.domain.Material;

public interface IMaterialService {
	
	public List<Material> consultarMateriales();
	
	public Optional<Material> consultarMaterial(Integer idMaterial);

	public Material crearMaterial(Material material);
	
	public Material actualizarMaterial(Integer id , Material material);
	
	public void elimitarMaterial(Integer id);
	
	public Optional<Material> buscarMaterialPorNombre(String nombre);
	
    List<Material> buscarMaterialRangoStock(Integer stockMin, Integer stockMax);
    
    List<Material> buscarMaterialRangoPrecio(Double precioMin, Double precioMax);
}
