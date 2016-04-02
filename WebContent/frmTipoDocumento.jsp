<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>.: Sistema Ventas - Mantenimiento Tipo Documento</title>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="MenuPrincipal.jsp"></jsp:include>
		<div id="page-wrapper">
			<div class="container-fluid">
				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">Mantenimiento Tipo Documento</h1>

					</div>
				</div>

				<div class="row">
					<div class="col-lg-6">
						<div class="panel with-nav-tabs panel-primary">
							<div class="panel-heading">
								<ul class="nav nav-tabs">
									<li class="active"><a href="#tab1primary" id="tab1"
										data-toggle="tab">Consulta Tipo Documento</a></li>
									<li id="eventotab2primary" class="disabled"><a id="tab2"
										href="#tab2primary" data-toggle="tab">Datos Tipo Documento</a></li>
								</ul>
							</div>
							<div class="panel-body">
								<div class="tab-content">
									<div class="tab-pane fade in active" id="tab1primary">
										<div class="row">
											<div class="form-group">
												<label class="col-lg-2" for="nombre">Descripcion:</label>
												<div class="col-lg-5">
													<input class="form-control" type="text"
														id="txt_Descripcion_buscar" name="txt_Descripcion_buscar"
														placeholder="Descripcion" />
												</div>
												<div class="col-lg-5">
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
																	<th>Descripción</th>
																	<th>Abreviatura</th>
																	<th colspan="3" style="text-align: center;">Acción</th>
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
										<form class="form-horizontal" id="frm_tipodocumento">
											<input type="hidden" id="hiddencodTipodocumento" value="0"
												name="hiddencodTipodocumento" /> <input type="hidden"
												id="hiddenindaccion" name="hiddenindaccion" value="0" />
											<!-- 
										hiddenindaccion : 1 Nuevo
										hiddenindaccion : 2 Modificar
										 -->

											<div class="row">
												<div class="col-lg-12">
													<div class="form-group">

														<label class="control-label col-lg-3" for="nombre">Codigo
															:</label>
														<div class="col-lg-8">
															<input class="form-control" type="text"
																id="txt_codigo_guardar" name="txt_codigo_guardar"
																disabled="disabled" />
														</div>
													</div>
												</div>

												<div class="col-lg-12">
													<div class="form-group">
														<label class="control-label col-lg-3" for="nombre">Descripcion:</label>
														<div class="col-lg-8">
															<input class="form-control" type="text"
																id="txt_descripcion_guardar"
																name="txt_descripcion_guardar" placeholder="Descripcion" />
														</div>
													</div>
												</div>

												<div class="col-lg-12">
													<div class="form-group">
														<label class="control-label col-lg-3" for="nombre">Abreviatura:</label>
														<div class="col-lg-8">
															<input class="form-control" type="text"
																id="txt_Abreviatura_guardar"
																name="txt_Abreviatura_guardar" placeholder="Abreviatura" />
														</div>
													</div>
												</div>
											</div>
											<div class="form-group">
												<div class="col-md-12 col-md-offset-3">
													<button id="btn_salir" type="reset" class="btn btn-primary">Salir</button>
													<button id="btn_enviar" class="btn btn-primary">
														Guardar <span class="fa  fa-save"></span>
													</button>
												</div>
												<br> <br>
												<div class="col-md-12" id="mensajeAlerta"></div>
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

	<div class="modal fade" id="modalCondigurarCorrelativo" tabindex="-1"
		role="dialog">
		<div class="modal-dialog">
		<form class="form-horizontal" id="frm_correlativotipodocumento">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Configurar Correlativo</h4>
				</div>
				<div class="modal-body" id ="modalbodycorrelativotipodocumento">
					
					<input type="hidden" id="hiddencodTipodocumentocorrelativo" value="0"
												name="hiddencodTipodocumentocorrelativo" />
						<div class="row">
							<div class="col-lg-12">
								<div class="form-group">

									<label class="control-label col-lg-3" for="nombre">Tipo
										Doc. :</label>
									<div class="col-lg-8">
										<input class="form-control" type="text"
											id="txt_tipo_guardar" name="txt_tipo_guardar"
											readonly="readonly" />
									</div>
								</div>
							</div>

							<div class="col-lg-12">
								<div class="form-group">
									<label class="control-label col-lg-3" for="Serie">Serie:</label>
									<div class="col-lg-8">
										<input class="form-control" type="text"
											id="txt_Serie_guardar" name="txt_Serie_guardar"
											placeholder="Serie" />
									</div>
								</div>
							</div>

							<div class="col-lg-12">
								<div class="form-group">
									<label class="control-label col-lg-3" for="Correlativo">Correlativo:</label>
									<div class="col-lg-8">
										<input class="form-control" type="text"
											id="txt_Correlativo_guardar" name="txt_Correlativo_guardar"
											placeholder="Correlativo" />
									</div>
								</div>
							</div>
						</div>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button  class="btn btn-primary">
														Guardar <span class="fa  fa-save"></span>
													</button>				
				</div>
			</div>
			<!-- /.modal-content -->
			</form>
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

</body>
<script src="js/function-tipodocumento.js"></script>
</html>