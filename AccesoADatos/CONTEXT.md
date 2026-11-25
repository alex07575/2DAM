# 🗺️ Mapa de `AccesoADatos` — 2DAM

> Un vistazo rápido para saber **qué hay aquí, para qué sirve y dónde está cada cosa**.

## ⚡ El repositorio en 30 segundos

Es el trabajo del módulo **Acceso a Datos** de 2º de DAM. 

**Idea clave:** no son proyectos separados sin relación; son **versiones
sucesivas del mismo dominio** (`Cliente`, `Pago`, `Gasolinera`) ganando
complejidad. Si entiendes `gestionGasolinera`, entiendes la base de todos.

---

## 🌳 Árbol de carpetas

```
AccesoADatos/
│
├── README.md                          # Solo el título del módulo
├── CONTEXT.md                         # 👈 ESTE MAPA
│
├── gestionGasolinera/                 # ① CSV — el punto de partida
│   ├── src/                           # Principal, Gasolinera, Cliente, Pago,
│   │                                 #  GestorArchivos, AlmacenamientoCSV
│   ├── datos/                         # clientes.csv, pagos.csv
│   └── out/                           # .class compilados (artefacto)
│
├── practica2-json/                    # ② JSON — misma app, otro formato
│   ├── src/                           # + AlmacenamientoJSON, + MigraCSVToJson
│   ├── datos/  → datosJ/              # CSV de entrada → JSON de salida
│   └── out/                           # .class compilados (artefacto)
│
├── practicaBD/gestionGasolinera/      # ③ SQLite — primera conexión JDBC
│   ├── src/                           # + ConecSqlite, + CreaSqlite
│   ├── sqlite/gasolinera.sqlite       # base de datos
│   ├── *.jar                          # sqlite-jdbc 3.46.1.0 + slf4j 1.7.36
│   └── datos/                         # CSV originales (se conservan)
│
├── Ficheros binarios con Java/        # ④ Curso paralelo de 8 tareas
│   ├── build.gradle.kts               # Java 21 + JUnit 6 + Gradle Wrapper
│   ├── gradlew / gradlew.bat
│   └── ficherosBinarios/
│       ├── 01_Bytes/                  # bytes sin signo
│       ├── 02_InterpretarBytes/       # un byte, tres lecturas
│       ├── 03_Escribir/               # escribir binario
│       ├── 04_Leer/                   # leer sin modificar
│       ├── 05_TextoYBytes/            # UTF-8
│       ├── 06_EscribirEstructurado/   # escribir registros
│       ├── 07_LeerEstructurado/       # recuperar registros
│       └── 08_ModificarBMP/           # Proyecto final: pintar un píxel
│           (cada tarea: task.md + src/Main.java + test/Tests.java)
│
└── PracticaGasolineras/               # ⑤ ENTREGA ACTUAL — Fase 1
    ├── GasolineraCSVyJSON/
    │   ├── src/                       # ~40 clases: dominio nuevo + CSV/JSON
    │   ├── sql/                       # 01_esquema → 02_triggers →
    │   │                              # 03_datos → 04_comprobaciones
    │   ├── datos/  → datosJ/          # CSV y JSON heredados
    │   └── out/                       # .class compilados (artefacto)
    ├── CONTEXT.md                     # detalle de esta práctica
    ├── MODELO_RELACIONAL.md           # 23 tablas InnoDB
    ├── REGLAS_NEGOCIO.md              # qué garantiza la BD y qué no
    ├── JDBC_FUTURO.md                 # 3 operaciones pendientes
    ├── STYLE.md + AGENTS.md           # normas de estilo y de trabajo
    ├── Caso_practico_..._Fase_1.md    # enunciado del profesor
    ├── CAMBIOS.md + AUDITORIA_IA.odt  # historial y registro de IA
    └── out/                           # .class compilados (artefacto)
```

---

## 📊 Qué va en cada carpeta, en una frase

| # | Carpeta | En una frase | Archivos que mandan | Estado |
|:-:|---|---|---|---|
| ① | `gestionGasolinera/` | Alta de clientes y pagos guardados en CSV con un menú de consola. | `Principal.java`, `Gasolinera.java`, `GestorArchivos.java` | ✅ Funcional |
| ② | `practica2-json/` | Lo mismo, pero persistiendo en JSON y con migrador CSV→JSON. | `AlmacenamientoJSON.java`, `MigraCSVToJson.java` | ✅ Funcional |
| ③ | `practicaBD/gestionGasolinera/` | Aprende a abrir SQLite por JDBC y a leer metadatos del driver. | `ConecSqlite.java`, `CreaSqlite.java` | ✅ Funcional |
| ④ | `Ficheros binarios con Java/` | Curso guiado con tests de bytes, texto, registros y un BMP. | `task.md` de cada tarea, `build.gradle.kts` | ✅ 8 tareas |
| ⑤ | `PracticaGasolineras/` | Ampliación grande: modelo de tienda/estaciones + base MySQL de 23 tablas. | `01_esquema.sql`, `MODELO_RELACIONAL.md`, `DemostracionFase1.java` | 🚧 Fase 1, **sin JDBC** |


