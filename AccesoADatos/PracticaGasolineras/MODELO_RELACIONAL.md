# Modelo relacional

El esquema de `GasolineraCSVyJSON/sql/01_esquema.sql` contiene 23 tablas, todas
InnoDB y con una clave primaria técnica de una sola columna `INT AUTO_INCREMENT`.
Los códigos, referencias y números de negocio conservan restricciones `UNIQUE`
propias: no sustituyen a las claves técnicas.

## Tablas y finalidad

| Tabla | Finalidad | Claves y restricciones principales |
|---|---|---|
| `estacion` | Estaciones de la empresa | `codigo` único |
| `configuracion_facturacion` | Configuración de emisión de cada estación | Código único; `id_estacion` único |
| `surtidor` | Equipos asignados a una estación | Equipo único; `(id_estacion, numero_visible)` único |
| `empleado` | Personal y supervisor actual | Número interno y documento únicos; contacto requerido |
| `funcion` | Catálogo extensible de funciones | Código y nombre únicos |
| `asignacion_funcion` | Periodos de función de cada empleado | `(id_empleado, id_funcion, fecha_inicio)` único |
| `detalle_cajero` | Límite de devolución de una asignación | Una fila por asignación |
| `detalle_expendedor` | Acreditación y caducidad de una asignación | Una fila por asignación; acreditación única |
| `detalle_responsable_tienda` | Presupuesto de una asignación | Una fila por asignación |
| `detalle_jefe_estacion` | Nivel de autorización de una asignación | Una fila por asignación |
| `cliente` | Compradores registrados | Código único; matrícula opcional única |
| `tarjeta_fidelizacion` | Tarjetas asignadas o aún disponibles | Número único; como máximo una tarjeta por cliente |
| `producto` | Catálogo de productos | Referencia única; precio positivo; modalidad y formato coherentes |
| `proveedor` | Empresas proveedoras | Código e identificación fiscal únicos |
| `acuerdo_suministro` | Relación entre proveedor y producto | `(id_proveedor, id_producto)` único |
| `campania` | Campañas con fechas | Código único; fin no anterior al inicio |
| `inscripcion_campania` | Participación de clientes | `(id_cliente, id_campania)` único |
| `incidencia` | Incidencias de estación y responsable actual | Referencia única; responsable opcional |
| `venta` | Tickets de tienda | Referencia única; cliente opcional |
| `linea_venta` | Artículos y precios aplicados a una venta | `(id_venta, numero_orden)` único |
| `pago` | Pagos históricos de repostaje y pagos de tienda | `CHECK` separa los campos requeridos por tipo |
| `devolucion` | Devoluciones parciales de líneas | Referencias a línea y empleado |
| `historial_precio` | Cambios confirmados de precio | Trigger inserta precios anterior y nuevo |

Las claves foráneas usan `ON UPDATE RESTRICT ON DELETE RESTRICT`. Esto conserva
las referencias históricas y evita borrar registros que aún se usan. Las
relaciones opcionales, como el responsable de una incidencia o el titular de
una tarjeta sin asignar, se representan mediante claves foráneas anulables.

## Decisiones de diseño y correspondencia Java

- Las funciones del empleado se representan en `funcion` y en asignaciones
  históricas de `asignacion_funcion`. Los detalles específicos de cada tipo de
  función están en tablas separadas con una relación única a su asignación.
  Java modela esos conceptos con `Funcion`, `AsignacionFuncion` y las clases
  `Detalle...`; esto permite varias funciones simultáneas y conservar periodos
  anteriores.
- `Producto` es abstracta. `ProductoEnvasado` y `ProductoGranel` validan de
  forma polimórfica sus cantidades; la tabla `producto` conserva la modalidad y
  usa `formato_envase` o `precision_pesada` según corresponda.
- La tabla `pago` mantiene los datos de repostaje previos y añade los pagos de
  tienda. El `CHECK` requiere los campos de un tipo y excluye los del otro, sin
  reinterpretar litros o combustible como pagos de tienda.
- `linea_venta` guarda el precio unitario aplicado y el importe de la operación.
  Así, un cambio futuro del precio de catálogo no altera la venta histórica.
- `devolucion` conserva la cantidad, el importe reintegrado y la línea original;
  no borra ni reescribe la venta.
- Los importes Java se calculan con `BigDecimal` y redondeo monetario
  `HALF_UP`; el SQL utiliza tipos `DECIMAL`.

## Diccionario de datos abreviado

