package com.cfe.hernan.sistema.exceptionsDetalleServicio;

import org.apache.commons.lang.exception.NestableException;

public class DetalleServicioException extends NestableException {

	/**
	 * 
	 */
	public DetalleServicioException(String msg) {
		super(msg);
	}
}
