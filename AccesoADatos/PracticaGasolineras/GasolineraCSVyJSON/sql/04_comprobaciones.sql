USE gasolinera_fase1;

-- Conteos básicos: las tablas principales contienen datos suficientes.
SELECT 'estaciones' AS tabla, COUNT(*) AS registros FROM estacion
UNION ALL SELECT 'empleados', COUNT(*) FROM empleado
UNION ALL SELECT 'clientes', COUNT(*) FROM cliente
UNION ALL SELECT 'productos', COUNT(*) FROM producto
UNION ALL SELECT 'proveedores', COUNT(*) FROM proveedor
UNION ALL SELECT 'ventas', COUNT(*) FROM venta
UNION ALL SELECT 'líneas', COUNT(*) FROM linea_venta
UNION ALL SELECT 'pagos', COUNT(*) FROM pago
UNION ALL SELECT 'devoluciones', COUNT(*) FROM devolucion;

-- Ejemplo de consulta con varias líneas y cobros.
SELECT v.referencia_ticket, v.importe_total, v.estado,
       COUNT(DISTINCT l.id_linea) AS lineas,
       COUNT(DISTINCT p.id_pago) AS pagos
FROM venta v
LEFT JOIN linea_venta l ON l.id_venta = v.id_venta
LEFT JOIN pago p ON p.id_venta = v.id_venta
GROUP BY v.id_venta, v.referencia_ticket, v.importe_total, v.estado;

-- Cambio real: crea una entrada en el historial.
UPDATE producto SET precio_venta = 11.00
WHERE id_producto = 2 AND precio_venta = 10.00;

-- Mismo precio: no debe crear entrada.
UPDATE producto SET precio_venta = 11.00 WHERE id_producto = 2;

-- Solo descripción: no debe crear entrada.
UPDATE producto SET descripcion = 'Frutos secos a granel; formato revisado'
WHERE id_producto = 2;

-- Deben existir exactamente las entradas esperadas después de las comprobaciones anteriores.
SELECT * FROM historial_precio;

-- La línea conservó el precio antiguo aunque el catálogo haya cambiado.
SELECT id_linea, precio_unitario_aplicado
FROM linea_venta
WHERE id_producto = 2;

-- Rollback completo: el precio y el disparo deben revertirse.
START TRANSACTION;
SELECT COUNT(*) AS historial_antes FROM historial_precio;
UPDATE producto SET precio_venta = 12.00 WHERE id_producto = 2;
SELECT precio_venta FROM producto WHERE id_producto = 2;
SELECT COUNT(*) AS historial_dentro FROM historial_precio;
ROLLBACK;
SELECT precio_venta FROM producto WHERE id_producto = 2;
SELECT COUNT(*) AS historial_despues FROM historial_precio;

-- Operación deliberadamente inválida: referencia de estación inexistente.
-- Debe producir error por fk_surtidor_estacion.
-- INSERT INTO surtidor (codigo_equipo,id_estacion,numero_visible,combustible,estado)
-- VALUES ('SUR-ERROR',999,1,'GASOLINA 95','OPERATIVO');

-- Operación deliberadamente inválida: código de estación duplicado.
-- Debe producir error por UNIQUE(codigo).
-- INSERT INTO estacion (codigo,nombre,direccion,telefono,fecha_apertura)
-- VALUES ('EST-001','Duplicada','Sin dirección','910000999','2026-01-01');

-- Operación deliberadamente inválida: surtidor repetido en la misma estación.
-- Debe producir error por uq_surtidor_estacion_numero.
-- INSERT INTO surtidor (codigo_equipo,id_estacion,numero_visible,combustible,estado)
-- VALUES ('SUR-DUP',1,1,'GASOLINA 95','OPERATIVO');

-- Operación deliberadamente inválida: campaña con fin anterior al inicio.
-- Debe producir error por chk_campania_fechas.
-- INSERT INTO campania (codigo,nombre,descripcion,fecha_inicio,fecha_fin)
-- VALUES ('CAM-ERR','Error','Fechas inválidas','2026-05-01','2026-04-01');

-- Operación deliberadamente inválida: cantidad fraccionaria en producto envasado.
-- Debe producir error por chk_linea_unidad_entera.
-- INSERT INTO linea_venta (id_venta,id_producto,modalidad,numero_orden,cantidad,precio_unitario_aplicado,importe_linea)
-- VALUES (4,1,'ENVASADO',2,1.500,2.50,3.75);

-- Operación deliberadamente inválida: pago de repostaje mal clasificado.
-- Debe producir error por chk_pago_tipo.
-- INSERT INTO pago (tipo,id_cliente,fecha,importe,litros,combustible,id_venta,fecha_hora,medio_pago)
-- VALUES ('REPOSTAJE',1,'2026-03-01',20.00,NULL,NULL,NULL,NULL,NULL);

-- Comprobaciones pendientes expresamente: solapes, ciclos, altas conjuntas,
-- aislamiento del último acuerdo y devoluciones acumuladas concurrentes.