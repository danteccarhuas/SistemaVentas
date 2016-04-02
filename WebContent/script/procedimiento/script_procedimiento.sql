drop procedure if exists usp_Sel_Menu;
DELIMITER //
CREATE PROCEDURE usp_Sel_Menu(IN p_usuario varchar(45), IN p_password varchar(45) )
BEGIN 

	SELECT M.idmenu,m.descripcion,SM.idsubmenu,SM.descripcion as descripcionsubmenu,SM.url,m.tipografia FROM tb_menu M INNER JOIN tb_submenu SM 
	ON M.idmenu=SM.idmenu INNER JOIN tb_permiso_rol_submenu psubmenu
	ON psubmenu.idsubmenu=SM.idsubmenu INNER JOIN tb_rol rol
	ON psubmenu.idrol=ROL.idrol INNER JOIN tb_usuario USU
	ON USU.idrol=ROL.idrol INNER JOIN tb_trabajador TRA
	ON TRA.idtrabajador=USU.idtrabajador
	where USU.usuario = p_usuario and USU.password = p_password order by M.idmenu,SM.descripcion asc ;
END //
DELIMITER ;

drop procedure if exists usp_Sel_validarusuario;
DELIMITER //
CREATE PROCEDURE usp_Sel_validarusuario(IN p_usuario varchar(45), IN p_password varchar(45) )
BEGIN 
 
	SELECT TRA.idtrabajador,CAST(CONCAT(tra.nombres,' ',tra.apellidos) AS char) as Bienvenido, 
	tra.estado,tra.idtienda,tra.nombres
	FROM tb_trabajador TRA inner join tb_usuario USU
		ON TRA.idtrabajador=USU.idtrabajador INNER JOIN tb_rol rol 
		ON USU.idrol=ROL.idrol 
	where USU.usuario = p_usuario and USU.password = p_password ;
END //
DELIMITER ;


drop procedure if exists usp_Sel_Departamento;
DELIMITER //
CREATE PROCEDURE usp_Sel_Departamento()
BEGIN 

	SELECT iddepar,departamento from tb_departamento order by iddepar asc ;
END //
DELIMITER ;

drop procedure if exists usp_Sel_Provincia;
DELIMITER //
CREATE PROCEDURE usp_Sel_Provincia(
IN p_iddepar int
)
BEGIN 

	SELECT idProv,provincia from tb_provincia where  iddepar= p_iddepar order by idProv asc ;
END //
DELIMITER ;

drop procedure if exists usp_Sel_Distrito;
DELIMITER //
CREATE PROCEDURE usp_Sel_Distrito(
IN p_idProv int
)
BEGIN 

	SELECT idDist,distrito from tb_distrito where idProv = p_idProv order by idDist asc ;
END //
DELIMITER ;

drop procedure if exists usp_Ins_proveedor;
DELIMITER //

CREATE  PROCEDURE usp_Ins_proveedor( 
IN  p_razonsocial VARCHAR(225), 
IN  p_correo VARCHAR(45),
IN 	p_fax VARCHAR(45),
IN	p_telefono varchar(45),
IN	p_celular varchar(25),
In	p_sitioweb varchar(45),
IN	p_ruc varchar(11), 
IN	p_direccion VARCHAR(100),
IN	p_referencia varchar(255),
IN	p_contacto varchar(45),
IN  p_estado INT,
In	p_ubigeo int,
out p_codigoproveedor VARCHAR(12)
     )
BEGIN
	declare v_cod_prov VARCHAR(12);
	set v_cod_prov='';
	 
	select  concat('PROV',CAST(RIGHT(CONCAT('00000000' , CAST(RTRIM(CAST(RIGHT(IFNULL(MAX(codigoproveedor),0),8) 
	as signed integer)+1)AS CHAR(10000) CHARACTER SET utf8) ),8)AS CHAR(10000) CHARACTER SET utf8)) 
	into v_cod_prov
	from tb_proveedor;

    INSERT INTO tb_proveedor
         (
		codigoproveedor, 
		razonsocial,
		correo,
		fax,	
		telefono,
		celular,
		sitioweb,
		ruc,
		direccion,
		referencia,
		contacto,
		estado,
		ubigeo
         )
    VALUES 
         ( 
		v_cod_prov,
		p_razonsocial , 
		p_correo ,
		p_fax ,
		p_telefono ,
		p_celular ,
		p_sitioweb ,
		p_ruc , 
		p_direccion ,
		p_referencia ,
		p_contacto ,
		p_estado ,
		p_ubigeo 
         ); 
/*		(select CURDATE()),*/

   /* select max(cod_cliente) into v_cod_aux
	from tb_cliente;*/
	set p_codigoproveedor=v_cod_prov;
END //
DELIMITER ;

drop procedure if exists usp_Cons_Proveedores;
DELIMITER //
CREATE  PROCEDURE usp_Cons_Proveedores(
IN p_codigoproveedor varchar(12),
IN p_razonsocial varchar(255),
IN p_ruc varchar(11),
IN p_limit int,
IN p_desde int

)
BEGIN 
	PREPARE STMT FROM  "select	p.codigoproveedor, 
	p.razonsocial,
	p.ruc,
	p.correo,
	p.telefono,			
	p.direccion,
	p.contacto
	from tb_proveedor  p
	where (CONCAT(p.razonsocial) like CONCAT(""%"", ?,""%"") or ''= CONCAT(""%"",?,""%""))
	and (p.codigoproveedor= ? or ? = '0')
	and (p.ruc= ? or '0' = ?) and estado = 1 order by p.razonsocial asc limit ? offset ?"  ;
	
	SET @p_codigoproveedor = p_codigoproveedor; 
	SET @p_razonsocial = p_razonsocial; 
	SET @p_ruc = p_ruc; 
	SET @p_limit = p_limit; 
	SET @p_desde = p_desde; 
	EXECUTE STMT USING @p_razonsocial,@p_razonsocial,@p_codigoproveedor,@p_codigoproveedor,@p_ruc,@p_ruc, @p_limit,@p_desde;
	DEALLOCATE PREPARE STMT;
END //
DELIMITER ;

