package utn.isi.dan.material.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import utn.isi.dan.material.domain.Material;

@Repository
public interface MaterialRepositoryDao extends JpaRepository<Material, Integer> {
	
	
	Optional<Material> findByNombre(String nombre);
	
    List<Material> findByStockActualBetween(Integer stockMin, Integer stockMax);

    List<Material> findByPrecioBetween(Double precioMin, Double precioMax);
	
}
