package com.cfe.hernan.sistema.model.dto;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.cfe.hernan.sistema.model.Cliente;
import com.cfe.hernan.sistema.model.Comunidad;
import com.cfe.hernan.sistema.model.PeriodoFechas;

public class DetalleServicioDto {

	private int idDetalleServicio;
	private double totalPago;
	private Comunidad comunidad;
	private Cliente cliente;
	private PeriodoFechas periodo;
	private Long numeroServicio;

	public int getIdDetalleServicio() {
		return idDetalleServicio;
	}

	public double getTotalPago() {
		return totalPago;
	}

	public Comunidad getComunidad() {
		return comunidad;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public PeriodoFechas getPeriodo() {
		return periodo;
	}

	public Long getNumeroServicio() {
		return numeroServicio;
	}

	@Override
	public String toString() {
		return "DetalleServicioDto [idDetalleServicio=" + idDetalleServicio + ", totalPago=" + totalPago
				+ ", comunidad=" + comunidad + ", cliente=" + cliente + ", periodo=" + periodo + ", numeroServicio="
				+ numeroServicio + "]";
	}

}
