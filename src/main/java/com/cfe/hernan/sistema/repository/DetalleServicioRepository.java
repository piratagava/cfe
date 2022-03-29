package com.cfe.hernan.sistema.repository;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cfe.hernan.sistema.model.DetalleServicio;

@Repository
public interface DetalleServicioRepository extends JpaRepository<DetalleServicio, Integer> {

	@Modifying
	@Transactional
	@Query(value = "Update detalle_servicio Set total_pago=?, id_cliente=?, id_comunidad=?,"
			+ "id_periodo=?,numero_servicio=? where id_detalle_servicio=? ", nativeQuery = true)
	public void actualizarDetalleService(Double total_pago, Long id_cliente, int idPeriodo, int id_comunidad,
			Long nuemeroServicio, int id_detalle_servicio);

	@Query(value = "SELECT * FROM detalle_servicio;", nativeQuery = true)
	public List<DetalleServicio> aListOfDetalleService();

	@Query(value = "select * from detalle_servicio where id_cliente=?", nativeQuery = true)
	public DetalleServicio obtengoDetallePorIdCliente(Long id_cliente);

	@Query(value = "select * from detalle_servicio\n"			
			+ "where numero_servicio =?;", nativeQuery = true)
	public DetalleServicio obtengoDetalleServicioPorNumeroServicioDelCliente(Long numeroServicio);
	
	@Query(value = "select  sum(total_pago) from detalle_servicio;", nativeQuery = true)
	public int obtengoSumaTotalDePagos();
	
}