drop procedure if exists usp_TotaRegist_Proveedores;
DELIMITER //
CREATE  PROCEDURE usp_TotaRegist_Proveedores(
IN p_codigoproveedor varchar(12),
IN p_razonsocial varchar(255),
IN p_ruc varchar(11),
OUT P_TOTALREGISTRO INT
)
BEGIN 
	declare v_TOTALREGISTRO INT;
	set v_TOTALREGISTRO=0;

	select	count(p.codigoproveedor) into v_TOTALREGISTRO
	from tb_proveedor  p
	where (CONCAT(p.razonsocial) like CONCAT("%",p_razonsocial,"%") or ''= CONCAT("%",p_razonsocial,"%"))
	and (p.codigoproveedor= p_codigoproveedor or p_codigoproveedor = '0')
	and (p.ruc= p_ruc or '0' = p_ruc) and estado = 1;
	set P_TOTALREGISTRO = v_TOTALREGISTRO;
END //
DELIMITER ;

drop procedure if exists usp_eliminar_Proveedores;
DELIMITER //
CREATE  PROCEDURE usp_eliminar_Proveedores(
IN p_codigoproveedor varchar(12)
)
BEGIN 
	update tb_proveedor set  estado = 0 
	where codigoproveedor =  p_codigoproveedor;
END //
DELIMITER ;


drop procedure if exists usp_obt_datosProveedor;
DELIMITER //
CREATE  PROCEDURE usp_obt_datosProveedor(
IN p_codigoproveedor varchar(12)
)
BEGIN 
	select 
	p.codigoproveedor,p.razonsocial,p.correo,
	p.fax,p.telefono,p.celular,p.sitioweb,p.ruc,
	p.direccion,p.referencia,p.contacto,p.estado,
	ub.iddepar,ub.idprov,ub.iddist
	from tb_proveedor p inner join tb_ubigeo ub
	on p.ubigeo = ub.ubigeo
	where codigoproveedor =  p_codigoproveedor;
END //
DELIMITER ;

drop procedure if exists usp_UPD_proveedor;
DELIMITER //

CREATE  PROCEDURE usp_UPD_proveedor( 
IN  P_codigoproveedor VARCHAR(12),
IN  p_razonsocial VARCHAR(225), 
IN  p_correo VARCHAR(45),
IN 	p_fax VARCHAR(45),
IN	p_telefono varchar(45),
IN	p_celular varchar(25),
In	p_sitioweb varchar(45),
IN	p_ruc varchar(11), 
IN	p_direccion VARCHAR(100),
IN	p_referencia varchar(255),
IN	p_contacto varchar(45),
IN  p_estado INT,
In	p_ubigeo int
     )
BEGIN
	
	UPDATE tb_proveedor 
		SET 		
		razonsocial =  p_razonsocial,
		correo =  p_correo,
		fax =  p_fax,	
		telefono = p_telefono,
		celular =  p_celular,
		sitioweb = p_sitioweb,
		ruc = p_ruc,
		direccion =  p_direccion,
		referencia =  p_referencia,
		contacto = p_contacto,
		estado = p_estado,
		ubigeo = p_ubigeo
	WHERE codigoproveedor = P_codigoproveedor;

	
END //
DELIMITER ;

drop procedure if exists usp_Ins_marca;
DELIMITER //

CREATE  PROCEDURE usp_Ins_marca( 
IN  p_descripcion VARCHAR(45),
out p_codigo int
)
BEGIN
    INSERT INTO tb_marca
         (
		descripcion,
		estado,
		fecharegistro
         )
    VALUES 
         ( 
		p_descripcion,
		1,
		CURDATE()
         ); 
set p_codigo = LAST_INSERT_ID();

END //
DELIMITER ;


drop procedure if exists usp_UPD_marca;
DELIMITER //

CREATE  PROCEDURE usp_UPD_marca( 
IN  p_descripcion VARCHAR(45),
IN  p_codigo int

     )
BEGIN
	
	UPDATE tb_marca 
		SET 		
		descripcion =  p_descripcion,
		fechamodificacion = CURDATE()
	WHERE idmarca = p_codigo;
	
END //
DELIMITER ;



drop procedure if exists usp_eliminar_marca;
DELIMITER //
CREATE  PROCEDURE usp_eliminar_marca(
IN  p_codigo int
)
BEGIN 
	update tb_marca set  estado = 0 
	where idmarca =  p_codigo;
END //
DELIMITER ;


drop procedure if exists usp_TotaRegist_Marca;
DELIMITER //
CREATE  PROCEDURE usp_TotaRegist_Marca(
IN  p_descripcion VARCHAR(45),
OUT P_TOTALREGISTRO INT
)
BEGIN 
	declare v_TOTALREGISTRO INT;
	set v_TOTALREGISTRO=0;

	select	count(p.idmarca) into v_TOTALREGISTRO
	from tb_marca  p
	where (CONCAT(p.descripcion) like CONCAT("%",p_descripcion,"%") or ''= CONCAT("%",p_descripcion,"%"))	
	and estado = 1;
	set P_TOTALREGISTRO = v_TOTALREGISTRO;
END //
DELIMITER ;


drop procedure if exists usp_Cons_Marca;
DELIMITER //
CREATE  PROCEDURE usp_Cons_Marca(
IN  p_descripcion VARCHAR(45),
IN p_limit int,
IN p_desde int

)
BEGIN 
	PREPARE STMT FROM  "select	p.idmarca, 
	p.descripcion	
	from tb_marca  p
	where (CONCAT(p.descripcion) like CONCAT(""%"", ?,""%"") or ''= CONCAT(""%"",?,""%""))
	and estado = 1 order by p.descripcion asc limit ? offset ?"  ;
	
	SET @p_descripcion = p_descripcion; 	
	SET @p_limit = p_limit; 
	SET @p_desde = p_desde; 
	EXECUTE STMT USING @p_descripcion,@p_descripcion, @p_limit,@p_desde;
	DEALLOCATE PREPARE STMT;
END //
DELIMITER ;


drop procedure if exists usp_obt_datosMarca;
DELIMITER //
CREATE  PROCEDURE usp_obt_datosMarca(
IN  p_codigo int
)
BEGIN 
	select 
	p.idmarca,p.descripcion
	from tb_marca p 
	where idmarca =  p_codigo;
END //
DELIMITER ;

/*****************procimiento de moneda************************/


drop procedure if exists usp_Ins_moneda;
DELIMITER //

CREATE  PROCEDURE usp_Ins_moneda( 
IN  p_descripcion VARCHAR(45),
IN  p_simbolo VARCHAR(25),
out p_codigo int
)
BEGIN
    INSERT INTO tb_moneda
         (
		descripcion,
		estado,
		simbolo,
		fecharegistro
         )
    VALUES 
         ( 
		p_descripcion,
		1,
		p_simbolo,
		CURDATE()
         ); 
