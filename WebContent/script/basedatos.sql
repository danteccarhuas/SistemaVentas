SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `bdsistemaventas` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `bdsistemaventas` ;

-- -----------------------------------------------------
-- Table `bdsistemaventas`.`tb_ubigeo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdsistemaventas`.`tb_ubigeo` ;

CREATE  TABLE IF NOT EXISTS `bdsistemaventas`.`tb_ubigeo` (
  `ubigeo` INT NOT NULL ,
  `iddepar` INT NULL ,
  `departamente` VARCHAR(100) NULL ,
  `idprov` INT NULL ,
  `provincia` VARCHAR(100) NULL ,
  `iddist` INT NULL ,
  `distrito` VARCHAR(100) NULL ,
  PRIMARY KEY (`ubigeo`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdsistemaventas`.`tb_cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdsistemaventas`.`tb_cliente` ;

CREATE  TABLE IF NOT EXISTS `bdsistemaventas`.`tb_cliente` (
  `idcliente` SMALLINT NOT NULL AUTO_INCREMENT ,
  `nombres` VARCHAR(45) NULL ,
  `apellidos` VARCHAR(100) NULL ,
  `correo` VARCHAR(45) NULL ,
  `celular` VARCHAR(45) NULL ,
  `telefono` VARCHAR(45) NULL ,
  `fecha_creacion` DATETIME NULL ,
  `fecha_modificacion` DATETIME NULL ,
  `idtipocliente` VARCHAR(45) NULL ,
  `ruc` VARCHAR(11) NULL ,
  `dni` VARCHAR(8) NULL ,
  `estado` INT NULL ,
  `ubigeo` INT NOT NULL ,
  PRIMARY KEY (`idcliente`) ,
  INDEX `fk_tb_cliente_tb_ubigeo1_idx` (`ubigeo` ASC) ,
  CONSTRAINT `fk_tb_cliente_tb_ubigeo1`
    FOREIGN KEY (`ubigeo` )
    REFERENCES `bdsistemaventas`.`tb_ubigeo` (`ubigeo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdsistemaventas`.`tb_marca`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdsistemaventas`.`tb_marca` ;

CREATE  TABLE IF NOT EXISTS `bdsistemaventas`.`tb_marca` (
  `idmarca` INT NOT NULL AUTO_INCREMENT ,
  `descripcion` VARCHAR(45) NULL ,
  `estado` INT NULL ,
  `fecharegistro` DATETIME NULL ,
  `fechamodificacion` DATETIME NULL ,
  PRIMARY KEY (`idmarca`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdsistemaventas`.`tb_categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdsistemaventas`.`tb_categoria` ;

CREATE  TABLE IF NOT EXISTS `bdsistemaventas`.`tb_categoria` (
  `idcategoria` INT NOT NULL AUTO_INCREMENT ,
  `descripcion` VARCHAR(100) NULL ,
  `estado` INT NULL ,
  `fecharegistro` DATETIME NULL ,
  `fechamodificacion` DATETIME NULL ,
  PRIMARY KEY (`idcategoria`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdsistemaventas`.`tb_producto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdsistemaventas`.`tb_producto` ;

CREATE  TABLE IF NOT EXISTS `bdsistemaventas`.`tb_producto` (
  `idproducto` SMALLINT NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(50) NULL ,
  `descripcion` VARCHAR(255) NULL ,
  `estado` INT NULL ,
  `preciocompra` DECIMAL(12,5) NULL ,
  `precioventa` DECIMAL(12,5) NULL ,
  `fechacreacion` DATETIME NULL ,
  `fechamodificacion` DATETIME NULL ,
  `idmarca` INT NOT NULL ,
  `idcategoria` INT NOT NULL ,
  `idgenero` INT NOT NULL ,
  `talla` VARCHAR(25) NULL ,
  PRIMARY KEY (`idproducto`) ,
  INDEX `fk_tb_producto_tb_marca1_idx` (`idmarca` ASC) ,
  INDEX `fk_tb_producto_tb_categoria1_idx` (`idcategoria` ASC) ,
  CONSTRAINT `fk_tb_producto_tb_marca1`
    FOREIGN KEY (`idmarca` )
    REFERENCES `bdsistemaventas`.`tb_marca` (`idmarca` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_producto_tb_categoria1`
    FOREIGN KEY (`idcategoria` )
    REFERENCES `bdsistemaventas`.`tb_categoria` (`idcategoria` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdsistemaventas`.`tb_tienda`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdsistemaventas`.`tb_tienda` ;

CREATE  TABLE IF NOT EXISTS `bdsistemaventas`.`tb_tienda` (
  `idtienda` SMALLINT NOT NULL AUTO_INCREMENT ,
  `Descripcion` VARCHAR(45) NULL ,
  `Direccion` VARCHAR(255) NULL ,
  `telefono` VARCHAR(25) NULL ,
  `estado` INT NULL ,
  `fechacreacion` DATETIME NULL ,
  `fechamodificacion` DATETIME NULL ,
  PRIMARY KEY (`idtienda`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdsistemaventas`.`tb_trabajador`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdsistemaventas`.`tb_trabajador` ;

CREATE  TABLE IF NOT EXISTS `bdsistemaventas`.`tb_trabajador` (
  `idtrabajador` SMALLINT NOT NULL AUTO_INCREMENT ,
  `nombres` VARCHAR(45) NULL ,
  `apellidos` VARCHAR(45) NULL ,
  `correo` VARCHAR(45) NULL ,
  `telefono` VARCHAR(25) NULL ,
  `dni` VARCHAR(45) NULL ,
  `fechanacimiento` DATETIME NULL ,
  `direccion` VARCHAR(225) NULL ,
  `estado` INT NULL ,
  `fechacreacion` DATETIME NULL ,
  `fechamodificacion` DATETIME NULL ,
  `idtienda` INT NULL ,
  PRIMARY KEY (`idtrabajador`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdsistemaventas`.`tb_rol`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdsistemaventas`.`tb_rol` ;

CREATE  TABLE IF NOT EXISTS `bdsistemaventas`.`tb_rol` (
  `idrol` INT NOT NULL AUTO_INCREMENT ,
  `descripcion` VARCHAR(45) NULL ,
  `estado` VARCHAR(45) NULL ,
  `fechacreacion` VARCHAR(45) NULL ,
  `fechamodificacion` VARCHAR(45) NULL ,
  PRIMARY KEY (`idrol`) ,
  UNIQUE INDEX `descripcion_UNIQUE` (`descripcion` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdsistemaventas`.`tb_usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdsistemaventas`.`tb_usuario` ;

CREATE  TABLE IF NOT EXISTS `bdsistemaventas`.`tb_usuario` (
  `idusuario` INT NOT NULL AUTO_INCREMENT ,
  `usuario` VARCHAR(45) NULL ,
  `password` VARCHAR(45) NULL ,
  `fechacreacion` DATETIME NULL ,
  `fechamodificacion` DATETIME NULL ,
  `idrol` INT NOT NULL ,
  `idtrabajador` SMALLINT NOT NULL ,
  PRIMARY KEY (`idusuario`) ,
  INDEX `fk_tb_usuario_tb_rol1_idx` (`idrol` ASC) ,
  INDEX `fk_tb_usuario_tb_trabajador1_idx` (`idtrabajador` ASC) ,
  CONSTRAINT `fk_tb_usuario_tb_rol1`
    FOREIGN KEY (`idrol` )
    REFERENCES `bdsistemaventas`.`tb_rol` (`idrol` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_usuario_tb_trabajador1`
    FOREIGN KEY (`idtrabajador` )
    REFERENCES `bdsistemaventas`.`tb_trabajador` (`idtrabajador` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdsistemaventas`.`tb_menu`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdsistemaventas`.`tb_menu` ;

CREATE  TABLE IF NOT EXISTS `bdsistemaventas`.`tb_menu` (
  `idmenu` INT NOT NULL AUTO_INCREMENT ,
  `descripcion` VARCHAR(45) NULL ,
  `estado` INT NULL ,
  `fechacreacion` DATETIME NULL ,
  `fechamodificacion` DATETIME NULL ,
  `tipografia` VARCHAR(100) NULL ,
  PRIMARY KEY (`idmenu`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdsistemaventas`.`tb_submenu`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdsistemaventas`.`tb_submenu` ;

CREATE  TABLE IF NOT EXISTS `bdsistemaventas`.`tb_submenu` (
  `idsubmenu` INT NOT NULL AUTO_INCREMENT ,
  `descripcion` VARCHAR(45) NULL ,
  `url` VARCHAR(45) NULL ,
  `fechacreacion` DATETIME NULL ,
  `fechamodificacion` DATETIME NULL ,
  `idmenu` INT NOT NULL ,
  PRIMARY KEY (`idsubmenu`) ,
  INDEX `fk_tb_submenu_tb_menu1_idx` (`idmenu` ASC) ,
  UNIQUE INDEX `descripcion_UNIQUE` (`descripcion` ASC) ,
  CONSTRAINT `fk_tb_submenu_tb_menu1`
    FOREIGN KEY (`idmenu` )
    REFERENCES `bdsistemaventas`.`tb_menu` (`idmenu` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdsistemaventas`.`tb_permiso_rol_submenu`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdsistemaventas`.`tb_permiso_rol_submenu` ;

CREATE  TABLE IF NOT EXISTS `bdsistemaventas`.`tb_permiso_rol_submenu` (
  `idsubmenu` INT NOT NULL ,
  `idrol` INT NOT NULL ,
  INDEX `fk_tb_permiso_rol_submenu_tb_submenu1_idx` (`idsubmenu` ASC) ,
  INDEX `fk_tb_permiso_rol_submenu_tb_rol1_idx` (`idrol` ASC) ,
  CONSTRAINT `fk_tb_permiso_rol_submenu_tb_submenu1`
    FOREIGN KEY (`idsubmenu` )
    REFERENCES `bdsistemaventas`.`tb_submenu` (`idsubmenu` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_permiso_rol_submenu_tb_rol1`
    FOREIGN KEY (`idrol` )
    REFERENCES `bdsistemaventas`.`tb_rol` (`idrol` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdsistemaventas`.`tb_almacen`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdsistemaventas`.`tb_almacen` ;

CREATE  TABLE IF NOT EXISTS `bdsistemaventas`.`tb_almacen` (
  `idalmacen` INT NOT NULL AUTO_INCREMENT ,
  `idproducto` SMALLINT NOT NULL ,
  `idtipoprenda` INT NOT NULL ,
  `idtalla` INT NOT NULL ,
  `idsexo` INT NOT NULL ,
  `stockactual` INT NULL ,
  `fecharegistro` DATETIME NULL ,
  `fechamodificacion` DATETIME NULL ,
  PRIMARY KEY (`idalmacen`) ,
  INDEX `fk_tb_almacen_tb_producto1_idx` (`idproducto` ASC) ,
  CONSTRAINT `fk_tb_almacen_tb_producto1`
    FOREIGN KEY (`idproducto` )
    REFERENCES `bdsistemaventas`.`tb_producto` (`idproducto` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdsistemaventas`.`tb_producto_tienda`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdsistemaventas`.`tb_producto_tienda` ;

CREATE  TABLE IF NOT EXISTS `bdsistemaventas`.`tb_producto_tienda` (
  `idproducto` SMALLINT NOT NULL ,
  `idtienda` SMALLINT NOT NULL ,
  `stockproducto` INT NULL ,
  INDEX `fk_tb_producto_tienda_tb_producto1_idx` (`idproducto` ASC) ,
  INDEX `fk_tb_producto_tienda_tb_tienda1_idx` (`idtienda` ASC) ,
  CONSTRAINT `fk_tb_producto_tienda_tb_producto1`
    FOREIGN KEY (`idproducto` )
    REFERENCES `bdsistemaventas`.`tb_producto` (`idproducto` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_producto_tienda_tb_tienda1`
    FOREIGN KEY (`idtienda` )
    REFERENCES `bdsistemaventas`.`tb_tienda` (`idtienda` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdsistemaventas`.`tb_kardex`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdsistemaventas`.`tb_kardex` ;

CREATE  TABLE IF NOT EXISTS `bdsistemaventas`.`tb_kardex` (
  `idkardex` INT NOT NULL AUTO_INCREMENT ,
  `idproducto` SMALLINT NOT NULL ,
  `idtienda` SMALLINT NOT NULL ,
  `idtrabajador` SMALLINT NOT NULL ,
  `idtipo_operacion` INT NULL ,
  `idtipodocumento` INT NULL ,
  `nreserie` VARCHAR(10) NULL ,
  `correlativo` VARCHAR(50) NULL ,
  `fecharegistro` DATETIME NULL ,
  `cantidad` INT NULL ,
  `preciounitario` DECIMAL(12,5) NULL ,
  `total` DECIMAL(12,5) NULL ,
  PRIMARY KEY (`idkardex`) ,
  INDEX `fk_tb_kardex_tb_producto1_idx` (`idproducto` ASC) ,
  INDEX `fk_tb_kardex_tb_tienda1_idx` (`idtienda` ASC) ,
  INDEX `fk_tb_kardex_tb_trabajador1_idx` (`idtrabajador` ASC) ,
  CONSTRAINT `fk_tb_kardex_tb_producto1`
    FOREIGN KEY (`idproducto` )
    REFERENCES `bdsistemaventas`.`tb_producto` (`idproducto` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_kardex_tb_tienda1`
    FOREIGN KEY (`idtienda` )
    REFERENCES `bdsistemaventas`.`tb_tienda` (`idtienda` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_kardex_tb_trabajador1`
    FOREIGN KEY (`idtrabajador` )
    REFERENCES `bdsistemaventas`.`tb_trabajador` (`idtrabajador` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdsistemaventas`.`tb_funcionalidad`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdsistemaventas`.`tb_funcionalidad` ;

CREATE  TABLE IF NOT EXISTS `bdsistemaventas`.`tb_funcionalidad` (
  `idfuncionalidad` INT NOT NULL ,
  `descripcion` VARCHAR(225) NULL ,
  `estado` INT NULL ,
  PRIMARY KEY (`idfuncionalidad`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdsistemaventas`.`tb_parametrizador`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdsistemaventas`.`tb_parametrizador` ;

CREATE  TABLE IF NOT EXISTS `bdsistemaventas`.`tb_parametrizador` (
  `idparametrizador` INT NOT NULL ,
  `idparamconsecutivo` INT NOT NULL ,
  `valor` INT NULL ,
  `simbolo` VARCHAR(25) NULL ,
  `descripcion` VARCHAR(45) NULL ,
  `estado` INT NULL ,
  `idfuncionalidad` INT NOT NULL ,
  PRIMARY KEY (`idparametrizador`, `idparamconsecutivo`) ,
  INDEX `fk_tb_parametrizador_tb_funcionalidad1_idx` (`idfuncionalidad` ASC) ,
  CONSTRAINT `fk_tb_parametrizador_tb_funcionalidad1`
    FOREIGN KEY (`idfuncionalidad` )
    REFERENCES `bdsistemaventas`.`tb_funcionalidad` (`idfuncionalidad` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdsistemaventas`.`tb_proveedor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdsistemaventas`.`tb_proveedor` ;

CREATE  TABLE IF NOT EXISTS `bdsistemaventas`.`tb_proveedor` (
  `idproveedor` SMALLINT NOT NULL AUTO_INCREMENT ,
  `codigoproveedor` VARCHAR(12) NOT NULL ,
  `razonsocial` VARCHAR(255) NULL ,
  `correo` VARCHAR(45) NULL ,
  `fax` VARCHAR(45) NULL ,
  `telefono` VARCHAR(45) NULL ,
  `celular` VARCHAR(45) NULL ,
  `sitioweb` VARCHAR(45) NULL ,
  `ruc` VARCHAR(11) NULL ,
  `direccion` VARCHAR(45) NULL ,
  `referencia` VARCHAR(255) NULL ,
  `contacto` VARCHAR(45) NULL ,
  `estado` INT NULL ,
  `ubigeo` INT NOT NULL ,
  PRIMARY KEY (`idproveedor`) ,
  INDEX `fk_tb_proveedor_tb_ubigeo1_idx` (`ubigeo` ASC) ,
  CONSTRAINT `fk_tb_proveedor_tb_ubigeo1`
    FOREIGN KEY (`ubigeo` )
    REFERENCES `bdsistemaventas`.`tb_ubigeo` (`ubigeo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdsistemaventas`.`tb_departamento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdsistemaventas`.`tb_departamento` ;

CREATE  TABLE IF NOT EXISTS `bdsistemaventas`.`tb_departamento` (
  `iddepar` INT NOT NULL ,
  `departamento` VARCHAR(50) NULL ,
  PRIMARY KEY (`iddepar`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdsistemaventas`.`tb_provincia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdsistemaventas`.`tb_provincia` ;

CREATE  TABLE IF NOT EXISTS `bdsistemaventas`.`tb_provincia` (
  `idProv` INT NOT NULL ,
  `provincia` VARCHAR(45) NULL ,
  `iddepar` INT NOT NULL ,
  PRIMARY KEY (`idProv`) ,
  INDEX `fk_tb_provincia_tb_departamento1_idx` (`iddepar` ASC) ,
  CONSTRAINT `fk_tb_provincia_tb_departamento1`
    FOREIGN KEY (`iddepar` )
    REFERENCES `bdsistemaventas`.`tb_departamento` (`iddepar` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdsistemaventas`.`tb_distrito`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdsistemaventas`.`tb_distrito` ;

CREATE  TABLE IF NOT EXISTS `bdsistemaventas`.`tb_distrito` (
  `idDist` INT NOT NULL ,
  `distrito` VARCHAR(50) NULL ,
  `idProv` INT NOT NULL ,
  PRIMARY KEY (`idDist`) ,
  INDEX `fk_tb_distrito_tb_provincia1_idx` (`idProv` ASC) ,
  CONSTRAINT `fk_tb_distrito_tb_provincia1`
    FOREIGN KEY (`idProv` )
    REFERENCES `bdsistemaventas`.`tb_provincia` (`idProv` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdsistemaventas`.`tb_ordencompra`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdsistemaventas`.`tb_ordencompra` ;

CREATE  TABLE IF NOT EXISTS `bdsistemaventas`.`tb_ordencompra` (
  `idordencompra` INT NOT NULL ,
  `nro_ordencompra` VARCHAR(50) NOT NULL ,
  `idproveedor` SMALLINT NOT NULL ,
  `estado` INT NULL ,
  `preciobruto` DECIMAL(12,5) NULL ,
  `descuento` INT NULL ,
  `precioneto` DECIMAL(12,5) NULL ,
  `igv` DECIMAL(12,5) NULL ,
  `total` DECIMAL(12,5) NULL ,
  `indincluyeigv` INT NULL ,
  PRIMARY KEY (`nro_ordencompra`) ,
  INDEX `fk_tb_ordencompra_tb_proveedor1_idx` (`idproveedor` ASC) ,
  CONSTRAINT `fk_tb_ordencompra_tb_proveedor1`
    FOREIGN KEY (`idproveedor` )
    REFERENCES `bdsistemaventas`.`tb_proveedor` (`idproveedor` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdsistemaventas`.`tb_ordencompra_producto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdsistemaventas`.`tb_ordencompra_producto` ;

CREATE  TABLE IF NOT EXISTS `bdsistemaventas`.`tb_ordencompra_producto` (
  `nro_ordencompra` VARCHAR(50) NOT NULL ,
  `idproducto` SMALLINT NOT NULL ,
  `idtipoprenda` INT NOT NULL ,
  `idtalla` INT NOT NULL ,
  `idsexo` INT NOT NULL ,
  `cantidad` INT NULL ,
  `descuento` INT NULL ,
  `preciocompra` DECIMAL(12,5) NULL ,
  `total` DECIMAL(12,5) NULL ,
  PRIMARY KEY (`nro_ordencompra`, `idproducto`) ,
  INDEX `fk_tb_ordencompra_has_tb_producto_tb_producto1_idx` (`idproducto` ASC) ,
  INDEX `fk_tb_ordencompra_has_tb_producto_tb_ordencompra1_idx` (`nro_ordencompra` ASC) ,
  CONSTRAINT `fk_tb_ordencompra_has_tb_producto_tb_ordencompra1`
    FOREIGN KEY (`nro_ordencompra` )
    REFERENCES `bdsistemaventas`.`tb_ordencompra` (`nro_ordencompra` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_ordencompra_has_tb_producto_tb_producto1`
    FOREIGN KEY (`idproducto` )
    REFERENCES `bdsistemaventas`.`tb_producto` (`idproducto` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdsistemaventas`.`tb_moneda`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdsistemaventas`.`tb_moneda` ;

CREATE  TABLE IF NOT EXISTS `bdsistemaventas`.`tb_moneda` (
  `idmoneda` INT NOT NULL AUTO_INCREMENT ,
  `descripcion` VARCHAR(45) NULL ,
  `simbolo` VARCHAR(25) NULL ,
  `estado` INT NULL ,
  `fecharegistro` DATETIME NULL ,
  `fechamodificacion` DATETIME NULL ,
  PRIMARY KEY (`idmoneda`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdsistemaventas`.`tb_tipodocumento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdsistemaventas`.`tb_tipodocumento` ;

CREATE  TABLE IF NOT EXISTS `bdsistemaventas`.`tb_tipodocumento` (
  `idtipodocumento` INT NOT NULL AUTO_INCREMENT ,
  `descripcion` VARCHAR(45) NULL ,
  `abreviatura` VARCHAR(45) NULL ,
  `estado` INT NULL ,
  `fecharegistro` DATETIME NULL ,
  `fechamodificacion` DATETIME NULL ,
  PRIMARY KEY (`idtipodocumento`) )
ENGINE = InnoDB;

USE `bdsistemaventas` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
