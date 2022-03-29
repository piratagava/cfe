app.service("PeriodoService", function() {

	this.ocultarElementos = function() {
		//son componentes de la web ocultos
		$("#formPeriodoGuardar").hide();
		$("#encabezadoPeriodoFechas").show();
		$("#tablePricipal").hide();	
		cargarTablePeriodo();
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

});