package com.cfe.hernan.sistema.model.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class RoleDto {

	private String name;
	private int idRole;

	public String getName() {
		return name;
	}

	public int getIdRole() {
		return idRole;
	}

	@Override
	public String toString() {
		return "RoleDto [name=" + name + ", idRole=" + idRole + "]";
	}

}
