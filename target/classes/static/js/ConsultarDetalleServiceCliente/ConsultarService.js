var app = angular.module('SearchCliente', ['datatables', 'ngResource']);
app.controller("controllerCliente", ["$scope", "$http", function ($scope, $http) {

	$("#detallesService").hide();

	$scope.numero_servicio = null;

	$scope.consultarDatosCliente = function (numero_servicio) {
		var datosCliente = { numeroServicio: numero_servicio };
		var numeroServicio = document.getElementById("txtNumServicio").value;
		var validar = validarDatosCliente(numeroServicio);

		if (validar == 1) {
			$http({
				method: 'post',
				url: 'consultaDetalleServicioPorNumeroServicioDelCliente', data: datosCliente
			}).then(function onSuccess(response) {								
				if(response.data==""){
				alertify.notify('No existe su registro verificar nuevamente', 'error', 5, null);	
				}else{
				$scope.cliente = response.data;				
				$scope.clientePago = response.data.totalPago;				
				$("#detallesService").show();
				setTimeout("location.href='/index'", 16000);
				}
				
			}).catch(function onError(response) {
				if (response == false) {
					alertify.notify('Se genero error interno intente mas tarde', 'error', 5, null);
				}
			});
		} else {
			alertify.notify('Llene correctamente los campos', 'error', 5, null);
		}
	}


}]);


this.validarDatosCliente = function (numeroServicio) {
	//variable binary de inicio
	var valido = 2;

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


