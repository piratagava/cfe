package com.cfe.hernan.sistema.service;

import java.util.List;

import com.cfe.hernan.sistema.exceptionsPeriodoFechas.PeriodoFechasException;
import com.cfe.hernan.sistema.model.PeriodoFechas;

public interface PeriodoFechasService {

	public void guardarPeriodoFecha(PeriodoFechas periodoFechas) throws PeriodoFechasException;

	public List<PeriodoFechas> listarTodoPeriodoFechas() throws PeriodoFechasException;

	public void deleteCliente(int periodoFechas) throws PeriodoFechasException;

	//pendiente
	//public void actualizarDetalleService(Date limitePago, Date corteLuz) throws PeriodoFechasException;
}
