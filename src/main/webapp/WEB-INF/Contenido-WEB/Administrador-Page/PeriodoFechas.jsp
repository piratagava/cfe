<%--
	  User: hernan
	  Date: 19/10/20
	  Time: 15:46
	  To change this template use File | Settings | File Templates.
	--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html data-ng-app="AltaPeriodo" data-ng-controller="controllerPeriodo">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="author" content="Hernan García Valladares">
<title>Página del Administrador</title>

<!-- Importamos las librerias de Java-Core y JSP-->
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/include.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/bootstrap.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/angular.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/dashboarStyle.jsp"%>
<%@ include
	file="/WEB-INF/Contenido-WEB/Recursos-Web/notificaciones.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/fontawesome.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/dataTable.jsp"%>
<%@ include
	file="/WEB-INF/Contenido-WEB/Recursos-Web/periodoFechasDatajs.jsp"%>
<link rel="stylesheet" href="css_global/login.css" />

<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>

</head>
<body>

	<!-- LLamo el menu Logout y barra principal de ecabezado de Admin -->
	<jsp:include page="../Recursos-Web/NavarLogout.jsp"></jsp:include>

	<div class="container-fluid">
		<div class="row">

			<!-- LLamo el menu principal de Admin -->
			<jsp:include page="../Recursos-Web/MenuPricipalAdministrador.jsp"></jsp:include>

			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">

				<!--Para Cliente boton guardar y listar lado derecho del encabezado-->
				<div id="encabezadoPeriodoFechas">
					<div
						class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
						<h1 class="h2">Periodo de Fechas</h1>
						<div class="btn-toolbar mb-2 mb-md-0">
							<div class="btn-group mr-2">
								<button type="button" class="btn btn-outline-success"
									data-ng-click="openAgregarPeriodo()">Agregar nuevo</button>
							</div>
							<div class="btn-group mr-2">
								<button id="btnShowTable"
									data-ng-click="openListarPeriodoFechas()" type="button"
									class="btn btn-outline-success">Listar Fechas</button>
							</div>
							<div class="btn-group mr-2">
								<button id="btnHideTable"
									data-ng-click="closeListarPeriodoFechas()" type="button"
									class="btn btn-outline-success">Cerrar Lista</button>
							</div>
							<div class="btn-group mr-2">
								<button type="button" onclick="doLogout()"
									class="btn btn-outline-success" id="logoutButton">Cerrar
									Sesion</button>
							</div>
						</div>
					</div>
				</div>

				<!-- Formulario para llenar infomarcion a guardar -->
				<div id="formPeriodoGuardar">
					<div class="card-body">
						<form id="periodoForm">
							<div class="form-group">
								<input id="txtid" hidden="true" type="text" class="form-control"
									placeholder="Id" data-ng-model="periodo.id_periodo"
									required="required"
									pattern="^[A-Z\u00C0-\u00DCa-z\u00E0-\u00FC0-9\s]{1,99}$">
							</div>

							<div class="form-group">
								<label for="start">Limite de Pago:</label> <input type="date"
									id="txtLimitePago" name="start" ng-model="fecha"
									max="2021-12-31" required="true" />
							</div>

							<div class="form-group">
								<label for="startPago">Corte de Luz:</label> <input type="date"
									id="txtCorteLuz" name="startPago" ng-model="pago"
									max="2021-12-31" required="true"  />
							</div>

							<br>
							<button type="submit" class="btn btn-primary"
								data-ng-click="guardarDatosPeriodo()">Guardar Datos</button>
							<button type="submit" class="btn btn-primary"
								data-ng-click="cerrarFormularioPeriodo()">Cerrar</button>

						</form>
					</div>
				</div>

				<!--Tabla para Mostar clientes-->
				<div id="tablePricipal">
					<div class="card-body">
						<div class="table-responsive">
							<table id="tablaPeriodo" class="table table-hover"
								style="width: 100%;">
								<thead valign="top" class="thead-dark">
									<tr valign="top">
										<th align="center">Id Periodo</th>
										<th align="center">Fecha Limite de Pago</th>
										<th align="center">Corte de Luz</th>
										<th align="center">Actualizar</th>
										<th align="center">Eliminar</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>

				<!-- Modal para actualizar Datos cliente -->
				<div class="modal fade" id="myModalPeriodo" tabindex="-1"
					role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="modalLabel">Actualizar Fechas</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>

							<div class="modal-body">
								<form id="periodoForm2">
									<div class="form-group">
										<label for="recipient-name" class="col-form-label">ID
											Periodo: </label> <input id="txtidp" type="text" class="form-control"
											placeholder="Id" data-ng-model="periodo.id_periodo"
											required="required"
											pattern="^[A-Z\u00C0-\u00DCa-z\u00E0-\u00FC0-9\s]{1,99}$"
											disabled="disabled">
									</div>

									<div class="form-group">
										<label for="start">Limite de Pago:</label> <input id="txtPago"
											type="date" name="input" ng-model="fecha2" max="2021-12-31"
											required />
									</div>

									<div class="form-group">
										<label for="startPago">Corte de Luz:</label> <input
											id="txtCorte" type="date" name="input" ng-model="pago2"
											max="2021-12-31" required="required" />
									</div>

								</form>
							</div>

							<div class="modal-footer">
								<button type="submit" class="btn btn-primary"
									data-ng-click="actualizarDatosPeriodo()">Actualizar
									Datos</button>
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Cancelar</button>
							</div>
						</div>
					</div>
				</div>
				<!-- Termina div del Modal -->

			</main>
		</div>
	</div>
</body>
</html>
