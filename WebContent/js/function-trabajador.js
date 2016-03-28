$(document).ready(function(e) {
		$('#btn_nuevo').on('click', function () {		
		 $('#tab1').prop( "disabled", true ).addClass('disabled');
		 $('#tab2').prop( "disabled", false ).removeClass('disabled');
		 $('#eventotab2primary').prop( "disabled", false ).removeClass('disabled');/*Quitamos el disabled del tab eventotab2primary*/
		 $('.nav-tabs > .active').prop( "disabled", true ).addClass('disabled').next('li').find('a').trigger('click');		
		 $('#hiddenindaccion').val(1); 
	});
	
	$('#btn_salir').on('click', function () {
		$("#frm_Trabajador").data('bootstrapValidator').resetForm(true);/*Limpiamos todos los controles del formulario frm_Proveedor */
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
	
	/*Metodo para eliminar trabajadores*/
	$(document).on("click","#btn_modaleliminar",function(e) {
		e.preventDefault();		
		$('#hiddencodtrab').val($(this).data('id'));
		$("#modalRemove").modal({
			keyboard : false
		});
	});
	
	$(".removeBtn").click(function(e){
		$("#modalRemove").modal("hide");		
		console.log($('#hiddencodtrab').val());
		var  codtrab=$('#hiddencodtrab').val();
		$.ajax({
			url : 'trabajador?metodo=EliminarTrabajador',
			type : 'post',
			data : {codtrab : codtrab},
			dataType : 'json',
			success : function(result) {				
				$("#paginador").html("");/*Limpiar los numero de paginacion*/
				initGrilla();
			}
		});
		
	});
	
	/*Metodo para modificar trabajadores*/
	$(document).on("click","#btn_editar",function(e) {		
		e.preventDefault();		
		$('#hiddencodtrab').val($(this).data('id'));
		$('#hiddenindaccion').val(2); 		
		var codigo_trab = $('#hiddencodtrab').val();		
		$.ajax({
			url : 'trabajador?metodo=ObtenerDatosTrabajador',
			type : 'post',
			data : {codigo_trab:codigo_trab},
			dataType : 'json',
			success : function(result) {
				var data1=result[0];					
				$('#tab1').prop( "disabled", true ).addClass('disabled');
				$('#tab2').prop( "disabled", false ).removeClass('disabled');
				$('#eventotab2primary').prop( "disabled", false ).removeClass('disabled');/*Quitamos el disabled del tab eventotab2primary*/
			 	$('.nav-tabs > .active').prop( "disabled", true ).addClass('disabled').next('li').find('a').trigger('click');		
			 	$("#tab2primary #txt_cod_trab_guardar").val(data1.codigotrabajador);
				$("#tab2primary #txt_nombres").val(data1.nombres);
				$("#tab2primary #txt_apellidos").val(data1.apellidos);
				$("#tab2primary #txt_correo").val(data1.correo);
				$("#tab2primary #txt_telefono").val(data1.telefono);
				$("#tab2primary #txt_dni").val(data1.dni);
				$("#tab2primary #txt_fec_nac").val(formatDate(data1.fechanacimiento));
				$("#tab2primary #txt_direccion").val(data1.direccion);
				$("#tab2primary #cbo_estado").val(data1.estado);
				$("#tab2primary #cbo_tienda").val(data1.tienda.idtienda);

			}
		});
	});
	
	function formatDate(date) {
	    var d = new Date(date),
	        month = '' + (d.getMonth() + 1),
	        day = '' + d.getDate(),
	        year = d.getFullYear();

	    if (month.length < 2) month = '0' + month;
	    if (day.length < 2) day = '0' + day;

	    return [year, month, day].join('-');
	}
	
	$('#datePicker').datepicker({
	    format: 'yyyy-mm-dd',
	    language: "es"
	  })
	  .on('changeDate', function(e) {
	    $('#frm_Trabajador').bootstrapValidator('revalidateField', 'txt_fec_nac');
	  });	
	/* Valida las etiquedas del formulario */
	$("#frm_Trabajador").bootstrapValidator({
				message : 'This value is not valid',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				"txt_nombres" : {
					validators : {
						notEmpty : {
							message : 'Ingrese Nombres por favor.'
						}
					}
				},
				"txt_apellidos" : {
					validators : {
						notEmpty : {
							message : 'Ingrese Apellidos por favor.'
						}
					}
				},
				"txt_correo" : {
					validators : {
						notEmpty : {
							message : 'Ingrese correo electronico por favor.'
						},
						emailAddress : {
							message : 'El Correo Electronico ingresada no es válida.'
						}
					}
				},
				"txt_telefono" : {
					validators : {
						notEmpty : {
							message : 'Ingrese teléfono por favor.'
						},
						stringLength : {
							min : 9,
							max : 9,
							message : 'El celular tiene 9 cifras maximo.'
						},
						integer : {
							message : 'Ingrese solo numeros.'
						}
					}
				},
				"txt_dni" : {
					validators : {
						notEmpty : {
							message : 'Ingrese DNI por favor.'
						},
						stringLength : {
							min : 8,
							max : 8,
							message : 'El DNI tiene 8 cifras maximo.'
						},
						integer : {
							message : 'Ingrese solo numeros.'
						}
					}
				},
				"txt_fec_nac" : {
					validators : {
						notEmpty : {
							message : 'Seleccione su fecha de nacimiento por favor.'
						}
					}
				},
				"txt_direccion" : {
					validators : {
						notEmpty : {
							message : 'Ingrese dirección por favor.'
						}
					}
				},
				"cbo_estado" : {
					validators : {
						notEmpty : {
							message : 'Seleccione estado por favor.'
						}
					}
				},
				"cbo_tienda" : {
					validators : {
						notEmpty : {
							message : 'Seleccione una Tienda por favor.'
						}
					}
				}
			}
		});
	$('#frm_Trabajador').on('success.form.bv', function(e) {
		e.preventDefault();
		GuardarTrabajador();
		if ($('#hiddenindaccion').val() == 1){/*Si es la accion de insertar se bloqueara el boton btn_enviar*/
			$("#btn_enviar").prop("disabled", true);
		}
		
	});
	
	function GuardarTrabajador(){		 
		  $('#ModalLoading').modal('show');
		  $.ajax({
				url : 'trabajador?metodo=RegistrarModificarTrabajador',
				type : 'post',
				data : $('#frm_Trabajador').serialize(),
				dataType:'json',
				success:function(result){
					$('#ModalLoading').modal('hide');
					var valor=eval(result);					
					if(valor.indAccion=="1"){
						if(valor.codigotrabajador=="-1"){
							$('#mensajeAlerta').html("<div class='alert alert-warning'>Ocurrio un error al registrar los datos del Trabajador</div>");
						}else{
							$("#tab2primary #txt_cod_trab_guardar").val(valor.codigotrabajador);
							$('#mensajeAlerta').html("<div class='alert alert-success'>Se registro satisfactoriamente los datos del Trabajador con el codigo "+ valor.codigotrabajador +"</div>");
						}
					}else{
						if(valor.codigotrabajador=="-1"){
							$('#mensajeAlerta').html("<div class='alert alert-warning'>Ocurrio un error al modificar los datos del Trabajador</div>");
						}else{							
							$('#mensajeAlerta').html("<div class='alert alert-success'>Se modificaron satisfactoriamente los datos del Trabajador</div>");
						}
					}
				},
				error : function(xhr, ajaxOptions, thrownError) {
					$('#ModalLoading').modal('hide');
					$('#mensajeAlerta').html("<div class='alert alert-danger'>Ocurrio un error por favor comuniquese con el Administrador del Sistema</div>");
				}
		  });
		
		
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
	var txt_codigotrab_buscar = $("#txt_codigotrabajador_buscar").val();
	var txt_dni_buscar = $("#txt_dni_buscar").val();
	var txt_nombre_buscar = $("#txt_nombres_buscar").val();
	$.ajax({
		url : 'trabajador?metodo=ListarTrabajadores',
		type : 'post',
		data : {txt_codigotrab_buscar:txt_codigotrab_buscar,txt_dni_buscar:txt_dni_buscar,txt_nombre_buscar:txt_nombre_buscar,limit:itemsPorPagina,offset:desde},
		dataType : 'json',
		success : function(result) {
			var lista=result[0];
			$("#rellenar").html("");
			var trHTML = '';
			if (lista.length > 0){
				for ( var i = 0; i < lista.length; i++) {
					trHTML += '<tr><td>'
						+ lista[i]['codigotrabajador']
						+ '</td><td>'
						+ lista[i]['nombres']
						+ '</td><td>'
						+ lista[i]['correo']
						+ '</td><td>'
						+ lista[i]['dni']
						+ '</td><td>'
						+ lista[i]['direccion']
						+ '</td><td>'
						+ lista[i]['telefono']
						+ '</td><td>'
						+ lista[i]['tienda']['descripcion']
						+ '</td><td><a href="" data-id="'+lista[i]['codigotrabajador']+'" id="btn_editar" class="btn btn-info"><span class="fa fa-pencil-square-o " ></span> </a></td>'
						+ '</td><td><a  data-id="'+lista[i]['codigotrabajador']+'" id="btn_modaleliminar" class="btn btn-danger"><span class="fa fa-trash-o" ></span></a></td>';
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
	var txt_codigotrab_buscar = $("#txt_codigotrabajador_buscar").val();
	var txt_dni_buscar = $("#txt_dni_buscar").val();
	var txt_nombres_buscar = $("#txt_nombres_buscar").val();
	$.ajax({
		url : 'trabajador?metodo=TotalRegistrosTrabajadores',
		type : 'post',
		data : {txt_codigotrab_buscar:txt_codigotrab_buscar,txt_dni_buscar:txt_dni_buscar,txt_nombres_buscar:txt_nombres_buscar},
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
		url : 'trabajador?metodo=LoadEstados',
		type : 'post',
		data : '',
		dataType : 'json',
		success : function(result) {
			var datosEstado = result[0], datosTienda=result[1];	
			
			comboestado = document.getElementById('cbo_estado');
			for ( var i = 0; i < datosEstado.length; i++) {
				comboestado.options[comboestado.length] = new Option(datosEstado[i].descripcion, datosEstado[i].valor);
			}
			
			combotienda = document.getElementById('cbo_tienda');
			combotienda.options[0] = new Option('- Seleccione -', '');
			for ( var i = 0; i < datosTienda.length; i++) {
				combotienda.options[combotienda.length] = new Option(datosTienda[i].descripcion, datosTienda[i].idtienda);
			}
		}
	});
}
