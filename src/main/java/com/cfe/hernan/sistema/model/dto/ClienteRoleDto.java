package com.cfe.hernan.sistema.model.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class ClienteRoleDto {

	private int idCliente;
	private String name;

	public int getIdCliente() {
		return idCliente;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "ClienteRoleDto [idCliente=" + idCliente + ", name=" + name + "]";
	}

}
