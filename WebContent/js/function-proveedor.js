$(document)	.ready(function(e) {
	/*Metodo para eliminar proveedores*/
	$(document).on("click","#btn_modaleliminar",function(e) {
		e.preventDefault();		
		$('#hiddencodprov').val($(this).data('id'));
		$("#modalRemove").modal({
			keyboard : false
		});
	});
	$(".removeBtn").click(function(e){
		$("#modalRemove").modal("hide");		
		console.log($('#hiddencodprov').val());
		var  codprov=$('#hiddencodprov').val();
		$.ajax({
			url : 'proveedor?metodo=EliminarProveedor',
			type : 'post',
			data : {codprov : codprov},
			dataType : 'json',
			success : function(result) {				
				$("#paginador").html("");/*Limpiar los numero de paginacion*/
				initGrilla();
			}
		});
		
	});
	/*Metodo para modificar proveedores*/
	$(document).on("click","#btn_editar",function(e) {		
		e.preventDefault();		
		$('#hiddencodprov').val($(this).data('id'));
		$('#hiddenindaccion').val(2); 		
		var codigo_provee = $('#hiddencodprov').val();		
		$.ajax({
			url : 'proveedor?metodo=ObtenerDatosProveedor',
			type : 'post',
			data : {codigo_provee:codigo_provee},
			dataType : 'json',
			success : function(result) {
				var data1=result[0];					
				$('#tab1').prop( "disabled", true ).addClass('disabled');
				$('#tab2').prop( "disabled", false ).removeClass('disabled');
				$('.nav-tabs > .active').prop( "disabled", true ).addClass('disabled').next('li').find('a').trigger('click');			
				$("#tab2primary #txt_cod_prov_guardar").val(data1.codigoproveedor);
				$("#tab2primary #txt_razon_social").val(data1.razonsocial);
				$("#tab2primary #txt_correo").val(data1.correo);
				$("#tab2primary #txt_fax").val(data1.fax);
				$("#tab2primary #txt_telefono").val(data1.telefono);
				$("#tab2primary #txt_celular").val(data1.celular);
				$("#tab2primary #txt_sitioweb").val(data1.sitioweb);
				$("#tab2primary #txt_ruc").val(data1.ruc);
				$("#tab2primary #txt_direccion").val(data1.direccion);
				$("#tab2primary #txt_referencia").val(data1.referencia);
				$("#tab2primary #txt_contacto").val(data1.contacto);
				$("#tab2primary #cbo_estado").val(data1.contacto);
				$("#tab2primary #cbo_departamento").val(data1.ubigeo.departamento.iddepar).find('select').trigger('click');
				$("#tab2primary #cbo_provincia").val(data1.ubigeo.distrito.iddist );
				$("#tab2primary #cbo_distrito").val(data1.ubigeo.provincia.idprov);
			}
		});
	});
	
	
	$('#btn_nuevo').on('click', function () {		
		 $('#tab1').prop( "disabled", true ).addClass('disabled');
		 $('#tab2').prop( "disabled", false ).removeClass('disabled');
		 $('.nav-tabs > .active').prop( "disabled", true ).addClass('disabled').next('li').find('a').trigger('click');		
		 $('#hiddenindaccion').val(1); 
	});	
	
	$('#btn_salir').on('click', function () {
		$("#frm_Proveedor").data('bootstrapValidator').resetForm(true);/*Limpiamos todos los controles del formulario frm_Proveedor */
		$('#tab1').prop( "disabled", false ).removeClass('disabled');
		$('#tab2').prop( "disabled", true ).addClass('disabled');
		$('.nav-tabs > .active').prop( "disabled", true ).addClass('disabled').prev('li').find('a').trigger('click');    
	});
	
	$('#btn_buscar').on('click', function () {
		$("#paginador").html("");/*Limpiar los numero de paginacion*/
		$('#ModalLoading').modal('show');
		initGrilla();		
		$('#ModalLoading').modal('hide');
	});
	
	/* Valida las etiquedas del formulario */
	$("#frm_Proveedor").bootstrapValidator({
				message : 'This value is not valid',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				"txt_razon_social" : {
					validators : {
						notEmpty : {
							message : 'Ingrese Razón Social por favor.'
						}
					}
				},
				"txt_ruc" : {
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
				"txt_contacto" : {
					validators : {
						notEmpty : {
							message : 'Ingrese nombre del contacto por favor.'
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
				"txt_referencia" : {
					validators : {
						notEmpty : {
							message : 'Ingrese Referencia por favor.'
						},
						stringLength : {
							min : 1,
							max : 255,
							message : 'Ingrese 255 caracteres como maximo.'
						}
					}
				},
				"txt_fax" : {
					validators : {
						notEmpty : {
							message : 'Ingrese Nombres por favor.'
						}
					}
				},
				"txt_telefono" : {
					validators : {
						notEmpty : {
							message : 'Ingrese número telefono por favor.'
						},
						stringLength : {
							min : 1,
							max : 7,
							message : 'El telefono tiene 7 cifras maximo.'
						},
						integer : {
							message : 'Ingrese solo numeros.'
						}
					}
				},
				"txt_sitioweb" : {
					validators : {
						notEmpty : {
							message : 'Ingrese Sitio por favor.'
						}
					}
				},
				"txt_celular" : {
					validators : {
						notEmpty : {
							message : 'Ingrese número celular por favor.'
						},
						stringLength : {
							min : 1,
							max : 9,
							message : 'El celular tiene 9 cifras maximo.'
						},
						integer : {
							message : 'Ingrese solo numeros.'
						}
					}
				},
				"txt_direccion" : {
					validators : {
						notEmpty : {
							message : 'Ingrese direccion por favor.'
						}
					}
				}
			}
		});
	$('#frm_Proveedor').on('success.form.bv', function(e) {
		e.preventDefault();
		GuardarProveedor();
		$("#btn_enviar").prop("disabled", true);
	});

	$('#cbo_departamento').change(function() {
				CargarComboDependiente(this, 'cbo_provincia','LoadComboProvincia');
	});

	$('#cbo_provincia').change(function() {
				CargarComboDependiente(this, 'cbo_distrito','LoadComboDistrito');
	});
	function GuardarProveedor(){		 
		  $('#ModalLoading').modal('show');
		  $.ajax({
				url : 'proveedor?metodo=RegistrarModificarProveedor',
				type : 'post',
				data : $('#frm_Proveedor').serialize(),
				dataType:'json',
				success:function(result){
					$('#ModalLoading').modal('hide');
					var valor=eval(result);
					if(valor.codigoproveedor=="-1"){
						$('#mensajeAlerta').html("<div class='alert alert-warning'>Ocurrio un error al registrar los datos del Proveedor</div>");
					}else{
						$("#tab2primary #txt_cod_prov_guardar").val(valor.codigoproveedor);
						$('#mensajeAlerta').html("<div class='alert alert-success'>Se registro satisfactoriamente los datos del Proveedor con el codigo "+ valor.codigoproveedor +"</div>");
					}
				},
				error : function(xhr, ajaxOptions, thrownError) {
					$('#ModalLoading').modal('hide');
					$('#mensajeAlerta').html("<div class='alert alert-danger'>Ocurrio un error al registrar los datos del Proveedor</div>");
				}
		  });
		
		
	}
	function CargarComboDependiente(cboFiltro, cbocascada,action) {
		var valueSelected = cboFiltro.options[cboFiltro.selectedIndex].value;
		cbocascada = document.getElementById(cbocascada);
		LimpiarCombo(cbocascada);
		$.ajax({
			type : "get",
			url : "proveedor?metodo=" + action,
			data : {idvalue : valueSelected},
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
	 $('#eventotab2primary').prop( "disabled", true ).addClass('disabled');
});

var paginador;
var totalPaginas;
var itemsPorPagina = 5;
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
	
	var txt_codigoprov_buscar = $("#txt_codigoprov_buscar").val();
	var txt_ruc_buscar = $("#txt_ruc_buscar").val();
	var txt_razonsocial_buscar = $("#txt_razonsocial_buscar").val();
	$.ajax({
		url : 'proveedor?metodo=LoadProveedores',
		type : 'post',
		data : {txt_codigoprov_buscar:txt_codigoprov_buscar,txt_ruc_buscar:txt_ruc_buscar,txt_razonsocial_buscar:txt_razonsocial_buscar,limit:itemsPorPagina,offset:desde},
		dataType : 'json',
		success : function(result) {
			var lista=result[0];
			$("#rellenar").html("");
			var trHTML = '';
			if (lista.length > 0){
				for ( var i = 0; i < lista.length; i++) {
					trHTML += '<tr><td>'
						+ lista[i]['codigoproveedor']
						+ '</td><td>'
						+ lista[i]['razonsocial']
						+ '</td><td>'
						+ lista[i]['ruc']
						+ '</td><td>'
						+ lista[i]['correo']
						+ '</td><td>'
						+ lista[i]['telefono']
						+ '</td><td>'
						+ lista[i]['direccion']
						+ '</td><td>'
						+ lista[i]['contacto']
						+ '</td><td><a href="" data-id="'+lista[i]['codigoproveedor']+'" id="btn_editar" class="btn btn-info"><span class="fa fa-pencil-square-o " ></span> </a></td>'
						+ '</td><td><a  data-id="'+lista[i]['codigoproveedor']+'" id="btn_modaleliminar" class="btn btn-danger"><span class="fa fa-trash-o" ></span></a></td>';
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
	var txt_codigoprov_buscar = $("#txt_codigoprov_buscar").val();
	var txt_ruc_buscar = $("#txt_ruc_buscar").val();
	var txt_razonsocial_buscar = $("#txt_razonsocial_buscar").val();
	$.ajax({
		url : 'proveedor?metodo=TotalRegistrosProveedores',
		type : 'post',
		data : {txt_codigoprov_buscar:txt_codigoprov_buscar,txt_ruc_buscar:txt_ruc_buscar,txt_razonsocial_buscar:txt_razonsocial_buscar},
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
		url : 'proveedor?metodo=LoadComboDepartamento',
		type : 'get',
		data : '',
		dataType : 'json',
		success : function(result) {
			var datos = result[0];
			ComboDepartamento = document.getElementById('cbo_departamento');
			ComboDepartamento.options[0] = new Option('- Seleccione -','');
			for ( var i = 0; i < datos.length; i++) {
				ComboDepartamento.options[ComboDepartamento.length] = new Option(datos[i].departamento, datos[i].iddepar);
			}
		}
	});
}
