package com.cfe.hernan.sistema.service.implement;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfe.hernan.sistema.exceptionsDetalleServicio.DetalleServicioException;
import com.cfe.hernan.sistema.model.DetalleServicio;
import com.cfe.hernan.sistema.repository.DetalleServicioRepository;
import com.cfe.hernan.sistema.service.DetalleServicioService;

@Service
public class DetalleServicioServiceImplement implements DetalleServicioService {
	private static final Logger log = Logger.getLogger(DetalleServicioServiceImplement.class);

	@Autowired
	private DetalleServicioRepository detalleServicioRepo;

	@Override
	public void guardarDetalleServicio(DetalleServicio detalleServicio) throws DetalleServicioException {
		StringBuilder mensaje = new StringBuilder();
		try {
			if (detalleServicio.getComunidad() == null || detalleServicio.getTotalPago() < 0
					|| detalleServicio.getCliente() == null) {
				log.warn("UNO DE LOS ATRIBUTOS VIENE EN NULL");
				throw new DetalleServicioException("LOS DATOS BIENEN EN NULL:");
			} else {
				detalleServicioRepo.save(detalleServicio);
			}
		} catch (DetalleServicioException e) {
			mensaje.append("NO SE PUDO GUARDAR DETALLES DEL CLIENTE ERROR EN EL CATCH").append(e.getMessage());
			log.error("ENTRO EN EL CATCH DE GUARDAR DETALLES");
			throw new DetalleServicioException(e.getMessage());
		}
	}

	@Override
	public List<DetalleServicio> getDetalleServicio() throws DetalleServicioException {
		return detalleServicioRepo.findAll();
	}

	@Override
	public void actualizarDetalleService(Double total_pago, Long id_cliente, int id_comunidad, int idPeriodo,
			Long noServicio, int id_detalle_servicio) throws DetalleServicioException {
		detalleServicioRepo.actualizarDetalleService(total_pago, id_cliente, id_comunidad, idPeriodo, noServicio,
				id_detalle_servicio);
	}

	@Override
	public void eliminarDetalleService(int detalleServicio) throws DetalleServicioException {
		detalleServicioRepo.deleteById(detalleServicio);
	}

	@Override
	public List<DetalleServicio> aListOfDetalleServicio() throws DetalleServicioException {
		return detalleServicioRepo.aListOfDetalleService();
	}

	@Override
	public DetalleServicio obtengoDetallePorIdCliente(Long id_cliente) throws DetalleServicioException {
		return detalleServicioRepo.obtengoDetallePorIdCliente(id_cliente);
	}

	@Override
	public DetalleServicio consultaPorIdDetalleService(Long detalleNumeroServicio) throws DetalleServicioException {
		return detalleServicioRepo.obtengoDetalleServicioPorNumeroServicioDelCliente(detalleNumeroServicio);
	}

	@Override
	public Integer obtengoSumaTotalDePagosDetalleService() throws DetalleServicioException {
		return detalleServicioRepo.obtengoSumaTotalDePagos();
	}

}
