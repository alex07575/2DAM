# Operaciones futuras con JDBC

En esta fase no hay conexión JDBC ni persistencia MySQL desde Java. Las
siguientes operaciones describen trabajo para una fase posterior. Cada
operación debe usar parámetros SQL y transacciones para que una validación o
escritura fallida no deje datos parciales.

## 1. Crear y cobrar una venta

**Datos de entrada:** estación, empleado, cliente opcional, fecha/hora, líneas
con producto y cantidad, y uno o varios pagos con su medio.

**Reglas que debe respetar:**

- Confirmar que estación y empleado existen y que el empleado pertenece a esa
  estación. El cliente, si se indica, debe existir.
- Rechazar productos retirados y validar cada cantidad según modalidad. Para
  granel, respetar la precisión configurada.
- Leer el precio vigente y guardar en cada línea el precio aplicado y el
  importe calculado. Usar `BigDecimal` y el redondeo monetario común
  (`HALF_UP`).
- No registrar una venta confirmada sin líneas. Calcular el total a partir de
  ellas y no confiar en un total recibido del cliente.
- Registrar los pagos y pasar la venta a `PAGADA` solo cuando su suma coincida
  exactamente con el total.
- Insertar cabecera, líneas y pagos dentro de una transacción; ante un error,
  revertir la operación completa.

Las clases `Venta`, `LineaVenta`, `Producto` y `PagoTienda` aportan parte de las
validaciones de dominio, pero no sustituyen las comprobaciones de concurrencia
ni las operaciones de persistencia.

## 2. Registrar una devolución

**Datos de entrada:** línea de venta, cantidad devuelta, motivo, empleado,
fecha/hora y si el producto es apto para reventa.

**Reglas que debe respetar:**

- Comprobar que la línea pertenece a una venta pagada y que el empleado existe.
- Validar la cantidad con la modalidad y precisión del producto.
- Calcular el reintegro usando el precio realmente aplicado en la línea
  original, no el precio vigente de catálogo.
- Consultar y bloquear de forma adecuada la línea y las devoluciones previas;
  la suma devuelta no puede superar la cantidad ni el importe originales,
  incluso si hay solicitudes concurrentes.
- Insertar la devolución completa dentro de una transacción. No borrar ni
  modificar la línea original ni sus pagos.

La clase `Devolucion` y el modelo `LineaVenta` representan la regla en el
dominio. La base actual solo limita cantidades e importes individuales a
valores positivos; no calcula las acumulaciones.

## 3. Cambiar el supervisor

**Datos de entrada:** empleado y nuevo supervisor opcional (un valor nulo deja
al empleado sin supervisor).

**Reglas que debe respetar:**

- Verificar que el empleado y, si se proporciona, el supervisor existen.
- Rechazar que una persona se supervise a sí misma y recorrer la cadena actual
  para evitar ciclos indirectos.
- Actualizar el supervisor sin modificar ventas ni la identidad histórica del
  empleado.
- Ejecutar la validación y actualización como una operación controlada para
  reducir conflictos con cambios simultáneos de supervisión.

El trigger SQL impide la autosupervisión directa, pero los ciclos indirectos
requieren validación adicional. `Empleado.asignarSupervisor` ya comprueba
autosupervisión y ciclos en el modelo Java; esa protección no se aplica a
actualizaciones hechas por otros clientes SQL.

## Documentación relacionada

- `MODELO_RELACIONAL.md`: entidades, relaciones y limitaciones de las
  restricciones actuales.
- `REGLAS_NEGOCIO.md`: separación entre reglas garantizadas y pendientes.
- `GasolineraCSVyJSON/sql/04_comprobaciones.sql`: consultas y casos preparados
  para comprobar parte del comportamiento de la base de datos.
