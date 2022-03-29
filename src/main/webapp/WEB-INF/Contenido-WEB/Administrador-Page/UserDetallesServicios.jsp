<%--  
  User: hernan
  Date: 19/10/20
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html data-ng-app="SearchCliente" data-ng-controller="controllerCliente">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="author" content="Hernan García Valladares">
<title>CFE Notification's</title>


<!-- Importamos las librerias de Java-Core y JSP-->
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/include.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/bootstrap.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/angular.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/dashboarStyle.jsp"%>
<%@ include
	file="/WEB-INF/Contenido-WEB/Recursos-Web/notificaciones.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/fontawesome.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/dataTable.jsp"%>
<script src="https://kit.fontawesome.com/64d58efce2.js"
	crossorigin="anonymous"></script>

<link rel="stylesheet" href="css_global/login.css" />
<script type="text/javascript" src="js/LoginCliente/client.js"></script>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/searchService.jsp"%>
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
			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
				<!--Para Cliente boton guardar y listar lado derecho del encabezado-->
				<div id="encabezadoCliente">
					<div
						class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
						<h1 class="h2">Servicio de consulta CFE</h1>
					</div>
				</div>

				<!-- Formulario para llenar infomarcion a guardar -->
				<div id="formClienteConsultar">
					<div class="card-body">
						<form id="clienteForm">
							<div class="form-group">
								<label for="Servicio">Numero de Servicio:</label> <input
									type="number" id="txtNumServicio"
									data-ng-model="numero_servicio" required="required" min="1"
									pattern="^[0-9]{1,15}$" />
							</div>

							<button type="submit" class="btn btn-success"
								data-ng-click="consultarDatosCliente(numero_servicio)">Consultar</button>

							<a href="#openModal">Cual es mi numero de Servicio ?</a> <a
								href="/index">Regresar</a>
						</form>
					</div>
				</div>


				<div id="openModal" class="modalDialog">
					<div>
						<a href="#close" title="Close" class="closed">X</a>
						<h2>No sabes cual es tu numero de servicio ?</h2>
						<p style="color: red">Busca este numero en tu recibo de luz en
							el apartado que señala No.Servicio con ese digito podras
							consultar !!</p>
					</div>
				</div>



				<div id="detallesService">
					<div class="row g-5" align="center">
						<div class="col-md-5 col-lg-4 order-md-last"
							style="margin-left: 70px">
							<h4
								class="d-flex justify-content-between align-items-center mb-3">
								<span class="text-success">Servicio de Luz</span> <span
									class="badge bg-success rounded-pill">CFE</span>
							</h4>
							<ul class="list-group mb-3">
								<li class="list-group-item d-flex justify-content-between lh-sm">
									<div>
										<h6 class="my-0">Nombre del cliente:</h6>
									</div> <span class="cliente" data-ng-repeat="x in cliente">{{x.username}}</span>
								</li>
								<li class="list-group-item d-flex justify-content-between lh-sm">
									<div>
										<h6 class="my-0">Apellido Paterno:</h6>
									</div> <span class="cliente" data-ng-repeat="x in cliente">{{x.apellidoPaterno}}</span>
								</li>
								<li class="list-group-item d-flex justify-content-between lh-sm">
									<div>
										<h6 class="my-0">Apellido Materno:</h6>
									</div> <span class="cliente" data-ng-repeat="x in cliente">{{x.apellidoMaterno}}</span>
								</li>
								<li class="list-group-item d-flex justify-content-between lh-sm">
									<div>
										<h6 class="my-0">Corte de Luz:</h6>
									</div> <span class="cliente" data-ng-repeat="x in cliente">{{x.corteLuz}}</span>
								</li>
								<li class="list-group-item d-flex justify-content-between lh-sm">
									<div>
										<h6 class="my-0">Limite de Pago:</h6>
									</div> <span class="cliente" data-ng-repeat="x in cliente">{{x.limitePago}}</span>
								</li>

								<li class="list-group-item d-flex justify-content-between lh-sm">
									<div class="text-success">
										<h6 class="my-0">Total a Pagar:</h6>

									</div>
									<span style="margin-right: 35px" class="cliente">$
										{{clientePago}}</span>
								</li>

								<li
									class="list-group-item d-flex justify-content-between bg-light">
									<div class="text-danger">
										<h6 class="my-0">Aviso:</h6>
										<span>Favor de pasar a pagar con cambio de un horario
											de 9 AM asta 5 PM muchas gracias !!</span>
									</div>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<!-- Fin detalleService -->

			</main>
		</div>
	</div>
</body>
</html>