set p_codigo = LAST_INSERT_ID();

END //
DELIMITER ;


drop procedure if exists usp_UPD_moneda;
DELIMITER //

CREATE  PROCEDURE usp_UPD_moneda( 
IN  p_descripcion VARCHAR(45),
IN  p_simbolo VARCHAR(25),
IN  p_codigo int

     )
BEGIN
	
	UPDATE tb_moneda 
		SET 		
		descripcion =  p_descripcion,
		simbolo =  p_simbolo,
		fechamodificacion = CURDATE()
	WHERE idmoneda = p_codigo;
	
END //
DELIMITER ;



drop procedure if exists usp_eliminar_moneda;
DELIMITER //
CREATE  PROCEDURE usp_eliminar_moneda(
IN  p_codigo int
)
BEGIN 
	update tb_moneda set  estado = 0 
	where idmoneda =  p_codigo;
END //
DELIMITER ;


drop procedure if exists usp_TotaRegist_Marca;
DELIMITER //
CREATE  PROCEDURE usp_TotaRegist_Marca(
IN  p_descripcion VARCHAR(45),
OUT P_TOTALREGISTRO INT
)
BEGIN 
	declare v_TOTALREGISTRO INT;
	set v_TOTALREGISTRO=0;

	select	count(p.idmoneda) into v_TOTALREGISTRO
	from tb_moneda  p
	where (CONCAT(p.descripcion) like CONCAT("%",p_descripcion,"%") or ''= CONCAT("%",p_descripcion,"%"))	
	and estado = 1;
	set P_TOTALREGISTRO = v_TOTALREGISTRO;
END //
DELIMITER ;


drop procedure if exists usp_Cons_moneda;
DELIMITER //
CREATE  PROCEDURE usp_Cons_moneda(
IN  p_descripcion VARCHAR(45),
IN p_limit int,
IN p_desde int

)
BEGIN 
	PREPARE STMT FROM  "select	p.idmoneda, 
	p.descripcion,p.simbolo
	from tb_moneda  p
	where (CONCAT(p.descripcion) like CONCAT(""%"", ?,""%"") or ''= CONCAT(""%"",?,""%""))
	and estado = 1 order by p.descripcion asc limit ? offset ?"  ;
	
	SET @p_descripcion = p_descripcion; 	
	SET @p_limit = p_limit; 
	SET @p_desde = p_desde; 
	EXECUTE STMT USING @p_descripcion,@p_descripcion, @p_limit,@p_desde;
	DEALLOCATE PREPARE STMT;
END //
DELIMITER ;


drop procedure if exists usp_obt_datosmoneda;
DELIMITER //
CREATE  PROCEDURE usp_obt_datosmoneda(
IN  p_codigo int
)
BEGIN 
	select 
	p.idmoneda,p.descripcion,p.simbolo
	from tb_moneda p 
	where idmoneda =  p_codigo;
END //
DELIMITER ;


/*****************procimiento de tipo documento************************/


drop procedure if exists usp_Ins_tipodocumento;
DELIMITER //

CREATE  PROCEDURE usp_Ins_tipodocumento( 
IN  p_descripcion VARCHAR(45),
IN  p_abreviatura VARCHAR(45),
out p_codigo int
)
BEGIN
    INSERT INTO tb_tipodocumento
         (
		descripcion,
		estado,
		abreviatura,
		fecharegistro
         )
    VALUES 
         ( 
		p_descripcion,
		1,
		p_abreviatura,
		CURDATE()
         ); 
	set p_codigo = LAST_INSERT_ID();
	
	insert into tb_configurarcorrelativo
	(serie,correlativo,idtipodocumento) 
	values('','',p_codigo);
END //
DELIMITER ;


drop procedure if exists usp_UPD_tipodocumento;
DELIMITER //

CREATE  PROCEDURE usp_UPD_tipodocumento( 
IN  p_descripcion VARCHAR(45),
IN  p_abreviatura VARCHAR(45),
IN  p_codigo int

     )
BEGIN
	
	UPDATE tb_tipodocumento 
		SET 		
		descripcion =  p_descripcion,
		abreviatura =  p_abreviatura,
		fechamodificacion = CURDATE()
	WHERE idtipodocumento = p_codigo;
	
END //
DELIMITER ;



drop procedure if exists usp_eliminar_tipodocumento;
DELIMITER //
CREATE  PROCEDURE usp_eliminar_tipodocumento(
IN  p_codigo int
)
BEGIN 
	update tb_tipodocumento set  estado = 0 
	where idtipodocumento =  p_codigo;
END //
DELIMITER ;


drop procedure if exists usp_TotaRegist_tipodocumento;
DELIMITER //
CREATE  PROCEDURE usp_TotaRegist_tipodocumento(
IN  p_descripcion VARCHAR(45),
OUT P_TOTALREGISTRO INT
)
BEGIN 
	declare v_TOTALREGISTRO INT;
	set v_TOTALREGISTRO=0;

	select	count(p.idtipodocumento) into v_TOTALREGISTRO
	from tb_tipodocumento  p
	where (CONCAT(p.descripcion) like CONCAT("%",p_descripcion,"%") or ''= CONCAT("%",p_descripcion,"%"))	
	and estado = 1;
	set P_TOTALREGISTRO = v_TOTALREGISTRO;
END //
DELIMITER ;


drop procedure if exists usp_Cons_tipodocumento;
DELIMITER //
CREATE  PROCEDURE usp_Cons_tipodocumento(
IN  p_descripcion VARCHAR(45),
IN p_limit int,
IN p_desde int

)
BEGIN 
	PREPARE STMT FROM  "select	p.idtipodocumento, 
	p.descripcion,p.abreviatura
	from tb_tipodocumento  p
	where (CONCAT(p.descripcion) like CONCAT(""%"", ?,""%"") or ''= CONCAT(""%"",?,""%""))
	and estado = 1 order by p.descripcion asc limit ? offset ?"  ;
	
	SET @p_descripcion = p_descripcion; 	
	SET @p_limit = p_limit; 
	SET @p_desde = p_desde; 
	EXECUTE STMT USING @p_descripcion,@p_descripcion, @p_limit,@p_desde;
	DEALLOCATE PREPARE STMT;
END //
DELIMITER ;


drop procedure if exists usp_obt_datostipodocumento;
DELIMITER //
CREATE  PROCEDURE usp_obt_datostipodocumento(
IN  p_codigo int
)
BEGIN 
	select 
	p.idtipodocumento,p.descripcion,p.abreviatura
	from tb_tipodocumento p 
	where p.idtipodocumento =  p_codigo;
