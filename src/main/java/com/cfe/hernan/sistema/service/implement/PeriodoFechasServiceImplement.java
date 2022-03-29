package com.cfe.hernan.sistema.service.implement;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfe.hernan.sistema.exceptionsPeriodoFechas.PeriodoFechasException;
import com.cfe.hernan.sistema.model.PeriodoFechas;
import com.cfe.hernan.sistema.repository.PeriodoFechasRepository;
import com.cfe.hernan.sistema.service.PeriodoFechasService;

@Service
public class PeriodoFechasServiceImplement implements PeriodoFechasService {

	private static final Logger log = Logger.getLogger(PeriodoFechasServiceImplement.class);

	@Autowired
	private PeriodoFechasRepository periodoFechasRepository;

	@Override
	public void guardarPeriodoFecha(PeriodoFechas periodoFechas) throws PeriodoFechasException {
		StringBuilder mensaje = new StringBuilder();
		try {
			if (periodoFechas.getLimitePago() == null || periodoFechas.getCorteLuz() == null) {
				log.warn("UNO DE LOS ATRIBUTOS VIENE EN NULL PERIODO FECHAS");
				throw new PeriodoFechasException("LOS DATOS SON NULL EN guardarPeriodoFecha :");
			} else {
				periodoFechasRepository.save(periodoFechas);
			}
		} catch (PeriodoFechasException e) {
			mensaje.append("NO SE PUDO GUARDAR PERIODO FECHAS").append(e.getMessage());
			log.error(
					"ENTRO EN EL CATCH DE GUARDAR PERIODO FECHAS PORQUE LOS ATRIBUTOS DE PERIODO FECHA VIENEN EN NULL");
			throw new PeriodoFechasException(e.getMessage());
		}

	}

	@Override
	public List<PeriodoFechas> listarTodoPeriodoFechas() throws PeriodoFechasException {
		return periodoFechasRepository.findAll();
	}

	@Override
	public void deleteCliente(int periodoFechas) throws PeriodoFechasException {
		periodoFechasRepository.deleteById(periodoFechas);
	}

}
