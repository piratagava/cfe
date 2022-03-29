package com.cfe.hernan.sistema.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "periodo")
public class PeriodoFechas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_periodo")
	private int idPeriodo;

	@Column(name = "limite_pago")
	@NotNull(message = "Limite de pago no debe ir vacio")
	private Date limitePago;

	@Column(name = "corte_luz")
	@NotNull(message = "corte_luz no debe ir vacio")
	private Date corteLuz;

	public int getIdPeriodo() {
		return idPeriodo;
	}

	public void setIdPeriodo(int idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public Date getLimitePago() {
		return limitePago;
	}

	public void setLimitePago(Date limitePago) {
		this.limitePago = limitePago;
	}

	public Date getCorteLuz() {
		return corteLuz;
	}

	public void setCorteLuz(Date corteLuz) {
		this.corteLuz = corteLuz;
	}

}
