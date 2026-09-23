import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Almacenamiento {
    private static final Path CARPETA = Path.of("datos");
    private static final Path CLIENTES = CARPETA.resolve("clientes.csv");
    private static final Path PAGOS = CARPETA.resolve("pagos.csv");

    public static void preparar() throws IOException {
        Files.createDirectories(CARPETA);

        if (Files.notExists(CLIENTES)) {
            Files.createFile(CLIENTES);
        }

        if (Files.notExists(PAGOS)) {
            Files.createFile(PAGOS);
        }
    }

    public static List<String> leerClientes = new ArrayList<>(); {
        List<String> clientes = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(CLIENTES, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = br.readLine()) != null){
                clientes.add(linea);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> leerPagos = new ArrayList<>();{
        List<String> pagos = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(PAGOS, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = br.readLine()) != null){
                pagos.add(linea);
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }


            }