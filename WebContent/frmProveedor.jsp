<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>.: Sistema Ventas - Mantenimiento Proveedor</title>
</head>
<body>

	<div id="wrapper">
		<jsp:include page="MenuPrincipal.jsp"></jsp:include>

		<div id="page-wrapper">
			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							Mantenimiento Proveedor
							<!--  <small>Statistics Overview</small> -->
						</h1>
						<!-- <ol class="breadcrumb">
							<li class="active"><i class="fa fa-dashboard"></i> Dashboard
							</li>
						</ol> -->
					</div>
				</div>

				<div class="row">

					<div class="col-lg-12">
						<div class="panel with-nav-tabs panel-primary">
							<div class="panel-heading">
								<ul class="nav nav-tabs">
									<li class="active"><a href="#tab1primary"
										data-toggle="tab">Consulta Proveedor</a></li>
									<li><a href="#tab2primary" data-toggle="tab">Datos
											Proveedor</a></li>
									<!-- <li><a href="#tab3primary" data-toggle="tab">Primary 3</a></li>
                            <li class="dropdown">
                                <a href="#" data-toggle="dropdown">Dropdown <span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="#tab4primary" data-toggle="tab">Primary 4</a></li>
                                    <li><a href="#tab5primary" data-toggle="tab">Primary 5</a></li>
                                </ul>
                            </li> -->
								</ul>
							</div>
							<div class="panel-body">
								<div class="tab-content">
									<div class="tab-pane fade in active" id="tab1primary">
										<div class="row">
											<div class="form-group">
												<label class="col-lg-1" for="nombre">RUC:</label>
												<div class="col-lg-2">
													<input class="form-control" type="text" id="ruc"
														name="txt_ruc" placeholder="Ruc" />
												</div>

												<label class="col-lg-1" for="nombre">Razón Social:</label>
												<div class="col-lg-5">
													<input class="form-control" type="text" id="razonsocial"
														name="txt_razonsocial" placeholder="Razón Social" />
												</div>

												<label class="col-lg-1" for="estado">Estado:</label>
												<div class="col-lg-2">
													<select class="form-control" id="opcion">
														<option value="">Activo #1</option>
														<option value="">Desactivo #2</option>
													</select>
												</div>
											</div>

										</div>
										<br>
										<div class="row">
											<div class="form-group">
												<div class="col-lg-12">
													<div class="table-responsive">
														<table class="table table-bordered">
															<tr class="">
																<th># ID</th>
																<th>Nombre</th>
																<th>Usuario</th>
																<th># ID</th>
																<th>Nombre</th>
																<th>Usuario</th>
															</tr>
															<tr>
																<td>1</td>
																<td>Segundo</td>
																<td>mike</td>
																<td>1</td>
																<td>Segundo</td>
																<td>mike</td>
															</tr>
															<tr>
																<td>2</td>
																<td>Segundo</td>
																<td>mike</td>
																<td>2</td>
																<td>Segundo</td>
																<td>mike</td>
															</tr>
															<tr>
																<td>3</td>
																<td>Segundo</td>
																<td>mike</td>
																<td>3</td>
																<td>Segundo</td>
																<td>mike</td>
															</tr>

														</table>
													</div>
												</div>
											</div>
										</div>
									</div>

									<div class="tab-pane fade" id="tab2primary">


										<br>
										<form class="form-horizontal" action="">
											<div class="form-group">

												<label class="control-label col-lg-1" for="nombre">Razon
													Social:</label>
												<div class="col-lg-2">
													<input class="form-control" type="text" id="razonsocial"
														name="txt_ruc" placeholder="Razón Social" />
												</div>


												<label class="control-label col-lg-1" for="nombre">Ruc:</label>
												<div class="col-lg-2">
													<input class="form-control" type="text" id="ruc"
														name="txt_razonsocial" placeholder="Ruc" />
												</div>

												<label class="control-label col-lg-1" for="nombre">Email:</label>
												<div class="col-lg-2">
													<input class="form-control" type="text" id="correo"
														name="txt_correo" placeholder="Email" />
												</div>


												<label class="control-label col-lg-1" for="nombre">Contacto:
												</label>
												<div class="col-lg-2">
													<input class="form-control" type="text" id="correo"
														name="txt_correo" placeholder="Contacto" />
												</div>

											</div>
											<div class="form-group">
												<label class="control-label col-lg-1" for="nombre">Fax:</label>
												<div class="col-lg-2">
													<input class="form-control" type="text" id="fax"
														name="txt_fax" placeholder="Fax" />
												</div>


												<label class="control-label col-lg-1" for="nombre">Telefono:</label>
												<div class="col-lg-2">
													<input class="form-control" type="text" id="ruc"
														name="txt_telefono" placeholder="telefono" />
												</div>

												<label class="control-label col-lg-1" for="nombre">Celular:</label>
												<div class="col-lg-2">
													<input class="form-control" type="text" id="celular"
														name="txt_celular" placeholder="Celular" />
												</div>


												<label class="control-label col-lg-1" for="nombre">Sitio
													Web: </label>
												<div class="col-lg-2">
													<input class="form-control" type="text" id="sitioweb"
														name="txt_sitioweb" placeholder="Sitio Web" />
												</div>

											</div>

											
											<div class="form-group">
												<label class="control-label col-lg-1" for="nombre">Departamento:</label>
												<div class="col-lg-2">
													<select class="form-control" id="opcion">
														<option value="">Departamento #1</option>
														<option value="">Departamento #2</option>
													</select>
												</div>


												<label class="control-label col-lg-1" for="nombre">Provincia:</label>
												<div class="col-lg-2">
													<select class="form-control" id="opcion">
														<option value="">Provincia #1</option>
														<option value="">Provincia #2</option>
													</select>
												</div>

												<label class="control-label col-lg-1" for="nombre">Distrito:</label>
												<div class="col-lg-2">
													<select class="form-control" id="opcion">
														<option value="">Distrito #1</option>
														<option value="">Distrito #2</option>
													</select>
												</div>


												<label class="control-label col-lg-1" for="nombre">Estado:</label>
												<div class="col-lg-2">
													<select class="form-control" id="opcion">
														<option value="">Activo #1</option>
														<option value="">Desactivo #2</option>
													</select>
												</div>

											</div>
											

											<div class="form-group">
												<label class="control-label col-lg-1" for="nombre">Dirección:</label>
												<div class="col-lg-5">
													<input class="form-control" type="text" id="direccion"
														name="txt_direccion" placeholder="Dirección" />
												</div>


												<label class="control-label col-lg-1" for="nombre">Referencia:</label>
												<div class="col-lg-5">
													<input class="form-control" type="text" id="ruc"
														name="txt_referencia" placeholder="Referencia" />
												</div>

												

											</div>

											<div class="form-group">
												<div class="col-md-2 col-md-offset-2">
													<button id="btn_enviar" class="btn btn-primary">Enviar</button>
												</div>
											</div>


										</form>

									</div>

								</div>
								<!--   <div class="tab-pane fade" id="tab3primary">Primary 3</div>
                        <div class="tab-pane fade" id="tab4primary">Primary 4</div>
                        <div class="tab-pane fade" id="tab5primary">Primary 5</div> -->
							</div>
						</div>
					</div>
				</div>

			</div>


			<!-- /.row -->
		</div>
		<!-- /.row -->
	</div>
	

</body>

<script src="js/function-proveedor.js"></script>
</html>