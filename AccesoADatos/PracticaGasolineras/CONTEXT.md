# Contexto del proyecto

## Propósito y alcance

Este repositorio contiene la práctica de ampliación de una aplicación de
gasolinera para el módulo de Acceso a Datos. La entrega corresponde a la fase de
diseño relacional y preparación del dominio Java.

La aplicación anterior de consola y su persistencia CSV/JSON se conservan. La
nueva parte añade un modelo de dominio para tienda y actividad de las estaciones,
un esquema MySQL con datos de ejemplo y un trigger de historial de precios. En
esta fase Java todavía no se conecta a MySQL: las operaciones JDBC se describen
como trabajo futuro.

## Estructura

- `GasolineraCSVyJSON/src/`: clases Java en el paquete por defecto. Incluye la
  aplicación anterior (`Principal`), persistencia CSV/JSON y migración, además
  de las clases nuevas y `DemostracionFase1`.
- `GasolineraCSVyJSON/sql/`: scripts numerados para esquema, triggers, datos y
  comprobaciones.
- `GasolineraCSVyJSON/datos/` y `GasolineraCSVyJSON/datosJ/`: ficheros CSV y
  JSON usados por la aplicación anterior.
- `STYLE.md`: convenciones observadas y recomendaciones para el código Java.
- `MODELO_RELACIONAL.md`: tablas, relaciones y diccionario de datos.
- `REGLAS_NEGOCIO.md`: garantías actuales y controles pendientes.
- `JDBC_FUTURO.md`: tres operaciones previstas para la fase JDBC.
- `AUDITORIA_IA.odt`: registro de intervenciones de IA de la práctica.
- `Caso_practico_Gasolinera_MySQL_Fase_1.md`: enunciado de referencia.

## Puesta en marcha

Para compilar y ejecutar las demostraciones, sigue las instrucciones de
`README.md`. La base de datos se prepara ejecutando los scripts SQL en orden.
Antes de reiniciarla, revisa la advertencia de borrado de datos del README.

## Límites conocidos

- No hay DAO, conexión JDBC ni consultas SQL desde Java en esta fase.
- `04_comprobaciones.sql` incluye consultas de inspección, cambios de precio y
  rollback; las sentencias que deben fallar están comentadas y se ejecutan
  manualmente. El script modifica algunos datos de ejemplo.
- Según los `INSERT` actuales de `03_datos.sql`, hay 3 estaciones, 4
  proveedores, 4 campañas, 4 ventas y 9 líneas de venta. El enunciado propone
  como referencia al menos 5 registros por tabla principal y 10 líneas; esos
  umbrales aún no se alcanzan para todas las tablas.
- Las restricciones que requieren comprobar varias filas u operaciones
  concurrentes no quedan cubiertas solo con las claves, `CHECK` y triggers
  actuales. Consulta `REGLAS_NEGOCIO.md`.

## Pautas para cambios

Lee `AGENTS.md` y `STYLE.md` antes de modificar el proyecto. Mantén el dominio
separado de la persistencia, conserva las funcionalidades CSV/JSON y no añadas
credenciales personales. En la fase actual, no introduzcas JDBC en las clases
del dominio.
