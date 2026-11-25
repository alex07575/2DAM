-- Esquema de la Fase 1. Requiere MySQL 8.0.16+ (CHECK aplicados) e InnoDB.
CREATE DATABASE IF NOT EXISTS gasolinera_fase1
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;
USE gasolinera_fase1;

CREATE TABLE estacion (
    id_estacion INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(30) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(200) NOT NULL,
    telefono VARCHAR(25) NOT NULL,
    fecha_apertura DATE NOT NULL
) ENGINE = InnoDB;

CREATE TABLE configuracion_facturacion (
    id_configuracion INT AUTO_INCREMENT PRIMARY KEY,
    codigo_configuracion VARCHAR(30) NOT NULL UNIQUE,
    id_estacion INT NOT NULL UNIQUE,
    serie_ticket VARCHAR(20) NOT NULL,
    siguiente_numero BIGINT NOT NULL,
    texto_pie VARCHAR(500) NOT NULL,
    CONSTRAINT chk_configuracion_numero CHECK (siguiente_numero > 0),
    CONSTRAINT fk_configuracion_estacion FOREIGN KEY (id_estacion)
        REFERENCES estacion (id_estacion) ON UPDATE RESTRICT ON DELETE RESTRICT
) ENGINE = InnoDB;

CREATE TABLE surtidor (
    id_surtidor INT AUTO_INCREMENT PRIMARY KEY,
    codigo_equipo VARCHAR(30) NOT NULL UNIQUE,
    id_estacion INT NOT NULL,
    numero_visible SMALLINT UNSIGNED NOT NULL,
    combustible VARCHAR(30) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    CONSTRAINT uq_surtidor_estacion_numero UNIQUE (id_estacion, numero_visible),
    CONSTRAINT chk_surtidor_numero CHECK (numero_visible > 0),
    CONSTRAINT chk_surtidor_estado CHECK (estado IN ('OPERATIVO', 'AVERIADO', 'FUERA_SERVICIO')),
    CONSTRAINT fk_surtidor_estacion FOREIGN KEY (id_estacion)
        REFERENCES estacion (id_estacion) ON UPDATE RESTRICT ON DELETE RESTRICT
) ENGINE = InnoDB;

CREATE TABLE empleado (
    id_empleado INT AUTO_INCREMENT PRIMARY KEY,
    numero_interno VARCHAR(30) NOT NULL UNIQUE,
    documento_identidad VARCHAR(20) NOT NULL UNIQUE,
    nombre VARCHAR(120) NOT NULL,
    telefono VARCHAR(25) NULL,
    email VARCHAR(254) NULL,
    fecha_incorporacion DATE NOT NULL,
    id_estacion INT NOT NULL,
    id_supervisor INT NULL,
    CONSTRAINT chk_empleado_contacto CHECK (telefono IS NOT NULL OR email IS NOT NULL),
    CONSTRAINT fk_empleado_estacion FOREIGN KEY (id_estacion)
        REFERENCES estacion (id_estacion) ON UPDATE RESTRICT ON DELETE RESTRICT,
    CONSTRAINT fk_empleado_supervisor FOREIGN KEY (id_supervisor)
        REFERENCES empleado (id_empleado) ON UPDATE RESTRICT ON DELETE RESTRICT
) ENGINE = InnoDB;

CREATE TABLE funcion (
    id_funcion INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(30) NOT NULL UNIQUE,
    nombre VARCHAR(80) NOT NULL UNIQUE
) ENGINE = InnoDB;

CREATE TABLE asignacion_funcion (
    id_asignacion INT AUTO_INCREMENT PRIMARY KEY,
    id_empleado INT NOT NULL,
    id_funcion INT NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NULL,
    CONSTRAINT uq_asignacion_inicio UNIQUE (id_empleado, id_funcion, fecha_inicio),
    CONSTRAINT chk_asignacion_fechas CHECK (fecha_fin IS NULL OR fecha_fin >= fecha_inicio),
    CONSTRAINT fk_asignacion_empleado FOREIGN KEY (id_empleado)
        REFERENCES empleado (id_empleado) ON UPDATE RESTRICT ON DELETE RESTRICT,
    CONSTRAINT fk_asignacion_funcion FOREIGN KEY (id_funcion)
        REFERENCES funcion (id_funcion) ON UPDATE RESTRICT ON DELETE RESTRICT
) ENGINE = InnoDB;

CREATE TABLE detalle_cajero (
    id_detalle_cajero INT AUTO_INCREMENT PRIMARY KEY,
    id_asignacion INT NOT NULL UNIQUE,
    limite_devolucion DECIMAL(10,2) NOT NULL,
    CONSTRAINT chk_cajero_limite CHECK (limite_devolucion > 0),
    CONSTRAINT fk_cajero_asignacion FOREIGN KEY (id_asignacion)
        REFERENCES asignacion_funcion (id_asignacion) ON UPDATE RESTRICT ON DELETE RESTRICT
) ENGINE = InnoDB;

