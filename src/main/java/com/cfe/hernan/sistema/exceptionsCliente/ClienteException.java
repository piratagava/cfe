package com.cfe.hernan.sistema.exceptionsCliente;

import org.apache.commons.lang.exception.NestableException;

public class ClienteException extends NestableException {
	private static final long serialVersionUID = 1L;

	public ClienteException(String msg) {
		super(msg);
	}

}
