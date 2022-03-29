package com.cfe.hernan.sistema.restAuthentication;

import com.cfe.hernan.sistema.model.Cliente;
import com.cfe.hernan.sistema.service.ClienteSecurityService;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonRestController {

	private final ClienteSecurityService clienteService;

	public PersonRestController(ClienteSecurityService clienteService) {
		this.clienteService = clienteService;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/api/cliente")
	public ResponseEntity<Cliente> getActualCliente() {

		Optional<Cliente> value = clienteService.getUserWithAuthorities();

		if (value.isPresent()) {
			Cliente ok = value.get();
			return ResponseEntity.ok(ok);
		}
		return (ResponseEntity<Cliente>) ResponseEntity.badRequest();

		// ResponseEntity.ok(clienteService.getUserWithAuthorities().get());
	}

}
