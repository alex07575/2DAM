import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;

public class CreaSqlite {
    private CreaSqlite() {
    }

    public static void main(String[] args) {
        Path archivo = Path.of("gasolinera.sqlite");

        try (Connection conexion = DriverManager.getConnection("jdbc:sqlite:" + archivo.toAbsolutePath())) {
            try (Statement configuracion = conexion.createStatement()) {
                configuracion.execute("PRAGMA foreign_keys = ON");
            }
            DatabaseMetaData datos = conexion.getMetaData();
            System.out.println("Base de datos en: " + archivo.toAbsolutePath());
            System.out.println("Gestor: " + datos.getDatabaseProductName());
            System.out.println("Versión del gestor: " + datos.getDatabaseProductVersion());
            System.out.println("Driver: " + datos.getDriverName());
            System.out.println("Versión del driver: " + datos.getDriverVersion());
            System.out.println("Conexión válida: " + conexion.isValid(2));
            System.out.println("Cerrada dentro del bloque: " + conexion.isClosed());
        } catch (SQLException e) {
            System.err.println("Error al consultar metadatos de SQLite: " + e.getMessage());
        }
    }
}