CREATE TABLE detalle_expendedor (
    id_detalle_expendedor INT AUTO_INCREMENT PRIMARY KEY,
    id_asignacion INT NOT NULL UNIQUE,
    numero_acreditacion VARCHAR(40) NOT NULL UNIQUE,
    caducidad DATE NOT NULL,
    CONSTRAINT fk_expendedor_asignacion FOREIGN KEY (id_asignacion)
        REFERENCES asignacion_funcion (id_asignacion) ON UPDATE RESTRICT ON DELETE RESTRICT
) ENGINE = InnoDB;

CREATE TABLE detalle_responsable_tienda (
    id_detalle_responsable INT AUTO_INCREMENT PRIMARY KEY,
    id_asignacion INT NOT NULL UNIQUE,
    presupuesto_compras DECIMAL(12,2) NOT NULL,
    CONSTRAINT chk_responsable_presupuesto CHECK (presupuesto_compras > 0),
    CONSTRAINT fk_responsable_asignacion FOREIGN KEY (id_asignacion)
        REFERENCES asignacion_funcion (id_asignacion) ON UPDATE RESTRICT ON DELETE RESTRICT
) ENGINE = InnoDB;

CREATE TABLE detalle_jefe_estacion (
    id_detalle_jefe INT AUTO_INCREMENT PRIMARY KEY,
    id_asignacion INT NOT NULL UNIQUE,
    nivel_autorizacion TINYINT UNSIGNED NOT NULL,
    CONSTRAINT chk_jefe_nivel CHECK (nivel_autorizacion > 0),
    CONSTRAINT fk_jefe_asignacion FOREIGN KEY (id_asignacion)
        REFERENCES asignacion_funcion (id_asignacion) ON UPDATE RESTRICT ON DELETE RESTRICT
) ENGINE = InnoDB;

CREATE TABLE cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    codigo_cliente VARCHAR(30) NOT NULL UNIQUE,
    nombre VARCHAR(120) NOT NULL,
    telefono VARCHAR(25) NULL,
    email VARCHAR(254) NULL,
    matricula VARCHAR(15) NULL UNIQUE,
    CONSTRAINT chk_cliente_contacto CHECK (telefono IS NOT NULL OR email IS NOT NULL)
) ENGINE = InnoDB;

CREATE TABLE tarjeta_fidelizacion (
    id_tarjeta INT AUTO_INCREMENT PRIMARY KEY,
    numero VARCHAR(40) NOT NULL UNIQUE,
    fecha_emision DATE NOT NULL,
    id_cliente INT NULL UNIQUE,
    fecha_asignacion DATE NULL,
    CONSTRAINT chk_tarjeta_asignacion CHECK (
        (id_cliente IS NULL AND fecha_asignacion IS NULL)
        OR (id_cliente IS NOT NULL AND fecha_asignacion IS NOT NULL
            AND fecha_asignacion >= fecha_emision)
    ),
    CONSTRAINT fk_tarjeta_cliente FOREIGN KEY (id_cliente)
        REFERENCES cliente (id_cliente) ON UPDATE RESTRICT ON DELETE RESTRICT
) ENGINE = InnoDB;

CREATE TABLE producto (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    referencia_comercial VARCHAR(40) NOT NULL UNIQUE,
    descripcion VARCHAR(255) NOT NULL,
    precio_venta DECIMAL(10,2) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    modalidad VARCHAR(15) NOT NULL,
    formato_envase VARCHAR(80) NULL,
    precision_pesada TINYINT UNSIGNED NULL,
    CONSTRAINT uq_producto_modalidad UNIQUE (id_producto, modalidad),
    CONSTRAINT chk_producto_precio CHECK (precio_venta > 0),
    CONSTRAINT chk_producto_estado CHECK (estado IN ('ACTIVO', 'RETIRADO')),
    CONSTRAINT chk_producto_modalidad CHECK (
        (modalidad = 'ENVASADO' AND formato_envase IS NOT NULL AND precision_pesada IS NULL)
        OR (modalidad = 'GRANEL' AND formato_envase IS NULL
            AND precision_pesada BETWEEN 1 AND 3)
    )
) ENGINE = InnoDB;

CREATE TABLE proveedor (
    id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
    codigo_proveedor VARCHAR(30) NOT NULL UNIQUE,
    identificacion_fiscal VARCHAR(30) NOT NULL UNIQUE,
    razon_social VARCHAR(150) NOT NULL,
    telefono VARCHAR(25) NULL,
    email VARCHAR(254) NULL,
    CONSTRAINT chk_proveedor_contacto CHECK (telefono IS NOT NULL OR email IS NOT NULL)
) ENGINE = InnoDB;

