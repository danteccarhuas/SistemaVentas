INSERT INTO `bdsistemaventas`.`tb_rol` (`descripcion`, `estado`, `fechacreacion`, `fechamodificacion`) VALUES 
('Administrador', '1', '2016-01-01', '2016-01-01'),
('Trabajador', '1', '2016-01-01', '2016-01-01');


INSERT INTO `bdsistemaventas`.`tb_trabajador` (`codigotrabajador`,`nombres`, `apellidos`, `correo`, `telefono`, `dni`, `fechanacimiento`, `direccion`, `estado`, `fechacreacion`, `fechamodificacion`, `idtienda`) 
VALUES ('Admin','Administrador ', 'de Sistema', 'admin@gmail.com', '412655', '70116577', '2016-01-01', 'San Martin de Porres', '1', '2016-01-01', '2016-01-01', null);

INSERT INTO `bdsistemaventas`.`tb_usuario` (`usuario`, `password`, `fechacreacion`, `fechamodificacion`, `idrol`, `idtrabajador`) VALUES ('admin', 'admin', '2016-01-01', '2016-01-01', '1', '1');


INSERT INTO `bdsistemaventas`.`tb_permiso_rol_submenu` (`idsubmenu`, `idrol`) VALUES 
(1,1),
(2,1),
(3,1),
(4,1),
(5,1),
(6,1),
(7,1),
(8,1),
(9,1),
(10,1),
(11,1),
(12,1),
(13,1),
(14,1),
(15,1),
(16,1),
(17,1),
(18,1),
(19,1),
(20,1),
(21,1),
(22,1);


