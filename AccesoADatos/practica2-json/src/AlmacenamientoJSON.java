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
    public static final String JSON_OPEN = "[\n";
    public static final String JSON_CLOSE = "\n]";

    public AlmacenamientoJSON() throws  IOException{
        if (!Files.exists(CARPETA)) Files.createDirectories(CARPETA);
        if (!Files.exists(CLIENTES)) Files.writeString(CLIENTES, JSON_OPEN + JSON_CLOSE);
        if (!Files.exists(PAGOS)) Files.writeString(PAGOS, JSON_OPEN + JSON_CLOSE);
    }

    private String ClienteJson(Cliente c) {
        return "{\"id\": " + c.getId() + ",\"nombre\": \"" + c.getNombre() + "\",\"telefono\": \""
                + c.getTelefono() + "\",\"matricula\": \"" + c.getMatricula() + "\"}";
    }
    private String PagoJson(Pago p) {
        return "{\"id\": " + p.getId() + ",\"id_cliente\": " + p.getId_cliente()
                + ",\"fecha\": \"" + p.getFecha() + "\",\"importe\": " + p.getImporte()
                + ",\"litros\": " + p.getLitros() + ",\"combustible\": \"" + p.getCombustible() + "\"}";
    }

    @Override
    public List<Cliente> leerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(CLIENTES, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains("\"id\":")) {
                    String[] campos = linea.replaceAll("[\"{}]", "").split(",");
                    int id = Integer.parseInt(campos[0].split(":")[1].trim());
                    String nombre = campos[1].split(":")[1].trim();
                    String telefono = campos[2].split(":")[1].trim();
                    String matricula = campos[3].split(":")[1].trim();
                    clientes.add(new Cliente(id, nombre, telefono, matricula));
                }
            }
            clientes.sort(null);
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
        while ((linea = br.readLine()) != null) {
            if (linea.contains("\"id\":")) {
                String[] campos = linea.replaceAll("[\"{}]", "").split(",");
                int id = Integer.parseInt(campos[0].split(":")[1].trim());
                int idCli = Integer.parseInt(campos[1].split(":")[1].trim());
                LocalDate fec = LocalDate.parse(campos[2].split(":")[1].trim());
                double imp = Double.parseDouble(campos[3].split(":")[1].trim());
                double lit = Double.parseDouble(campos[4].split(":")[1].trim());
                String com = campos[5].split(":")[1].trim();
                pagos.add(new Pago(id, idCli, fec, imp, lit, com ));
            }
        }
        pagos.sort(null);
    } catch (IOException e){
        throw new RuntimeException();
    }
    return  pagos;
    }
    @Override
    public void guardarCliente(Cliente c){
        List<Cliente> clientes = leerClientes();
        clientes.add(c);
        try (BufferedWriter bw = Files.newBufferedWriter(CLIENTES, StandardCharsets.UTF_8, StandardOpenOption.CREATE)){
            bw.write(JSON_OPEN);
            String clientes_en_string = clientes.stream().map(this::ClienteJson).reduce
                    ((String s1, String s2) -> s1 + ",\n" + s2).orElse("");
            bw.write(clientes_en_string);
            bw.write(JSON_CLOSE);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    @Override
    public void guardarPago(Pago p){
        List<Pago> pagos = leerPagos();
        pagos.add(p);
        try (BufferedWriter bw = Files.newBufferedWriter(PAGOS, StandardCharsets.UTF_8, StandardOpenOption.CREATE)) {
            bw.write(JSON_OPEN);
            String pagos_en_string = pagos.stream().map(this::PagoJson).reduce
                    ((String s1, String s2) -> s1 + ",\n" + s2).orElse("");
            bw.write(pagos_en_string);
            bw.write(JSON_CLOSE);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