END //
DELIMITER ;



/*****************procimiento de categoria************************/


drop procedure if exists usp_Ins_categoria;
DELIMITER //

CREATE  PROCEDURE usp_Ins_categoria( 
IN  p_descripcion VARCHAR(45),
out p_codigo int
)
BEGIN
    INSERT INTO tb_categoria
         (
		descripcion,
		estado,
		fecharegistro
         )
    VALUES 
         ( 
		p_descripcion,
		1,
		CURDATE()
         ); 
set p_codigo = LAST_INSERT_ID();

END //
DELIMITER ;


drop procedure if exists usp_UPD_categoria;
DELIMITER //

CREATE  PROCEDURE usp_UPD_categoria( 
IN  p_descripcion VARCHAR(45),
IN  p_codigo int

     )
BEGIN
	
	UPDATE tb_categoria 
		SET 		
		descripcion =  p_descripcion,
		fechamodificacion = CURDATE()
	WHERE idcategoria = p_codigo;
	
END //
DELIMITER ;



drop procedure if exists usp_eliminar_categoria;
DELIMITER //
CREATE  PROCEDURE usp_eliminar_categoria(
IN  p_codigo int
)
BEGIN 
	update tb_categoria set  estado = 0 
	where idcategoria =  p_codigo;
END //
DELIMITER ;


drop procedure if exists usp_TotaRegist_categoria;
DELIMITER //
CREATE  PROCEDURE usp_TotaRegist_categoria(
IN  p_descripcion VARCHAR(45),
OUT P_TOTALREGISTRO INT
)
BEGIN 
	declare v_TOTALREGISTRO INT;
	set v_TOTALREGISTRO=0;

	select	count(p.idcategoria) into v_TOTALREGISTRO
	from tb_categoria  p
	where (CONCAT(p.descripcion) like CONCAT("%",p_descripcion,"%") or ''= CONCAT("%",p_descripcion,"%"))	
	and estado = 1;
	set P_TOTALREGISTRO = v_TOTALREGISTRO;
END //
DELIMITER ;


drop procedure if exists usp_Cons_categoria;
DELIMITER //
CREATE  PROCEDURE usp_Cons_categoria(
IN  p_descripcion VARCHAR(45),
IN p_limit int,
IN p_desde int

)
BEGIN 
	PREPARE STMT FROM  "select	p.idcategoria, 
	p.descripcion
	from tb_categoria  p
	where (CONCAT(p.descripcion) like CONCAT(""%"", ?,""%"") or ''= CONCAT(""%"",?,""%""))
	and estado = 1 order by p.descripcion asc limit ? offset ?"  ;
	
	SET @p_descripcion = p_descripcion; 	
	SET @p_limit = p_limit; 
	SET @p_desde = p_desde; 
	EXECUTE STMT USING @p_descripcion,@p_descripcion, @p_limit,@p_desde;
	DEALLOCATE PREPARE STMT;
END //
DELIMITER ;


drop procedure if exists usp_obt_datoscategoria;
DELIMITER //
CREATE  PROCEDURE usp_obt_datoscategoria(
IN  p_codigo int
)
BEGIN 
	select 
	p.idcategoria,p.descripcion
	from tb_categoria p 
	where p.idcategoria =  p_codigo;
END //
DELIMITER ;


/*****************procimiento de CLIENTE************************/

drop procedure if exists usp_Ins_cliente;
DELIMITER //

CREATE  PROCEDURE usp_Ins_cliente( 
IN  p_nombres VARCHAR(45), 
IN  p_apellidos VARCHAR(45),
IN 	p_correo VARCHAR(45),
IN	p_celular varchar(45),
IN	p_telefono int,
In	p_idtipocliente int,
IN	p_ruc varchar(11), 
IN	p_dni VARCHAR(8),
IN  p_estado INT,
In	p_ubigeo int,
In	p_idsexo int,
out p_codigocliente VARCHAR(12)
     )
BEGIN
	declare v_cod_cli VARCHAR(12);
	set v_cod_cli='';
	 
	select  concat('CLI',CAST(RIGHT(CONCAT('00000000' , CAST(RTRIM(CAST(RIGHT(IFNULL(MAX(codigocliente),0),8) 
	as signed integer)+1)AS CHAR(10000) CHARACTER SET utf8) ),8)AS CHAR(10000) CHARACTER SET utf8)) 
	into v_cod_cli
	from tb_cliente;

    INSERT INTO Tb_cliente
         (
			codigocliente,
			nombres,
			apellidos,
			correo,
			celular,
			telefono,
			fecha_creacion,
			idtipocliente,
			ruc,dni,
			estado,
			ubigeo,
			idsexo
         )
    VALUES 
         ( 
		v_cod_cli,
		p_nombres, 
		p_apellidos,
		p_correo,
		p_celular,
		p_telefono ,
		CURDATE(),
		p_idtipocliente ,
		p_ruc, 
		p_dni,
		p_estado ,
		p_ubigeo ,
		p_idsexo 
         ); 

	set p_codigocliente=v_cod_cli;
END //
DELIMITER ;

drop procedure if exists usp_Sel_pametrizador;
DELIMITER //
CREATE PROCEDURE usp_Sel_pametrizador(IN p_idfuncionalidad int)
BEGIN 

	select valor,descripcion from tb_parametrizador 
	where idfuncionalidad = p_idfuncionalidad and estado = 1 order by descripcion asc;
END //
DELIMITER ;




/*****************procimiento de Tienda************************/
drop procedure if exists usp_Ins_tienda;
DELIMITER //

CREATE  PROCEDURE usp_Ins_tienda( 
IN  p_Descripcion VARCHAR(45), 
IN  p_Direccion VARCHAR(255),
IN 	p_telefono VARCHAR(25),
IN	p_estado int,
out p_codigotienda int
     )
BEGIN	

    INSERT INTO tb_tienda
         (
			Descripcion,
			Direccion,
			telefono,
			estado,
			fechacreacion
         )
    VALUES 
         ( 
			p_Descripcion , 
			p_Direccion ,
			p_telefono ,
			p_estado ,
			CURDATE()
         ); 
	set p_codigotienda = LAST_INSERT_ID();
END //
DELIMITER ;


drop procedure if exists usp_UPD_tienda;
DELIMITER //

