app.service("DetalleService", function() {

	this.ocultarElementos = function() {
		//son componentes de la web ocultos
		$("#formDetalleServiceGuardar").hide();
		$("#encabezadoDetalleService").show();
		$("#tablePricipal").hide();
		cargarTable(); // realizo la peticion al servidor que me de la lista de todos los clientes		
		cargarTableDetalleServicio();		
		document.getElementById("btnHideTable").disabled = true;
	}

	this.obtengoToken = function() {
		//define storage para ver si existe una sesion
		var TOKEN_KEY = "jwtToken";
		var storage = localStorage.getItem(TOKEN_KEY);
		var cachedToken;

		if (storage == null) {
			window.location.href = "/index";
		}
	}

	this.createAuthorizationTokenHeader = function() {
		if (storage) {
			return { "Authorization": "Bearer " + storage };
		} else {
			return {};
		}
	}


	this.validarDatosCliente = function(totalPago,numeroServicio) {
		//variable binary de inicio
		var valido = 4;
		if (totalPago.length > 0) {
			let validaNumTotalPago = /^[0-9]{1,5}$/
			let esValido = validaNumTotalPago.exec(totalPago);

			if (esValido == null || totalPago <= 0) {
				alertify.notify('El número de cupos no ha sido validado', 'error', 3, null);
			} else {
				valido >>= 1;
			}
		}

		if (numeroServicio.length > 0) {
			let validaNumServicio = /^[0-9]{1,15}$/
			let esValido = validaNumServicio.exec(numeroServicio);

			if (esValido == null || numeroServicio <= 0) {
				alertify.notify('El número de servicio no ah sido validado', 'error', 3, null);
			} else {
				valido >>= 1;
			}
		}

		return valido;
	}

});