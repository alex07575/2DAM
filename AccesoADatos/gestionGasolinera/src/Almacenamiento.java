import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
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

    public static void guardarCliente(Cliente c) throws IOException {
        try(BufferedWriter bw = Files.newBufferedWriter(CLIENTES, StandardCharsets.UTF_8)) {
            String linea = c.getId() + ";" + c.getNombre() + ";" +
                    c.getTelefono() + ";" + c.getMatricula();
            bw.write(linea);
            bw.newLine();
            System.out.println("Cliente guardado: " + c.getNombre());
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de clientes no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al guardar cliente: " + e.getMessage());
        }
    }

    public static void guardarPago(Pago p) throws IOException {
        try (BufferedWriter bw = Files.newBufferedWriter(PAGOS, StandardCharsets.UTF_8)) {
            String linea = p.getId() + ";" + p.getId_cliente() + ";" +
                    p.getFecha() + ";" + p.getImporte() + ";" +
                    p.getLitros() + ";" + p.getCombustible();
            bw.write(linea);
            bw.newLine();
            System.out.println("Pago guardado: $" + p.getImporte());
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de pagos no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al guardar pago: " + e.getMessage());
        }
    }
}