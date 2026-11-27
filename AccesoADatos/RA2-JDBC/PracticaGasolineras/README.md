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

## MySQL

Los scripts están en `GasolineraCSVyJSON/sql/` y se ejecutan en este orden:

1. `01_esquema.sql`: crea y selecciona `gasolinera_fase1`, y crea las tablas.
2. `02_triggers.sql`: crea los triggers de autosupervisión directa y de
   historial de precios.
3. `03_datos.sql`: carga los datos de ejemplo.
4. `04_comprobaciones.sql`: ejecuta consultas demostrativas y comprobaciones.

En MySQL Workbench, abre y ejecuta cada archivo en ese orden.

### Reiniciar desde cero

Si quieres descartar por completo la base de datos y su contenido, ejecuta
primero: 

```sql
DROP DATABASE IF EXISTS gasolinera_fase1;
```
Después repite los cuatro scripts en el orden indicado. 

### Alcance de las comprobaciones SQL

`04_comprobaciones.sql` contiene consultas de ejemplo, actualiza el
precio y la descripción de un producto, y prueba un cambio de precio dentro de
una transacción que luego revierte. Las sentencias de inserción inválidas están
comentadas para que un error esperado no interrumpa las demás consultas; se
pueden descomentar y ejecutar individualmente. El archivo no es un conjunto de
pruebas automatizado y algunas consultas dejan cambios persistentes en los
datos de ejemplo.

Los `INSERT` actuales de `03_datos.sql` incluyen, entre otros, 3 estaciones, 4
proveedores, 4 campañas, 4 ventas y 10 líneas de venta. El enunciado usa como
referencia al menos 5 registros por tabla principal y 10 líneas; esas cifras
están pendientes de completar en los datos.

## Documentación

- `CONTEXT.md`: propósito, estructura y límites actuales del proyecto.
- `STYLE.md` y `AGENTS.md`: estilo y reglas de trabajo.
- `AUDITORIA_IA.odt`: registro breve de tres intervenciones de IA.
- `Caso_practico_Gasolinera_MySQL_Fase_1.md`: enunciado.
