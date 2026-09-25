import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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

    public static List<Cliente> leerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(CLIENTES, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = br.readLine()) != null){
                String [] lineaDividida = linea.split(";");
                Cliente c1 = new Cliente(Integer.parseInt(lineaDividida[0]),lineaDividida[1],lineaDividida[2],lineaDividida[3]);
                clientes.add(c1);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }

    public static List<Pago> leerPagos(){
        List<Pago> pagos = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(PAGOS, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = br.readLine()) != null){
                String[] lineaDiv = linea.split(";");
                Pago p1 = new Pago(Integer.parseInt(lineaDiv[0]),Integer.parseInt(lineaDiv[1]), LocalDate.parse(lineaDiv[2]),Double.parseDouble(lineaDiv[3]),Double.parseDouble(lineaDiv[4]),lineaDiv[5]);
                pagos.add(p1);
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        return pagos;
    }

    public static void guardarCliente(Cliente c) throws IOException {
        try(BufferedWriter bw = Files.newBufferedWriter(CLIENTES, StandardCharsets.UTF_8, StandardOpenOption.APPEND,StandardOpenOption.CREATE)) {
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
        try (BufferedWriter bw = Files.newBufferedWriter(PAGOS, StandardCharsets.UTF_8, StandardOpenOption.APPEND,StandardOpenOption.CREATE)) {
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