package utn.isi.dan.material.rest;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import utn.isi.dan.material.domain.Material;
import utn.isi.dan.material.exception.BadRequestException;
import utn.isi.dan.material.exception.NotFoundException;
import utn.isi.dan.material.service.IMaterialService;

@RestController
@CrossOrigin
@RequestMapping("/api/material")
@Api(value = "MaterialRest")
public class MaterialRest {

	@Autowired
	IMaterialService materialService;

	@PostMapping
	@ApiOperation(value = "Crea un nuevo Material")
	public ResponseEntity<?> crearMaterial(@RequestBody Material material ) {
		
		
		if(material.getNombre()==null || material.getNombre().isEmpty()) {
			throw new BadRequestException("Nombre del material Requerido");			
		}
		
		return ResponseEntity.ok(materialService.crearMaterial(material));

	}

	@GetMapping(path = "/{id}")
	@ApiOperation(value = "Busca un Material por id.")
	public ResponseEntity<?> consultarMaterial(@PathVariable Integer id) {
		return ResponseEntity.of(this.materialService.consultarMaterial(id));
	}

	@GetMapping
	@ApiOperation(value = "Devuelve todos los Materials.")
	public ResponseEntity<List<Material>> consultarMateriales() {
		return ResponseEntity.ok(this.materialService.consultarMateriales());
	}
	
	@PutMapping(path = "/{id}")
	@ApiOperation(value = "Actualizar un Material.")
	public ResponseEntity<?> actualizarMaterial(@RequestBody Material material, @PathVariable Integer id){
		
		
        return ResponseEntity.ok(this.materialService.actualizarMaterial(id, material));

	}
	
    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Elimina un Material ")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {

        this.materialService.elimitarMaterial(id);
        
        return ResponseEntity.ok().build();
    }
    
    
    @GetMapping(params = "nombre")
    @ApiOperation(value = "Busca un Material por Nombre")
    public ResponseEntity<Material> materialPorNombre(@RequestParam(name = "nombre") String nombre) {

        Optional<Material> body = this.materialService.buscarMaterialPorNombre(nombre);

        if (body.isPresent()) {
            return ResponseEntity.ok(body.get());
        }
        else {
            throw new NotFoundException("Material no encontrado. Nombre: " + nombre);
        }
    }
    
    
    @GetMapping(params = {"precioMin", "precioMax"})
    @ApiOperation(value = "Busca Materiales por rango de precios")
    public ResponseEntity<List<Material>> materialesPorRangoPrecio(
            @RequestParam(name = "precioMin") Double precioMin,
            @RequestParam(name = "precioMax") Double precioMax) {
        
        return ResponseEntity.ok(this.materialService.buscarMaterialRangoPrecio(precioMin, precioMax));

    }
    
    @GetMapping(params = {"stockMin", "stockMax"})
    @ApiOperation(value = "Busca Materiales por rango de stock")
    public ResponseEntity<List<Material>> materialesPorRangoStock(
            @RequestParam(name = "stockMin") Integer stockMin,
            @RequestParam(name = "stockMax") Integer stockMax) {

        return ResponseEntity.ok(this.materialService.buscarMaterialRangoStock(stockMin, stockMax));

    }
	

}
