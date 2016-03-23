<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>.: Sistema Ventas - Mantenimiento Marca</title>
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
							Mantenimiento Marca
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
										data-toggle="tab">Consulta Marca</a></li>
									<li id="eventotab2primary"><a id="tab2"
										href="#tab2primary" data-toggle="tab">Datos Marca</a></li>
								</ul>
							</div>
							<div class="panel-body">
								<div class="tab-content">
									<div class="tab-pane fade in active" id="tab1primary">
										<div class="row">
											<div class="form-group">
												<label class="col-lg-1" for="nombre">codigo:</label>
												<div class="col-lg-2">
													<input class="form-control" type="text"
														id="txt_codigoprov_buscar"
														name="txt_codigoproveedor_buscar"
														placeholder="Codigo proveedor" />
												</div>

											
												<label class="col-lg-1" for="nombre">Descripcion:</label>
												<div class="col-lg-4">
													<input class="form-control" type="text"
														id="txt_Descripcion_buscar" name="txt_Descripcion_buscar"
														placeholder="Descripcion" />
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
										
										<br>

										<div class="row">
											<div class="form-group">
												<div class="col-lg-12">
													<div class="table-responsive">
														<table id="miTabla" class="table table-bordered">
															<thead>
																<tr class="">
																	<th>Codigo Marca.</th>
																	<th>Descripcion</th>
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
												<div class="col-lg-4">
													<div class="form-group">

														<label class="control-label col-lg-5" for="nombre">Codigo Marca
															:</label>
														<div class="col-lg-7">
															<input class="form-control" type="text"
																id="txt_codigo_guardar" name="txt_codigo_guardar"
																disabled="disabled" />
														</div>
													</div>
												</div>

												<div class="col-lg-3">
													<div class="form-group">
														<label class="control-label col-lg-4" for="nombre">Descripcion:</label>
														<div class="col-lg-9">
															<input class="form-control" type="text" id="txt_descripcion_guardar"
																name="txt_descripcion_guardar" placeholder="Descripcion" />
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

		</div>
		<!-- /.row -->
	</div>
</body>
</html>