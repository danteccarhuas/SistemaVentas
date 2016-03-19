$(document).ready(function(e){
	/*Valida las etiquedas del formulario */
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
	
	$('#cbo_departamento').change(function(){		
		CargarComboDependiente(this,'cbo_provincia','LoadComboProvincia');
	});
	
	$('#cbo_provincia').change(function(){		
		CargarComboDependiente(this,'cbo_distrito','LoadComboDistrito');
	});
	
	
	function CargarComboDependiente(cboFiltro,cbocascada,action){
		
		var valueSelected=cboFiltro.options[cboFiltro.selectedIndex].value;
		cbocascada=document.getElementById(cbocascada);
		LimpiarCombo(cbocascada);
		$.ajax({
			type:"get",
			url:"proveedor?metodo="+action,
			data:{idvalue:valueSelected},
			dataType:'json',
			success:function(resultado){
				//var data=eval(resultado);
				var data = resultado[0];
				//alert(a);
				//alert(resultado.length);
				if(data.length>0){
					llenarCombo(data, cbocascada);
				}else{
					alert("no hay datos");
				}
			},
			error:function(xrh,ajaxOptions,thrownError){
				alert("Error status code: "+xrh.status);
				alert("Error details: "+ thrownError);
			}
		});
		}
	function LimpiarCombo(cbocascada){
		while(cbocascada.length>0){
			cbocascada.remove(cbocascada.length-1);
		}
	}
	function llenarCombo(result,cbocascada){
		if (cbocascada.id=='cbo_provincia'){			
			cbocascada.options[0]= new Option('- Seleccione -','');
			for(var i=0;i<result.length;i++){
				cbocascada.options[cbocascada.length]= new Option(result[i].provincia,result[i].idprov);
			}	
			
		}
		if (cbocascada.id=='cbo_distrito'){
			cbocascada.options[0]= new Option('- Seleccione -','');
			for(var i=0;i<result.length;i++){
				cbocascada.options[cbocascada.length]= new Option(result[i].distrito,result[i].iddist);
			}
		}
	}
});

/*Se ejecuta cuando carga por primera vez la pagina*/
$(window).load(function() {
	LoadCombos();
});
function LoadCombos() {
		$.ajax({
			url : 'proveedor?metodo=LoadComboDepartamento',
			type : 'get',
			data : '',
			dataType : 'json',
			success : function(result) {
				var datos=result[0];
				ComboDepartamento=document.getElementById('cbo_departamento');
				ComboDepartamento.options[0]= new Option('- Seleccione -','');
				for(var i=0;i<datos.length;i++){
					ComboDepartamento.options[ComboDepartamento.length]= new Option(datos[i].departamento,datos[i].iddepar);
				}
			}
		});
	}
	

