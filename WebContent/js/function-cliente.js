$(document)	.ready(function(e) {
	
	$("#cbo_tipopersona").change(function(){
		var valor= this.options[this.selectedIndex].value;	
		validarRucDNI(valor);
	});
	
	function validarRucDNI(valor){
		$('#frm_cliente').bootstrapValidator('resetField', $('#txt_ruc_guardar'));
		$('#frm_cliente').bootstrapValidator('resetField', $('#txt_dni_guardar') );
		if (valor == 1){			
			$('#txt_dni_guardar').prop( "disabled", false ).removeClass('disabled');
			$('#txt_ruc_guardar').prop( "disabled", true ).addClass('disabled');		   
		}else if(valor == 2){			
			$('#txt_dni_guardar').prop( "disabled", true ).removeClass('disabled');
			$('#txt_ruc_guardar').prop( "disabled", false ).addClass('disabled');
		}
	}
	$('#btn_nuevo').on('click', function () {		
		 $('#tab1').prop( "disabled", true ).addClass('disabled');
		 $('#tab2').prop( "disabled", false ).removeClass('disabled');
		 $('#eventotab2primary').prop( "disabled", false ).removeClass('disabled');/*Quitamos el disabled del tab eventotab2primary*/
		 $('.nav-tabs > .active').prop( "disabled", true ).addClass('disabled').next('li').find('a').trigger('click');		
		 $('#hiddenindaccion').val(1); 
	});	
	
	$('#btn_salir').on('click', function () {
		$("#frm_cliente").data('bootstrapValidator').resetForm(true);/*Limpiamos todos los controles del formulario frm_Proveedor */
		$('#tab1').prop( "disabled", false ).removeClass('disabled');
		$('#tab2').prop( "disabled", true ).addClass('disabled');
		$('.nav-tabs > .active').prop( "disabled", true ).addClass('disabled').prev('li').find('a').trigger('click');  
		$("#mensajeAlerta").html("");
		$("#paginador").html("");/*Limpiar los numero de paginacion*/
		initGrilla();
		$("#btn_enviar").prop("disabled", false);
	});
	$('#btn_buscar').on('click', function () {
		$("#paginador").html("");/*Limpiar los numero de paginacion*/
		$('#ModalLoading').modal('show');
		initGrilla();		
		$('#ModalLoading').modal('hide');
	});
	/*Metodo para eliminar proveedores*/
	$(document).on("click","#btn_modaleliminar",function(e) {
		e.preventDefault();		
		$('#hiddencodcliente').val($(this).data('id'));
		$("#modalRemove").modal({
			keyboard : false
		});
	});
	
	$(".removeBtn").click(function(e){
		$("#modalRemove").modal("hide");		
		var  codcliente=$('#hiddencodcliente').val();
		$.ajax({
			url : 'cliente?metodo=EliminarCategoria',
			type : 'post',
			data : {codcliente : codcliente},
			dataType : 'json',
			success : function(result) {				
				$("#paginador").html("");/*Limpiar los numero de paginacion*/
				initGrilla();
			}
		});
		
	});
	/*Metodo para modificar Marca*/
	$(document).on("click","#btn_editar",function(e) {			
		e.preventDefault();		
		$('#hiddencodcliente').val($(this).data('id'));
		$('#hiddenindaccion').val(2); 		
		var codcliente = $('#hiddencodcliente').val();		
		$.ajax({
			url : 'cliente?metodo=ObtenerCliente',
			type : 'post',
			data : {codcliente:codcliente},
			dataType : 'json',
			success : function(result) {
				var data1=result[0];	
				$('#tab1').prop( "disabled", true ).addClass('disabled');
				$('#tab2').prop( "disabled", false ).removeClass('disabled');
				$('#eventotab2primary').prop( "disabled", false ).removeClass('disabled');/*Quitamos el disabled del tab eventotab2primary*/
				$('.nav-tabs > .active').prop( "disabled", true ).addClass('disabled').next('li').find('a').trigger('click');			
				$("#tab2primary #txt_cod_cliente_guardar").val(data1.codigocliente);
				$("#tab2primary #txt_nombre_guardar").val(data1.nombres);	
				$("#tab2primary #txt_apellidos_guardar").val(data1.apellidos);	
				$("#tab2primary #txt_correo_guardar").val(data1.correo);	
				$("#tab2primary #txt_telefono_guardar").val(data1.telefono);	
				$("#tab2primary #txt_celular_guardar").val(data1.celular);	
				$("#tab2primary #cbo_tipopersona").val(data1.tipopersona.valor);
				$("#tab2primary #txt_ruc_guardar").val(data1.ruc);	
				$("#tab2primary #txt_dni_guardar").val(data1.dni);	
				$("#tab2primary #txt_direccion_guarda").val(data1.direccion);	
				$("#tab2primary #txt_referencia_guardar").val(data1.referencia);
				$("#tab2primary #cbo_estado").val(data1.estado.valor);		
				$("#tab2primary #cbo_departamento").val(data1.ubigeo.departamento.iddepar).change();
				$("#hiddenvalueprovincia").val(data1.ubigeo.provincia.idprov);
				$("#hiddenvaluedistrito").val(data1.ubigeo.distrito.iddist);
				programarCargaPronvincia();
				$("#tab2primary input[name=generoRadios][value='"+ data1.sexo.valor +"']").prop("checked",true);
				validarRucDNI($('#cbo_tipopersona').val());
			}
		});
	});
	
	/*Metodo para setar los combo de ubigeo*/
	function programarCargaPronvincia(){	
		    setTimeout(function(){setearProvincia();},1000); // 1000ms = 1s
		}
	function setearProvincia(){
			$("#tab2primary #cbo_provincia").val($("#hiddenvalueprovincia").val());
			CargarComboDependiente($("#hiddenvalueprovincia").val(), 'cbo_distrito','LoadComboDistrito');
			programarDistrito();
		}	
	function programarDistrito(){	
	    setTimeout(function(){setearDistrito();},1000); // 1000ms = 1s
	}
	function setearDistrito(){
		$("#tab2primary #cbo_distrito").val($("#hiddenvaluedistrito").val());
	}

	/* Valida las etiquedas del formulario */
	$("#frm_cliente").bootstrapValidator({
				message : 'This value is not valid',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				"txt_nombre_guardar" : {
					validators : {
						notEmpty : {
							message : 'Ingrese Descripcion por favor.'
						}
					}
				},
				"txt_apellidos_guardar" : {
					validators : {
						notEmpty : {
							message : 'Ingrese Descripcion por favor.'
						}
					}
				},
				"txt_correo_guardar" : {
					validators : {
						notEmpty : {
							message : 'Ingrese Descripcion por favor.'
						},
						emailAddress : {
							message : 'El Correo Electronico ingresado no es válida.'
						}
					}
				},
				"txt_telefono_guardar" : {
					validators : {
						notEmpty : {
							message : 'Ingrese telefono por favor.'
						},
						stringLength : {
							min : 1,
							max : 7,
							message : 'Teléfono tiene 07 cifras máximo.'
						},
						integer : {
							message : 'Ingrese solo números.'
						}
					}
				},
				"txt_celular_guardar" : {
					validators : {
						notEmpty : {
							message : 'Ingrese celular por favor.'
						},						
						stringLength : {
							min : 1,
							max : 9,
							message : 'El Celular tiene 09 cifras máximo.'
						},
						integer : {
							message : 'Ingrese solo números.'
						}
					}
				},
				"txt_ruc_guardar" : {
					validators : {
						notEmpty : {
							message : 'Ingrese numero de ruc por favor.'
						},
						stringLength : {
							min : 1,
							max : 11,
							message : 'El RUC tiene 11 cifras maximo.'
						},
						integer : {
							message : 'Ingrese solo numeros.'
						}
					}
				},
				"txt_dni_guardar" : {
					validators : {
						notEmpty : {
							message : 'Ingrese numero de dni por favor.'
						},
						stringLength : {
							min : 1,
							max : 8,
							message : 'El DNI tiene 08 cifras maximo.'
						},
						integer : {
							message : 'Ingrese solo numeros.'
						}
					}
				},
				"txt_direccion_guarda" : {
					validators : {
						notEmpty : {
							message : 'Ingrese direccion por favor.'
						}
					}
				},
				"txt_referencia_guardar" : {
					validators : {
						notEmpty : {
							message : 'Ingrese una referencia por favor.'
						},
						stringLength : {
							min : 1,
							max : 255,
							message : 'Ingrese 255 caracteres como maximo.'
						}
					}
				},
				"cbo_tipopersona" : {
					validators : {
						notEmpty : {
							message : 'Seleccione tipo de persona por favor.'
						}
					}
				},
				"cbo_departamento" : {
					validators : {
						notEmpty : {
							message : 'Seleccione un Departamento por favor.'
						}
					}
				},
				"cbo_provincia" : {
					validators : {
						notEmpty : {
							message : 'Seleccione una Provincia por favor.'
						}
					}
				},
				"cbo_distrito" : {
					validators : {
						notEmpty : {
							message : 'Seleccione un Distrito por favor.'
						}
					}
				},
				"generoRadios": {
	                // The group will be set as default (.form-group)
	                validators: {
	                    notEmpty: {
	                        message: 'Por favor seleccione un genero'
	                    }
	                }
	            }
			}
	});
	$('#frm_cliente').on('success.form.bv', function(e) {
		e.preventDefault();
		GuardarCliente();
		if ($('#hiddenindaccion').val() == 1){/*Si es la accion de insertar se bloqueara el boton btn_enviar*/
			$("#btn_enviar").prop("disabled", true);
		}		
	});
	
	
	
	function GuardarCliente(){		 
		  $('#ModalLoading').modal('show');
		  $.ajax({
				url : 'cliente?metodo=RegistrarModificarCliente',
				type : 'post',
				data : $('#frm_cliente').serialize(),
				dataType:'json',
				success:function(result){
					$('#ModalLoading').modal('hide');
					var valor=eval(result);					
					if(valor.indAccion=="1"){
						if(valor.codigocliente=="-1"){
							$('#mensajeAlerta').html("<div class='alert alert-warning'>Ocurrio un error al registrar los datos del cliente</div>");
						}else{
							$("#tab2primary #txt_codigo_guardar").val(valor.codigocliente);
							$('#mensajeAlerta').html("<div class='alert alert-success'>Se registro satisfactoriamente los datos del cliente con el codigo : "+ valor.codigocliente +"</div>");
						}
					}else{
						if(valor.codigomarca=="-1"){
							$('#mensajeAlerta').html("<div class='alert alert-warning'>Ocurrio un error al modificar los datos del cliente</div>");
						}else{							
							$('#mensajeAlerta').html("<div class='alert alert-success'>Se modificaron satisfactoriamente los datos del cliente</div>");
						}
					}
				},
				error : function(xhr, ajaxOptions, thrownError) {
					$('#ModalLoading').modal('hide');
					$('#mensajeAlerta').html("<div class='alert alert-danger'>Ocurrio un error por favor comuniquese con el Administrador del Sistema</div>");
				}
		  });
	}
	$('#cbo_departamento').change(function() {
		var combodepartamento = $("#cbo_departamento").val(); //(cboFiltro.options[cboFiltro.selectedIndex].value
		CargarComboDependiente(combodepartamento, 'cbo_provincia','LoadComboProvincia');
	});

	$('#cbo_provincia').change(function() {
		var comboprovincia = $("#cbo_provincia").val();
		CargarComboDependiente(comboprovincia, 'cbo_distrito','LoadComboDistrito');
	});
	
	function CargarComboDependiente(cboFiltro, cbocascada,action) {
	
		cbocascada = document.getElementById(cbocascada);
		LimpiarCombo(cbocascada);
		$.ajax({
			type : "get",
			url : "cliente?metodo=" + action,
			data : {idvalue : cboFiltro},
			dataType : 'json',
			success : function(resultado) {				
				var data = resultado[0];				
				if (data.length > 0) {
					llenarCombo(data, cbocascada);
				}
			},
			error : function(xrh, ajaxOptions, thrownError) {
				alert("Error status code: " + xrh.status);
				alert("Error details: " + thrownError);
			}
		});
	}
	function LimpiarCombo(cbocascada) {
		while (cbocascada.length > 0) {
			cbocascada.remove(cbocascada.length - 1);
		}
	}
	function llenarCombo(result, cbocascada) {
		if (cbocascada.id == 'cbo_provincia') {
			cbocascada.options[0] = new Option('- Seleccione -', '');
			for ( var i = 0; i < result.length; i++) {
				cbocascada.options[cbocascada.length] = new Option(result[i].provincia, result[i].idprov);
			}

		}
		if (cbocascada.id == 'cbo_distrito') {
			cbocascada.options[0] = new Option('- Seleccione -', '');
			for ( var i = 0; i < result.length; i++) {
				cbocascada.options[cbocascada.length] = new Option(result[i].distrito, result[i].iddist);
			}
		}
	}
});

