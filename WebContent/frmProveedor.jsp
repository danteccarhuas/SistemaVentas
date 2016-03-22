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
									<li class="active"><a href="#tab1primary" id="tab1"
										data-toggle="tab">Consulta Proveedor</a></li>
									<li id="eventotab2primary"><a id="tab2"
										href="#tab2primary" data-toggle="tab">Datos Proveedor</a></li>
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
												<label class="col-lg-2" for="nombre">codigo
													Proveedor:</label>
												<div class="col-lg-2">
													<input class="form-control" type="text"
														id="txt_codigoprov_buscar"
														name="txt_codigoproveedor_buscar"
														placeholder="Codigo proveedor" />
												</div>

												<label class="col-lg-1" for="nombre">Ruc:</label>
												<div class="col-lg-3">
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
												<label class="col-lg-2" for="nombre">Razón Social:</label>
												<div class="col-lg-6">
													<input class="form-control" type="text"
														id="txt_razonsocial_buscar" name="txt_razonsocial_buscar"
														placeholder="Razón Social" />
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
																	<th>Codigo Prov.</th>
																	<th>Razón Social</th>
																	<th>Ruc</th>
																	<th>Correo</th>
																	<th>Telefono</th>
																	<th>Dirección</th>
																	<th>Contacto</th>
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
										<form class="form-horizontal" id="frm_Proveedor">
										<input type="hidden" id="hiddencodprov" value="0"  name="hiddencodprov"/>
										<input type="hidden" id="hiddenindaccion" name="hiddenindaccion" value="0" />
										<!-- 
										hiddenindaccion : 1 Nuevo
										hiddenindaccion : 2 Modificar
										 -->
										
											<div class="row">
												<div class="col-lg-5">
													<div class="form-group">

														<label class="control-label col-lg-5" for="nombre">Cod.
															Prov:</label>
														<div class="col-lg-7">
															<input class="form-control" type="text"
																id="txt_cod_prov_guardar" name="txt_cod_prov_guardar"
																disabled="disabled" />
														</div>
													</div>
												</div>

												<div class="col-lg-3">
													<div class="form-group">
														<label class="control-label col-lg-3" for="nombre">Ruc:</label>
														<div class="col-lg-9">
															<input class="form-control" type="text" id="txt_ruc"
																name="txt_ruc" placeholder="Ruc" />
														</div>
													</div>

												</div>

												<div class="col-lg-4">
													<div class="form-group">

														<label class="control-label col-lg-3" for="nombre">Email:</label>
														<div class="col-lg-9">
															<input class="form-control" type="text" id="txt_correo"
																name="txt_correo" placeholder="Email" />
														</div>
													</div>

												</div>
											</div>

											<div class="row">

												<div class="col-lg-5">
													<div class="form-group">


														<label class="control-label col-lg-5" for="nombre">Razon
															Social:</label>
														<div class="col-lg-7">
															<input class="form-control" type="text" id="txt_razon_social"
																name="txt_razon_social" placeholder="Razón Social" />
														</div>

													</div>
												</div>

												<div class="col-lg-3">
													<div class="form-group">
														<label class="control-label col-lg-3" for="nombre">Fax:</label>
														<div class="col-lg-9">
															<input class="form-control" type="text" id="txt_fax"
																name="txt_fax" placeholder="Fax" />
														</div>
													</div>
												</div>
												<div class="col-lg-4">
													<div class="form-group">
														<label class="control-label col-lg-3" for="nombre">Telefono:</label>
														<div class="col-lg-9">
															<input class="form-control" type="text" id="txt_telefono"
																name="txt_telefono" placeholder="telefono" />
														</div>
													</div>

												</div>
											</div>
											<div class="row">
												<div class="col-lg-5">
													<div class="form-group">
														<label class="control-label col-lg-5" for="nombre">Sitio
															Web: </label>
														<div class="col-lg-7">
															<input class="form-control" type="text" id="txt_sitioweb"
																name="txt_sitioweb" placeholder="Sitio Web" />
														</div>
													</div>
												</div>

												<div class="col-lg-3">
													<div class="form-group">
														<label class="control-label col-lg-3" for="nombre">Celular:</label>
														<div class="col-lg-9">
															<input class="form-control" type="text" id="txt_celular"
																name="txt_celular" placeholder="Celular" />
														</div>
													</div>
												</div>

												<div class="col-lg-4">
													<div class="form-group">
														<label class="control-label col-lg-3" for="nombre">Estado:</label>
														<div class="col-lg-9">
															<select class="form-control" name="cbo_estado"
																id="opcion">
																<option value="1">Activo</option>
																<option value="2">Desactivo</option>
															</select>
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
												<div class="col-lg-6">
													<div class="form-group">
														<label class="control-label col-lg-4 " for="nombre">Dirección:</label>
														<div class="col-lg-8">
															<input class="form-control" type="text" id="txt_direccion"
																name="txt_direccion" placeholder="Dirección" />
														</div>
													</div>
												</div>
												<div class="col-lg-6">
													<div class="form-group">
														<label class="control-label col-lg-2" for="nombre">Contacto:
														</label>
														<div class="col-lg-10">
															<input class="form-control" type="text" id="txt_contacto"
																name="txt_contacto" name="txt_contacto"
																placeholder="Contacto" />
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-lg-12">
													<div class="form-group">
														<label class="control-label col-lg-2" for="nombre">Referencia:</label>
														<div class="col-lg-10">
															<textarea class="form-control" id="txt_referencia"
																name="txt_referencia" rows="4" placeholder="Referencia"></textarea>
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

<input type="hidden" id="hiddenvalueprovincia" value="0" />
<input type="hidden" id="hiddenvaluedistrito" value="0" />

<script src="js/function-proveedor.js"></script>
</html>