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
IN	p_direccion VARCHAR(45),
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
IN	p_direccion VARCHAR(45),
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





