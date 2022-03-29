package com.cfe.hernan.sistema.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "detalle_servicio")
public class DetalleServicio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idDetalleServicio;

	@Column(name = "total_pago")
	@NotNull(message = "total_pago no debe ir vacio")
	private double totalPago;

	// Relacion Para detalle Servicio es mejor hacerlo rapudo con Eager
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_comunidad", nullable = false)
	private Comunidad comunidad;

	// Relacion Para detalle Servicio es mejor hacerlo rapudo con Eager
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cliente", nullable = false)
	private Cliente cliente;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_periodo", nullable = false)
	private PeriodoFechas periodo;

	@Column(name = "numero_servicio")
	@NotNull(message = "Numero de servicio no debe ir vacio")
	private Long numeroServicio;

	public DetalleServicio() {
		DetalleServicio detalleServicio;
	}

	public int getIdDetalleServicio() {
		return idDetalleServicio;
	}

	public void setIdDetalleServicio(int idDetalleServicio) {
		this.idDetalleServicio = idDetalleServicio;
	}

	public double getTotalPago() {
		return totalPago;
	}

	public void setTotalPago(double totalPago) {
		this.totalPago = totalPago;
	}

	public Comunidad getComunidad() {
		return comunidad;
	}

	public void setComunidad(Comunidad comunidad) {
		this.comunidad = comunidad;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public PeriodoFechas getPeriodo() {
		return periodo;
	}

	public void setPeriodo(PeriodoFechas periodo) {
		this.periodo = periodo;
	}

	public Long getNumeroServicio() {
		return numeroServicio;
	}

	public void setNumeroServicio(Long numeroServicio) {
		this.numeroServicio = numeroServicio;
	}

}