CREATE  PROCEDURE usp_UPD_tienda( 
IN  p_Descripcion VARCHAR(45), 
IN  p_Direccion VARCHAR(255),
IN 	p_telefono VARCHAR(25),
IN	p_estado int,
in p_codigotienda int
     )
BEGIN
	
    UPDATE  tb_tienda
		SET 
			Descripcion =p_Descripcion,
			Direccion = p_Direccion,
			telefono =p_telefono,
			estado = p_estado,
			fechamodificacion=curdate()	
	WHERE idtienda = p_codigotienda;
	
END //
DELIMITER ;



drop procedure if exists usp_eliminar_tienda;
DELIMITER //
CREATE  PROCEDURE usp_eliminar_tienda(
IN  p_codigo int
)
BEGIN 
	update tb_tienda set  estado = 0 
	where idtienda =  p_codigo;
END //
DELIMITER ;


drop procedure if exists usp_TotaRegist_tienda;
DELIMITER //
CREATE  PROCEDURE usp_TotaRegist_tienda(
IN  p_Descripcion VARCHAR(45), 
OUT P_TOTALREGISTRO INT
)
BEGIN 
	declare v_TOTALREGISTRO INT;
	set v_TOTALREGISTRO=0;

	select	count(p.idtienda) into v_TOTALREGISTRO
	from tb_tienda  p
	where (CONCAT(p.Descripcion) like CONCAT("%",p_Descripcion,"%") or ''= CONCAT("%",p_Descripcion,"%"))	
	and estado = 1;
	set P_TOTALREGISTRO = v_TOTALREGISTRO;
END //
DELIMITER ;


drop procedure if exists usp_Cons_tienda;
DELIMITER //
CREATE  PROCEDURE usp_Cons_tienda(
IN  p_descripcion VARCHAR(45),
IN p_limit int,
IN p_desde int

)
BEGIN 
	PREPARE STMT FROM  "select	p.idtienda, 
	p.Descripcion,p.Direccion,p.telefono
	from tb_tienda  p
	where (CONCAT(p.Descripcion) like CONCAT(""%"", ?,""%"") or ''= CONCAT(""%"",?,""%""))
	and estado = 1 order by p.descripcion asc limit ? offset ?"  ;
	
	SET @p_descripcion = p_descripcion; 	
	SET @p_limit = p_limit; 
	SET @p_desde = p_desde; 
	EXECUTE STMT USING @p_descripcion,@p_descripcion, @p_limit,@p_desde;
	DEALLOCATE PREPARE STMT;
END //
DELIMITER ;


drop procedure if exists usp_obt_datostienda;
DELIMITER //
CREATE  PROCEDURE usp_obt_datostienda(
IN  p_codigo int
)
BEGIN 
	select 
	p.idtienda,p.Descripcion,p.Direccion,p.telefono,p.estado
	from tb_tienda p 
	where p.idtienda =  p_codigo;
END //
DELIMITER ;

drop procedure if exists usp_TotaRegist_Trabajadores;
DELIMITER //
CREATE  PROCEDURE usp_TotaRegist_Trabajadores(
IN p_codigotrabajador varchar(12),
IN p_nombres varchar(45),
IN p_dni varchar(11),
OUT P_TOTALREGISTRO INT
)
BEGIN 
	declare v_TOTALREGISTRO INT;
	set v_TOTALREGISTRO=0;

	select	count(t.codigotrabajador) into v_TOTALREGISTRO
	from tb_trabajador t
	where (CONCAT(t.nombres,' ',t.apellidos) like CONCAT("%",p_nombres,"%") or 
		''= CONCAT("%",p_nombres,"%"))
	and (t.codigotrabajador= p_codigotrabajador or p_codigotrabajador = '0')
	and (t.dni= p_dni or '0' = p_dni) and estado = 1 and codigotrabajador not in ('Admin');
	set P_TOTALREGISTRO = v_TOTALREGISTRO;
END //
DELIMITER ;


drop procedure if exists usp_Cons_Trabajadores;
DELIMITER //
CREATE  PROCEDURE usp_Cons_Trabajadores(
IN p_codigotrabajador varchar(12),
IN p_nombres varchar(45),
IN p_dni varchar(11),
IN p_limit int,
IN p_desde int

)
BEGIN 
	PREPARE STMT FROM  "select	t.codigotrabajador, 
	concat(t.nombres,' ',t.apellidos ) 'trabajador',
	t.correo,
	t.dni,
	t.direccion,
	t.telefono,
	td.Descripcion
	from tb_trabajador t inner join tb_tienda td
	on t.idtienda=td.idtienda
	where (CONCAT(t.nombres,' ',t.apellidos) like CONCAT(""%"", ?,""%"") or ''= CONCAT(""%"",?,""%""))
	and (t.codigotrabajador= ? or ? = '0')
	and (t.dni= ? or '0' = ?) and t.estado = 1 and codigotrabajador not in ('Admin') order by t.nombres asc limit ? offset ?"  ;
	
	SET @p_codigotrabajador = p_codigotrabajador; 
	SET @p_nombres = p_nombres; 
	SET @p_dni = p_dni; 
	SET @p_limit = p_limit; 
	SET @p_desde = p_desde; 
	EXECUTE STMT USING @p_nombres,@p_nombres,@p_codigotrabajador,@p_codigotrabajador,@p_dni,@p_dni, 
						@p_limit,@p_desde;
	DEALLOCATE PREPARE STMT;
END //
DELIMITER ;


drop procedure if exists usp_Sel_Tienda;
DELIMITER //
CREATE PROCEDURE usp_Sel_Tienda()
BEGIN 

	select idtienda,Descripcion from tb_tienda 
	where estado = 1 order by descripcion asc;
END //
DELIMITER ;

drop procedure if exists usp_Ins_trabajador;
DELIMITER //

CREATE  PROCEDURE usp_Ins_trabajador( 
IN  p_nombres VARCHAR(45), 
IN  p_apellidos VARCHAR(45),
IN 	p_correo VARCHAR(45),
IN	p_telefono varchar(25),
IN	p_dni varchar(45),
In	p_fechanacimiento date,
IN	p_direccion varchar(225), 
IN	p_estado int(11),
IN	p_idtienda int(11),
out p_codigotrabajador VARCHAR(12)
     )
