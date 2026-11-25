import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConecSqlite {
    private static final String URL = "jdbc:sqlite:gasolinera.sqlite";

    private ConecSqlite() {

    }

    public static void main(String[] args) {
        Path archivo = args.length == 0 ? Path.of("sqlite/gasolinera.sqlite") : Path.of(args[0]);
        if (!Files.isRegularFile(archivo)) {
            System.err.println("No existe la base SQLite: " + archivo.toAbsolutePath());
            System.err.println("Prepárala con: python tools/preparar_sqlite.py");
            return;
        }

        String url = "jdbc:sqlite:" + archivo.toAbsolutePath();
        try (Connection conexion = DriverManager.getConnection(url)) {

            try (Statement configuracion = conexion.createStatement()) {
                configuracion.execute("PRAGMA foreign_keys = ON");
            }
            System.out.println("Conexión SQLite abierta: " + archivo.toAbsolutePath());
            System.out.println("Conexión válida: " + conexion.isValid(2));
            System.out.println("Auto-commit: " + conexion.getAutoCommit());
        } catch (SQLException e) {
            System.err.println("Error al conectar con SQLite: " + e.getMessage());
            return;
        }
        System.out.println("La conexión se ha cerrado al salir de try-with-resources.");
    }
}