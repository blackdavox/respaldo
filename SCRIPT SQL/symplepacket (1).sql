-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-05-2021 a las 01:46:20
-- Versión del servidor: 10.4.18-MariaDB
-- Versión de PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `symplepacket`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `agg_carrito` (IN `id` INT, IN `cant` INT)  NO SQL
BEGIN
INSERT INTO carrito  (cr_producto_id,cr_cantidad)VALUES ((SELECT pro_producto_id FROM inventario WHERE pro_producto_id = id) , cant);

SELECT pro_producto_id, pro_precio_producto*cant FROM inventario WHERE pro_producto_id = id;


UPDATE inventario SET pro_cantidad_producto= pro_cantidad_producto-cant WHERE pro_producto_id= id;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `calculos_carrito` ()  NO SQL
BEGIN
set @subtotal= (SELECT SUM(c.cr_cantidad*i.pro_precio_producto) from symplepacket.carrito c
                inner JOIN symplePacket.inventario i on i.pro_producto_id = c.cr_producto_id);
set @ivaPor= (select prm_valor from symplepacket.parametros);
set @ivaTotal =(@subtotal*@ivaPor)/100;
set @total = @subtotal+ @ivatotal;
select @subtotal, @ivaPor, @ivaTotal, @total;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `create_cv` (IN `TipoId` INT, IN `ClieId` INT, IN `ivap` DOUBLE)  NO SQL
BEGIN
INSERT INTO comprobanteventa (cv_tipo_id,cv_cliente_id,cv_ivap) VALUES(TipoId,ClieId,ivap);
set @idcv= (SELECT max(cv_com_id) FROM comprobanteventa);
set @cont= (SELECT COUNT(cr_carrito_id)FROM carrito);
set @i=1;
WHILE(@i<=@cont) DO
INSERT INTO detalle_comprobante (dc_producto_id,dc_cv_id,dc_cantidad) VALUES((SELECT cr_producto_id FROM carrito WHERE cr_carrito_id=@i),@idcv,(SELECT cr_cantidad FROM carrito WHERE cr_carrito_id=@i ));
set @i=@i+1;
end while;
DELETE  FROM carrito;
ALTER TABLE carrito AUTO_INCREMENT=1;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `pruebas` ()  BEGIN
set @snt=CONCAT('SELECT * from claseproducto Where ',campo,' regexp "',valor,'" ;');
PREPARE sent FROM @snt;
EXECUTE sent;
DEALLOCATE PREPARE sent;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `return_inventario` (IN `id` INT, INOUT `cant` INT)  NO SQL
BEGIN
UPDATE inventario set pro_cantidad_producto= pro_cantidad_producto+ cant
where pro_producto_id= id;

DELETE FROM carrito  WHERE cr_producto_id= id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `show_car` ()  NO SQL
BEGIN
SELECT cr_carrito_id,c.cr_producto_id,sum(c.cr_cantidad),
CONCAT(cpp.cp_nombre_clase," ",i.pro_marca_producto," ",i.pro_nombre_producto," ",i.pro_modelo_producto)as descripcion,

i.pro_precio_producto,SUM(c.cr_cantidad*i.pro_precio_producto) as importe  FROM symplepacket.carrito c

inner JOIN symplePacket.inventario i on i.pro_producto_id = c.cr_producto_id
INNER join symplepacket.claseproducto cpp on cpp.cp_clase_id = i.pro_clase_producto GROUP BY c.cr_producto_id;


END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `show_claseprod` (IN `campo` VARCHAR(100), IN `valor` VARCHAR(100))  BEGIN
set @snt=CONCAT('SELECT * from claseproducto Where ',campo,' regexp "',valor,'" ;');
PREPARE sent FROM @snt;
EXECUTE sent;
DEALLOCATE PREPARE sent;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `show_clientes` (IN `campo` VARCHAR(100), IN `valor` VARCHAR(100))  BEGIN
set @snt=CONCAT('SELECT cli_cliente_id,CONCAT(cli_nombres_cliente," ", cli_apellidos_cliente)AS NOMBRES,cli_documento_cliente,cli_correo_cliente,cli_telefono_cliente,cli_direccion_cliente FROM clientes Where ',campo,' regexp "',valor,'" ;');
PREPARE sent FROM @snt;
     EXECUTE sent;
     DEALLOCATE PREPARE sent;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `show_products` (IN `campo` VARCHAR(100), IN `valor` VARCHAR(100))  NO SQL
    DETERMINISTIC
