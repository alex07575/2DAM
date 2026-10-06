import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MigraCSVToJson implements GestorArchivos{
    private static final Path CARPETAJSON = Path.of("datosJ");
    private static final Path CLIENTESJSON = CARPETAJSON.resolve("clientes.json");
    private static final Path PAGOSJSON = CARPETAJSON.resolve("pagos.json");

    public MigraCSVToJson() throws IOException{
        Files.createDirectories(CARPETAJSON);
        if (Files.notExists(CLIENTESJSON)) {
            Files.createFile(CLIENTESJSON);
        }

        if (Files.notExists(PAGOSJSON)) {
            Files.createFile(PAGOSJSON);
        }
    }

    @Override
    public List<Cliente> leerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(CLIENTESJSON, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = br.readLine()) != null){
                String [] lineaDividida = linea.split(",");
                Cliente c1 = new Cliente(Integer.parseInt(lineaDividida[0]),lineaDividida[1],lineaDividida[2],lineaDividida[3]);
                clientes.add(c1);
                clientes.sort(null);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }

    @Override
    public List<Pago> leerPagos() {
        List<Pago> pagos = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(PAGOSJSON, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = br.readLine()) != null){
                String[] lineaDiv = linea.split(",");
                Pago p1 = new Pago(Integer.parseInt(lineaDiv[0]),Integer.parseInt(lineaDiv[1]), LocalDate.parse(lineaDiv[2]),Double.parseDouble(lineaDiv[3]),Double.parseDouble(lineaDiv[4]),lineaDiv[5]);
                pagos.add(p1);
                pagos.sort(null);
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        return pagos;
    }

    @Override
    public void guardarCliente(Cliente c) {
        try(BufferedWriter bw = Files.newBufferedWriter(CLIENTESJSON, StandardCharsets.UTF_8, StandardOpenOption.APPEND,StandardOpenOption.CREATE)) {
            String linea = "\n[" + "id: " + c.getId() + "," + c.getNombre() + "," +
                    c.getTelefono() + "," + c.getMatricula()+ "\n]";
            bw.write(linea);
            bw.newLine();
            System.out.println("Cliente guardado: " + c.getNombre());
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de clientes no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al guardar cliente: " + e.getMessage());
        }
    }

    @Override
    public void guardarPago(Pago p) {
        try (BufferedWriter bw = Files.newBufferedWriter(PAGOSJSON, StandardCharsets.UTF_8, StandardOpenOption.APPEND,StandardOpenOption.CREATE)) {
            String linea = p.getId() + "," + p.getId_cliente() + "," +
                    p.getFecha() + "," + p.getImporte() + "," +
                    p.getLitros() + "," + p.getCombustible()+ ";";
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
