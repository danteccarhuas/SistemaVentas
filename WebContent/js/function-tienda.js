$(document)	.ready(function(e) {
	
	$('#btn_nuevo').on('click', function () {		
		 $('#tab1').prop( "disabled", true ).addClass('disabled');
		 $('#tab2').prop( "disabled", false ).removeClass('disabled');
		 $('#eventotab2primary').prop( "disabled", false ).removeClass('disabled');/*Quitamos el disabled del tab eventotab2primary*/
		 $('.nav-tabs > .active').prop( "disabled", true ).addClass('disabled').next('li').find('a').trigger('click');		
		 $('#hiddenindaccion').val(1); 
	});	
	
	$('#btn_salir').on('click', function () {
		$("#frm_tienda").data('bootstrapValidator').resetForm(true);/*Limpiamos todos los controles del formulario frm_Proveedor */
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
		$('#hiddencodtienda').val($(this).data('id'));
		$("#modalRemove").modal({
			keyboard : false
		});
	});
	
	$(".removeBtn").click(function(e){
		$("#modalRemove").modal("hide");		
		var  codtienda=$('#hiddencodtienda').val();
		$.ajax({
			url : 'tienda?metodo=EliminarTienda',
			type : 'post',
			data : {codtienda : codtienda},
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
		$('#hiddencodtienda').val($(this).data('id'));
		$('#hiddenindaccion').val(2); 		
		var codtienda = $('#hiddencodtienda').val();		
		$.ajax({
			url : 'tienda?metodo=ObtenerDatosTienda',
			type : 'post',
			data : {codtienda:codtienda},
			dataType : 'json',
			success : function(result) {
				var data1=result[0];					
				$('#tab1').prop( "disabled", true ).addClass('disabled');
				$('#tab2').prop( "disabled", false ).removeClass('disabled');
				$('#eventotab2primary').prop( "disabled", false ).removeClass('disabled');/*Quitamos el disabled del tab eventotab2primary*/
				$('.nav-tabs > .active').prop( "disabled", true ).addClass('disabled').next('li').find('a').trigger('click');	
				$("#tab2primary #txt_codigo_guardar").val(data1.idtienda);
				$("#tab2primary #txt_descripcion_guardar").val(data1.descripcion);
				$("#tab2primary #txt_direccion_guardar").val(data1.direccion);			
				$("#tab2primary #txt_telefono_guardar").val(data1.telefono);
				$("#tab2primary #cbo_estado").val(data1.estado.valor);		
			}
		});
	});
	/* Valida las etiquedas del formulario */
	$("#frm_tienda").bootstrapValidator({
				message : 'This value is not valid',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				"txt_descripcion_guardar" : {
					validators : {
						notEmpty : {
							message : 'Ingrese Descripcion por favor.'
						}
					}
				},
				"txt_direccion_guardar" : {
					validators : {
						notEmpty : {
							message : 'Ingrese direccion por favor.'
						}
					}
				},
				"txt_telefono_guardar" : {
					validators : {
						notEmpty : {
							message : 'Ingrese telefono por favor.'
						}
					}
				},
				"cbo_estado" : {
					validators : {
						notEmpty : {
							message : 'Seleccione un estado por favor.'
						}
					}
				}
			}
	});
	$('#frm_tienda').on('success.form.bv', function(e) {
		e.preventDefault();
		Guardartienda();
		if ($('#hiddenindaccion').val() == 1){/*Si es la accion de insertar se bloqueara el boton btn_enviar*/
			$("#btn_enviar").prop("disabled", true);
		}
		
	});
	
	function Guardartienda(){		 
		  $('#ModalLoading').modal('show');
		  $.ajax({
				url : 'tienda?metodo=RegistrarModificarTienda',
				type : 'post',
				data : $('#frm_tienda').serialize(),
				dataType:'json',
				success:function(result){
					$('#ModalLoading').modal('hide');
					var valor=eval(result);					
					if(valor.indAccion=="1"){
						if(valor.codigocategoria=="-1"){
							$('#mensajeAlerta').html("<div class='alert alert-warning'>Ocurrio un error al registrar los datos de la tienda</div>");
						}else{
							$("#tab2primary #txt_codigo_guardar").val(valor.codigotienda);
							$('#mensajeAlerta').html("<div class='alert alert-success'>Se registro satisfactoriamente los datos de la tienda con el codigo : "+ valor.codigotienda +"</div>");
						}
					}else{
						if(valor.codigomarca=="-1"){
							$('#mensajeAlerta').html("<div class='alert alert-warning'>Ocurrio un error al modificar los datos de la tienda</div>");
						}else{							
							$('#mensajeAlerta').html("<div class='alert alert-success'>Se modificaron satisfactoriamente los datos de la tienda</div>");
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
	alert("ddd");
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
	var txt_Descripcion_buscar = $("#txt_Descripcion_buscar").val();
	$.ajax({
		url : 'tienda?metodo=ListarTienda',
		type : 'post',
		data : {txt_Descripcion_buscar:txt_Descripcion_buscar,limit:itemsPorPagina,offset:desde},
		dataType : 'json',
		success : function(result) {
			var lista=result[0];
			$("#rellenar").html("");
			var trHTML = '';
			if (lista.length > 0){
				for ( var i = 0; i < lista.length; i++) {
					trHTML += '<tr><td>'
						+ lista[i]['idtienda']
						+ '</td><td>'
						+ lista[i]['descripcion']	
						+ '</td><td>'
						+ lista[i]['direccion']	
						+ '</td><td>'
						+ lista[i]['telefono']	
						+ '</td><td><a href="" data-id="'+lista[i]['idtienda']+'" id="btn_editar" class="btn btn-info"><span class="fa fa-pencil-square-o " ></span> </a></td>'
						+ '</td><td><a  data-id="'+lista[i]['idtienda']+'" id="btn_modaleliminar" class="btn btn-danger"><span class="fa fa-trash-o" ></span></a></td>';
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
	
	var txt_Descripcion_buscar = $("#txt_Descripcion_buscar").val();
	$.ajax({
		url : 'tienda?metodo=TotalRegistrosTienda',
		type : 'post',
		data : {txt_Descripcion_buscar:txt_Descripcion_buscar},
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
		url : 'tienda?metodo=LoadEstados',
		type : 'post',
		data : '',
		dataType : 'json',
		success : function(result) {
			var datosEstado = result[0];					
			comboestado = document.getElementById('cbo_estado');		
			for ( var i = 0; i < datosEstado.length; i++) {
				comboestado.options[comboestado.length] = new Option(datosEstado[i].descripcion, datosEstado[i].valor);
			}
		}
	});
}