| Grupo | Columnas y tipos relevantes | Reglas relevantes |
|---|---|---|
| `estacion` | `codigo`, `nombre`, `direccion`, `telefono`, `fecha_apertura` | Datos obligatorios; código único |
| `configuracion_facturacion` | `codigo_configuracion`, `id_estacion`, `serie_ticket`, `siguiente_numero`, `texto_pie` | Estación única; número siguiente positivo |
| `surtidor` | `codigo_equipo`, `id_estacion`, `numero_visible`, `combustible`, `estado` | Número positivo; estado controlado |
| `empleado` | `numero_interno`, `documento_identidad`, `nombre`, contacto, fechas, estación y supervisor | Contacto opcional individualmente, pero debe existir teléfono o email; supervisor opcional |
| `funcion` / `asignacion_funcion` | Catálogo, empleado, función, inicio y fin | Inicio único por empleado/función; fin nulo o no anterior al inicio |
| Detalles de función | Límite `DECIMAL(10,2)`, acreditación y caducidad, presupuesto `DECIMAL(12,2)`, nivel | Una fila como máximo por asignación y valores positivos donde hay `CHECK` |
| `cliente` / `tarjeta_fidelizacion` | Código, nombre, contactos, matrícula, número y fechas | Cliente requiere teléfono o email; el `CHECK` de asignación pretende coordinar titular y fecha |
| `producto` | Referencia, descripción, precio `DECIMAL(10,2)`, estado, modalidad y detalle de modalidad | Precio positivo; el `CHECK` vincula modalidad y detalle, con la salvedad de `NULL` explicada abajo |
| `proveedor` / `acuerdo_suministro` | Código, identificador fiscal, contactos, producto, referencia, precio y plazo | Contacto de proveedor requerido; precio y plazo positivos; acuerdo único por par |
| `campania` / `inscripcion_campania` | Código, fechas, cliente y campaña | Fechas ordenadas; inscripción única por cliente y campaña |
| `incidencia` | Referencia, estación, descripción, `DATETIME`, estado y responsable opcional | Estado controlado; las referencias deben existir |
| `venta` / `linea_venta` | Ticket, estación, empleado, cliente opcional, total, estado, producto, modalidad, cantidad y precio | Orden única por ticket; cantidad, precio e importe positivos; envasado exige unidades enteras |
| `pago` | Campos de repostaje (`cliente`, fecha, litros, combustible) y de tienda (`venta`, fecha/hora, medio) | El `CHECK` exige una forma válida y excluye la otra |
| `devolucion` | Línea, empleado, cantidad, motivo, fecha/hora, importe, apta para reventa | Cantidad e importe positivos |
| `historial_precio` | Producto, precio anterior, precio nuevo y fecha/hora | Precios positivos y diferentes |

## Integridad y límites del esquema

Las restricciones locales —unicidad, campos obligatorios, valores positivos,
estados y algunas relaciones entre columnas— se aplican con `PRIMARY KEY`,
`UNIQUE`, `FOREIGN KEY` y `CHECK`. El trigger de precio de
`02_triggers.sql` registra cambios reales, no inserciones iniciales ni
actualizaciones que mantengan el precio. Al ser InnoDB, ese registro se revierte
junto con la actualización si la transacción hace `ROLLBACK`.

No todas las reglas del relato se pueden garantizar con las restricciones
actuales:

- En MySQL, un `CHECK` que evalúa `UNKNOWN` por un valor `NULL` no rechaza la
  fila. Por eso `chk_tarjeta_asignacion` puede aceptar una tarjeta con
  `id_cliente` informado y `fecha_asignacion` nula; el `CHECK` de modalidad
  puede aceptar un producto `GRANEL` con `precision_pesada` nula; y el `CHECK`
  de pago puede aceptar un pago `TIENDA` sin `medio_pago`. Estas columnas son
  anulables en el esquema y requieren añadir comprobaciones explícitas de
  `IS NOT NULL` o validarlas en la aplicación.
- Una estación puede insertarse sin su configuración o sin surtidores; el
  esquema no obliga a que exista al menos uno de cada tipo. Las altas deben
  coordinarse en un servicio/transacción y las bajas deben proteger el último
  surtidor.
- El esquema permite crear proveedores o productos sin acuerdos y retirar el
  último acuerdo. La aplicación debe validar las altas y bajas en conjunto.
- Las claves foráneas de `venta` y `incidencia` verifican que estación y
  empleado existan, pero no verifican que el empleado pertenezca a esa estación.
- `UNIQUE` en asignaciones impide repetir la misma fecha inicial, pero no impide
  periodos solapados de una misma función.
- Los triggers impiden la autosupervisión directa; la prevención de ciclos
  indirectos requiere validación adicional.
- En una línea, el SQL exige cantidad entera para modalidad `ENVASADO`, pero no
  comprueba que una cantidad `GRANEL` respete la precisión configurada en el
  producto ni verifica la multiplicación entre cantidad, precio e importe.
- La base no exige que una venta tenga líneas, que la suma de líneas coincida
  con el total ni que la suma de pagos coincida con dicho total.
- Las devoluciones tienen cantidades e importes positivos, pero el esquema no
  suma devoluciones previas ni evita superar la cantidad o el importe originales
  ante operaciones concurrentes.

La implementación prevista para estos controles se detalla en
`REGLAS_NEGOCIO.md` y `JDBC_FUTURO.md`.