CREATE TABLE acuerdo_suministro (
    id_acuerdo INT AUTO_INCREMENT PRIMARY KEY,
    id_proveedor INT NOT NULL,
    id_producto INT NOT NULL,
    referencia_proveedor VARCHAR(50) NOT NULL,
    precio_compra DECIMAL(10,2) NOT NULL,
    plazo_entrega_dias SMALLINT UNSIGNED NOT NULL,
    CONSTRAINT uq_acuerdo_proveedor_producto UNIQUE (id_proveedor, id_producto),
    CONSTRAINT chk_acuerdo_precio CHECK (precio_compra > 0),
    CONSTRAINT chk_acuerdo_plazo CHECK (plazo_entrega_dias > 0),
    CONSTRAINT fk_acuerdo_proveedor FOREIGN KEY (id_proveedor)
        REFERENCES proveedor (id_proveedor) ON UPDATE RESTRICT ON DELETE RESTRICT,
    CONSTRAINT fk_acuerdo_producto FOREIGN KEY (id_producto)
        REFERENCES producto (id_producto) ON UPDATE RESTRICT ON DELETE RESTRICT
) ENGINE = InnoDB;

CREATE TABLE campania (
    id_campania INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(30) NOT NULL UNIQUE,
    nombre VARCHAR(120) NOT NULL,
    descripcion VARCHAR(500) NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    CONSTRAINT chk_campania_fechas CHECK (fecha_fin >= fecha_inicio)
) ENGINE = InnoDB;

CREATE TABLE inscripcion_campania (
    id_inscripcion INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    id_campania INT NOT NULL,
    fecha_inscripcion DATE NOT NULL,
    CONSTRAINT uq_inscripcion_cliente_campania UNIQUE (id_cliente, id_campania),
    CONSTRAINT fk_inscripcion_cliente FOREIGN KEY (id_cliente)
        REFERENCES cliente (id_cliente) ON UPDATE RESTRICT ON DELETE RESTRICT,
    CONSTRAINT fk_inscripcion_campania FOREIGN KEY (id_campania)
        REFERENCES campania (id_campania) ON UPDATE RESTRICT ON DELETE RESTRICT
) ENGINE = InnoDB;

CREATE TABLE incidencia (
    id_incidencia INT AUTO_INCREMENT PRIMARY KEY,
    referencia VARCHAR(40) NOT NULL UNIQUE,
    id_estacion INT NOT NULL,
    descripcion VARCHAR(500) NOT NULL,
    fecha_apertura DATETIME NOT NULL,
    estado VARCHAR(20) NOT NULL,
    id_responsable INT NULL,
    CONSTRAINT chk_incidencia_estado CHECK (estado IN ('ABIERTA', 'EN_CURSO', 'RESUELTA', 'CERRADA')),
    CONSTRAINT fk_incidencia_estacion FOREIGN KEY (id_estacion)
        REFERENCES estacion (id_estacion) ON UPDATE RESTRICT ON DELETE RESTRICT,
    CONSTRAINT fk_incidencia_responsable FOREIGN KEY (id_responsable)
        REFERENCES empleado (id_empleado) ON UPDATE RESTRICT ON DELETE RESTRICT
) ENGINE = InnoDB;

CREATE TABLE venta (
    id_venta INT AUTO_INCREMENT PRIMARY KEY,
    referencia_ticket VARCHAR(50) NOT NULL UNIQUE,
    id_estacion INT NOT NULL,
    id_empleado INT NOT NULL,
    id_cliente INT NULL,
    fecha_hora DATETIME NOT NULL,
    importe_total DECIMAL(12,2) NOT NULL,
    estado VARCHAR(15) NOT NULL,
    CONSTRAINT chk_venta_total CHECK (importe_total >= 0),
    CONSTRAINT chk_venta_estado CHECK (estado IN ('ABIERTA', 'PAGADA', 'ANULADA')),
    CONSTRAINT fk_venta_estacion FOREIGN KEY (id_estacion)
        REFERENCES estacion (id_estacion) ON UPDATE RESTRICT ON DELETE RESTRICT,
    CONSTRAINT fk_venta_empleado FOREIGN KEY (id_empleado)
        REFERENCES empleado (id_empleado) ON UPDATE RESTRICT ON DELETE RESTRICT,
    CONSTRAINT fk_venta_cliente FOREIGN KEY (id_cliente)
        REFERENCES cliente (id_cliente) ON UPDATE RESTRICT ON DELETE RESTRICT
) ENGINE = InnoDB;

