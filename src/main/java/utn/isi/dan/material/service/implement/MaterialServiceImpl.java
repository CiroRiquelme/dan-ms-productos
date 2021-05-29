package utn.isi.dan.material.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utn.isi.dan.material.dao.MaterialRepositoryDao;
import utn.isi.dan.material.domain.Material;
import utn.isi.dan.material.exception.NotFoundException;
import utn.isi.dan.material.service.IMaterialService;

@Service
public class MaterialServiceImpl implements IMaterialService {
	
	@Autowired
	MaterialRepositoryDao materialRepository;
	
	
	public List<Material> consultarMateriales() {
		return this.materialRepository.findAll();
	}


	@Override
	public Optional<Material> consultarMaterial(Integer idMaterial) {
		
		return this.materialRepository.findById(idMaterial);
	}
	
	@Override
	public Material crearMaterial (Material material) {
		return this.materialRepository.save(material);
	}


	@Override
	public Material actualizarMaterial(Integer id, Material material) {

        if (materialRepository.existsById(id)) {
        	material.setId(id);
            return materialRepository.save(material);
        }
        else {
            throw new NotFoundException("Material inexistente. Id: " + id);
        }
	}


	@Override
	public void elimitarMaterial(Integer id) {


		if (materialRepository.existsById(id)) {
			materialRepository.deleteById(id);
		} else {
			throw new NotFoundException("Material inexistente. Id: " + id);
		}
	}


	@Override
	public Optional<Material> buscarMaterialPorNombre(String nombre) {		
		return this.materialRepository.findByNombre(nombre);
	}


	@Override
	public List<Material> buscarMaterialRangoStock(Integer stockMin, Integer stockMax) {
		return this.materialRepository.findByStockActualBetween(stockMin, stockMax);
	}


	@Override
	public List<Material> buscarMaterialRangoPrecio(Double precioMin, Double precioMax) {
		return this.materialRepository.findByPrecioBetween(precioMin, precioMax);
	}
	
	
	
	
	

}
