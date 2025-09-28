CREATE DATABASE `empresa` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_uca1400_ai_ci */;

CREATE TABLE `franquicia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


CREATE TABLE `franquicia_sucursal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_franquicia` int(11) NOT NULL,
  `id_sucursal` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `franquicia_sucursal_franquicia_FK` (`id_franquicia`),
  KEY `franquicia_sucursal_sucursal_FK` (`id_sucursal`),
  CONSTRAINT `franquicia_sucursal_franquicia_FK` FOREIGN KEY (`id_franquicia`) REFERENCES `franquicia` (`id`),
  CONSTRAINT `franquicia_sucursal_sucursal_FK` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursal` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


CREATE TABLE `producto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `cantidad_stock` int(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;



CREATE TABLE `sucursal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


CREATE TABLE `sucursal_producto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_sucursal` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `sucursal_producto_producto_FK` (`id_producto`),
  KEY `sucursal_producto_sucursal_FK` (`id_sucursal`),
  CONSTRAINT `sucursal_producto_producto_FK` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`),
  CONSTRAINT `sucursal_producto_sucursal_FK` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursal` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


