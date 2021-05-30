package utn.isi.dan.material.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.hamcrest.MatcherAssert.*;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import utn.isi.dan.material.DanMsMaterialApplicationTests;
import utn.isi.dan.material.domain.Material;

@SpringBootTest(classes = DanMsMaterialApplicationTests.class)
public class MaterialRepositoryDaoTest {
	
	@Autowired
	public MaterialRepositoryDao materialRepository;
	
	
	@Autowired
	public UnidadRepositoryDao unidadRepository;
	
	@Test
    @Order(1) 
	public void RepositoryNotNull() {
		assertNotNull(materialRepository);
	}
	
	@Test
    @Sql("/datos_test.sql")
    @Order(2)    
	public void findAllMaterials() {
		
		List<Material> listaMateriales = materialRepository.findAll();
		
		assertFalse(listaMateriales.isEmpty());
		assertThat(listaMateriales.size(), is(equalTo(3)));	
		
	}
	

}