CREATE TABLE linea_venta (
    id_linea INT AUTO_INCREMENT PRIMARY KEY,
    id_venta INT NOT NULL,
    id_producto INT NOT NULL,
    modalidad VARCHAR(15) NOT NULL,
    numero_orden SMALLINT UNSIGNED NOT NULL,
    cantidad DECIMAL(10,3) NOT NULL,
    precio_unitario_aplicado DECIMAL(10,2) NOT NULL,
    importe_linea DECIMAL(12,2) NOT NULL,
    CONSTRAINT uq_linea_orden UNIQUE (id_venta, numero_orden),
    CONSTRAINT chk_linea_orden CHECK (numero_orden > 0),
    CONSTRAINT chk_linea_cantidad CHECK (cantidad > 0),
    CONSTRAINT chk_linea_precio CHECK (precio_unitario_aplicado > 0),
    CONSTRAINT chk_linea_importe CHECK (importe_linea > 0),
    CONSTRAINT chk_linea_unidad_entera CHECK (
        modalidad <> 'ENVASADO' OR cantidad = FLOOR(cantidad)
    ),
    CONSTRAINT fk_linea_venta FOREIGN KEY (id_venta)
        REFERENCES venta (id_venta) ON UPDATE RESTRICT ON DELETE RESTRICT,
    CONSTRAINT fk_linea_producto_modalidad FOREIGN KEY (id_producto, modalidad)
        REFERENCES producto (id_producto, modalidad) ON UPDATE RESTRICT ON DELETE RESTRICT
) ENGINE = InnoDB;

-- Una tabla conserva los pagos antiguos de repostaje sin reinterpretarlos y añade
-- pagos de tienda. El CHECK exige los campos propios del tipo elegido.
CREATE TABLE pago (
    id_pago INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(15) NOT NULL,
    id_cliente INT NULL,
    fecha DATE NULL,
    importe DECIMAL(12,2) NOT NULL,
    litros DECIMAL(10,3) NULL,
    combustible VARCHAR(30) NULL,
    id_venta INT NULL,
    fecha_hora DATETIME NULL,
    medio_pago VARCHAR(20) NULL,
    CONSTRAINT chk_pago_importe CHECK (importe > 0),
    CONSTRAINT chk_pago_tipo CHECK (
        (tipo = 'REPOSTAJE' AND id_cliente IS NOT NULL AND fecha IS NOT NULL
            AND litros IS NOT NULL AND litros > 0 AND combustible IS NOT NULL
            AND id_venta IS NULL AND fecha_hora IS NULL AND medio_pago IS NULL)
        OR (tipo = 'TIENDA' AND id_venta IS NOT NULL AND fecha_hora IS NOT NULL
            AND medio_pago IN ('EFECTIVO', 'TARJETA', 'TRANSFERENCIA', 'OTRO')
            AND id_cliente IS NULL AND fecha IS NULL AND litros IS NULL AND combustible IS NULL)
    ),
    CONSTRAINT fk_pago_cliente FOREIGN KEY (id_cliente)
        REFERENCES cliente (id_cliente) ON UPDATE RESTRICT ON DELETE RESTRICT,
    CONSTRAINT fk_pago_venta FOREIGN KEY (id_venta)
        REFERENCES venta (id_venta) ON UPDATE RESTRICT ON DELETE RESTRICT
) ENGINE = InnoDB;

CREATE TABLE devolucion (
    id_devolucion INT AUTO_INCREMENT PRIMARY KEY,
    id_linea INT NOT NULL,
    id_empleado INT NOT NULL,
    cantidad DECIMAL(10,3) NOT NULL,
    motivo VARCHAR(500) NOT NULL,
    fecha_hora DATETIME NOT NULL,
    importe_reintegrado DECIMAL(12,2) NOT NULL,
    apta_reventa BOOLEAN NOT NULL,
    CONSTRAINT chk_devolucion_cantidad CHECK (cantidad > 0),
    CONSTRAINT chk_devolucion_importe CHECK (importe_reintegrado > 0),
    CONSTRAINT fk_devolucion_linea FOREIGN KEY (id_linea)
        REFERENCES linea_venta (id_linea) ON UPDATE RESTRICT ON DELETE RESTRICT,
    CONSTRAINT fk_devolucion_empleado FOREIGN KEY (id_empleado)
        REFERENCES empleado (id_empleado) ON UPDATE RESTRICT ON DELETE RESTRICT
) ENGINE = InnoDB;

CREATE TABLE historial_precio (
    id_historial INT AUTO_INCREMENT PRIMARY KEY,
    id_producto INT NOT NULL,
    precio_anterior DECIMAL(10,2) NOT NULL,
    precio_nuevo DECIMAL(10,2) NOT NULL,
    fecha_hora DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_historial_precios CHECK (
        precio_anterior > 0 AND precio_nuevo > 0 AND precio_anterior <> precio_nuevo
    ),
    CONSTRAINT fk_historial_producto FOREIGN KEY (id_producto)
        REFERENCES producto (id_producto) ON UPDATE RESTRICT ON DELETE RESTRICT
) ENGINE = InnoDB;
