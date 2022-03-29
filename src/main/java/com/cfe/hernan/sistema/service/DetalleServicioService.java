package com.cfe.hernan.sistema.service;

import java.util.List;

import com.cfe.hernan.sistema.exceptionsDetalleServicio.DetalleServicioException;
import com.cfe.hernan.sistema.model.DetalleServicio;

public interface DetalleServicioService {

	public void guardarDetalleServicio(DetalleServicio detalleServicio) throws DetalleServicioException;

	public List<DetalleServicio> getDetalleServicio() throws DetalleServicioException;

	public void actualizarDetalleService(Double total_pago, Long id_cliente, int id_comunidad, int idPeriodo,
			Long noServicio, int id_detalle_servicio) throws DetalleServicioException;

	public void eliminarDetalleService(int detalleServicio) throws DetalleServicioException;

	public List<DetalleServicio> aListOfDetalleServicio() throws DetalleServicioException;

	public DetalleServicio consultaPorIdDetalleService(Long detalleNumeroServicio) throws DetalleServicioException;

	public DetalleServicio obtengoDetallePorIdCliente(Long id_cliente) throws DetalleServicioException;

	public Integer obtengoSumaTotalDePagosDetalleService() throws DetalleServicioException;
}
