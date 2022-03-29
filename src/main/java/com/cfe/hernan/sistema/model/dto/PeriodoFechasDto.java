package com.cfe.hernan.sistema.model.dto;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class PeriodoFechasDto {

	private int idPeriodo;
	private Date limitePago;
	private Date corteLuz;

	public int getIdPeriodo() {
		return idPeriodo;
	}

	public Date getLimitePago() {
		return limitePago;
	}

	public Date getCorteLuz() {
		return corteLuz;
	}

	@Override
	public String toString() {
		return "PeriodoFechas [idPeriodo=" + idPeriodo + ", limitePago=" + limitePago + ", corteLuz=" + corteLuz + "]";
	}

}
