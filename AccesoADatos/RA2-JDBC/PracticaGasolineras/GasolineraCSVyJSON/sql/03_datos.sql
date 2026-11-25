USE gasolinera_fase1;

INSERT INTO estacion (codigo,nombre,direccion,telefono,fecha_apertura) VALUES
('EST-001','Estación Centro','Calle Mayor 10','910000000','2020-01-01'),
('EST-002','Estación Norte','Avenida del Norte 25','910000020','2021-03-15'),
('EST-003','Estación Sur','Carretera Sur 8','910000030','2019-09-10');

INSERT INTO configuracion_facturacion (codigo_configuracion,id_estacion,serie_ticket,siguiente_numero,texto_pie) VALUES
('CFG-001',1,'A',1001,'Gracias por su visita'),
('CFG-002',2,'N',2001,'Conserve su ticket'),
('CFG-003',3,'S',3001,'Gracias por elegirnos');

INSERT INTO surtidor (codigo_equipo,id_estacion,numero_visible,combustible,estado) VALUES
('SUR-001',1,1,'GASOLINA 95','OPERATIVO'),
('SUR-002',1,2,'GASOLEO A','AVERIADO'),
('SUR-003',1,3,'GASOLINA 98','OPERATIVO'),
('SUR-004',2,1,'GASOLINA 95','OPERATIVO'),
('SUR-005',2,2,'GASOLEO A','FUERA_SERVICIO'),
('SUR-006',3,1,'GASOLEO A','OPERATIVO');

INSERT INTO empleado (numero_interno,documento_identidad,nombre,telefono,email,fecha_incorporacion,id_estacion,id_supervisor) VALUES
('EMP-001','12345678A','Ana López','600000001','ana@ejemplo.test','2022-05-01',1,NULL),
('EMP-002','23456789B','Carlos Ruiz','600000002','carlos@ejemplo.test','2023-01-10',1,1),
('EMP-003','34567890C','Elena Martín','600000003','elena@ejemplo.test','2022-08-20',2,1),
('EMP-004','45678901D','David Gómez','600000004','david@ejemplo.test','2024-02-01',2,NULL),
('EMP-005','56789012E','Lucía Soto','600000005','lucia@ejemplo.test','2021-11-11',3,1),
('EMP-006','67890123F','Marta Díaz',NULL,'marta@ejemplo.test','2025-04-01',3,NULL);

INSERT INTO funcion (codigo,nombre) VALUES
('CAJERO','Cajero'),('EXPENDEDOR','Expendedor'),
('RESP_TIENDA','Responsable de tienda'),('JEFE','Jefe de estación');

INSERT INTO asignacion_funcion (id_empleado,id_funcion,fecha_inicio,fecha_fin) VALUES
(1,1,'2022-05-01','2023-05-01'),(1,1,'2023-06-01',NULL),
(1,4,'2023-06-01',NULL),(2,1,'2023-01-10',NULL),
(2,2,'2023-02-01',NULL),(3,3,'2022-08-20',NULL),
(4,4,'2024-02-01',NULL),(5,2,'2021-11-11',NULL),
(6,1,'2025-04-01',NULL);

INSERT INTO detalle_cajero (id_asignacion,limite_devolucion) VALUES (1,100.00),(2,150.00),(4,80.00),(9,100.00);
INSERT INTO detalle_expendedor (id_asignacion,numero_acreditacion,caducidad) VALUES
(5,'ACR-EMP-002','2027-12-31'),(8,'ACR-EMP-005','2026-12-31');
INSERT INTO detalle_responsable_tienda (id_asignacion,presupuesto_compras) VALUES (6,2500.00);
INSERT INTO detalle_jefe_estacion (id_asignacion,nivel_autorizacion) VALUES (3,3),(7,4);

INSERT INTO cliente (codigo_cliente,nombre,telefono,email,matricula) VALUES
('CLI-001','Luis Pérez','600000102','luis@ejemplo.test','1234AAA'),
('CLI-002','María García','600000103','maria@ejemplo.test','5678BBB'),
('CLI-003','Jorge León',NULL,'jorge@ejemplo.test','9012CCC'),
('CLI-004','Sara Vega','600000104',NULL,NULL),
('CLI-005','Óscar Ruiz',NULL,'oscar@ejemplo.test',NULL),
('CLI-006','Nuria Gil','600000106','nuria@ejemplo.test',NULL);

INSERT INTO tarjeta_fidelizacion (numero,fecha_emision,id_cliente,fecha_asignacion) VALUES
('TAR-001','2024-01-10',1,'2024-01-12'),
('TAR-002','2024-02-15',2,'2024-02-20'),
('TAR-003','2024-03-01',NULL,NULL),
('TAR-004','2024-04-01',NULL,NULL),
('TAR-005','2024-05-01',3,'2024-05-05');

INSERT INTO producto (referencia_comercial,descripcion,precio_venta,estado,modalidad,formato_envase,precision_pesada) VALUES
('BOLSA-001','Frutos secos en bolsa',2.50,'ACTIVO','ENVASADO','Bolsa de 250 g',NULL),
('GRA-001','Frutos secos a granel',10.00,'ACTIVO','GRANEL',NULL,3),
('AGUA-001','Agua mineral 1,5 l',1.20,'ACTIVO','ENVASADO','Botella de 1,5 l',NULL),
('CAF-001','Café mezcla',1.50,'ACTIVO','ENVASADO','Vaso',NULL),
('ACEIT-001','Aceitunas a granel',8.50,'ACTIVO','GRANEL',NULL,2),
('CHOC-001','Chocolate especial',3.75,'RETIRADO','ENVASADO','Tableta',NULL);

