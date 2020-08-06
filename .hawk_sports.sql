-- CREATE DATABASE hawk_sports
-- DROP DATABASE hawk_sports

CREATE TABLE usuario(
	id_usuario SMALLINT AUTO_INCREMENT PRIMARY KEY,
	usuario VARCHAR(25) NOT NULL,
	contrasena CHAR(32) NOT NULL, 
	imagen VARCHAR(30) NOT NULL,
	fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	tipo VARCHAR(30) NOT NULL CHECK(tipo = 'Administrador' OR tipo = 'Empleado' OR tipo = 'Cliente'),
	estado BOOLEAN NOT NULL DEFAULT TRUE 
);

CREATE TABLE direccion(
	id_direccion SMALLINT AUTO_INCREMENT PRIMARY KEY,
	calle VARCHAR(40) NOT NULL,
	numero VARCHAR(5),
	colonia VARCHAR(40) NOT NULL,
	codigo_p CHAR(5) NOT NULL,
	ciudad VARCHAR(40) NOT NULL,
	entidad_f VARCHAR(30) NOT NULL,
	estado BOOLEAN NOT NULL DEFAULT TRUE 
);

CREATE TABLE empleado(
	id_empleado SMALLINT AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(20) NOT NULL,
	apellido_p VARCHAR(20) NOT NULL,
	apellido_m VARCHAR(20),
	telefono CHAR(10) NOT NULL,
	correo_e VARCHAR(40) NOT NULL,
	puesto VARCHAR(20) NOT NULL,
	sueldo FLOAT NOT NULL,
	estado BOOLEAN NOT NULL DEFAULT TRUE,
	id_direccion SMALLINT NOT NULL,
	id_usuario SMALLINT, 
	FOREIGN KEY(id_direccion) REFERENCES direccion(id_direccion) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE proveedor(
	id_proveedor SMALLINT AUTO_INCREMENT PRIMARY KEY,
	rfc CHAR(13) NOT NULL UNIQUE,
	nombre VARCHAR(20) NOT NULL,
	apellido_p VARCHAR(20) NOT NULL,
	apellido_m VARCHAR(20),
	telefono CHAR(10) NOT NULL,
	correo_e VARCHAR(40) NOT NULL,
	estado BOOLEAN NOT NULL DEFAULT TRUE,
	id_direccion SMALLINT NOT NULL,
	FOREIGN KEY(id_direccion) REFERENCES direccion(id_direccion) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE cliente(
	id_cliente SMALLINT AUTO_INCREMENT PRIMARY KEY,
	rfc CHAR(13) NOT NULL UNIQUE,
	nombre VARCHAR(20) NOT NULL,
	apellido_p VARCHAR(20) NOT NULL,
	apellido_m VARCHAR(20),
	telefono CHAR(10) NOT NULL,
	correo_e VARCHAR(40) NOT NULL,
	estado BOOLEAN NOT NULL DEFAULT TRUE,
	id_direccion SMALLINT NOT NULL,
	id_usuario SMALLINT, 
	FOREIGN KEY(id_direccion) REFERENCES direccion(id_direccion) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE categoria(
	id_categoria SMALLINT AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(30) NOT NULL,
	estado BOOLEAN NOT NULL DEFAULT TRUE 
);

CREATE TABLE producto(
	id_producto SMALLINT AUTO_INCREMENT PRIMARY KEY,
	sku CHAR(8) NOT NULL, 
	descripcion VARCHAR(50) NOT NULL,
	precio_compra FLOAT NOT NULL,
	precio_venta FLOAT NOT NULL,
	existencia INTEGER NOT NULL,
	imagen VARCHAR(30) NOT NULL,
	estado BOOLEAN NOT NULL DEFAULT TRUE,
	id_categoria SMALLINT NOT NULL,
	FOREIGN KEY(id_categoria) REFERENCES categoria(id_categoria) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE pedido(
	id_pedido INTEGER AUTO_INCREMENT PRIMARY KEY,
	fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	total FLOAT NOT NULL, 
	id_proveedor SMALLINT NOT NULL,
	id_empleado SMALLINT NOT NULL, 
	FOREIGN KEY(id_proveedor) REFERENCES proveedor(id_proveedor) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY(id_empleado) REFERENCES empleado(id_empleado)ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE detalle_pedido(
	id_pedido INTEGER AUTO_INCREMENT NOT NULL,
	id_producto SMALLINT NOT NULL,
	cantidad INTEGER NOT NULL,
	subtotal FLOAT NOT NULL,
	FOREIGN KEY(id_pedido) REFERENCES pedido(id_pedido) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY(id_producto) REFERENCES producto(id_producto)ON UPDATE CASCADE ON DELETE CASCADE,
	PRIMARY KEY(id_pedido, id_producto)
);

CREATE TABLE venta(
	id_venta SMALLINT AUTO_INCREMENT PRIMARY KEY,
	fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	total FLOAT NOT NULL,
	id_cliente SMALLINT NOT NULL,
	FOREIGN KEY(id_cliente) REFERENCES cliente(id_cliente) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE detalle_venta(
	id_venta SMALLINT AUTO_INCREMENT NOT NULL, 
	id_producto SMALLINT NOT NULL, 
	cantidad INTEGER NOT NULL, 
	subtotal FLOAT NOT NULL,
	FOREIGN KEY(id_venta) REFERENCES venta(id_venta) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY(id_producto) REFERENCES producto(id_producto) ON UPDATE CASCADE ON DELETE CASCADE,
	PRIMARY KEY(id_venta, id_producto)
);

-- 1) INSERTS

-- direccion
INSERT INTO direccion (calle, numero, colonia, codigo_p, ciudad, entidad_f ) VALUES ('15','2000','El cerrito', '94930', 'Yanga','Veracruz');
INSERT INTO direccion (calle, numero, colonia, codigo_p, ciudad, entidad_f ) VALUES ('165','1204','El palmar', '61856', 'Mendoza','Veracruz');
INSERT INTO direccion (calle, numero, colonia, codigo_p, ciudad, entidad_f ) VALUES ('250','456','Centro', '61857', 'San Juan','Veracruz');
INSERT INTO direccion (calle, numero, colonia, codigo_p, ciudad, entidad_f ) VALUES ('158','789','San roman', '51314', 'Palmar','Veracruz');
INSERT INTO direccion (calle, numero, colonia, codigo_p, ciudad, entidad_f ) VALUES('23','951','El mirador', '51521', 'Amatlán','Veracruz');
INSERT INTO direccion (calle, numero, colonia, codigo_p, ciudad, entidad_f ) VALUES('09','753','Oriente', '94850', 'Coatzacoalcos','Veracruz');
INSERT INTO direccion (calle, numero, colonia, codigo_p, ciudad, entidad_f ) VALUES('03','852','Puerto', '94860', 'Córdoba','Veracruz');
INSERT INTO direccion (calle, numero, colonia, codigo_p, ciudad, entidad_f ) VALUES('47','417','San miguel', '94860', 'Orizaba','Veracruz');
INSERT INTO direccion (calle, numero, colonia, codigo_p, ciudad, entidad_f ) VALUES('89','639','Texcoco', '94862', 'Carrillo','Veracruz');
INSERT INTO direccion (calle, numero, colonia, codigo_p, ciudad, entidad_f ) VALUES('44','4569','Maestros', '94870', 'Maguey','Veracruz');
INSERT INTO direccion (calle, numero, colonia, codigo_p, ciudad, entidad_f ) VALUES('36','9563','Portillo', '94873', 'Mata de caña','Veracruz');
INSERT INTO direccion (calle, numero, colonia, codigo_p, ciudad, entidad_f ) VALUES ('23','856','Lomas', '94877', 'Cuitláhuac','Veracruz');
INSERT INTO direccion (calle, numero, colonia, codigo_p, ciudad, entidad_f ) VALUES ('99','1001','Centro', '94880', 'Cuitláhuac','Veracruz');
INSERT INTO direccion (calle, numero, colonia, codigo_p, ciudad, entidad_f ) VALUES ('74','813','Azulejo', '94900', 'Yanga','Veracruz');
INSERT INTO direccion (calle, numero, colonia, codigo_p, ciudad, entidad_f ) VALUES ('155','208','El campo', '94906', 'Minatitlán','Veracruz');

-- usuario
INSERT INTO usuario (usuario, contrasena, imagen, tipo) VALUES ('alejandro20', '1234567890', 'alejandro.jpg', 'Administrador');
INSERT INTO usuario (usuario, contrasena, imagen, tipo) VALUES('ahc2806', 'ahc280699', 'alonso.jpg', 'Administrador');
INSERT INTO usuario (usuario, contrasena, imagen, tipo) VALUES('xavi04', '1234567890', 'xavier.jpg', 'Administrador');
INSERT INTO usuario (usuario, contrasena, imagen, tipo) VALUES('Maria', 'marianita23', 'maria.jpg', 'Empleado');
INSERT INTO usuario (usuario, contrasena, imagen, tipo) VALUES('Naomi', 'naomi212', 'naomi.jpg', 'Empleado');
INSERT INTO usuario (usuario, contrasena, imagen, tipo) VALUES('Alex', 'alexx21', 'alex.jpg', 'Empleado');
INSERT INTO usuario (usuario, contrasena, imagen, tipo) VALUES('Julio', 'Juliopr23', 'julio.jpg', 'Empleado');
INSERT INTO usuario (usuario, contrasena, imagen, tipo) VALUES('Francisco', 'fran234', 'francisco.jpg', 'Empleado');
INSERT INTO usuario (usuario, contrasena, imagen, tipo) VALUES('Pedro', 'pedro12', 'pedro.jpg', 'Empleado');
INSERT INTO usuario (usuario, contrasena, imagen, tipo) VALUES('Roberto', 'ro5256', 'roberto.jpg', 'Cliente');
INSERT INTO usuario (usuario, contrasena, imagen, tipo) VALUES('Alexa', 'alexa213', 'alexa.jpg', 'Cliente');
INSERT INTO usuario (usuario, contrasena, imagen, tipo) VALUES('Angelica', 'angelica22', 'angelica.jpg', 'Cliente');
INSERT INTO usuario (usuario, contrasena, imagen, tipo) VALUES('Marcos', 'mar212', 'marcos.jpg', 'Cliente');
INSERT INTO usuario (usuario, contrasena, imagen, tipo) VALUES('Alondra', 'alon213', 'alondra.jpg', 'Cliente');
INSERT INTO usuario (usuario, contrasena, imagen, tipo) VALUES('Abimael', 'abima234', 'abimael.jpg', 'Cliente');

-- categoria
INSERT INTO categoria(nombre) VALUES ('Deportes');
INSERT INTO categoria(nombre) VALUES ('Hombres');
INSERT INTO categoria(nombre) VALUES ('Mujeres');
INSERT INTO categoria(nombre) VALUES ('Niños');
INSERT INTO categoria(nombre) VALUES ('Marca');

-- proveedor
INSERT INTO proveedor(rfc, nombre, apellido_p, apellido_m, telefono, correo_e, id_direccion) VALUES('KLD050620158', 'Mario', 'Rincon', NULL, '2785546912', 'mariorinrin@gmail.com','10');
INSERT INTO proveedor(rfc, nombre, apellido_p, apellido_m, telefono, correo_e, id_direccion) VALUES('JSH205619886', 'Gerardo', 'Hernandez', 'Cid', '2712075539', 'gercid@gmail.com','11');
INSERT INTO proveedor(rfc, nombre, apellido_p, apellido_m, telefono, correo_e, id_direccion) VALUES('IPO559925647', 'Fernando', 'Flores', 'Altamira', '2716447895', 'ferflores@gmail.com', '12');
INSERT INTO proveedor(rfc, nombre, apellido_p, apellido_m, telefono, correo_e, id_direccion) VALUES('JUE784523156', 'Rodrigo', 'Martinez', NULL, '2715886497', 'rodriMartinez@gmail.com', '13');
INSERT INTO proveedor(rfc, nombre, apellido_p, apellido_m, telefono, correo_e, id_direccion) VALUES('GHR116312470', 'Juan', 'Morales', 'Sanchez', '2716032455','juanmorales@gmail.com', '14');

-- empleado
INSERT INTO empleado(nombre, apellido_p, apellido_m, telefono, correo_e, puesto, sueldo, id_direccion, id_usuario) VALUES ('Alejandro', 'Morales', 'Vázquez', '2794561278', 'alejandromv@gmail.com', 'Supervisor', 1200, 1,1);
INSERT INTO empleado(nombre, apellido_p, apellido_m, telefono, correo_e, puesto, sueldo, id_direccion, id_usuario) VALUES ('Alonso', 'Hernández', 'Cortez', '2711345429', 'alonsohc@gmail.com', 'Gerente', 1800, 2,2);
INSERT INTO empleado(nombre, apellido_p, apellido_m, telefono, correo_e, puesto, sueldo, id_direccion, id_usuario) VALUES ('Francisco', 'Cid', 'León', '2794567816', 'franciscocl@gmail.com', 'Supervisor', 1200, 3,3);
INSERT INTO empleado(nombre, apellido_p, apellido_m, telefono, correo_e, puesto, sueldo, id_direccion, id_usuario) VALUES ('Maria', 'Díaz', 'Martinez', '2786491232', 'mariadm@gmail.com', 'Vendedor', 600, 4,4);
INSERT INTO empleado(nombre, apellido_p, apellido_m, telefono, correo_e, puesto, sueldo, id_direccion, id_usuario) VALUES ('Nahomi', 'Luna', 'Pérez', '2789637412', 'nahomilp@gmail.com', 'Vendedor', 600, 5,5);
INSERT INTO empleado(nombre, apellido_p, apellido_m, telefono, correo_e, puesto, sueldo, id_direccion, id_usuario) VALUES ('Alex', 'Sánchez', 'García', '2789632058', 'alexsg@gmail.com', 'Cajero', 700, 6,6);
INSERT INTO empleado(nombre, apellido_p, apellido_m, telefono, correo_e, puesto, sueldo, id_direccion, id_usuario) VALUES ('Julio', 'Meza', 'Álvarez', '2789636306', 'julioma@gmail.com', 'Cajero', 700, 7,7);
INSERT INTO empleado(nombre, apellido_p, apellido_m, telefono, correo_e, puesto, sueldo, id_direccion, id_usuario) VALUES ('Francisco', 'Aguilar', 'Arellano', '2789638401', 'franciscoaa@gmail.com', 'Bodega',700, 8,8);
INSERT INTO empleado(nombre, apellido_p, apellido_m, telefono, correo_e, puesto, sueldo, id_direccion, id_usuario) VALUES ('Pedro', 'Vázquez', 'Espinoza', '2782950015', 'pedrove@gmail.com', 'Bodega', 700, 9,9);

-- cliente
INSERT INTO cliente(rfc, nombre, apellido_p, apellido_m, telefono, correo_e, id_direccion, id_usuario) VALUES('wee99887720016', 'Roberto', 'Morales', NULL, '2781592648','robertom@gmail.com',10, 10);
INSERT INTO cliente(rfc, nombre, apellido_p, apellido_m, telefono, correo_e, id_direccion, id_usuario) VALUES('hjk776645129d', 'Alexa', 'Montes', NULL, '2794562211', 'alexam@gmail.com',11,11);
INSERT INTO cliente(rfc, nombre, apellido_p, apellido_m, telefono, correo_e, id_direccion, id_usuario) VALUES('uio78546210d9', 'Angélica', 'Hernandez', NULL, '2712072921', 'angelicah@gmail.com',12,12);
INSERT INTO cliente(rfc, nombre, apellido_p, apellido_m, telefono, correo_e, id_direccion, id_usuario) VALUES('jkl74968521d4', 'Marcos', 'Guevara', 'Ladron', '2756489584', 'marcosgl@gmail.com',13,13);
INSERT INTO cliente(rfc, nombre, apellido_p, apellido_m, telefono, correo_e, id_direccion, id_usuario) VALUES('jko749562185f', 'Alondra', 'Duran', 'Barbosa', '2716648523', 'alondradb@gmail.com',14,14);
INSERT INTO cliente(rfc, nombre, apellido_p, apellido_m, telefono, correo_e, id_direccion, id_usuario) VALUES('jko794792185f', 'Abimael', 'Avendaño', 'Rosas', '2715204679', 'abimaelar@gmail.com',15,15);

-- producto
INSERT INTO producto(sku, descripcion, precio_compra, precio_venta, existencia, imagen, id_categoria) VALUES ('0001', 'Balón Futbol adidas', 200.0, 250.0, 30, 'balon.jpg', 1);
INSERT INTO producto(sku, descripcion, precio_compra, precio_venta, existencia, imagen, id_categoria) VALUES ('0002', 'Balón basquet', 300.0, 320.0, 20, 'basquet.jpg', 1);
INSERT INTO producto(sku, descripcion, precio_compra, precio_venta, existencia, imagen, id_categoria) VALUES ('0210', 'Bate de Beisbol aluminio', 600.0, 680.0, 12, 'bateBeisbol.jpg', 1);
INSERT INTO producto(sku, descripcion, precio_compra, precio_venta, existencia, imagen, id_categoria) VALUES ('0312', 'Calcetas grises', 30.0, 42.0, 60, 'calcetas.jpg', 2);
INSERT INTO producto(sku, descripcion, precio_compra, precio_venta, existencia, imagen, id_categoria) VALUES ('4562', 'Googles para bucear', 230.0, 250.0, 45, 'googles.jpg', 1);
INSERT INTO producto(sku, descripcion, precio_compra, precio_venta, existencia, imagen, id_categoria) VALUES ('1021', 'Gorra Boston', 122.0, 135.0, 35, 'gorraBoston.jpg', 2);
INSERT INTO producto(sku, descripcion, precio_compra, precio_venta, existencia, imagen, id_categoria) VALUES ('1024', 'Gorra Dodgers', 122.0, 135.0, 60, 'gorraDodgers.jpg', 2);
INSERT INTO producto(sku, descripcion, precio_compra, precio_venta, existencia, imagen, id_categoria) VALUES ('1026', 'Guante Beisbol', 600.0, 699.0, 65, 'guante.jpg', 2);
INSERT INTO producto(sku, descripcion, precio_compra, precio_venta, existencia, imagen, id_categoria) VALUES ('1064', 'Pelota beisbol', 48.0, 55.0, 250, 'pelotaBeisbol.jpg', 1);
INSERT INTO producto(sku, descripcion, precio_compra, precio_venta, existencia, imagen, id_categoria) VALUES ('1028', 'Pelota tennis', 60.0, 62.0, 230, 'pelotaTenis.jpg', 1);
INSERT INTO producto(sku, descripcion, precio_compra, precio_venta, existencia, imagen, id_categoria) VALUES ('1030', 'Playera para dama', 320.0, 360.0, 60, 'playeraDama.jpg', 3);
INSERT INTO producto(sku, descripcion, precio_compra, precio_venta, existencia, imagen, id_categoria) VALUES ('1032', 'Playera unisex nike', 350.0, 400.0, 35, 'playeraNike.jpg', 2);
INSERT INTO producto(sku, descripcion, precio_compra, precio_venta, existencia, imagen, id_categoria) VALUES ('1034', 'Playera Real Madrid', 400.0, 450.0, 40, 'playeraRMadrid.jpg', 2);
INSERT INTO producto(sku, descripcion, precio_compra, precio_venta, existencia, imagen, id_categoria) VALUES ('1035', 'Playera roja hombre', 302.0, 330.0, 60, 'playeraRoja.png', 2);
INSERT INTO producto(sku, descripcion, precio_compra, precio_venta, existencia, imagen, id_categoria) VALUES ('1038', 'Raqueta tennis wilson', 600.0, 650.0, 15, 'raqueta.jpg', 1);
INSERT INTO producto(sku, descripcion, precio_compra, precio_venta, existencia, imagen, id_categoria) VALUES ('1041', 'Playera seleccion local', 500.0, 550.0, 60, 'seleccionLocal.jpg', 2);
INSERT INTO producto(sku, descripcion, precio_compra, precio_venta, existencia, imagen, id_categoria) VALUES ('1044', 'Playera seleccion visitante', 502.0, 580.0, 60, 'seleccionVisitante.jpg', 2);
INSERT INTO producto(sku, descripcion, precio_compra, precio_venta, existencia, imagen, id_categoria) VALUES ('1049', 'Short adidas', 220.0,300.0, 45, 'shortAdidas.jpg', 2);
INSERT INTO producto(sku, descripcion, precio_compra, precio_venta, existencia, imagen, id_categoria) VALUES ('1061', 'Short puma', 250.0, 300.0, 90, 'shortPuma.jpg', 2);
INSERT INTO producto(sku, descripcion, precio_compra, precio_venta, existencia, imagen, id_categoria) VALUES ('1088', 'Tennis puma', 800.0, 850.0, 20, 'tennisPuma.jpg', 2);

-- Borrado de tablas
DROP TABLE detalle_venta;
DROP TABLE venta;
DROP TABLE detalle_pedido;
DROP TABLE pedido;
DROP TABLE proveedor;
DROP TABLE empleado;
DROP TABLE cliente;
DROP TABLE direccion;
DROP TABLE usuario;
DROP TABLE producto;
DROP TABLE categoria;



-- 2) INDICES 
CREATE INDEX i_nombre_categoria ON categoria (nombre);
CREATE INDEX i_precio_producto ON producto (precio_venta);
CREATE INDEX i_usuario_usuario ON usuario (usuario);
CREATE INDEX i_entidad_direccion ON direccion (entidad_f);
CREATE INDEX i_apellidop_cliente ON cliente (apellido_p);
CREATE INDEX i_puesto_empleado ON empleado (puesto);
CREATE UNIQUE INDEX IF NOT EXISTS i_rfc_proveedor ON proveedor (rfc);



-- 3) TRIGGERS
DELIMITER $$
    CREATE TRIGGER elimina_direccion_al_actualizar_empleado AFTER UPDATE ON empleado FOR EACH ROW
    BEGIN
        UPDATE direccion d SET d.estado = NEW.estado WHERE d.id_direccion = OLD.id_direccion;
    END;
$$

DELIMITER $$
    CREATE TRIGGER elimina_direccion_al_actualizar_cliente AFTER UPDATE ON cliente FOR EACH ROW
    BEGIN
        UPDATE direccion d SET d.estado = NEW.estado WHERE d.id_direccion = OLD.id_direccion;
    END;
$$

DELIMITER $$
    CREATE TRIGGER elimina_direccion_al_actualizar_proveedor AFTER UPDATE ON proveedor FOR EACH ROW
    BEGIN
        UPDATE direccion d SET d.estado = NEW.estado WHERE d.id_direccion = OLD.id_direccion;
    END;
$$

DELIMITER $$
    CREATE TRIGGER encripta_la_contrasena_del_usuario_insertar BEFORE INSERT ON usuario FOR EACH ROW
    BEGIN
	
        SET NEW.contrasena = HEX(AES_ENCRYPT(NEW.contrasena, 'HawkSports'));
    END;
$$

DELIMITER $$
    CREATE TRIGGER encripta_la_contrasena_del_usuario_editar BEFORE UPDATE ON usuario FOR EACH ROW
    BEGIN
        SET NEW.contrasena = HEX(AES_ENCRYPT(NEW.contrasena, 'HawkSports'));
    END;
$$

-- Borrado de TRIGGERS
DROP TRIGGER IF EXISTS elimina_direccion_al_actualizar_empleado;
DROP TRIGGER IF EXISTS elimina_direccion_al_actualizar_cliente;
DROP TRIGGER IF EXISTS elimina_direccion_al_actualizar_proveedor;
DROP TRIGGER IF EXISTS encripta_la_contrasena_del_usuario_insertar;
DROP TRIGGER IF EXISTS encripta_la_contrasena_del_usuario_editar;



-- 4) PROCEDIMIENTOS ALMACENADOS
DELIMITER $$
	CREATE PROCEDURE desencriptar_contrasena (IN idUsuario SMALLINT, OUT passwd VARCHAR(40))
	BEGIN
		SELECT AES_DECRYPT(UNHEX(contrasena), 'HawkSports') 
		INTO passwd
		FROM usuario
		WHERE id_usuario = idUsuario;
	END;
$$

DELIMITER $$
	CREATE PROCEDURE nombre_usuario_logueado(IN user VARCHAR(25), IN passwd VARCHAR(40), OUT full_name VARCHAR(62))
	BEGIN
		DECLARE tipo_user VARCHAR(15);
		SELECT tipo
		INTO tipo_user
		FROM usuario
		WHERE usuario = user AND contrasena = HEX(AES_ENCRYPT(passwd, 'HawkSports'));

		IF tipo_user = 'Administrador' OR tipo_user = 'Empleado' THEN
			SELECT CONCAT(nombre, ' ', apellido_p, ' ', apellido_m)
			INTO full_name
			FROM usuario u INNER JOIN empleado e ON u.id_usuario = e.id_usuario
			WHERE u.usuario = user AND contrasena = HEX(AES_ENCRYPT(passwd, 'HawkSports'));
		ELSE
			SELECT CONCAT(nombre, ' ', apellido_p, ' ', apellido_m)
			INTO full_name
			FROM usuario u INNER JOIN cliente c ON u.id_usuario = c.id_usuario
			WHERE u.usuario = user AND contrasena = HEX(AES_ENCRYPT(passwd, 'HawkSports'));
		END IF;
	END;
$$

-- Borrado de PROCEDIMIENTOS ALMACENADOS
DROP PROCEDURE IF EXISTS nombre_usuario_logueado;
DROP PROCEDURE IF EXISTS desencriptar_contrasena;



-- 5) VISTAS
-- Lista de todos los usuarios
CREATE OR REPLACE VIEW datos_usuarios AS SELECT id_usuario, usuario, tipo, fecha_creacion FROM usuario;
SELECT * FROM datos_usuarios;

-- Lista de todos los proveedores incluyendo su dirección (la dirección debe aparecer en una sóla columna)
CREATE OR REPLACE VIEW datos_proveedores AS SELECT id_proveedor, rfc, nombre, CONCAT (calle,', ', numero,' ', colonia,', ', codigo_p,', ', ciudad,', ', entidad_f) AS direccion, telefono, correo_e 
FROM proveedor p INNER JOIN direccion d ON p.id_direccion=d.id_direccion;
SELECT * FROM datos_proveedores;

-- Lista de todos los empleados incluyendo su dirección (la dirección debe aparecer en una sóla columna)
CREATE OR REPLACE VIEW datos_empleados AS SELECT id_empleado, nombre, puesto, sueldo, CONCAT (calle,', ', numero,' ', colonia,', ', codigo_p,', ', ciudad,', ', entidad_f) AS direccion, telefono, correo_e 
FROM empleado e INNER JOIN direccion d ON e.id_direccion=d.id_direccion;
SELECT * FROM datos_empleados;

-- Lista de todos los clientes incluyendo su dirección (la dirección debe aparecer en una sóla columna)
CREATE OR REPLACE VIEW datos_clientes AS SELECT id_cliente, rfc, nombre, CONCAT (calle,', ', numero,' ', colonia,', ', codigo_p,', ', ciudad,', ', entidad_f) AS direccion, telefono, correo_e 
FROM cliente c INNER JOIN direccion d ON c.id_direccion=d.id_direccion;
SELECT * FROM datos_clientes;

-- Lista de todos los productos incluyendo su categoría
CREATE OR REPLACE VIEW datos_productos AS SELECT id_producto, sku, descripcion, precio_venta, precio_compra, nombre AS categoria, existencia 
FROM producto p INNER JOIN categoria c ON p.id_categoria=c.id_categoria;
SELECT * FROM datos_productos;