BEGIN
set @snt=CONCAT('SELECT i.pro_producto_id, cpp.cp_nombre_clase  , i.pro_marca_producto , i.pro_nombre_producto , i.pro_modelo_producto  , i.pro_cantidad_producto  , i.pro_precio_producto FROM symplePacket.inventario i inner JOIN symplePacket.claseproducto cpp on cpp.cp_clase_id = i.pro_clase_producto Where ',campo,' regexp "',valor,'" ;');
PREPARE sent FROM @snt;
     EXECUTE sent;
     DEALLOCATE PREPARE sent;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `show_tipocom` (IN `campo` VARCHAR(100), IN `valor` VARCHAR(100))  BEGIN
set @snt=CONCAT('SELECT * FROM tipo_comprobante Where ',campo,' regexp "',valor,'" ;');

PREPARE sent FROM @snt;
     EXECUTE sent;
     DEALLOCATE PREPARE sent;
     END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrito`
--

CREATE TABLE `carrito` (
  `cr_carrito_id` int(11) NOT NULL,
  `cr_producto_id` int(11) NOT NULL,
  `cr_cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `carrito`
--

INSERT INTO `carrito` (`cr_carrito_id`, `cr_producto_id`, `cr_cantidad`) VALUES
(1, 3, 1),
(2, 3, 1),
(3, 3, 1),
(4, 2, 1),
(5, 3, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `claseproducto`
--

CREATE TABLE `claseproducto` (
  `cp_clase_id` int(11) NOT NULL,
  `cp_nombre_clase` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `claseproducto`
--

INSERT INTO `claseproducto` (`cp_clase_id`, `cp_nombre_clase`) VALUES
(1, 'Procesador'),
(2, 'Tarjeta de video'),
(3, 'Placa'),
(4, 'Ram'),
(5, 'Hdd'),
(6, 'Ssd'),
(7, 'cases');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `cli_cliente_id` int(11) NOT NULL,
  `cli_nombres_cliente` varchar(20) NOT NULL,
  `cli_apellidos_cliente` varchar(20) NOT NULL,
  `cli_documento_cliente` varchar(15) NOT NULL,
  `cli_correo_cliente` varchar(50) NOT NULL,
  `cli_telefono_cliente` varchar(15) NOT NULL,
  `cli_direccion_cliente` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`cli_cliente_id`, `cli_nombres_cliente`, `cli_apellidos_cliente`, `cli_documento_cliente`, `cli_correo_cliente`, `cli_telefono_cliente`, `cli_direccion_cliente`) VALUES
(1, 'KEVIN DAVID', 'MACIAS PONCE', '932420144', 'DAVIDMACIAS2703', '0988491805', 'MUCHO LOTE '),
(2, 'ANGIE DE LOS AN', 'SANCHEZ CERDA', '987654321', 'SJAHDKAHSD', '9898237498234', 'SALITRE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comprobanteventa`
--

CREATE TABLE `comprobanteventa` (
  `cv_com_id` int(11) NOT NULL,
  `cv_tipo_id` int(11) DEFAULT NULL,
  `cv_cliente_id` int(11) DEFAULT NULL,
  `cv_ivap` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `comprobanteventa`
--

INSERT INTO `comprobanteventa` (`cv_com_id`, `cv_tipo_id`, `cv_cliente_id`, `cv_ivap`) VALUES
(17, 1, 1, 12),
(18, 1, 1, 12),
(19, 1, 1, 12),
(20, 1, 1, 12),
(21, 1, 1, 14),
(22, 1, 1, 14);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_comprobante`
--

CREATE TABLE `detalle_comprobante` (
  `dc_id_detalle` int(11) NOT NULL,
  `dc_producto_id` int(11) NOT NULL,
  `dc_cv_id` int(11) DEFAULT NULL,
  `dc_cantidad` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `detalle_comprobante`
--

INSERT INTO `detalle_comprobante` (`dc_id_detalle`, `dc_producto_id`, `dc_cv_id`, `dc_cantidad`) VALUES
(1, 3, 21, 1),
(2, 2, 21, 1),
(3, 1, 21, 1),
(4, 2, 22, 2),
(5, 3, 22, 2),
(6, 1, 22, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventario`
--

CREATE TABLE `inventario` (
  `pro_producto_id` int(11) NOT NULL,
  `pro_clase_producto` int(10) NOT NULL,
  `pro_marca_producto` varchar(10) NOT NULL,
  `pro_nombre_producto` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `pro_modelo_producto` varchar(10) NOT NULL,
  `pro_cantidad_producto` int(11) NOT NULL,
  `pro_costo_producto` double NOT NULL,
  `pro_precio_producto` double NOT NULL,
  `pro_fechaIngreso_producto` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `inventario`
--

INSERT INTO `inventario` (`pro_producto_id`, `pro_clase_producto`, `pro_marca_producto`, `pro_nombre_producto`, `pro_modelo_producto`, `pro_cantidad_producto`, `pro_costo_producto`, `pro_precio_producto`, `pro_fechaIngreso_producto`) VALUES
(1, 1, 'Amd', 'Ryzen', '3200g', 18, 125, 145, '2021-01-05'),
(2, 7, 'Altek pc', '200 series', '200-R02', 17, 55, 75, '2021-01-05'),
(3, 7, 'Altek pc', '200 series', '200-R03', 14, 55, 75, '2021-01-05');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `parametros`
--

CREATE TABLE `parametros` (
  `prm_id` int(11) NOT NULL,
  `prm_nombre` varchar(10) NOT NULL,
  `prm_valor` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `parametros`
--

INSERT INTO `parametros` (`prm_id`, `prm_nombre`, `prm_valor`) VALUES
(1, 'IVA', 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_comprobante`
--

CREATE TABLE `tipo_comprobante` (
  `tc_tipo_id` int(11) NOT NULL,
  `tc_nombre_tipo` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tipo_comprobante`
--

INSERT INTO `tipo_comprobante` (`tc_tipo_id`, `tc_nombre_tipo`) VALUES
(1, 'FACTURA'),
(2, 'NOTA DE VENTA');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `carrito`
--
ALTER TABLE `carrito`
  ADD PRIMARY KEY (`cr_carrito_id`),
  ADD KEY `cr_producto_id` (`cr_producto_id`);

--
-- Indices de la tabla `claseproducto`
--
ALTER TABLE `claseproducto`
  ADD PRIMARY KEY (`cp_clase_id`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`cli_cliente_id`);

--
-- Indices de la tabla `comprobanteventa`
--
ALTER TABLE `comprobanteventa`
  ADD PRIMARY KEY (`cv_com_id`),
  ADD KEY `tipo_id` (`cv_tipo_id`),
  ADD KEY `cliente_id` (`cv_cliente_id`);

--
-- Indices de la tabla `detalle_comprobante`
--
ALTER TABLE `detalle_comprobante`
  ADD PRIMARY KEY (`dc_id_detalle`),
  ADD KEY `cr_producto_id` (`dc_producto_id`),
  ADD KEY `cr_factura_id` (`dc_cv_id`);

--
-- Indices de la tabla `inventario`
--
ALTER TABLE `inventario`
  ADD PRIMARY KEY (`pro_producto_id`),
  ADD KEY `FK_clase_producto` (`pro_clase_producto`);

--
-- Indices de la tabla `parametros`
--
ALTER TABLE `parametros`
  ADD PRIMARY KEY (`prm_id`);

--
-- Indices de la tabla `tipo_comprobante`
--
ALTER TABLE `tipo_comprobante`
  ADD PRIMARY KEY (`tc_tipo_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `carrito`
--
ALTER TABLE `carrito`
  MODIFY `cr_carrito_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `claseproducto`
--
ALTER TABLE `claseproducto`
  MODIFY `cp_clase_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `cli_cliente_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `comprobanteventa`
--
ALTER TABLE `comprobanteventa`
  MODIFY `cv_com_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT de la tabla `detalle_comprobante`
--
ALTER TABLE `detalle_comprobante`
  MODIFY `dc_id_detalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `inventario`
--
ALTER TABLE `inventario`
  MODIFY `pro_producto_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `parametros`
--
ALTER TABLE `parametros`
  MODIFY `prm_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `tipo_comprobante`
--
ALTER TABLE `tipo_comprobante`
  MODIFY `tc_tipo_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `carrito`
--
ALTER TABLE `carrito`
  ADD CONSTRAINT `carrito_ibfk_1` FOREIGN KEY (`cr_producto_id`) REFERENCES `inventario` (`pro_producto_id`);

--
-- Filtros para la tabla `comprobanteventa`
--
ALTER TABLE `comprobanteventa`
  ADD CONSTRAINT `comprobanteventa_ibfk_1` FOREIGN KEY (`cv_tipo_id`) REFERENCES `tipo_comprobante` (`tc_tipo_id`),
  ADD CONSTRAINT `comprobanteventa_ibfk_2` FOREIGN KEY (`cv_cliente_id`) REFERENCES `clientes` (`cli_cliente_id`);

--
-- Filtros para la tabla `detalle_comprobante`
--
ALTER TABLE `detalle_comprobante`
  ADD CONSTRAINT `detalle_comprobante_ibfk_1` FOREIGN KEY (`dc_producto_id`) REFERENCES `inventario` (`pro_producto_id`),
  ADD CONSTRAINT `detalle_comprobante_ibfk_2` FOREIGN KEY (`dc_cv_id`) REFERENCES `comprobanteventa` (`cv_com_id`);

--
-- Filtros para la tabla `inventario`
--
ALTER TABLE `inventario`
  ADD CONSTRAINT `inventario_ibfk_1` FOREIGN KEY (`pro_clase_producto`) REFERENCES `claseproducto` (`cp_clase_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
