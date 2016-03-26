	INSERT INTO `tb_menu` (`descripcion`,`estado`,`fechacreacion`,`fechamodificacion`,`tipografia`) VALUES
	('Mantenimiento',1,'2016-01-01','2016-01-01','fa fa-fw fa-table'),
	('Compras',1,'2016-01-01','2016-01-01','fa fa-shopping-cart '),
	('Ventas',1,'2016-01-01','2016-01-01','fa fa-file-text-o'),
	('Caja',1,'2016-01-01','2016-01-01','fa fa-calculator'),
	('Inventario',1,'2016-01-01','2016-01-01','fa fa-fw fa-dashboard'),
	('Reportes',1,'2016-01-01','2016-01-01','fa fa-fw fa-bar-chart-o');
	
	/*********************************************/
	INSERT INTO `tb_submenu` (`descripcion`, `url`,`fechacreacion`,`fechamodificacion`,`idmenu` ) VALUES
	('Cliente','cliente?metodo=loadCliente','2016-01-01','2016-01-01',1),
	('Trabajador','trabajador?metodo=loadTrabajador','2016-01-01','2016-01-01',1),
	('Proveedor','proveedor?metodo=loadProveedor','2016-01-01','2016-01-01',1),
	('Marca','marca?metodo=loadMarca','2016-01-01','2016-01-01',1),
	('Producto','producto?metodo=loadProducto','2016-01-01','2016-01-01',1),
	('Tipo Documento','tipodocumento?metodo=loadTipodocumento','2016-01-01','2016-01-01',1),
	('Tienda','tienda?metodo=loadTienda','2016-01-01','2016-01-01',1),
	('Categoria','categoria?metodo=loadCategoria','2016-01-01','2016-01-01',1),
	('Moneda','moneda?metodo=loadMoneda','2016-01-01','2016-01-01',1),
	('Almacen','ruta','2016-01-01','2016-01-01',1),
	('Orden de Compra','ruta','2016-01-01','2016-01-01',2),
	('Orden de Venta','ruta','2016-01-01','2016-01-01',3),
	('Facturación','ruta','2016-01-01','2016-01-01',3),
	('Resumen Caja','ruta','2016-01-01','2016-01-01',4),
	('Gastos','ruta','2016-01-01','2016-01-01',4),
	('Ingreso Kardex','ruta','2016-01-01','2016-01-01',5),
	('Movimiento Kardex','ruta','2016-01-01','2016-01-01',5),
	('Stock','ruta','2016-01-01','2016-01-01',5),
	('Registro Venta','ruta','2016-01-01','2016-01-01',6),
	('Registro Compra','ruta','2016-01-01','2016-01-01',6),
	('Venta por Fecha','ruta','2016-01-01','2016-01-01',6),
	('Venta por Cliente','ruta','2016-01-01','2016-01-01',6),
	('Venta por producto','ruta','2016-01-01','2016-01-01',6)
	
	/*
	call usp_Ins_proveedor ('segundo mike','c@gmail.com','9898989','89898989','45454545','www.hotmail.com','12356879541'
				,'Malecon tarapaca','por la calla puhinaua','gisela ccarhuas',1,111, @p_codigoproveedor);
	select @p_codigoproveedor;
	
	insert into tb_funcionalidad (idfuncionalidad,descripcion,estado) 
	values (1,'Estado del Registro',1);
	
	insert into tb_parametrizador(idparametrizador,idparamconsecutivo,valor,simbolo,descripcion,estado,idfuncionalidad)
	values (1,1,0,'','Desactivo',1,1);
	
	insert into tb_parametrizador(idparametrizador,idparamconsecutivo,valor,simbolo,descripcion,estado,idfuncionalidad)
	values (1,2,1,'','Activo',1,1);

	*/
