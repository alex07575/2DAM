import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AlmacenamientoJSON implements GestorArchivos{
    public static final Path CARPETA = Path.of("datosJ");
    public static final Path CLIENTES = CARPETA.resolve("clientes.json");
    public static final Path PAGOS = CARPETA.resolve("pagos.json");

    public AlmacenamientoJSON() throws  IOException{
        Files.createDirectories(CARPETA);

        if (Files.notExists(CLIENTES)) {
            Files.createFile(CLIENTES);
        }

        if (Files.notExists(PAGOS)) {
            Files.createFile(PAGOS);
        }
    }

    @Override
    public List<Cliente> leerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(CLIENTES, StandardCharsets.UTF_8)) {
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
    public List<Pago> leerPagos(){
        List<Pago> pagos = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(PAGOS, StandardCharsets.UTF_8)) {
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
    public void guardarCliente(Cliente c){
        try(BufferedWriter bw = Files.newBufferedWriter(CLIENTES, StandardCharsets.UTF_8, StandardOpenOption.APPEND,StandardOpenOption.CREATE)) {
            String linea = "\nid:" + "\"" + c.getId() + "\"" + "\"" + "nombre:" +  "\"" + "\"" + c.getNombre() +  "\"" + ","
                    + "\"" + "telefono:" + "\"" + "\"" + c.getTelefono() + "\"" + "," + "\"" + "matricula:" + "\"" + "\"" + c.getMatricula() + "\"";
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
    public void guardarPago(Pago p){
        try (BufferedWriter bw = Files.newBufferedWriter(PAGOS, StandardCharsets.UTF_8, StandardOpenOption.APPEND,StandardOpenOption.CREATE)) {
            String linea = "\nid:" + "\"" + "\"" + p.getId() + "\"" + "," + "\"" + "ClienteId:" + "\"" + "\"" + p.getId_cliente() + "\"" + ","
                    + "\"" + "fecha:" + "\"" + "\"" + p.getFecha() + "\"" + "," + "\"" + "importe:" + "\"" + "\"" + p.getImporte() + "\"" + ","
                    + "\"" + "litros:" + "\"" + "\"" + p.getLitros() + "\"" + "," + "\"" + "combustible:" + "\"" + p.getCombustible() + "\"";
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
