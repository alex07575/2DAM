import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Flujo {
    private Path archivoCliente = Path.of("datos","clientes.csv");
    private Path archivoPago = Path.of("datos","pagos.csv");

    private static final String CABECERA_CLIENTES = "id;nombre;telefono;matricula";
    private static final String CABECERA_PAGOS = "id;idCliente;fecha;importe;litros;combustible";

    public void abrirFlujo() throws IOException {
        Files.createDirectories(archivoCliente.toAbsolutePath().getParent());

        if (Files.notExists(archivoCliente)) {
            Files.writeString(archivoCliente, CABECERA_CLIENTES + System.lineSeparator(), StandardCharsets.UTF_8);
        }
        if (Files.notExists(archivoPago)) {
            Files.writeString(archivoPago, CABECERA_PAGOS + System.lineSeparator(), StandardCharsets.UTF_8);
        }
    }

    public List<Cliente> cargarClientes() throws IOException {
        List<Cliente> clientes = new ArrayList<>();
        List<String> lineas = Files.readAllLines(archivoCliente, StandardCharsets.UTF_8);

        for (int i = 1; i < lineas.size(); i++) {
            String linea = lineas.get(i);
            if (linea.isBlank()) {
                continue;
            }
            String[] campos = linea.split(";", -1);
            if (campos.length != 4) {
                throw new IOException("La línea " + (i + 1) + " de " + archivoCliente + " no tiene 4 campos.");
            }
            try {
                int id = Integer.parseInt(campos[0].trim());
                clientes.add(new Cliente(id, campos[1], campos[2], campos[3]));
            } catch (IllegalArgumentException e) {
                throw new IOException("La línea " + (i + 1) + " de " + archivoCliente
                        + " no se puede interpretar: " + e.getMessage(), e);
            }
        }
        return clientes;
    }

    public List<Pagos> cargarPagos() throws IOException {
        List<Pagos> pagos = new ArrayList<>();
        List<String> lineas = Files.readAllLines(archivoPago, StandardCharsets.UTF_8);

        for (int i = 1; i < lineas.size(); i++) {
            String linea = lineas.get(i);
            if (linea.isBlank()) {
                continue;
            }
            String[] campos = linea.split(";", -1);
            if (campos.length != 6) {
                throw new IOException("La línea " + (i + 1) + " de " + archivoPago + " no tiene 6 campos.");
            }
            try {
                int id = Integer.parseInt(campos[0].trim());
                int idCliente = Integer.parseInt(campos[1].trim());
                LocalDate fecha = LocalDate.parse(campos[2].trim());
                BigDecimal importe = new BigDecimal(campos[3].trim());
                BigDecimal litros = new BigDecimal(campos[4].trim());
                pagos.add(new Pagos(id, idCliente, fecha, importe, litros, campos[5]));
            } catch (RuntimeException e) {
                throw new IOException("La línea " + (i + 1) + " de " + archivoPago
                        + " no se puede interpretar: " + e.getMessage(), e);
            }
        }
        return pagos;
    }

    public void guardarCliente(Cliente cliente) throws IOException {
        String linea = cliente.getId() + ";" + cliente.getNombre() + ";"
                + cliente.getTelefono() + ";" + cliente.getMatricula();
    }

    public void guardarPago(Pagos pago) throws IOException {
        String linea = pago.getId() + ";" + pago.getId_cliente() + ";" + pago.getFecha() + ";"
                + pago.getImporte().toPlainString() + ";" + pago.getLitros().toPlainString() + ";"
                + pago.getCombustible();
    }

}
