var app = angular.module('AltaDetalleService', ['datatables', 'ngResource']);
app.controller("controllerDetalleService", ["$scope", "$http", "DetalleService", function ($scope, $http, DetalleService) {

      DetalleService.ocultarElementos();
      var $selectComunidad = $('#selectComunidad');
      var $selectRole = $('#selectRole');

      $scope.openAgregarDetalleService = function () {
         $("#formDetalleServiceGuardar").show();
         $("#detalleServiceForm").show();
         $("#tablePricipal").hide();
         $selectComunidad.val($selectComunidad.children('option:first').val());
         $selectRole.val($selectRole.children('option:first').val());
      };

      $scope.cerrarFormularioDetalleService = function () {
         $("#detalleServiceForm").hide();
         $selectComunidad.val($selectComunidad.children('option:first').val());
         $selectRole.val($selectRole.children('option:first').val());
      };

      $scope.openListarDetalleService = function () {
         document.getElementById("btnShowTable").disabled = true;
         $("#tablePricipal").show();
         document.getElementById("btnHideTable").disabled = false;
         $("#detalleServiceForm").hide();
         $selectComunidad.val($selectComunidad.children('option:first').val());
         $selectRole.val($selectRole.children('option:first').val());
      };

      $scope.closeListarDetalleService = function () {
         document.getElementById("btnShowTable").disabled = false;
         $("#tablePricipal").hide();
         document.getElementById("btnHideTable").disabled = true;
         $selectComunidad.val($selectComunidad.children('option:first').val());
         $selectRole.val($selectRole.children('option:first').val());
      };

      $scope.calcularTotal = function () {
         $http({
            url: 'consultaTotalPago', contentType: "application/json; charset=utf-8", dataType: "json",
            headers: DetalleService.createAuthorizationTokenHeader()
         }).then(function (response) {
            $scope.totalPagoDetalleService = response.data;
         }, function errorCallback(response) {
            alertify.notify("Sin Permisos Suficientes ah caducado su sesion", "error", 10, null);

         });
      }


      DetalleService.obtengoToken();

      $http({
         url: 'listaComunidad', contentType: "application/json; charset=utf-8", dataType: "json",
         headers: DetalleService.createAuthorizationTokenHeader()
      }).then(function (response) {
         $scope.comunidades = response.data;
      }, function errorCallback(response) {
         alertify.notify("Sin Permisos Suficientes ah caducado su sesion", "error", 10, null);
         window.location.href = "/index";
      });

      $http({
         url: 'listaPeriodo', contentType: "application/json; charset=utf-8", dataType: "json",
         headers: DetalleService.createAuthorizationTokenHeader()
      }).then(function (response) {
         $scope.fechas = response.data;
      }, function errorCallback(response) {
         alertify.notify("Sin Permisos Suficientes ah caducado su sesion", "error", 10, null);
         window.location.href = "/index";
      });

      $scope.total_pago = null;
      //es para asignar la comunidad
      $scope.selectedCar = null;
      $scope.selectedFecha = null;
      $scope.numero_servicio = null;



      $scope.guardarDatosDetalleServicio = function (total_pago, selectedCar, selectedFecha, numero_servicio) {
         //obtengo el id_cliente
         var inputs = $('#valId').val();

         var datosCliente = {
            cliente: {idCliente: inputs}, totalPago: total_pago, numeroServicio: numero_servicio,
            comunidad: {idComunidad: selectedCar},
            periodo: {idPeriodo: selectedFecha}
         };

         //variables para hacer validaciones no campos vacios
         var totalPago = document.getElementById("txtNumTotalPago").value;
         var numeroServicio = document.getElementById("txtNumServicio").value;
         var validar = DetalleService.validarDatosCliente(totalPago, numeroServicio);

         if (validar == 1 && $('#selectRole').val().trim() != '' && $('#selectPeriodo').val().trim() != '') {
            $http({
               method: 'post', headers: DetalleService.createAuthorizationTokenHeader(),
               url: 'insertarDetalleService', data: datosCliente
            }).then(function onSuccess(response) {
               alertify.notify('Se guardo correctamente su registro', 'success', 5, null);
               $('#tablaDetalles').DataTable().ajax.reload();
               $http({
                  url: 'consultaEstaClienteEnDetalleServicio', contentType: "application/json; charset=utf-8", dataType: "json",headers: DetalleService.createAuthorizationTokenHeader()
               }).then(function (response) {
                  $('#tablaClientes').DataTable().ajax.reload();
               });      
               $("#detalleServiceForm")[0].reset();
               //location.reload(true);               
            }).catch(function onError(response) {
               if (response == false) {
                  alertify.notify('Se genero error interno intente mas tarde', 'error', 5, null);
               }
               if (response.status == 401) {
                  alertify.notify('Su Sesion Expiro intente mas tarde', 'error', 5, null);
                  window.location.href = "/index";
               }
            });
         } else {
            alertify.notify('Llene correctamente los campos', 'error', 5, null);
         }
      }

      $scope.actualizarDatosCliente = function () {
         let confirm = alertify.confirm('Actualizar Detalles Del Cliente', 'Desea Actualizar el registro ?', null, null).set('labels', {
            ok: 'Si',
            cancel: 'Cancelar'
         });
         confirm.set({
            transition: 'slide'
         });

         confirm.set('onok', function () {
            var idDetalleCliente = $("#valIds").val();
            var idCliente = $("#valIdCliente").val();
            var Total_a_Pagar = $("#txtNumTotalPagos").val();
            var seleccionComunidad = $("#selectComunidad").val();
            var seleccionPeriodo = $("#selectFecha").val();
            var numeroServicio = $("#txtNumServicios").val();
            var validar = DetalleService.validarDatosCliente(Total_a_Pagar, numeroServicio);

            if (validar == 1 && $('#selectComunidad').val().trim() != '' && $('#selectFecha').val().trim() != '') {
               $http({
                  method: 'put', headers: DetalleService.createAuthorizationTokenHeader(),
                  url: 'actualizarDetalleService', data: {
                     idDetalleServicio: idDetalleCliente,
                     totalPago: Total_a_Pagar,
                     cliente: {idCliente: idCliente},
                     comunidad: {idComunidad: seleccionComunidad},
                     periodo: {idPeriodo: seleccionPeriodo},
                     numeroServicio: numeroServicio,
                  }
               }).then(function onSuccess(response) {
                  $('#tablaDetalles').DataTable().ajax.reload();
                  alertify.notify("Se Actualizo Correctamente su Registro", "success", 5, null);
                  $('#myModalActualizar').modal('toggle');
                  $selectComunidad.val($selectComunidad.children('option:first').val());
               }).catch(function onError(response) {
                  if (response == false) {
                     alertify.notify('Se genero error interno intente mas tarde', 'error', 5, null);
                  }
                  if (response.status == 401) {
                     alertify.notify('Su Sesion Expiro Inicie Sesion Nuevamente', 'error', 8, null);
                     window.location.href = "/index";
                  }
               });
            } else {
               alertify.notify('Porfavor Asegurate llenar los campos correctamente, Vuelva Seleccionar una Comunidad Nuevamente', 'error', 7, null);
            }
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
function cargarTable() {
   var datatable = $('#tablaClientes').DataTable({
      "ajax": {
         "url": 'consultaEstaClienteEnDetalleServicio',
         "type": 'GET',
         "dataSrc": "",
         "dataType": "json",
         "headers": {"Authorization": "Bearer " + storage},
         "error": function (jqXHR) {
            alertify.notify("Error en la vista de la Tabla" + jqXHR.statusText + " " + jqXHR.responseText, 'error', 10, null);
         }
      },
      "columns": [
         {"data": "username"},
         {"data": "apellidoPaterno"},
         {"data": "apellidoMaterno"}, ],
      "columnDefs": [{
            "targets": 3,
            "bSortable": false, //no permite la ordenación de columnas individuales.
            "render": function () {
               return '<button type="button" id="editar" class="editar edit-modal btn btn-outline-primary botonEditar"><span class="fa fa-check"></span><span class="hidden-xs"></span></button>';
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
         $('#val').val(data.username + " " + data.apellidoPaterno + " " + data.apellidoMaterno);
         $('#valId').val(data.idCliente);
         $("#myModalCliente").modal('hide');//ocultamos el modal
      });
   }
   editar("#tablaClientes tbody", datatable);
}


function cargarTableDetalleServicio() {
   var datatable = $('#tablaDetalles').DataTable({
      "ajax": {
         "url": 'listaDetalleServicio',
         "type": 'GET',
         "dataSrc": "",
         "dataType": "json",
         "headers": {"Authorization": "Bearer " + storage}
      },
      "columns": [
         {"data": "cliente.username"},
         {"data": "cliente.apellidoPaterno"},
         {"data": "cliente.apellidoMaterno"},
         {"data": "numeroServicio"},
         {"data": "periodo.corteLuz"},
         {"data": "periodo.limitePago"},
         {"data": "comunidad.direccion"},
         {"data": "totalPago"}],
      "columnDefs": [{
            "targets": 8,
            "bSortable": false, //no permite la ordenación de columnas individuales.
            "render": function () {
               return '<button type="button" id="editar" class="editar edit-modal btn btn-outline-primary botonEditar"><span class="fa fa-pen"></span><span class="hidden-xs"></span></button>';
            }
         }, {
            "targets": 9,
            "data": null, //Si desea pasar datos que ya tiene para que cada seleccion sea diferente num
            "bSortable": false,
            "mRender": function (o) {
               return '<a class="btn btn-outline-danger" onclick="dialogEliminar(' + o.idDetalleServicio + ')" type="button"><span class="fas fa-trash-alt"></span><span class="hidden-xs"></span></a>';
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
         $('#myModalActualizar').modal('show');

         $("#valIds").val(data.idDetalleServicio);
         $("#valIdCliente").val(data.cliente.idCliente);
         $("#valNombre").val(data.cliente.username);
         $("#valAp").val(data.cliente.apellidoPaterno);
         $("#valAm").val(data.cliente.apellidoMaterno);
         $("#txtNumTotalPagos").val(data.totalPago);
         $("#txtNumServicios").val(data.numeroServicio);

         // first().focus() es para cuando abras el modal se dirija el puntero al campo que indicas
         $("#valIds").first().focus();
         $("#txtNumTotalPagos").first().focus();
      });
   }
   editar("#tablaDetalles tbody", datatable);
}


function dialogEliminar(id_detalle_servicio) {
   let confirm = alertify.confirm('Eliminar Producto', 'Desea eliminar el registro ?', null, null).set('labels', {
      ok: 'Si',
      cancel: 'Cancelar'
   });
   confirm.set({
      transition: 'slide'
   });

   confirm.set('onok', function () {
      eliminarCliente(id_detalle_servicio);
   });

   confirm.set('oncancel', function () { //llama al pulsar botón negativo
      alertify.error('Cancelo eliminar su registro', 3);
   });
}

function eliminarCliente(id_detalle_servicio) {
   var id_p = id_detalle_servicio;
   let json = {};
   json.idDetalleServicio = id_p;
   $.ajax({
      type: 'DELETE',
      contentType: 'application/json',
      dataType: 'json',
      url: 'deleteDetalleService',
      data: JSON.stringify(json),
      headers: {"Authorization": "Bearer " + storage},
      success: function (data) {
         alertify.notify("Se elimino correctamente", 'success', 10, null);
         $('#tablaDetalles').DataTable().ajax.reload();
         location.reload(true);
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