BEGIN
	declare v_cod_trab VARCHAR(12);
	set v_cod_trab='';
	 

	select cliente into v_cod_trab from (
	select  concat('TRAB',CAST(RIGHT(CONCAT('00000000' , CAST(RTRIM(CAST(RIGHT(IFNULL(MAX(codigotrabajador),0),8) 
	as signed integer)+1)AS CHAR(10000) CHARACTER SET utf8) ),8)AS CHAR(10000) CHARACTER SET utf8)) as cliente 
	from tb_trabajador) as tabla;

    INSERT INTO tb_trabajador
         (
		codigotrabajador, 
		nombres,
		apellidos,
		correo,	
		telefono,
		dni,
		fechanacimiento,
		direccion,
		estado,
		fechacreacion,
		fechamodificacion,
		idtienda
         )
    VALUES 
         ( 
		v_cod_trab,
		p_nombres , 
		p_apellidos ,
		p_correo ,
		p_telefono ,
		p_dni ,
		p_fechanacimiento ,
		p_direccion , 
		p_estado ,
		(select now()) ,
		(select now()) ,
		p_idtienda 
         ); 

	set p_codigotrabajador=v_cod_trab;
END //
DELIMITER ;


drop procedure if exists usp_obt_datosTrabajador;
DELIMITER //
CREATE  PROCEDURE usp_obt_datosTrabajador(
IN p_codigotrabajador varchar(12)
)
BEGIN 
	select 
	t.codigotrabajador,t.nombres,t.apellidos,
	t.correo,t.telefono,t.dni,t.fechanacimiento,t.direccion,
	t.estado,t.idtienda
	from tb_trabajador t
	where t.codigotrabajador =  p_codigotrabajador;
END //
DELIMITER ;

drop procedure if exists usp_UPD_trabajador;
DELIMITER //

CREATE  PROCEDURE usp_UPD_trabajador( 
IN  P_codigotrabajador VARCHAR(12),
IN  p_nombres VARCHAR(45), 
IN  p_apellidos VARCHAR(45),
IN 	p_correo VARCHAR(45),
IN	p_telefono varchar(25),
IN	p_dni varchar(45),
In	p_fechanacimiento date,
IN	p_direccion varchar(225), 
IN	p_estado int(11),
In	p_idtienda int(11)
     )
BEGIN
	
	UPDATE tb_trabajador 
		SET 		
		nombres =  p_nombres,
		apellidos =  p_apellidos,
		correo =  p_correo,	
		telefono = p_telefono,
		dni =  p_dni,
		fechanacimiento = p_fechanacimiento,
		direccion = p_direccion,
		estado =  p_estado,
		fechamodificacion =  (select now()),
		idtienda = p_idtienda
	WHERE codigotrabajador = P_codigotrabajador;

	
END //
DELIMITER ;

drop procedure if exists usp_eliminar_Trabajadores;
DELIMITER //
CREATE  PROCEDURE usp_eliminar_Trabajadores(
IN p_codigotrabajador varchar(12)
)
BEGIN 
	update tb_trabajador set  estado = 0 
	where codigotrabajador =  p_codigotrabajador;
END //
DELIMITER ;

drop procedure if exists usp_sel_Marca;
DELIMITER //
CREATE  PROCEDURE usp_sel_Marca(
)
BEGIN 
	select	p.idmarca, 
	p.descripcion	
	from tb_marca  p where p.estado = 1 ;
	
END //
DELIMITER ;

drop procedure if exists usp_sel_categoria;
DELIMITER //
CREATE  PROCEDURE usp_sel_categoria(
)
BEGIN 
	select	p.idcategoria, 
	p.descripcion
	from tb_categoria  p
	where p.estado = 1 ;
END //
DELIMITER ;


/*******Procedimiento para matenimiento producto*************/


drop procedure if exists usp_Ins_producto;
DELIMITER //

CREATE  PROCEDURE usp_Ins_producto( 
IN  p_nombre VARCHAR(50), 
IN  p_descripcion VARCHAR(255),
IN	p_estado int(11),
IN	p_preciocompra decimal(12,5),
In	p_precioventa decimal(12,5),
IN	p_idmarca int(11), 
IN	p_idcategoria int(11),
IN	p_idgenero int(11),
IN	p_talla varchar(45),
out p_codigoproducto VARCHAR(12)
     )
BEGIN
	declare v_cod_prod VARCHAR(12);
	set v_cod_prod='';
	 
	select  concat('PROD',CAST(RIGHT(CONCAT('00000000' , CAST(RTRIM(CAST(RIGHT(IFNULL(MAX(codigoproducto),0),8) 
	as signed integer)+1)AS CHAR(10000) CHARACTER SET utf8) ),8)AS CHAR(10000) CHARACTER SET utf8)) 
	into v_cod_prod
	from tb_producto;

    INSERT INTO tb_producto
         (
			codigoproducto,
			nombre,
			descripcion,
			estado,
			preciocompra,
			precioventa,
			fechacreacion,
			idmarca,
			idcategoria,
			idgenero,
			talla)
    VALUES 
         ( 
		v_cod_prod,
		p_nombre , 
		p_descripcion ,
		p_estado ,
		p_preciocompra ,
		p_precioventa ,
		(select now()) ,
		p_idmarca ,
		p_idcategoria , 
		p_idgenero,
		p_talla
         ); 
	set p_codigoproducto=v_cod_prod;
END //
DELIMITER ;





drop procedure if exists usp_Cons_Producto;
DELIMITER //
CREATE  PROCEDURE usp_Cons_Producto(
IN p_codigoproducto varchar(12),
IN p_nombre varchar(50),
IN p_idmarca int(11),
IN p_idcategoria int(11),
IN p_idgenero int(11),
IN p_limit int,
IN p_desde int

)
BEGIN 
	PREPARE STMT FROM  "select	p.codigoproducto, 
	p.nombre,
	p.preciocompra,
	p.precioventa,
	p.talla,			
	p.idmarca,
	(select descripcion from tb_marca where idmarca= p.idmarca) as descripmarca,
	p.idcategoria,
	(select descripcion from tb_categoria where idcategoria= p.idcategoria) as descripcategoria,
	p.idgenero,
	(select descripcion from tb_parametrizador where idfuncionalidad = 2 and valor = p.idgenero) as descripgenero
	from tb_producto  p
	where (CONCAT(p.nombre) like CONCAT(""%"", ?,""%"") or ''= CONCAT(""%"",?,""%""))
	and (p.codigoproducto= ? or ? = '')
	and (p.idmarca= ? or '0' = ?) 
	and (p.idcategoria= ? or '0' = ?) 
	and (p.idgenero= ? or '0' = ?) 
	and estado = 1 order by p.codigoproducto asc limit ? offset ?"  ;
	
	SET @p_codigoproducto = p_codigoproducto; 
	SET @p_nombre = p_nombre; 
	SET @p_idmarca = p_idmarca; 
	SET @p_idcategoria = p_idcategoria; 
	SET @p_idgenero = p_idgenero; 
	SET @p_limit = p_limit; 
	SET @p_desde = p_desde; 
	EXECUTE STMT USING @p_nombre,@p_nombre,@p_codigoproducto,@p_codigoproducto,@p_idmarca,@p_idmarca,
					   @p_idcategoria,@p_idcategoria,@p_idgenero,@p_idgenero, @p_limit,@p_desde;
	DEALLOCATE PREPARE STMT;
