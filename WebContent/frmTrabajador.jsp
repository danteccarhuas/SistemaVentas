<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>.: Sistema Ventas - Mantenimiento Trabajador</title>
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
							Mantenimiento Trabajador
						</h1>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="panel with-nav-tabs panel-primary">
							<div class="panel-heading">
								<ul class="nav nav-tabs">
									<li class="active"><a href="#tab1primary" id="tab1"
										data-toggle="tab">Consulta Trabajador</a></li>
									<li id="eventotab2primary" class="disabled"><a id="tab2"
										href="#tab2primary" data-toggle="tab">Datos Trabajador</a></li>
								</ul>
							</div>
							<div class="panel-body">
								<div class="tab-content">
									<div class="tab-pane fade in active" id="tab1primary">
										<div class="row">
											<div class="form-group">
												<label class="col-lg-2" for="txt_codigotrabajador_buscar">codigo
													Trabajador:</label>
												<div class="col-lg-2">
													<input class="form-control" type="text"
														id="txt_codigotrabajador_buscar"
														name="txt_codigotrabajador_buscar"
														placeholder="Codigo trabajador" />
												</div>

												<label class="col-lg-1" for="txt_dni_buscar">DNI:</label>
												<div class="col-lg-3">
													<input class="form-control" type="text" id="txt_dni_buscar"
														name="txt_dni_buscar" placeholder="DNI" />
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
												<label class="col-lg-2" for="txt_nombres_buscar">Nombres:</label>
												<div class="col-lg-6">
													<input class="form-control" type="text"
														id="txt_nombres_buscar" name="txt_nombres_buscar"
														placeholder="Nombres" />
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
																	<th>Codigo Trab.</th>
																	<th>nombres</th>
																	<th>Correo</th>
																	<th>DNI</th>
																	<th>Dirección</th>
																	<th>Teléfono</th>
																	<th>Tienda</th>
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
										<form class="form-horizontal" id="frm_Trabajador">
											<input type="hidden" id="hiddencodtrab" value="0"
												name="hiddencodtrab" /> <input type="hidden"
												id="hiddenindaccion" name="hiddenindaccion" value="0" />
											<!-- 
										hiddenindaccion : 1 Nuevo
										hiddenindaccion : 2 Modificar
										 -->

											<div class="row">
												<div class="col-lg-5">
													<div class="form-group">

														<label class="control-label col-lg-5" for="txt_cod_trab_guardar">Cod.
															Trab:</label>
														<div class="col-lg-7">
															<input class="form-control" type="text"
																id="txt_cod_trab_guardar" name="txt_cod_trab_guardar"
																disabled="disabled" />
														</div>
													</div>
												</div>

												<div class="col-lg-3">
													<div class="form-group">
														<label class="control-label col-lg-3" for="txt_nombres">Nombres:</label>
														<div class="col-lg-9">
															<input class="form-control" type="text" id="txt_nombres"
																name="txt_nombres" placeholder="Nombres" />
														</div>
													</div>

												</div>

												<div class="col-lg-4">
													<div class="form-group">

														<label class="control-label col-lg-3" for="txt_apellidos">Apellidos:</label>
														<div class="col-lg-9">
															<input class="form-control" type="text" id="txt_apellidos"
																name="txt_apellidos" placeholder="Apellidos" />
														</div>
													</div>

												</div>
											</div>

											<div class="row">

												<div class="col-lg-5">
													<div class="form-group">


														<label class="control-label col-lg-5" for="txt_correo">Email:</label>
														<div class="col-lg-7">
															<input class="form-control" type="text"
																id="txt_correo" name="txt_correo"
																placeholder="Email" />
														</div>

													</div>
												</div>

												<div class="col-lg-3">
													<div class="form-group">
														<label class="control-label col-lg-3" for="txt_telefono">Teléfono:</label>
														<div class="col-lg-9">
															<input class="form-control" type="text" id="txt_telefono"
																name="txt_telefono" placeholder="Teléfono" />
														</div>
													</div>
												</div>
												<div class="col-lg-4">
													<div class="form-group">
														<label class="control-label col-lg-3" for="txt_dni">DNI:</label>
														<div class="col-lg-9">
															<input class="form-control" type="text" id="txt_dni"
																name="txt_dni" placeholder="DNI" />
														</div>
													</div>

												</div>
											</div>
											<div class="row">
												<div class="col-lg-5">
													<div class="form-group">
														<label class="control-label col-lg-5" for="txt_fec_nac">Fecha Nac.:</label>
														<div class="col-lg-7">
															<div class="input-group input-append date"
																id="datePicker">
																<input type="text" class="form-control"
																	id="txt_fec_nac" data-date-format="YYYY-MM-DD"
																	name="txt_fec_nac" readonly="readonly" /> <span
																	class="input-group-addon add-on span-datetime"><span
																	class="glyphicon glyphicon-calendar"></span></span>
															</div>
														</div>
													</div>
												</div>

												<div class="col-lg-3">
													<div class="form-group">
														<label class="control-label col-lg-3" for="txt_direccion">Dirección:</label>
														<div class="col-lg-9">
															<input class="form-control" type="text" id="txt_direccion"
																name="txt_direccion" placeholder="Dirección" />
														</div>
													</div>
												</div>

												<div class="col-lg-4">
													<div class="form-group">
														<label class="control-label col-lg-3" for="cbo_estado">Estado:</label>
														<div class="col-lg-9">
															<select class="form-control" name="cbo_estado" id="cbo_estado">
															</select>
														</div>
													</div>

												</div>
											</div>
											<div class="row">
												<div class="col-lg-5">
													<div class="form-group">
														<label class="control-label col-lg-5" for="cbo_tienda">Tienda:</label>
														<div class="col-lg-7">
															<select class="form-control" name="cbo_tienda"
																id="cbo_tienda">
															</select>
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
<script src="js/function-trabajador.js"></script>
</html>