/* Se ejecuta cuando carga por primera vez la pagina */
$(window).load(function() {
	initGrilla();
	LoadCombos();
	/*Desabilitamos el tab eventotab2primary*/
	$('#eventotab2primary').click(function(event){
	        if ($(this).hasClass('disabled')) {
	            return false;
	        }
	 });
	       

	$('#txt_dni_guardar').prop( "disabled", true ).addClass('disabled');
	$('#txt_ruc_guardar').prop( "disabled", true ).addClass('disabled');
	
});

var paginador;
var totalPaginas;
var itemsPorPagina = 3;
var numerosPorPagina = 10;

function creaPaginador(totalItems)
{
	paginador = $(".pagination");
	totalPaginas = Math.ceil(totalItems/itemsPorPagina);
	$('<li><a href="#" class="first_link"><</a></li>').appendTo(paginador);
	$('<li><a href="#" class="prev_link">«</a></li>').appendTo(paginador);
	
	var pag = 0;
	while(totalPaginas > pag)
	{
		$('<li><a href="#" class="page_link">'+(pag+1)+'</a></li>').appendTo(paginador);
		pag++;
	}	
	if(numerosPorPagina > 1)
	{
		$(".page_link").hide();
		$(".page_link").slice(0,numerosPorPagina).show();
	}		
	$('<li><a href="#" class="next_link">»</a></li>').appendTo(paginador);
	$('<li><a href="#" class="last_link">></a></li>').appendTo(paginador);

	paginador.find(".page_link:first").addClass("active");
	paginador.find(".page_link:first").parents("li").addClass("active");

	paginador.find(".prev_link").hide();

	paginador.find("li .page_link").click(function()
	{
		var irpagina =$(this).html().valueOf()-1;
		cargaPagina(irpagina);
		return false;
	});

	paginador.find("li .first_link").click(function()
	{
		var irpagina =0;
		cargaPagina(irpagina);
		return false;
	});
	paginador.find("li .prev_link").click(function()
	{
		var irpagina =parseInt(paginador.data("pag")) -1;
		cargaPagina(irpagina);
		return false;
	});

	paginador.find("li .next_link").click(function()
	{
		var irpagina =parseInt(paginador.data("pag")) +1;
		cargaPagina(irpagina);
		return false;
	});

	paginador.find("li .last_link").click(function()
	{
		var irpagina =totalPaginas -1;
		cargaPagina(irpagina);
		return false;
	});
	cargaPagina(0);
}


