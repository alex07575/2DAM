import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MigraCSVToJson {
    private final Path ARCHIVO_CLIENTE_CSV;
    private final Path ARCHIVO_CLIENTE_JSON;
    private final Path ARCHIVO_PAGO_CSV;
    private final Path ARCHIVO_PAGO_JSON;
    private final GestorArchivos g;
    public MigraCSVToJson(Path origenCSV, Path destinoJSON) throws IOException {
        ARCHIVO_CLIENTE_CSV = origenCSV.resolve("clientes.csv");
        ARCHIVO_CLIENTE_JSON = destinoJSON.resolve("clientes.json");
        ARCHIVO_PAGO_CSV = origenCSV.resolve("pagos.csv");
        ARCHIVO_PAGO_JSON = destinoJSON.resolve("pagos.json");
        g = new AlmacenamientoJSON();
    }

    public void migrarClientes() throws IOException {
        List<Cliente> clientes = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(ARCHIVO_CLIENTE_CSV, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.replace(";", "");
                String[] partes = linea.split(",");
                if (partes.length == 4) {
                    int id = Integer.parseInt(partes[0].trim());
                    boolean existe = false;
                    for (Cliente c : clientes) {
                        if (c.getId() == id) {
                            existe = true;
                        }
                    }
                    if (!existe) {
                        clientes.add(new Cliente(id, partes[1].trim(),
                                partes[2].trim(), partes[3].trim()));
                    }
                }
            }
        }

        try (BufferedWriter bw = Files.newBufferedWriter(ARCHIVO_CLIENTE_JSON, StandardCharsets.UTF_8)) {
            bw.write("[\n");
            for (int i = 0; i < clientes.size(); i++) {
                Cliente c = clientes.get(i);
                bw.write("{\"id\":" + c.getId() + ",\"nombre\":\"" + c.getNombre() + "\",\"telefono\":\""
                        + c.getTelefono() + "\",\"matricula\":\"" + c.getMatricula() + "\"}");
                if (i < clientes.size() - 1) {
                    bw.write(",\n");
                }else{
                    bw.write("\n");
                }
            }
            bw.write("]");
            bw.newLine();
        }

        System.out.println("Clientes migrados: " + clientes.size());
    }
    public void migrarPagos() throws IOException {
        List<Pago> pagos = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(ARCHIVO_PAGO_CSV, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.replace(";", "");
                String[] partes = linea.split(",");
                if (partes.length == 6) {
                    int id = Integer.parseInt(partes[0].trim());
                    boolean existe = false;
                    for (Pago p : pagos) {
                        if (p.getId() == id) {
                            existe = true;
                        }
                    }
                    if (!existe) {
                        pagos.add(new Pago(id, Integer.parseInt(partes[1].trim()),
                                LocalDate.parse(partes[2].trim()),
                                Double.parseDouble(partes[3].trim()),
                                Double.parseDouble(partes[4].trim()),
                                partes[5].trim()));
                    }
                }
            }
        }
        try (BufferedWriter bw = Files.newBufferedWriter(ARCHIVO_PAGO_JSON, StandardCharsets.UTF_8)) {
            bw.write("[\n");
            for (int i = 0; i < pagos.size(); i++) {
                Pago p = pagos.get(i);
                bw.write("{\"id\":" + p.getId() + ",\"id_cliente\":" + p.getId_cliente() + ",\"fecha\":\"" + p.getFecha() + "\",\"importe\":" + p.getImporte() + ",\"litros\":" + p.getLitros() + ",\"combustible\":\"" + p.getCombustible() + "\"}");
                if (i < pagos.size() - 1){
                    bw.write(",\n");
                }else{
                    bw.write("\n");
                }
            }
            bw.write("]");
            bw.newLine();
        }
        System.out.println("Pagos migrados: " + pagos.size());
    }
    public void ejecutar() throws IOException {
        System.out.println("Iniciando migración CSV a JSON");
        System.out.println("Validación completada");
        migrarClientes();
        migrarPagos();
        System.out.println("Migracion completada");
    }
}

