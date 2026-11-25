# PracticaGasolineras

Ampliación de una aplicación Java de gasolinera con persistencia existente en
CSV/JSON y un diseño relacional para MySQL. La entrega actual prepara el dominio
y la base de datos; **todavía no implementa persistencia JDBC desde Java**.

## Requisitos

- JDK 17 o superior.
- IntelliJ IDEA o herramientas `javac` y `java`.
- MySQL 8.0.16 o superior, necesario para aplicar las restricciones `CHECK`
  usadas en el esquema.
- Tablas InnoDB.

No se incluyen credenciales MySQL. Usa las de tu propio servidor, sin guardarlas
en los archivos del proyecto.

## Preparar MySQL

Los scripts están en `GasolineraCSVyJSON/sql/` y se ejecutan en este orden:

1. `01_esquema.sql`: crea y selecciona `gasolinera_fase1`, y crea las tablas.
2. `02_triggers.sql`: crea los triggers de autosupervisión directa y de
   historial de precios.
3. `03_datos.sql`: carga los datos de ejemplo.
4. `04_comprobaciones.sql`: ejecuta consultas demostrativas y comprobaciones.

En MySQL Workbench, abre y ejecuta cada archivo en ese orden. En el cliente
interactivo de MySQL también puedes usar `SOURCE` con la ruta de cada script,
por ejemplo:

```sql
SOURCE C:/ruta/al/proyecto/GasolineraCSVyJSON/sql/01_esquema.sql;
SOURCE C:/ruta/al/proyecto/GasolineraCSVyJSON/sql/02_triggers.sql;
SOURCE C:/ruta/al/proyecto/GasolineraCSVyJSON/sql/03_datos.sql;
SOURCE C:/ruta/al/proyecto/GasolineraCSVyJSON/sql/04_comprobaciones.sql;
```

Sustituye `C:/ruta/al/proyecto` por la ubicación real del repositorio. Ejecuta
la preparación inicial sobre una base de datos vacía. Aunque el script del
esquema crea la base de datos si no existe, las tablas no se crean con
`IF NOT EXISTS`; los datos tampoco están preparados para insertarse varias
veces. Por tanto, volver a ejecutar toda la secuencia sobre el mismo esquema
puede producir errores de tablas o claves duplicadas.

### Reiniciar desde cero

Si quieres descartar por completo la base de datos y su contenido, ejecuta
primero:

```sql
DROP DATABASE IF EXISTS gasolinera_fase1;
```

Después repite los cuatro scripts en el orden indicado. **Esta operación borra
permanentemente todas las tablas y datos de `gasolinera_fase1`; no la ejecutes
si necesitas conservarlos.** No es necesario añadir `IF NOT EXISTS` a cada
tabla: eso podría esconder que el esquema existente no coincide con el script.

### Alcance de las comprobaciones SQL

`04_comprobaciones.sql` contiene conteos y consultas de ejemplo, actualiza el
precio y la descripción de un producto, y prueba un cambio de precio dentro de
una transacción que luego revierte. Las sentencias de inserción inválidas están
comentadas para que un error esperado no interrumpa las demás consultas; se
pueden descomentar y ejecutar individualmente. El archivo no es un conjunto de
pruebas automatizado y algunas consultas dejan cambios persistentes en los
datos de ejemplo.

Los `INSERT` actuales de `03_datos.sql` incluyen, entre otros, 3 estaciones, 4
proveedores, 4 campañas, 4 ventas y 9 líneas de venta. El enunciado usa como
referencia al menos 5 registros por tabla principal y 10 líneas; esas cifras
están pendientes de completar en los datos.

## Compilar y ejecutar Java

Desde PowerShell, sitúate en `GasolineraCSVyJSON`:

```powershell
New-Item -ItemType Directory -Force out | Out-Null
javac -d out src\*.java
java -cp out DemostracionFase1
```

La demostración construye una venta con productos envasados y a granel, calcula
importes, registra dos medios de pago y muestra una devolución parcial y el
rechazo de una cantidad fraccionaria para producto envasado. No necesita MySQL.

Para iniciar la aplicación de consola anterior, desde la misma carpeta ejecuta:

```powershell
java -cp out Principal
```

Esta aplicación usa los ficheros de `datos/` y `datosJ/`; la clase
`MigraCSVToJson` conserva la operación de migración existente.

## Documentación

- `CONTEXT.md`: propósito, estructura y límites actuales del proyecto.
- `MODELO_RELACIONAL.md`: tablas, claves, relaciones y diccionario de datos.
- `REGLAS_NEGOCIO.md`: garantías de base de datos, limitaciones y evidencias.
- `JDBC_FUTURO.md`: operaciones previstas para la fase JDBC.
- `STYLE.md` y `AGENTS.md`: convenciones y pautas de trabajo.
- `AUDITORIA_IA.odt`: registro breve de tres intervenciones de IA.
- `Caso_practico_Gasolinera_MySQL_Fase_1.md`: enunciado.