function cargaPagina(pagina){
	var desde = pagina * itemsPorPagina;	
	var txt_codigo_buscar = $("#txt_codigo_buscar").val();
	var txt_nombre_apellido_buscar = $("#txt_nombre_apellido_buscar").val();
	var cbo_tipopersona_buscar = $("#cbo_tipopersona_buscar").val();
	var txt_dni_buscar = $("#txt_dni_buscar").val();
	var txt_ruc_buscar = $("#txt_ruc_buscar").val();
	$.ajax({
		url : 'cliente?metodo=ListarCliente',
		type : 'post',
		data : {txt_codigo_buscar:txt_codigo_buscar,txt_nombre_apellido_buscar:txt_nombre_apellido_buscar,cbo_tipopersona_buscar:cbo_tipopersona_buscar,txt_dni_buscar:txt_dni_buscar,txt_ruc_buscar:txt_ruc_buscar,limit:itemsPorPagina,offset:desde},
		dataType : 'json',
		success : function(result) {
			var lista=result[0];
			$("#rellenar").html("");		
			var trHTML = '';
			if (lista.length > 0){ 
				for ( var i = 0; i < lista.length; i++) {
					trHTML += '<tr><td>'
						+ lista[i]['codigocliente']
						+ '</td><td>'
						+ lista[i]['nombres']	
						+ '</td><td>'
						+ lista[i]['tipopersona']['descripcion']
						+ '</td><td>'
						+ lista[i]['ruc']	
						+ '</td><td>'
						+ lista[i]['dni']	
						+ '</td><td>'
						+ lista[i]['sexo']['descripcion']	
						+ '</td><td>'
						+ lista[i]['celular']	
						+ '</td><td><a href="" data-id="'+lista[i]['codigocliente']+'" id="btn_editar" class="btn btn-info"><span class="fa fa-pencil-square-o " ></span> </a></td>'
						+ '</td><td><a  data-id="'+lista[i]['codigocliente']+'" id="btn_modaleliminar" class="btn btn-danger"><span class="fa fa-trash-o" ></span></a></td>';
				}
				
				$('#rellenar').append(trHTML);
			}			
		}
	});
	
	if(pagina >= 1)
	{
		paginador.find(".prev_link").show();
	}
	else
	{
		paginador.find(".prev_link").hide();
	}
	if(pagina <(totalPaginas- numerosPorPagina))
	{
		paginador.find(".next_link").show();
	}else
	{
		paginador.find(".next_link").hide();
	}

	paginador.data("pag",pagina);

	if(numerosPorPagina>1)
	{
		$(".page_link").hide();
		if(pagina < (totalPaginas- numerosPorPagina))
		{
			$(".page_link").slice(pagina,numerosPorPagina + pagina).show();
		}
		else{
			if(totalPaginas > numerosPorPagina)
				$(".page_link").slice(totalPaginas- numerosPorPagina).show();
			else
				$(".page_link").slice(0).show();

		}
	}
	paginador.children().removeClass("active");
	paginador.children().eq(pagina+2).addClass("active");	
}

