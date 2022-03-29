package com.cfe.hernan.sistema.periodoFechasController;

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

import com.cfe.hernan.sistema.clienteController.ClienteController;
import com.cfe.hernan.sistema.exceptionsPeriodoFechas.PeriodoFechasException;
import com.cfe.hernan.sistema.model.PeriodoFechas;
import com.cfe.hernan.sistema.model.dto.PeriodoFechasDto;
import com.cfe.hernan.sistema.service.PeriodoFechasService;

@Controller
public class PeriodoFechasController {
	private static final Logger log = Logger.getLogger(ClienteController.class);
	@Autowired
	private PeriodoFechasService periodoFechasService;

	private static final String PERIODO_FECHA_INSERT_DATA = "/insertarPeriodo";
	private static final String LISTA_ALL_PERIODO_FECHA = "/listaPeriodo";
	private static final String UPDATE_PERIODO_FECHA = "/actualizarPeriodo";
	private static final String DELETE_PERIODO_FECHA = "/eliminarPeriodo";

	@PostMapping(value = PERIODO_FECHA_INSERT_DATA)
	public @ResponseBody Boolean insertarPeriodoFechas(@RequestBody PeriodoFechasDto periodo)
			throws PeriodoFechasException {
		StringBuilder mensaje = new StringBuilder();
		try {
			PeriodoFechas persistNewPeriodo = new PeriodoFechas();
			persistNewPeriodo.setLimitePago(periodo.getLimitePago());
			persistNewPeriodo.setCorteLuz(periodo.getCorteLuz());
			periodoFechasService.guardarPeriodoFecha(persistNewPeriodo);
			return true;
		} catch (PeriodoFechasException e) {
			mensaje.append("NO SE PUDO GUARDAR PERIODO ERROR CONTROLLER").append(e.getMessage());
			log.error("ENTRO EN EL CATCH DE INSERTAR CONTROLLER PERIODO");
			throw new PeriodoFechasException(e.getMessage());
		}
	}

	@GetMapping(value = LISTA_ALL_PERIODO_FECHA)
	public @ResponseBody List<PeriodoFechas> listarPeriodoFechas() throws PeriodoFechasException {
		StringBuilder mensaje = new StringBuilder();
		try {
			return periodoFechasService.listarTodoPeriodoFechas();
		} catch (PeriodoFechasException e) {
			mensaje.append("NO SE PUDO LISTAR PERIODO FECHAS ERROR CONTROLLER").append(e.getMessage());
			log.error("ENTRO EN EL CATCH DE LISTAR CONTROLLER PERIODO FECHAS");
			throw new PeriodoFechasException(e.getMessage());
		}
	}

	@DeleteMapping(value = DELETE_PERIODO_FECHA)
	public @ResponseBody Boolean deletePeriodoFechas(@RequestBody PeriodoFechasDto periodoFechas)
			throws PeriodoFechasException {
		StringBuilder mensaje = new StringBuilder();
		try {
			periodoFechasService.deleteCliente(periodoFechas.getIdPeriodo());
			return true;
		} catch (PeriodoFechasException ex) {
			mensaje.append("deletePeriodoFechas ERROR INTERNO").append(ex.getMessage());
			throw new PeriodoFechasException(ex.getMessage());
		}
	}

	@PutMapping(value = UPDATE_PERIODO_FECHA)
	public @ResponseBody Boolean updatePeriodoFechas(@RequestBody PeriodoFechasDto periodoFechas)
			throws PeriodoFechasException {
		StringBuilder mensaje = new StringBuilder();
		try {
			PeriodoFechas persistNewPeriodo = new PeriodoFechas();
			persistNewPeriodo.setIdPeriodo(periodoFechas.getIdPeriodo());
			persistNewPeriodo.setLimitePago(periodoFechas.getLimitePago());
			persistNewPeriodo.setCorteLuz(periodoFechas.getCorteLuz());
			periodoFechasService.guardarPeriodoFecha(persistNewPeriodo);
			return true;
		} catch (PeriodoFechasException e) {
			mensaje.append("NO SE PUDO ACTUALIZAR PERIODO DE FECHAS ERROR INTERNO").append(e.getMessage());
			log.error("ENTRO EN EL CATCH DE ACTUALIZAR PERIODO DE FECHAS CONTROLLER");
			throw new PeriodoFechasException(e.getMessage());
		}

	}
}
