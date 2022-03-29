var app = angular.module('AltaPeriodo', ['datatables', 'ngResource']);
app.controller("controllerPeriodo", ["$scope", "$http", "PeriodoService", function ($scope, $http, PeriodoService) {

	PeriodoService.ocultarElementos();

	$scope.openAgregarPeriodo = function () {
		$("#formPeriodoGuardar").show();
		$("#periodoForm").show();
		$("#tablePricipal").hide();
	};

	$scope.cerrarFormularioPeriodo = function () {
		$("#periodoForm").hide();
	};

	$scope.openListarPeriodoFechas = function () {
		document.getElementById("btnShowTable").disabled = true;
		$("#tablePricipal").show();
		document.getElementById("btnHideTable").disabled = false;
		$("#periodoForm").hide();
	};

	$scope.closeListarPeriodoFechas = function () {
		document.getElementById("btnShowTable").disabled = false;
		$("#tablePricipal").hide();
		document.getElementById("btnHideTable").disabled = true;
	};

	PeriodoService.obtengoToken();

	$scope.guardarDatosPeriodo = function () {
		//obtengo el id_cliente	aqui cambios por si las moscas
		var limitePago = $('#txtLimitePago').val();
		var corte_Luz = $('#txtCorteLuz').val();

		if (limitePago == "" || corte_Luz == "") {
			alertify.notify('Selecciona una fecha porfavor !!', 'error', 5, null);
		} else {
			var datosFechas = { limitePago: limitePago, corteLuz: corte_Luz };

			$http({ method: 'post', headers: PeriodoService.createAuthorizationTokenHeader(), url: 'insertarPeriodo', data: datosFechas }).then(function onSuccess(response) {
				$('#tablaPeriodo').DataTable().ajax.reload();
				$("#periodoForm")[0].reset();
				alertify.notify('Se agrego correctamente la fecha', 'success', 5, null);
			}).catch(function onError(response) {
				if (response == false) {
					alertify.notify('Se genero error interno intente mas tarde', 'error', 5, null);
				}
				if (response.status == 401) {
					alertify.notify('Su Sesion Expiro intente mas tarde', 'error', 5, null);
					window.location.href = "/index";
				}
			});
		}
	}

	$scope.actualizarDatosPeriodo = function () {
		let confirm = alertify.confirm('Actualizar Detalles Del Cliente', 'Desea Actualizar el registro ?', null, null).set('labels', {
			ok: 'Si',
			cancel: 'Cancelar'
		});
		confirm.set({
			transition: 'slide'
		});

		confirm.set('onok', function () {
			var idPeriodo = $("#txtidp").val();
			var Pago = $("#txtPago").val();
			var CorteLuz = $("#txtCorte").val();

			$http({
				method: 'put', headers: PeriodoService.createAuthorizationTokenHeader(),
				url: 'actualizarPeriodo', data: {
					idPeriodo: idPeriodo,
					corteLuz: CorteLuz,
					limitePago: Pago
				}
			}).then(function onSuccess(response) {
				$('#tablaPeriodo').DataTable().ajax.reload();
				alertify.notify("Se Actualizo Correctamente su Registro", "success", 5, null);
				$('#myModalPeriodo').modal('toggle');
			}).catch(function onError(response) {
				if (response == false) {
					alertify.notify('Se genero error interno intente mas tarde', 'error', 5, null);
				}
				if (response.status == 401) {
					alertify.notify('Su Sesion Expiro Inicie Sesion Nuevamente', 'error', 8, null);
					window.location.href = "/index";
				}
			});

		});
		confirm.set('oncancel', function () { //llama al pulsar botón negativo
			alertify.error('Cancelo Actualizar su registro', 3);
		});
	}
}]);


var TOKEN_KEY = "jwtToken";
var storage = localStorage.getItem(TOKEN_KEY);
function removeJwtToken() {
	localStorage.removeItem(TOKEN_KEY);
	window.location.href = "/index";
}
function doLogout() {
	removeJwtToken();
}

//es la de mi Periodo Service
function cargarTablePeriodo() {
	var datatable = $('#tablaPeriodo').DataTable({
		"ajax": {
			"url": 'listaPeriodo',
			"type": 'GET',
			"dataSrc": "",
			"dataType": "json",
			"headers": { "Authorization": "Bearer " + storage },
			"error": function (jqXHR) {
				alertify.notify("Error en la vista de la Tabla" + jqXHR.statusText + " " + jqXHR.responseText, 'error', 10, null);
				window.location.href = "/index";
			}
		},
		"columns": [
			{ "data": "idPeriodo" },
			{ "data": "limitePago" },
			{ "data": "corteLuz" }],
		"columnDefs": [{
			"targets": 3,
			"bSortable": false, //no permite la ordenación de columnas individuales.
			"render": function () {
				return '<button type="button" id="editar" class="editar edit-modal btn btn-outline-primary botonEditar"><span class="fa fa-pen"></span><span class="hidden-xs"></span></button>';
			}
		}, {
			"targets": 4,
			"data": null, //Si desea pasar datos que ya tiene para que cada seleccion sea diferente num
			"bSortable": false,
			"mRender": function (o) {
				return '<a class="btn btn-outline-danger" onclick="dialogEliminar(' + o.idPeriodo + ')" type="button"><span class="fas fa-trash-alt"></span><span class="hidden-xs"></span></a>';
			}
		}],
	});

	var editar = function (tbody, table) {
		$(tbody).on("click", "button.editar", function () {
			if (table.row(this).child.isShown()) {
				var data = table.row(this).data();
			} else {
				var data = table.row($(this).parents("tr")).data();
			}
			$('#myModalPeriodo').modal('show');

			$("#txtidp").val(data.idPeriodo);
			$("#txtPago").val(data.limitePago);
			$("#txtCorte").val(data.corteLuz);

			// first().focus() es para cuando abras el modal se dirija el puntero al campo que indicas			
			$("#txtPago").first().focus();
			$("#txtCorte").first().focus();

		});
	}
	editar("#tablaPeriodo tbody", datatable);
}


function dialogEliminar(idPeriodo) {
	let confirm = alertify.confirm('Eliminar Producto', 'Desea eliminar el registro ?', null, null).set('labels', {
		ok: 'Si',
		cancel: 'Cancelar'
	});
	confirm.set({
		transition: 'slide'
	});

	confirm.set('onok', function () {
		eliminarCliente(idPeriodo);
	});

	confirm.set('oncancel', function () { //llama al pulsar botón negativo
		alertify.error('Cancelo eliminar su registro', 3);
	});
}

function eliminarCliente(idPeriodo) {
	var id_p = idPeriodo;
	let json = {};
	json.idPeriodo = id_p;
	$.ajax({
		type: 'DELETE',
		contentType: 'application/json',
		dataType: 'json',
		url: 'eliminarPeriodo',
		data: JSON.stringify(json),
		headers: { "Authorization": "Bearer " + storage },
		success: function (data) {
			$('#tablaPeriodo').DataTable().ajax.reload();
		},
		error: function (response) {
			if (response.status == 401) {
				alertify.notify("Error Expiro su Sesion Vuelva De Entrar", 'error', 10, null);
				window.location.href = "/index";
			} else {
				alertify.notify('Error interno Primero Elimine a las personas con este Rol asociado', 'error', 8, null);
			}
		},
	});
}