package utn.isi.dan.material.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import utn.isi.dan.material.domain.MovimientosStock;

@Repository
public interface MovimientosStockRepositoryDao extends JpaRepository<MovimientosStock, Integer> {

}
