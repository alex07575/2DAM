# Reglas de negocio

Este documento distingue las garantías declaradas en MySQL de las validaciones
que dependen de Java o de una futura capa JDBC. Que los datos de ejemplo
respeten una regla no significa que la base de datos la aplique a cualquier
operación.

## Garantizadas por el esquema o los triggers actuales

| Regla | Garantía actual |
|---|---|
| Identidad técnica por tabla | Clave primaria `INT AUTO_INCREMENT` |
| Unicidad de códigos y referencias declarados | Restricciones `UNIQUE` |
| Una configuración como máximo por estación | `UNIQUE(id_estacion)` en `configuracion_facturacion` |
| Número visible único dentro de la estación | `UNIQUE(id_estacion, numero_visible)` |
| Referencias a registros existentes | Claves foráneas con `RESTRICT` |
| Campos requeridos y algunos valores positivos | `NOT NULL` y `CHECK` |
| Estados de surtidor, producto, venta e incidencia y valores no nulos permitidos para medio de pago | `CHECK` |
| Contacto de empleado, cliente y proveedor | `CHECK`: al menos teléfono o email no nulo; no descarta cadenas vacías |
| Correspondencia entre modalidad de producto y sus columnas específicas | `CHECK` combinado en `producto`; por la semántica de `NULL` de MySQL no garantiza que `precision_pesada` esté informada para `GRANEL` |
| Cantidad entera para productos envasados | `CHECK` en `linea_venta` |
| Fecha de fin de asignación/campaña y orden de fechas de tarjeta cuando se informa | `CHECK`; el de tarjeta no exige correctamente la fecha si ya hay titular |
| Como máximo una tarjeta por cliente y ninguna duplicación de inscripción | Restricciones `UNIQUE` |
| Una sola asignación con la misma fecha inicial para empleado y función | `UNIQUE` compuesto |
| Estructura de campos de pagos de repostaje y de tienda | `CHECK` en `pago`; puede aceptar un pago de tienda con `medio_pago` nulo |
| Precio anterior y nuevo distintos en una entrada de historial | `CHECK` en `historial_precio` |
| Rechazo de autosupervisión directa al insertar o actualizar | Triggers en `02_triggers.sql` |
| Historial automático al cambiar realmente el precio | Trigger `AFTER UPDATE` de producto |
| Sin historial al insertar producto, guardar el mismo precio o cambiar otro campo | El trigger solo inserta si cambia el precio |
| Reversión del historial junto con la modificación de precio | Transacción sobre tablas InnoDB |

Los `CHECK` requieren MySQL 8.0.16 o posterior para aplicarse como restricciones.

## Controles pendientes y tratamiento previsto

| Regla pendiente | Tratamiento recomendado |
|---|---|
| Crear estación con su configuración y al menos un surtidor | Alta transaccional y validación de elementos relacionados |
| Evitar que una estación se quede sin surtidores | Comprobar el último surtidor bajo transacción antes de retirarlo |
| Crear proveedor/producto con acuerdos y no dejar entidades aisladas | Alta y baja de acuerdos coordinadas en una transacción |
| Exigir precisión para producto `GRANEL`, titular y fecha de asignación coherentes, y medio de pago para `TIENDA` | Añadir validaciones `IS NOT NULL` en los `CHECK` correspondientes o validar en el servicio antes de persistir |
| Rechazar contactos vacíos o en blanco | Validar y normalizar los campos de contacto antes de persistir |
| Asegurar que el empleado que registra una venta o atiende una incidencia pertenece a la misma estación | Validación de servicio; las claves actuales solo comprueban que ambos registros existan |
| Evitar ciclos indirectos en la supervisión | Recorrer la cadena de supervisores antes de actualizar, dentro de una operación controlada |
| Evitar solapamientos de periodos de la misma función | Consultar periodos existentes y reservar/insertar bajo transacción |
| Confirmar venta solo con líneas y total calculado correctamente | Construir venta y líneas dentro de una transacción; calcular con precio vigente |
| Confirmar cobro cuando los pagos coincidan con el total | Sumar pagos y cambiar el estado de venta en la misma transacción |
| Respetar precisión de pesada de cada producto a granel | Validar la cantidad con la modalidad y precisión de producto antes de insertar |
| Evitar cambios en líneas de ventas cerradas | Restringir las operaciones de actualización en la capa de servicio |
| Limitar las devoluciones acumuladas a la cantidad e importe originales | Bloquear o serializar el cálculo de devoluciones e insertar en una transacción |

La aplicación Java de esta fase contiene validaciones de dominio para varias
reglas locales, como modalidad de producto, cantidades, supervisión y periodos.
Estas validaciones no sustituyen a controles JDBC/SQL: otros clientes de la base
de datos no pasan por el dominio Java.

## Evidencias disponibles en el repositorio

`GasolineraCSVyJSON/sql/04_comprobaciones.sql`:

- consulta conteos de las tablas principales y agrupa líneas y pagos por venta;
- cambia un precio, vuelve a guardar el mismo precio y cambia una descripción
  para mostrar cuándo debe escribirse el historial;
- consulta el precio histórico aplicado a líneas existentes;
- cambia un precio dentro de una transacción y ejecuta `ROLLBACK`;
- contiene ejemplos de inserciones inválidas comentadas para claves foráneas,
  unicidad y `CHECK`.

Las inserciones inválidas están comentadas: hay que ejecutar cada caso
individualmente para observar el error esperado. El script de comprobaciones
no es una suite automatizada ni deja intactos todos los datos de ejemplo.
No se afirma aquí que los scripts se hayan ejecutado contra un servidor MySQL;
la evidencia descrita es el contenido preparado en los archivos SQL.

Según los `INSERT` actuales de `03_datos.sql`, hay 3 estaciones, 4 proveedores,
4 campañas, 4 ventas y 9 líneas de venta. El enunciado propone como referencia
al menos 5 registros por tabla principal y 10 líneas; falta completar esos
volúmenes. El resto de los datos debe revisarse contra los casos requeridos
antes de considerar cerrada la preparación de la muestra.
