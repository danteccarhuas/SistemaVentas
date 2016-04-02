<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>.: Sistema Ventas - Mantenimiento Cliente</title>
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
							Mantenimiento Cliente
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
									<li class="active"><a href="#tab1primary" id="tab1"
										data-toggle="tab">Consulta Cliente</a></li>
									<li id="eventotab2primary" class="disabled"><a id="tab2"
										href="#tab2primary" data-toggle="tab">Datos Cliente</a></li>
								</ul>
							</div>
							<div class="panel-body">
								<div class="tab-content">
									<div class="tab-pane fade in active" id="tab1primary">

										<div class="row">
											<div class="form-group">
												<label class="col-lg-1" for="nombre">Codigo:</label>
												<div class="col-lg-2">
													<input class="form-control" type="text"
														id="txt_codigo_buscar" name="txt_codigo_buscar"
														placeholder="Codigo" />
												</div>

												<label class="col-lg-1" for="nombre">Nombres:</label>
												<div class="col-lg-8">
													<input class="form-control" type="text"
														id="txt_nombre_apellido_buscar"
														name="txt_nombre_apellido_buscar"
														placeholder="Nombre y/o apellidos" />
												</div>
											</div>
										</div>
										<br>
										<div class="row">
											<div class="form-group">
												<label class="col-lg-1" for="cbo_tipopersona_buscar">Tipo
													Persona:</label>
												<div class="col-lg-2">
													<select class="form-control" name="cbo_tipopersona_buscar"
														id="cbo_tipopersona_buscar">

													</select>
												</div>
												<label class="control-label col-lg-1" for="txt_dni_buscar">DNI:</label>
												<div class="col-lg-2" id="etiquetadni">
													<input class="form-control" type="text" id="txt_dni_buscar"
														name="txt_dni_buscar" placeholder="DNI" />
												</div>
												<label class="control-label col-lg-1" for="txt_ruc_buscar">Ruc:</label>
												<div class="col-lg-2" id="etiquetaruc">
													<input class="form-control" type="text" id="txt_ruc_buscar"
														name="txt_ruc_buscar" placeholder="Ruc" />
												</div>
												<div class="col-lg-3">
													<button id="btn_buscar" class="btn btn-primary">
														Buscar <span class="fa fa-search"></span>
													</button>
													<button id="btn_nuevo" class="btn btn-primary">
														Nuevo <span class="fa fa-file-o"></span>
													</button>
												</div>
											</div>
										</div>
										<br>
										<div class="row">
											<div class="form-group">
												<div class="col-lg-12">
													<div class="table-responsive">
														<table id="miTabla" class="table table-bordered">
															<thead>
																<tr class="">
																	<th>Codigo</th>
																	<th>Nombres y Apellidos</th>																
																	<th>Tipo persona</th>
																	<th>Ruc</th>
																	<th>DNI</th>
																	<th>Genero</th>
																	<th>Celular</th>
																	<th colspan="2">Acción</th>
																</tr>
															</thead>
															<tbody id="rellenar">
															</tbody>
														</table>
														<div class="col-md-12 text-center">
															<ul class="pagination" id="paginador"></ul>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>

									<div class="tab-pane fade" id="tab2primary">
										<br>
										<form class="form-horizontal" id="frm_cliente">
											<input type="hidden" id="hiddencodcliente" value="0"
												name="hiddencodcliente" /> <input type="hidden"
												id="hiddenindaccion" name="hiddenindaccion" value="0" />
											<!-- 
										hiddenindaccion : 1 Nuevo
										hiddenindaccion : 2 Modificar
										 -->

											<div class="row">
												<div class="col-lg-5">
													<div class="form-group">

														<label class="control-label col-lg-5"
															for="txt_cod_trab_guardar">Cod. Cliente:</label>
														<div class="col-lg-7">
															<input class="form-control" type="text"
																id="txt_cod_cliente_guardar"
																name="txt_cod_cliente_guardar" disabled="disabled" />
														</div>
													</div>
												</div>

												<div class="col-lg-3">
													<div class="form-group">
														<label class="control-label col-lg-3"
															for="txt_nombre_guardar">Nombres:</label>
														<div class="col-lg-9">
															<input class="form-control" type="text"
																id="txt_nombre_guardar" name="txt_nombre_guardar"
																placeholder="Nombres" />
														</div>
													</div>

												</div>

												<div class="col-lg-4">
													<div class="form-group">

														<label class="control-label col-lg-3"
															for="txt_apellidos_guardar">Apellidos:</label>
														<div class="col-lg-9">
															<input class="form-control" type="text"
																id="txt_apellidos_guardar" name="txt_apellidos_guardar"
																placeholder="Apellidos" />
														</div>
													</div>

												</div>
											</div>

											<div class="row">

												<div class="col-lg-5">
													<div class="form-group">


														<label class="control-label col-lg-5"
															for="txt_correo_guardar">Email:</label>
														<div class="col-lg-7">
															<input class="form-control" type="text"
																id="txt_correo_guardar" name="txt_correo_guardar"
																placeholder="Email" />
														</div>

													</div>
												</div>

												<div class="col-lg-3">
													<div class="form-group">
														<label class="control-label col-lg-3"
															for="txt_telefono_guardar">Teléfono:</label>
														<div class="col-lg-9">
															<input class="form-control" type="text"
																id="txt_telefono_guardar" name="txt_telefono_guardar"
																placeholder="Teléfono" />
														</div>
													</div>
												</div>
												<div class="col-lg-4">
													<div class="form-group">
														<label class="control-label col-lg-3"
															for="txt_celular_guardar">Celular:</label>
														<div class="col-lg-9">
															<input class="form-control" type="text"
																id="txt_celular_guardar" name="txt_celular_guardar"
																placeholder="Celular" />
														</div>
													</div>

												</div>
											</div>
											<div class="row">
												<div class="col-lg-5">
													<div class="form-group">
														<label class="control-label col-lg-5"
															for="cbo_tipopersona">Tipo Persona :</label>
														<div class="col-lg-7">
															<select class="form-control" name="cbo_tipopersona"
																id="cbo_tipopersona">
															</select>
														</div>
													</div>
												</div>

												<div class="col-lg-3">
													<div class="form-group">
														<label class="control-label col-lg-3"
															for="txt_ruc_guardar">Ruc:</label>
														<div class="col-lg-9" id="etiquetaruc">
															<input class="form-control" type="text"
																id="txt_ruc_guardar" name="txt_ruc_guardar"
																placeholder="Ruc" />
														</div>
													</div>
												</div>

												<div class="col-lg-4">
													<div class="form-group">
														<label class="control-label col-lg-3"
															for="txt_dni_guardar">DNI:</label>
														<div class="col-lg-9" id="etiquetadni">
															<input class="form-control" type="text"
																id="txt_dni_guardar" name="txt_dni_guardar"
																placeholder="DNI" />
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-lg-5">
													<div class="form-group">
														<label class="control-label col-lg-5" for="nombre">Departamento:</label>
														<div class="col-lg-7">
															<select class="form-control" name="cbo_departamento"
																id="cbo_departamento">
															</select>
														</div>
													</div>
												</div>

												<div class="col-lg-3">
													<div class="form-group">
														<label class="control-label col-lg-3" for="nombre">Provincia:</label>
														<div class="col-lg-9">
															<select class="form-control" name="cbo_provincia"
																id="cbo_provincia">
																<option value="">- Seleccione -</option>
															</select>
														</div>
													</div>
												</div>

												<div class="col-lg-4">
													<div class="form-group">
														<label class="control-label col-lg-3" for="nombre">Distrito:</label>
														<div class="col-lg-9">
															<select class="form-control" name="cbo_distrito"
																id="cbo_distrito">
																<option value="">- Seleccione -</option>
															</select>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-lg-8">
													<div class="form-group">
														<label class="control-label col-lg-3 "
															for="txt_direccion_guarda">Dirección:</label>
														<div class="col-lg-9">
															<input class="form-control" type="text"
																id="txt_direccion_guarda" name="txt_direccion_guarda"
																placeholder="Dirección" />
														</div>
													</div>
												</div>

												<div class="col-lg-4">
													<div class="form-group">
														<label class="control-label col-lg-3" for="nombre">Estado:</label>
														<div class="col-lg-9">
															<select class="form-control" name="cbo_estado"
																id="cbo_estado">
															</select>
														</div>
													</div>
												</div>

											</div>
											<div class="row">
												<div class="col-lg-8">
													<div class="form-group">
														<label class="control-label col-lg-3"
															for="txt_referencia_guardar">Referencia:</label>
														<div class="col-lg-9">
															<textarea class="form-control"
																id="txt_referencia_guardar"
																name="txt_referencia_guardar" rows="4"
																placeholder="Referencia"></textarea>
														</div>
													</div>
												</div>
												<div class="col-lg-4">
													<div class="form-group">
														<label class="col-lg-3">Genero:</label>
														<div class="col-lg-9">
															<div class="radio">
																<label> <input type="radio" name="generoRadios"
																	id="generoRadios" value="1"> <!-- checked -->
																	Masculino
																</label>
															</div>
															<div class="radio">
																<label> <input type="radio" name="generoRadios"
																	id="generoRadios" value="2"> Femenino
																</label>
															</div>
															<div class="radio">
																<label> <input type="radio" name="generoRadios"
																	id="generoRadios" value="3"> Otros
																</label>
															</div>
														</div>
													</div>
												</div>

											</div>

											<div class="form-group">
												<div class="col-md-3 col-md-offset-1">
													<button id="btn_salir" type="reset" class="btn btn-primary">Salir</button>
													<button id="btn_enviar" class="btn btn-primary">
														Guardar <span class="fa  fa-save"></span>


													</button>

												</div>
												<div class="col-md-7" id="mensajeAlerta"></div>
											</div>
										</form>
									</div>
								</div>

							</div>
						</div>

					</div>
				</div>

			</div>

		</div>
		<!-- /.row -->
	</div>

	<!-- Modal Start here-->
	<div class="modal fade bs-example-modal-sm" id="ModalLoading"
		tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">
						<span class="glyphicon glyphicon-time"> </span>Por favor,
						espere....
					</h4>
				</div>
				<div class="modal-body">
					<div class="progress">
						<div
							class="progress-bar progress-bar-info progress-bar-striped active"
							style="width: 100%"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Mensaje de
						Confirmacion</h4>
				</div>
				<div class="modal-body">Esta seguro que desea eliminar el
					registro seleccionado?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-danger removeBtn">Remove</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal ends Here -->
</body>
<input type="hidden" id="hiddenvalueprovincia" value="0" />
<input type="hidden" id="hiddenvaluedistrito" value="0" />
<script src="js/function-cliente.js"></script>
</html>