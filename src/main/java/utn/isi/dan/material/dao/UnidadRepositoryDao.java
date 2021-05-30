package utn.isi.dan.material.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import utn.isi.dan.material.domain.Unidad;

@Repository
public interface UnidadRepositoryDao extends JpaRepository<Unidad, Integer> {

}