INSERT INTO proveedor (codigo_proveedor,identificacion_fiscal,razon_social,telefono,email) VALUES
('PROV-001','B12345678','Distribuciones Centro','910000101','compras@centro.test'),
('PROV-002','A87654321','Mayorista Norte','910000102','ventas@norte.test'),
('PROV-003','B11223344','Alimentación Sur','910000103','sur@ejemplo.test'),
('PROV-004','A55667788','Suministros Unidos','910000104','info@unidos.test');

INSERT INTO acuerdo_suministro (id_proveedor,id_producto,referencia_proveedor,precio_compra,plazo_entrega_dias) VALUES
(1,1,'BOL-CENTRO',1.20,2),(1,2,'GRA-CENTRO',6.00,3),
(2,1,'BOL-NORTE',1.15,4),(2,3,'AGUA-NORTE',0.65,2),
(3,2,'GRA-SUR',5.80,5),(3,4,'CAF-SUR',0.80,2),
(4,5,'ACEIT-UNIDOS',5.50,4),(4,6,'CHOC-UNIDOS',2.10,3);

INSERT INTO campania (codigo,nombre,descripcion,fecha_inicio,fecha_fin) VALUES
('CAM-001','Puntos dobles','Duplicación de puntos','2026-01-01','2026-03-31'),
('CAM-002','Verano fresco','Promoción de bebidas','2026-06-01','2026-08-31'),
('CAM-003','Primavera','Promoción de frutos secos','2026-04-01','2026-05-31'),
('CAM-004','Cliente nuevo','Campaña sin inscritos','2026-12-01','2026-12-31');

INSERT INTO inscripcion_campania (id_cliente,id_campania,fecha_inscripcion) VALUES
(1,1,'2026-01-05'),(1,2,'2026-06-02'),(2,1,'2026-01-10'),
(3,1,'2026-02-01'),(3,3,'2026-04-05'),(4,2,'2026-06-10'),
(5,3,'2026-04-12');

INSERT INTO incidencia (referencia,id_estacion,descripcion,fecha_apertura,estado,id_responsable) VALUES
('INC-001',1,'Surtidor fuera de servicio','2026-02-10 09:15:00','ABIERTA',NULL),
('INC-002',1,'Revisión de impresora','2026-02-11 11:30:00','EN_CURSO',2),
('INC-003',2,'Fuga de aceite','2026-02-12 08:00:00','RESUELTA',3),
('INC-004',2,'Iluminación deficiente','2026-02-13 18:20:00','ABIERTA',3),
('INC-005',3,'Cambio de precinto','2026-02-14 10:10:00','EN_CURSO',5),
('INC-006',3,'Control de inventario','2026-02-15 12:00:00','CERRADA',NULL);

INSERT INTO venta (referencia_ticket,id_estacion,id_empleado,id_cliente,fecha_hora,importe_total,estado) VALUES
('TICKET-001',1,2,1,'2026-02-20 10:15:00',10.00,'PAGADA'),
('TICKET-002',1,1,NULL,'2026-02-20 11:00:00',5.20,'PAGADA'),
('TICKET-003',2,3,2,'2026-02-21 12:30:00',12.78,'PAGADA'),
('TICKET-004',3,5,NULL,'2026-02-22 09:45:00',8.50,'PAGADA');

INSERT INTO linea_venta (id_venta,id_producto,modalidad,numero_orden,cantidad,precio_unitario_aplicado,importe_linea) VALUES
(1,1,'ENVASADO',1,2,2.50,5.00),(1,2,'GRANEL',2,0.500,10.00,5.00),
(2,1,'ENVASADO',1,1,2.50,2.50),(2,3,'ENVASADO',2,1,1.20,1.20),(2,4,'ENVASADO',3,1,1.50,1.50),
(3,2,'GRANEL',1,0.250,10.00,2.50),(3,3,'ENVASADO',2,2,1.20,2.40),(3,5,'GRANEL',3,0.75,8.50,6.38),(3,4,'ENVASADO',4,1,1.50,1.50),
(4,5,'GRANEL',1,1.00,8.50,8.50);

INSERT INTO pago (tipo,id_cliente,fecha,importe,litros,combustible,id_venta,fecha_hora,medio_pago) VALUES
('TIENDA',NULL,NULL,5.00,NULL,NULL,1,'2026-02-20 10:16:00','EFECTIVO'),
('TIENDA',NULL,NULL,5.00,NULL,NULL,1,'2026-02-20 10:16:30','TARJETA'),
('TIENDA',NULL,NULL,5.20,NULL,NULL,2,'2026-02-20 11:01:00','EFECTIVO'),
('TIENDA',NULL,NULL,12.78,NULL,NULL,3,'2026-02-21 12:31:00','TARJETA'),
('TIENDA',NULL,NULL,8.50,NULL,NULL,4,'2026-02-22 09:46:00','TRANSFERENCIA'),
('REPOSTAJE',1,'2026-02-20',45.50,20.000,'GASOLINA 95',NULL,NULL,NULL);

INSERT INTO devolucion (id_linea,id_empleado,cantidad,motivo,fecha_hora,importe_reintegrado,apta_reventa) VALUES
(1,1,1,'Envase sin abrir','2026-02-21 10:00:00',2.50,TRUE),
(1,2,1,'Producto defectuoso','2026-02-22 10:00:00',2.50,FALSE),
(2,1,0.250,'Peso incorrecto','2026-02-21 11:00:00',2.50,FALSE),
(5,2,1,'Producto caducado','2026-02-22 12:00:00',1.20,FALSE),
(6,3,0.100,'Cambio de pedido','2026-02-23 09:00:00',0.12,FALSE);