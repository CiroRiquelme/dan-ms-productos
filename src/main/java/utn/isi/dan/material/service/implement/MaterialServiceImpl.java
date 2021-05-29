package utn.isi.dan.material.service.implement;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utn.isi.dan.material.dao.DetallePedidoRepositoryDao;
import utn.isi.dan.material.dao.MaterialRepositoryDao;
import utn.isi.dan.material.dao.MovimientosStockRepositoryDao;
import utn.isi.dan.material.dao.ProvisionRepositoryDao;
import utn.isi.dan.material.domain.DetallePedido;
import utn.isi.dan.material.domain.DetalleProvision;
import utn.isi.dan.material.domain.Material;
import utn.isi.dan.material.domain.MovimientosStock;
import utn.isi.dan.material.domain.Provision;
import utn.isi.dan.material.exception.NotFoundException;
import utn.isi.dan.material.service.IMaterialService;

@Service
public class MaterialServiceImpl implements IMaterialService {
	
	@Autowired
	MaterialRepositoryDao materialRepository;
	
	@Autowired
	DetallePedidoRepositoryDao detallePedidoRepository;
	
	@Autowired
	MovimientosStockRepositoryDao movimientosStockRepository;
	
	@Autowired
	ProvisionRepositoryDao provisionRepository;
	
	
    @Autowired
    ObjectMapper mapper;

	
	
    private static final Logger logger = LoggerFactory.getLogger(MaterialServiceImpl.class);
	
	
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
	
    @JmsListener(destination = "COLA_PEDIDOS")
    public void handle(Message message) throws JMSException {
    	
    	logger.info("1- LLEGO UN MENSAJE : " + message.toString());  	
    	
    	if(message instanceof TextMessage) {
    		TextMessage ms = (TextMessage) message;
    		
    		logger.info("2-  LLEGO UN MENSAJE " + ms.getText());
    		
			List<DetallePedidoDto> detallesPedidosDto = new ArrayList<DetallePedidoDto>();

    		//DetallePedidoDto detalleDto;
    		try {
			//	detalleDto= mapper.readValue(ms.getText(), DetallePedidoDto.class);
    			
    			detallesPedidosDto = mapper.readValue(ms.getText(), new TypeReference<List<DetallePedidoDto>>(){} );
    			
	    		logger.info("3-  LLEGO UN MENSAJE " + detallesPedidosDto.toString());
	    		
	    		this.comprobarDetallesPedido(detallesPedidosDto);

			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    }
    
    public void comprobarDetallesPedido(List<DetallePedidoDto> detallesPedidosDto) {
    	
    	
    	List<DetalleProvision> listaDetalleProvision = new ArrayList<DetalleProvision>();
    	
    	detallesPedidosDto.forEach(detalle -> 
    	{

            
            Optional<Material> materialOPT = materialRepository.findById(detalle.getIdMaterial());
            if(materialOPT.isPresent()) {
            	Material material = materialOPT.get();            	
            	
        		DetallePedido detallePedido = new DetallePedido();
        		detallePedido.setMaterial(material);
        		detallePedido.setCantidad(detalle.getCantidadMaterial());
        		
        		detallePedido = detallePedidoRepository.save(detallePedido);
        		
                MovimientosStock movimientoStock = new MovimientosStock();
                movimientoStock.setDetallePedido(detallePedido);
                movimientoStock.setMaterial(material);
                movimientoStock.setCantidadSalida(detallePedido.getCantidad());
                movimientoStock.setFecha(Instant.now().truncatedTo(ChronoUnit.MILLIS));
                
                movimientoStock = movimientosStockRepository.save(movimientoStock);           	
            	
            	
            	Integer nuevoStock = material.getStockActual() - detallePedido.getCantidad();
            	material.setStockActual(nuevoStock);
            	
            	materialRepository.save(material);
            	
            	if(nuevoStock <= material.getStockMinimo()) {
            		
                    DetalleProvision detalleProvision = new DetalleProvision();
                    detalleProvision.setMaterial(material);
                    detalleProvision.setCantidad(20 * material.getStockMinimo());
                    
                    listaDetalleProvision.add(detalleProvision);                   
            	}            	
            }   		
    		
    	}   	
    			);
    	
    		if(!listaDetalleProvision.isEmpty()) {
    		      Provision provision = new Provision();
    		      provision.setFechaProvision(Instant.now().truncatedTo(ChronoUnit.MILLIS));
    		      provision.setDetalle(listaDetalleProvision);    		      
    		      provisionRepository.save(provision);
    		}
    		
    		logger.info("4-  Se proceso el detalle ");

    	
    	
    }

}
