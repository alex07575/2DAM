# Guía de estilo observada en el proyecto

Esta guía toma como referencia las clases de `src/`. Distingue las costumbres existentes de las recomendaciones para el código nuevo, para mantener un estilo accesible sin copiar errores técnicos.

## Estilo que ya se usa

- Los nombres y mensajes están principalmente en español: `Cliente`, `Pago`, `listarClientes`.
- Las clases están en el paquete por defecto y directamente dentro de `src/`.
- Los atributos suelen ser privados y se exponen mediante getters.
- Las clases de dominio validan datos en sus constructores usando `IllegalArgumentException`.
- Se usan tipos y estructuras sencillos: `String`, `int`, `double`, `LocalDate`, `List` y `ArrayList`.
- `GestorArchivos` separa las operaciones de lectura y escritura de sus implementaciones CSV y JSON.
- Se usa `Path`, UTF-8 y `try-with-resources` para trabajar con archivos.
- La aplicación presenta un menú de consola y mensajes en español.

## Decisiones a evitar

- No lanzar `RuntimeException` genérica (`throw new RuntimeException(...)`). Para validaciones de dominio se debe usar `IllegalArgumentException` (u otras excepciones específicas).
- No mezclar lógica de archivos o SQL dentro de las clases del dominio.
- No añadir paquetes nuevos si el enunciado no lo pide; mantener la estructura en `src/` salvo que se indique lo contrario.


## Recomendaciones para las clases nuevas

- Mantener nombres en español y elegir una responsabilidad clara por clase.
- Mantener los atributos privados, preferir campos inmutables y validar los valores al crear el objeto.
- Usar `BigDecimal` para importes/precios y `LocalDate` o `LocalDateTime` para fechas. `double` no representa exactamente muchos decimales y no conviene para dinero.
- Usar `enum` para conjuntos cerrados de estados o tipos y métodos del dominio que expresen acciones válidas.
- Mantener el dominio independiente de archivos, JDBC y SQL. La persistencia se añade en una capa separada.
- Declarar colecciones con la interfaz `List` y crear una implementación concreta como `ArrayList`.
- Usar cuatro espacios de sangría, `camelCase` en métodos/variables y `PascalCase` en clases/enums.
- Propagar errores de entrada/salida de forma explícita; no devolver un valor aparentemente válido cuando ha fallado la lectura.

## Limitaciones del código actual que no se deben repetir

El lector CSV usa `split(",")`, que no interpreta comas dentro de un campo entrecomillado. El escritor añade `;` al último dato, de modo que se contamina el valor leído. El JSON se construye y analiza manualmente y el envoltorio existente no es JSON estándar; también puede fallar con comillas en los datos.

Los formatos antiguos deben seguir siendo utilizables, pero el código nuevo debe evitar concatenar separadores a los datos y debe escribir JSON estándar. Los constructores de `Cliente` y `Pago` existentes se conservan para no romper los archivos históricos.

## Límite de Fase 1

Las clases de dominio validan datos y representan reglas locales. No abren conexiones ni ejecutan consultas: JDBC se incorporará en la fase siguiente. Las reglas que dependen de varias filas (por ejemplo, impedir ciclos de supervisión o validar la suma de pagos) requieren una transacción y lógica de servicio/base de datos.