END //
DELIMITER ;

drop procedure if exists usp_TotaRegist_Producto;
DELIMITER //
CREATE  PROCEDURE usp_TotaRegist_Producto(
IN p_codigoproducto varchar(12),
IN p_nombre varchar(50),
IN p_idmarca int(11),
IN p_idcategoria int(11),
IN p_idgenero int(11),
OUT P_TOTALREGISTRO INT
)
BEGIN 
	declare v_TOTALREGISTRO INT;
	set v_TOTALREGISTRO=0;

	select	count(p.idproducto) into v_TOTALREGISTRO
	from tb_producto  p
	where  (CONCAT(p.nombre) like CONCAT("%", p_nombre ,"%") or ''= CONCAT("%", p_nombre ,"%"))
	and (p.codigoproducto= p_codigoproducto or p_codigoproducto = '')
	and (p.idmarca= p_idmarca or '0' = p_idmarca) 
	and (p.idcategoria= p_idcategoria or '0' = p_idcategoria) 
	and (p.idgenero= p_idgenero or '0' = p_idgenero) 
	and estado = 1;
	set P_TOTALREGISTRO = v_TOTALREGISTRO;
END //
DELIMITER ;



drop procedure if exists usp_obt_datosProducto;
DELIMITER //
CREATE  PROCEDURE usp_obt_datosProducto(
IN  p_codigoproducto varchar(12)
)
BEGIN 
	select	p.codigoproducto, 
	p.nombre,
	p.descripcion,
	p.preciocompra,
	p.precioventa,
	p.talla,			
	p.idmarca,
	(select descripcion from tb_marca where idmarca= p.idmarca) as descripmarca,
	p.idcategoria,
	(select descripcion from tb_categoria where idcategoria= p.idcategoria) as descripcategoria,
	p.idgenero,
	(select descripcion from tb_parametrizador where idfuncionalidad = 2 and valor = p.idgenero) as descripgenero,
	p.estado
	from tb_producto  p	
	where p.codigoproducto =  p_codigoproducto;
END //
DELIMITER ;
drop procedure if exists usp_eliminar_Producto;
DELIMITER //
CREATE  PROCEDURE usp_eliminar_Producto(
IN  p_codigoproducto VARCHAR(12)
)
BEGIN 
	update tb_producto set  estado = 0 
	where codigoproducto =  p_codigoproducto;
END //
DELIMITER ;


drop procedure if exists usp_UPD_producto;
DELIMITER //

CREATE  PROCEDURE usp_UPD_producto( 
IN  p_nombre VARCHAR(50), 
IN  p_descripcion VARCHAR(255),
IN	p_estado int(11),
IN	p_preciocompra decimal(12,5),
In	p_precioventa decimal(12,5),
IN	p_idmarca int(11), 
IN	p_idcategoria int(11),
IN	p_idgenero int(11),
IN	p_talla varchar(45),
IN  p_codigoproducto VARCHAR(12)

     )
BEGIN

	UPDATE tb_producto 
		SET 		
		nombre = p_nombre,
		descripcion = p_descripcion,
		estado = p_estado,
		preciocompra= p_preciocompra,
		precioventa = p_precioventa,
		fechamodificacion = CURDATE(),
		idmarca =  p_idmarca,
		idcategoria = p_idcategoria,
		idgenero = p_idgenero,
		talla = p_talla		
	WHERE codigoproducto =p_codigoproducto;
	
END //
DELIMITER ;



drop procedure if exists usp_Ins_Cliente;
DELIMITER //

CREATE  PROCEDURE usp_Ins_Cliente( 
IN  p_nombres VARCHAR(45), 
IN  p_apellidos VARCHAR(45),
IN 	p_correo VARCHAR(45),
IN	p_celular varchar(25),
IN	p_telefono varchar(25),
IN	p_direccion varchar(100),
IN	p_referencia varchar(100),
IN	p_idtipocliente int(11),
IN	p_ruc varchar(11),
IN	p_dni varchar(8),
IN	p_estado int(11),
IN	p_ubigeo int(11),
IN	p_idsexo int(11),
out p_codigocliente VARCHAR(12)
     )
BEGIN
	declare v_cod_cli VARCHAR(12);
	set v_cod_cli='';
	 

	select cliente into v_cod_cli from (
	select  concat('CLI',CAST(RIGHT(CONCAT('000000000' , CAST(RTRIM(CAST(RIGHT(IFNULL(MAX(codigocliente),0),8) 
	as signed integer)+1)AS CHAR(10000) CHARACTER SET utf8) ),8)AS CHAR(10000) CHARACTER SET utf8)) as cliente 
	from tb_cliente) as tabla;

    INSERT INTO tb_cliente
         (
		codigocliente,
		nombres,
		apellidos,
		correo,	
		celular,
		telefono,
		direccion,
		referencia,
		fecha_creacion,
		idtipocliente,
		ruc,
		dni,		
		estado,
		ubigeo,
		idgenero
		)
    VALUES 
         ( 
		v_cod_cli,
		p_nombres , 
		p_apellidos ,
		p_correo ,
		p_celular ,
		p_telefono,
		p_direccion,
		p_referencia,
		(select now()),
		p_idtipocliente,
		p_ruc,
		p_dni ,
		p_estado,
		p_ubigeo,
		p_idsexo
		 ); 
	set p_codigocliente=v_cod_cli;
END //
DELIMITER ;



drop procedure if exists usp_Upd_Cliente;
DELIMITER //

