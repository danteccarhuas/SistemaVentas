$(document)	.ready(function(e) {
	
	$('#btn_nuevo').on('click', function () {		
		 $('#tab1').prop( "disabled", true ).addClass('disabled');
		 $('#tab2').prop( "disabled", false ).removeClass('disabled');
		 $('.nav-tabs > .active').prop( "disabled", true ).addClass('disabled').next('li').find('a').trigger('click');		
		 
	});	
	
	$('#btn_salir').on('click', function () {
		$('#tab1').prop( "disabled", false ).removeClass('disabled');
		$('#tab2').prop( "disabled", true ).addClass('disabled');
		$('.nav-tabs > .active').prop( "disabled", true ).addClass('disabled').prev('li').find('a').trigger('click');    
	});
	
	$('#btn_buscar').on('click', function () {
		alert("soy el btn_buscar");
       // $("#frm_Proveedor").bootstrapValidator().resetForm();        
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
				url : 'proveedor?metodo=RegistrarProveedor',
				type : 'post',
				data : $('#frm_Proveedor').serialize(),
				dataType:'json',
				success:function(result){
					$('#ModalLoading').modal('hide');
					var valor=eval(result);
					if(valor.codigoproveedor=="-1"){
						$('#mensajeAlerta').html("<div class='alert alert-warning'>Ocurrio un error al registrar los datos del Proveedor</div>");
					}else{
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
	LoadCombos();
	 $('#eventotab2primary').prop( "disabled", true ).addClass('disabled');
});
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
