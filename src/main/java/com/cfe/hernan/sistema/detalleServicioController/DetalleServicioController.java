package com.cfe.hernan.sistema.detalleServicioController;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cfe.hernan.sistema.exceptionsCliente.ClienteException;
import com.cfe.hernan.sistema.exceptionsDetalleServicio.DetalleServicioException;

import com.cfe.hernan.sistema.model.DetalleServicio;
import com.cfe.hernan.sistema.model.dto.DetalleServicioDto;
import com.cfe.hernan.sistema.service.DetalleServicioService;

@Controller
public class DetalleServicioController {
	private static final Logger log = Logger.getLogger(DetalleServicioController.class);

	@Autowired
	private DetalleServicioService detalleService;

	private static final String DETALLE_SERVICIO_INSERT_DATA = "/insertarDetalleService";
	private static final String LISTA_ALL_DETALLESERVICIO = "/listaDetalleServicio";
	private static final String CONSULTA_ALL_DETALLESERVICIO = "/consultaDetalleServicio";
	private static final String UPDATE_SERVICIO_INSERT_DATA = "/actualizarDetalleService";
	private static final String DELETE_SERVICIO_INSERT_DATA = "/deleteDetalleService";
	private static final String CONSULTA_DETALLE_SERVICIO_CLIENTE_POR_NUMERO_SERVICIO = "/consultaDetalleServicioPorNumeroServicioDelCliente";
	private static final String CONSULTA_DETALLE_SERVICIO_CLIENTE = "/api/consultaDetalleServicioCliente";
	private static final String CONSULTA_TOTAL_PAGO_EN_DETALLE_SERVICIO = "/consultaTotalPago";

	@PostMapping(value = DETALLE_SERVICIO_INSERT_DATA)
	public @ResponseBody Boolean insertarDetalleService(@RequestBody DetalleServicioDto detalleServicio)
			throws DetalleServicioException {
		StringBuilder mensaje = new StringBuilder();

		try {
			DetalleServicio persistNewDetalleService = new DetalleServicio();

			System.out.print(detalleServicio.toString());
			persistNewDetalleService.setTotalPago(detalleServicio.getTotalPago());
			persistNewDetalleService.setCliente(detalleServicio.getCliente());
			persistNewDetalleService.setComunidad(detalleServicio.getComunidad());
			persistNewDetalleService.setPeriodo(detalleServicio.getPeriodo());
			persistNewDetalleService.setNumeroServicio(detalleServicio.getNumeroServicio());

			detalleService.guardarDetalleServicio(persistNewDetalleService);
			return true;
		} catch (DetalleServicioException ex) {
			mensaje.append("NO SE PUDO GUARDAR DETALLE SERVICIO").append(ex.getMessage());
			log.error("ENTRO EN EL CATCH DE GUARDAR DETALLE SERVICIO");
			return false;
		}
	}

	@PostMapping(value = CONSULTA_DETALLE_SERVICIO_CLIENTE)
	public @ResponseBody DetalleServicio obtengoDetalleService(@RequestBody DetalleServicioDto dService)
			throws DetalleServicioException {
		DetalleServicio obtengoConsulta = detalleService
				.obtengoDetallePorIdCliente(dService.getCliente().getIdCliente());
		return obtengoConsulta;
	}

	@PutMapping(value = UPDATE_SERVICIO_INSERT_DATA)
	public @ResponseBody Boolean actualizarDetalleService(@RequestBody DetalleServicioDto detalleServicio)
			throws DetalleServicioException {
		StringBuilder mensaje = new StringBuilder();
		try {
			detalleService.actualizarDetalleService(detalleServicio.getTotalPago(),
					detalleServicio.getCliente().getIdCliente(), detalleServicio.getComunidad().getIdComunidad(),
					detalleServicio.getPeriodo().getIdPeriodo(), detalleServicio.getNumeroServicio(),
					detalleServicio.getIdDetalleServicio());

			return true;
		} catch (DetalleServicioException e) {
			mensaje.append("NO SE PUDO UPDATE DETALLE SERVICIO").append(e.getMessage());
			log.error("ENTRO EN EL CATCH DE ACTUALIZAR DETALLE SERVICIO");
			return false;
		}
	}

	@DeleteMapping(value = DELETE_SERVICIO_INSERT_DATA)
	public @ResponseBody Boolean deleteDetalleService(@RequestBody DetalleServicioDto detalleServicio)
			throws DetalleServicioException {
		StringBuilder mensaje = new StringBuilder();
		try {
			detalleService.eliminarDetalleService(detalleServicio.getIdDetalleServicio());
			return true;
		} catch (DetalleServicioException ex) {
			mensaje.append("NO SE PUDO ELIMINAR DETALLE SERVICE ERROR INTERNO").append(ex.getMessage());
			log.error("ENTRO EN EL CATCH DE BORRAR DETALLE SERVICE CONTROLLER");
			throw new DetalleServicioException(ex.getMessage());
		}
	}

	@GetMapping(value = LISTA_ALL_DETALLESERVICIO)
	public @ResponseBody List<DetalleServicio> listarDetalleService() throws DetalleServicioException {
		List<DetalleServicio> listar = detalleService.aListOfDetalleServicio();
		return listar;

	}

	@PostMapping(value = CONSULTA_DETALLE_SERVICIO_CLIENTE_POR_NUMERO_SERVICIO)
	public @ResponseBody DetalleServicio obtengoDetalleServicePorIdConsulta(@RequestBody DetalleServicioDto dService)
			throws DetalleServicioException {
		DetalleServicio obtengoConsulta = detalleService.consultaPorIdDetalleService(dService.getNumeroServicio());
		return obtengoConsulta;
	}

	@GetMapping(value = CONSULTA_TOTAL_PAGO_EN_DETALLE_SERVICIO)
	public @ResponseBody Integer consultaTotalPago() throws DetalleServicioException {	
		return detalleService.obtengoSumaTotalDePagosDetalleService();
	}
}