CREATE  PROCEDURE usp_Upd_Cliente( 
IN  p_nombres VARCHAR(45), 
IN  p_apellidos VARCHAR(45),
IN 	p_correo VARCHAR(45),
IN	p_celular varchar(25),
IN	p_telefono varchar(25),
IN	p_direccion varchar(100),
IN	p_referencia varchar(100),
IN	p_idtipocliente int(11),
IN	p_ruc varchar(11),
IN	p_dni varchar(8),
IN	p_estado int(11),
IN	p_ubigeo int(11),
IN	p_idsexo int(11),
in p_codigocliente VARCHAR(12)
     )
BEGIN
	UPDATE tb_cliente 
		SET 		
		nombres=p_nombres,
		apellidos=p_apellidos,
		correo=p_correo,	
		celular=p_celular,
		telefono=p_telefono,
		direccion=p_direccion,
		referencia=p_referencia,
		fecha_modificacion=(select now()),
		idtipocliente=p_idtipocliente,
		ruc=p_ruc,
		dni=p_dni,		
		estado=p_estado,
		ubigeo=p_ubigeo,
		idgenero=p_idsexo
	WHERE codigocliente =p_codigocliente;  
END //
DELIMITER ;

drop procedure if exists usp_eliminar_cliente;
DELIMITER //
CREATE  PROCEDURE usp_eliminar_cliente(
IN  p_codigo int
)
BEGIN 
	update tb_cliente set  estado = 0 
	where codigocliente =  p_codigo;
END //
DELIMITER ;


/*
call usp_Cons_Cliente ('','',0,'0','0',0,5,0);
*/
drop procedure if exists usp_Cons_Cliente;
DELIMITER //
CREATE  PROCEDURE usp_Cons_Cliente(
IN p_codigocliente varchar(12),
IN p_nombres varchar(100),
IN p_idtipocliente int(11),
IN p_ruc int(11),
IN p_dni int(11),
IN p_idgenero int(11),
IN p_limit int,
IN p_desde int

)
BEGIN 
	PREPARE STMT FROM  "
	select	
	p.codigocliente, 
	p.nombres,
	p.apellidos,
	p.correo,
	p.celular,			
	p.idtipocliente,
	(select descripcion from tb_parametrizador where idfuncionalidad = 3 and valor = p.idtipocliente) as descripTipocliente,
	p.ruc,
	p.dni,
	p.idgenero,
	(select descripcion from tb_parametrizador where idfuncionalidad = 2 and valor = p.idgenero) as descripgenero
	from tb_cliente  p
	where (CONCAT(p.nombres, p.apellidos) like CONCAT(""%"", ?,""%"") or ''= CONCAT(""%"",?,""%""))
	and (p.codigocliente= ? or ? = '')
	and (p.idtipocliente= ? or 0 = ?) 
	and (p.ruc= ? or '0' = ?) 
	and (p.dni= ? or '0' = ?) 
	and (p.idgenero= ? or 0 = ?) 
	and estado = 1 order by p.codigocliente asc limit ? offset ?"  ;
	
	SET @p_codigocliente = p_codigocliente; 
	SET @p_nombres = p_nombres; 
	SET @p_idtipocliente = p_idtipocliente; 
	SET @p_ruc = p_ruc; 
	SET @p_dni = p_dni; 
	SET @p_idgenero = p_idgenero; 
	SET @p_limit = p_limit; 
	SET @p_desde = p_desde; 


	EXECUTE STMT USING @p_nombres,@p_nombres,@p_codigocliente,@p_codigocliente,@p_idtipocliente,@p_idtipocliente,
					   @p_ruc,@p_ruc,@p_dni,@p_dni,@p_idgenero,@p_idgenero, @p_limit,@p_desde;
	DEALLOCATE PREPARE STMT;
END //
DELIMITER ;
/*
call usp_TotaRegist_Cliente ('','',0,'0','0',0,@total);
select @total
*/
drop procedure if exists usp_TotaRegist_Cliente;
DELIMITER //
CREATE  PROCEDURE usp_TotaRegist_Cliente(
IN p_codigocliente varchar(12),
IN p_nombres varchar(100),
IN p_idtipocliente int(11),
IN p_ruc int(11),
IN p_dni int(11),
IN p_idgenero int(11),
OUT P_TOTALREGISTRO INT
)
BEGIN 
	declare v_TOTALREGISTRO INT;
	set v_TOTALREGISTRO=0;

	select	count(p.idcliente) into v_TOTALREGISTRO
	from tb_cliente  p
	where (CONCAT(p.nombres, p.apellidos) like CONCAT("%", p_nombres ,"%") or ''= CONCAT("%", p_nombres ,"%"))
	and (p.codigocliente= p_codigocliente or p_codigocliente = '')
	and (p.idtipocliente= p_idtipocliente or 0 = p_idtipocliente) 
	and (p.ruc= p_ruc or '0' = p_ruc) 
	and (p.dni= p_dni or '0' = p_dni) 
	and (p.idgenero= p_idgenero or 0 = p_idgenero) 
	and estado = 1;
	set P_TOTALREGISTRO = v_TOTALREGISTRO;
END //
DELIMITER ;



drop procedure if exists usp_obt_datosCliente;
DELIMITER //
CREATE  PROCEDURE usp_obt_datosCliente(
IN  p_codigocliente varchar(12)
)
BEGIN 
	select	
	p.codigocliente, 
	p.nombres,
	p.apellidos,
	p.correo,
	p.celular,
	p.telefono,
	p.direccion,
	p.referencia,
	p.idtipocliente,
	p.ruc,
	p.dni,
	p.estado,
	ub.iddepar,ub.idprov,ub.iddist,
	p.idgenero
	from tb_cliente  p	 inner join tb_ubigeo ub
	on p.ubigeo = ub.ubigeo
	where p.codigocliente =  p_codigocliente;
END //
DELIMITER ;


drop procedure if exists usp_obtener_correlativo;
DELIMITER //

CREATE  PROCEDURE usp_obtener_correlativo( 

IN p_idtipodocumento int
)
BEGIN
    select c.serie,c.correlativo,
	(select descripcion from tb_tipodocumento where idtipodocumento = c.idtipodocumento) as tipodoc
	from tb_configurarcorrelativo c
	where  c.idtipodocumento = p_idtipodocumento;		
END //
DELIMITER ;

drop procedure if exists usp_upd_correlativo;
DELIMITER //

CREATE  PROCEDURE usp_upd_correlativo( 
IN p_idtipodocumento int,
in p_serie varchar(10),
in p_correlativo varchar(30)
)
BEGIN
		update tb_configurarcorrelativo
		set serie = p_serie,
			correlativo = p_correlativo 
		where idtipodocumento = p_idtipodocumento;
END //
DELIMITER ;