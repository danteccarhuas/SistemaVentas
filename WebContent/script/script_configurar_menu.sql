INSERT INTO `tb_menu` (`descripcion`,`estado`,`fechacreacion`,`fechamodificacion`,`tipografia`) VALUES
('Mantenimiento',1,'2016-01-01','2016-01-01','fa fa-fw fa-table'),
('Compras',1,'2016-01-01','2016-01-01','fa fa-shopping-cart '),
('Ventas',1,'2016-01-01','2016-01-01','fa fa-file-text-o'),
('Caja',1,'2016-01-01','2016-01-01','fa fa-calculator'),
('Inventario',1,'2016-01-01','2016-01-01','fa fa-fw fa-dashboard'),
('Reportes',1,'2016-01-01','2016-01-01','fa fa-fw fa-bar-chart-o');

/*********************************************/
INSERT INTO `tb_submenu` (`descripcion`, `url`,`fechacreacion`,`fechamodificacion`,`idmenu` ) VALUES
('Cliente','ruta','2016-01-01','2016-01-01',1),
('Trajador','ruta','2016-01-01','2016-01-01',1),
('Proveedor','proveedor?metodo=loadProveedor','2016-01-01','2016-01-01',1),
('Marca','ruta','2016-01-01','2016-01-01',1),
('Producto','ruta','2016-01-01','2016-01-01',1),
('Tipo Documento','ruta','2016-01-01','2016-01-01',1),
('Asignar permisos','ruta','2016-01-01','2016-01-01',1),
('Moneda','ruta','2016-01-01','2016-01-01',1),
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