function initGrilla(){
	
	var txt_codigo_buscar = $("#txt_codigo_buscar").val();
	var txt_nombre_apellido_buscar = $("#txt_nombre_apellido_buscar").val();
	var cbo_tipopersona_buscar = $("#cbo_tipopersona_buscar").val();
	var txt_dni_buscar = $("#txt_dni_buscar").val();
	var txt_ruc_buscar = $("#txt_ruc_buscar").val();
	
	$.ajax({
		url : 'cliente?metodo=TotalRegistrosCliente',
		type : 'post',
		data : {txt_codigo_buscar:txt_codigo_buscar,txt_nombre_apellido_buscar:txt_nombre_apellido_buscar,cbo_tipopersona_buscar:cbo_tipopersona_buscar,txt_dni_buscar:txt_dni_buscar,txt_ruc_buscar:txt_ruc_buscar},
		dataType : 'json',
		success : function(result) {
			var valor=eval(result);
			var total = valor.TotalRegistro;
			creaPaginador(total);
		}
	});
	
}


function LoadCombos() {
	$.ajax({
		url : 'cliente?metodo=LoadComboDepartamento',
		type : 'get',
		data : '',
		dataType : 'json',
		success : function(result) {
			var datosDepartamento = result[0];
			var datosEstado = result[1];
			var datosTipopersona = result[2];
			
			ComboDepartamento = document.getElementById('cbo_departamento');
			ComboDepartamento.options[0] = new Option('- Seleccione -','');
			for ( var i = 0; i < datosDepartamento.length; i++) {
				ComboDepartamento.options[ComboDepartamento.length] = new Option(datosDepartamento[i].departamento, datosDepartamento[i].iddepar);
			}
			
			comboestado = document.getElementById('cbo_estado');		
			for ( var i = 0; i < datosEstado.length; i++) {
				comboestado.options[comboestado.length] = new Option(datosEstado[i].descripcion, datosEstado[i].valor );
			}
			
			combotipopersona = document.getElementById('cbo_tipopersona');		
			combotipopersona.options[0] = new Option('- Seleccione -','');
			for ( var i = 0; i < datosTipopersona.length; i++) {
				combotipopersona.options[combotipopersona.length] = new Option(datosTipopersona[i].descripcion, datosTipopersona[i].valor );
			}
			
			cbo_tipopersona_buscar = document.getElementById('cbo_tipopersona_buscar');		
			cbo_tipopersona_buscar.options[0] = new Option('- Seleccione -','');
			for ( var i = 0; i < datosTipopersona.length; i++) {
				cbo_tipopersona_buscar.options[cbo_tipopersona_buscar.length] = new Option(datosTipopersona[i].descripcion, datosTipopersona[i].valor );
			}
			
			
			
		}
	});
}
