$(document)	.ready(function(e) {
	
	
	$('#btn_nuevo').on('click', function () {		
		 $('#tab1').prop( "disabled", true ).addClass('disabled');
		 $('#tab2').prop( "disabled", false ).removeClass('disabled');
		 $('#eventotab2primary').prop( "disabled", false ).removeClass('disabled');/*Quitamos el disabled del tab eventotab2primary*/
		 $('.nav-tabs > .active').prop( "disabled", true ).addClass('disabled').next('li').find('a').trigger('click');		
		 $('#hiddenindaccion').val(1); 
	});	
	
	$('#btn_salir').on('click', function () {
		$("#frm_producto").data('bootstrapValidator').resetForm(true);/*Limpiamos todos los controles del formulario frm_Proveedor */
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
		$('#hiddencodproducto').val($(this).data('id'));
		$("#modalRemove").modal({
			keyboard : false
		});
	});
	
	$(".removeBtn").click(function(e){
		$("#modalRemove").modal("hide");		
		var  codproducto=$('#hiddencodproducto').val();
		$.ajax({
			url : 'producto?metodo=EliminarProducto',
			type : 'post',
			data : {codproducto : codproducto},
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
		$('#hiddencodproducto').val($(this).data('id'));
		$('#hiddenindaccion').val(2); 		
		var codproducto = $('#hiddencodproducto').val();		
		$.ajax({
			url : 'producto?metodo=ObtenerDatosProducto',
			type : 'post',
			data : {codproducto:codproducto},
			dataType : 'json',
			success : function(result) {
				var data1=result[0];					
				$('#tab1').prop( "disabled", true ).addClass('disabled');
				$('#tab2').prop( "disabled", false ).removeClass('disabled');
				$('#eventotab2primary').prop( "disabled", false ).removeClass('disabled');/*Quitamos el disabled del tab eventotab2primary*/
				$('.nav-tabs > .active').prop( "disabled", true ).addClass('disabled').next('li').find('a').trigger('click');			
				$("#tab2primary #txt_codigo_guardar").val(data1.codigoproducto);
				$("#tab2primary #txt_nombre_guardar").val(data1.nombre);			
				$("#tab2primary #txt_descripcion_guardar").val(data1.descripcion);
				$("#tab2primary #txt_preciocompra_guardar").val(data1.preciocompra);
				$("#tab2primary #txt_precioventa_guardar").val(data1.precioventa);
				$("#tab2primary #txt_talla_guardar").val(data1.talla);				
				$("#tab2primary #cbo_estado").val(data1.estado.valor);
				$("#tab2primary #cbo_marca").val(data1.marca.idmarca);
				$("#tab2primary #cbo_categoria").val(data1.categoria.idcategoria);
				$("#tab2primary #cbo_genero").val(data1.genero.valor);
				
			}
		});
	});
	/* Valida las etiquedas del formulario */
	$("#frm_producto").bootstrapValidator({
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
				"txt_descripcion_guardar" : {
					validators : {
						notEmpty : {
							message : 'Ingrese Descripcion por favor.'
						}
					}
				},
				"txt_preciocompra_guardar" : {
					validators : {
						notEmpty : {
							message : 'Ingrese precio de compra por favor.'
						},regexp:{
	                        regexp: /^[0-9]+\.[0-9]{0,2}$/i,
	                        message: 'Ingresa en formato ##.##'
						}
					}
				},
				"txt_precioventa_guardar" : {
					validators : {
						notEmpty : {
							message : 'Ingrese precio de venta por favor.'
						},regexp:{
	                        regexp: /^[0-9]+\.[0-9]{0,2}$/i,
	                        message: 'Ingresa en formato ##.##'
						}
					}
				},
				"txt_talla_guardar" : {
					validators : {
						notEmpty : {
							message : 'Ingrese talla por favor.'
						}
					}
				},
				"cbo_marca" : {
					validators : {
						notEmpty : {
							message : 'Seleccione marca por favor.'
						}
					}
				},
				"cbo_categoria" : {
					validators : {
						notEmpty : {
							message : 'Seleccione categoria por favor.'
						}
					}
				},
				"cbo_genero" : {
					validators : {
						notEmpty : {
							message : 'Seleccione genero por favor.'
						}
					}
				}
			}
	});
	$('#frm_producto').on('success.form.bv', function(e) {
		e.preventDefault();
		GuardarProducto();
		if ($('#hiddenindaccion').val() == 1){/*Si es la accion de insertar se bloqueara el boton btn_enviar*/
			$("#btn_enviar").prop("disabled", true);
		}
		
	});
	
	function GuardarProducto(){		 
		  $('#ModalLoading').modal('show');
		  $.ajax({
				url : 'producto?metodo=RegistrarModificarProducto',
				type : 'post',
				data : $('#frm_producto').serialize(),
				dataType:'json',
				success:function(result){
					$('#ModalLoading').modal('hide');
					var valor=eval(result);					
					if(valor.indAccion=="1"){
						if(valor.codigoproducto=="-1"){
							$('#mensajeAlerta').html("<div class='alert alert-warning'>Ocurrio un error al registrar los datos del producto</div>");
						}else{
							$("#tab2primary #txt_codigo_guardar").val(valor.codigoproducto);
							$('#mensajeAlerta').html("<div class='alert alert-success'>Se registro satisfactoriamente los datos del producto con el codigo : "+ valor.codigoproducto +"</div>");
						}
					}else{
						if(valor.codigoproducto=="-1"){
							$('#mensajeAlerta').html("<div class='alert alert-warning'>Ocurrio un error al modificar los datos del producto</div>");
						}else{							
							$('#mensajeAlerta').html("<div class='alert alert-success'>Se modificaron satisfactoriamente los datos del producto</div>");
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
	$('<li><a href="#" class="prev_link">�</a></li>').appendTo(paginador);
	
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
	$('<li><a href="#" class="next_link">�</a></li>').appendTo(paginador);
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
	var txt_codigo_buscar = $("#txt_codigo_buscar").val();
	var txt_nombre_buscar = $("#txt_nombre_buscar").val();
	var cbo_marca_buscar = $("#cbo_marca_buscar").val();
	var cbo_categoria_buscar = $("#cbo_categoria_buscar").val();
	var cbo_genero_buscar = $("#cbo_genero_buscar").val();
	$.ajax({
		url : 'producto?metodo=ListarProducto',
		type : 'post',
		data : {txt_codigo_buscar:txt_codigo_buscar,txt_nombre_buscar:txt_nombre_buscar,cbo_marca_buscar:cbo_marca_buscar,cbo_categoria_buscar:cbo_categoria_buscar,cbo_genero_buscar:cbo_genero_buscar,limit:itemsPorPagina,offset:desde},
		dataType : 'json',
		success : function(result) {
			var lista=result[0];
			$("#rellenar").html("");
			var trHTML = '';
			if (lista.length > 0){
				for ( var i = 0; i < lista.length; i++) {
					trHTML += '<tr><td>'
						+ lista[i]['codigoproducto']
						+ '</td><td>'
						+ lista[i]['nombre']											
						+ '</td><td>'
						+ lista[i]['marca']['descripcion']
						+ '</td><td>'
						+ lista[i]['categoria']['descripcion']
						+ '</td><td>'
						+ lista[i]['genero']['descripcion']
						+ '</td><td>'
						+ lista[i]['talla']
						+ '</td><td>'
						+ lista[i]['preciocompra']
						+ '</td><td>'
						+ lista[i]['precioventa']
						+ '</td><td><a href="" data-id="'+lista[i]['codigoproducto']+'" id="btn_editar" class="btn btn-info"><span class="fa fa-pencil-square-o " ></span> </a></td>'
						+ '</td><td><a  data-id="'+lista[i]['codigoproducto']+'" id="btn_modaleliminar" class="btn btn-danger"><span class="fa fa-trash-o" ></span></a></td>';
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
	var txt_nombre_buscar = $("#txt_nombre_buscar").val();
	var cbo_marca_buscar = $("#cbo_marca_buscar").val();
	var cbo_categoria_buscar = $("#cbo_categoria_buscar").val();
	var cbo_genero_buscar = $("#cbo_genero_buscar").val();
	$.ajax({
		url : 'producto?metodo=TotalRegistrosProducto',
		type : 'post',
		data : {txt_codigo_buscar:txt_codigo_buscar,txt_nombre_buscar:txt_nombre_buscar,cbo_marca_buscar:cbo_marca_buscar,cbo_categoria_buscar:cbo_categoria_buscar,cbo_genero_buscar:cbo_genero_buscar},
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
		url : 'producto?metodo=LoadCombo',
		type : 'post',
		data : '',
		dataType : 'json',
		success : function(result) {
			var datosMarca = result[0];
			var datosCategoria = result[1];	
			var datosGenero = result[2];	
			var datosEstado = result[3];
			
			comboMarca = document.getElementById('cbo_marca');	
			comboMarca.options[0] = new Option('- Seleccione -','');
			for ( var i = 0; i < datosMarca.length; i++) {
				comboMarca.options[comboMarca.length] = new Option(datosMarca[i].descripcion, datosMarca[i].idmarca);
			}
			
			comboCategoria = document.getElementById('cbo_categoria');	
			comboCategoria.options[0] = new Option('- Seleccione -','');
			for ( var i = 0; i < datosCategoria.length; i++) {
				comboCategoria.options[comboCategoria.length] = new Option(datosCategoria[i].descripcion, datosCategoria[i].idcategoria);
			}
			
			comboGenero = document.getElementById('cbo_genero');		
			comboGenero.options[0] = new Option('- Seleccione -','');
			for ( var i = 0; i < datosGenero.length; i++) {
				comboGenero.options[comboGenero.length] = new Option(datosGenero[i].descripcion, datosGenero[i].valor);
			}
			comboEstado = document.getElementById('cbo_estado');					
			for ( var i = 0; i < datosEstado.length; i++) {
				comboEstado.options[comboEstado.length] = new Option(datosEstado[i].descripcion, datosEstado[i].valor);
			}
			
			/*Cargo Combo del panel buscar*/
			
			comboMarca_buscar = document.getElementById('cbo_marca_buscar');	
			comboMarca_buscar.options[0] = new Option('- Seleccione -','');
			for ( var i = 0; i < datosMarca.length; i++) {
				comboMarca_buscar.options[comboMarca_buscar.length] = new Option(datosMarca[i].descripcion, datosMarca[i].idmarca);
			}
			
			comboCategoria_buscar = document.getElementById('cbo_categoria_buscar');	
			comboCategoria_buscar.options[0] = new Option('- Seleccione -','');
			for ( var i = 0; i < datosCategoria.length; i++) {
				comboCategoria_buscar.options[comboCategoria_buscar.length] = new Option(datosCategoria[i].descripcion, datosCategoria[i].idcategoria);
			}
			
			comboGenero_buscar = document.getElementById('cbo_genero_buscar');		
			comboGenero_buscar.options[0] = new Option('- Seleccione -','');
			for ( var i = 0; i < datosGenero.length; i++) {
				comboGenero_buscar.options[comboGenero_buscar.length] = new Option(datosGenero[i].descripcion, datosGenero[i].valor);
			}
			
			
		}
	});
}

