app.service("ComunidadService", function() {

	this.ocultarElementos = function() {
		// son componentes de la web ocultos
		$("#formComunidadGuardar").hide();
		$("#encabezadoComunidad").show();
		$("#tablePricipal").hide();
		cargarTable(); // realizo la peticion al servidor que me de la
		// lista de todos los clientes
		document.getElementById("btnHideTable").disabled = true;
	}

	this.obtengoToken = function() {
		//define storage para ver si existe una sesion
		var TOKEN_KEY = "jwtToken";
		var storage = localStorage.getItem(TOKEN_KEY);
		var cachedToken;

		if (storage == null) {
			window.location.href = "/es-login-page-Ing_Hernan";
		}
	}

	this.createAuthorizationTokenHeader = function() {
		if (storage) {
			return { "Authorization": "Bearer " + storage };
		} else {
			return {};
		}
	}

	//Existe varias formas de validar campos pero este metodo lo tome de un colega fywer porque es bueno en javascript
	this.validarDatosComunidad = function(nombreComunidad) {
		// variable binary de inicio
		var valido = 2;

		if (nombreComunidad.length > 0) {
			let validaNombre = /^[A-Z\u00C0-\u00DCa-z\u00E0-\u00FC0-9\s]{1,99}$/
			let esValido = validaNombre.exec(nombreComunidad);
			if (esValido == null || nombreComunidad.length == 0 || /^\s+$/.test(nombreComunidad)) {
				alertify.notify('Usa solo 30 Caracteres', 'error', 4, null);
			} else {
				valido >>= 1;
			}
		}
		return valido;
	}

});