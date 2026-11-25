import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlmacenamientoCSV implements GestorArchivos {
    public static final Path CARPETA = Path.of("datos");
    public static final Path CLIENTES = CARPETA.resolve("clientes.csv");
    public static final Path PAGOS = CARPETA.resolve("pagos.csv");

    public AlmacenamientoCSV() throws IOException {
        Files.createDirectories(CARPETA);
        if (Files.notExists(CLIENTES)) Files.createFile(CLIENTES);
        if (Files.notExists(PAGOS)) Files.createFile(PAGOS);
    }

    @Override
    public List<Cliente> leerClientes() throws IOException {
        List<Cliente> clientes = new ArrayList<>();
        for (List<String> campos : leerRegistros(CLIENTES)) {
            if (campos.size() != 4) throw new IOException("Registro de cliente CSV inválido: " + campos);
            String matricula = limpiarTerminadorAntiguo(campos.get(3));
            clientes.add(new Cliente(Integer.parseInt(campos.get(0)), campos.get(1),
                    campos.get(2), matricula));
        }
        clientes.sort(null);
        return clientes;
    }

    @Override
    public List<Pago> leerPagos() throws IOException {
        List<Pago> pagos = new ArrayList<>();
        for (List<String> campos : leerRegistros(PAGOS)) {
            if (campos.size() != 6) throw new IOException("Registro de pago CSV inválido: " + campos);
            String combustible = limpiarTerminadorAntiguo(campos.get(5));
            pagos.add(new Pago(Integer.parseInt(campos.get(0)), Integer.parseInt(campos.get(1)),
                    LocalDate.parse(campos.get(2)), Double.parseDouble(campos.get(3)),
                    Double.parseDouble(campos.get(4)), combustible));
        }
        pagos.sort(null);
        return pagos;
    }

    @Override
    public void guardarCliente(Cliente cliente) throws IOException {
        escribirRegistro(CLIENTES, List.of(String.valueOf(cliente.getId()), cliente.getNombre(),
                cliente.getTelefono(), cliente.getMatricula()));
    }

    @Override
    public void guardarPago(Pago pago) throws IOException {
        escribirRegistro(PAGOS, List.of(String.valueOf(pago.getId()),
                String.valueOf(pago.getId_cliente()), pago.getFecha().toString(),
                String.valueOf(pago.getImporte()), String.valueOf(pago.getLitros()),
                pago.getCombustible()));
    }

    private static List<List<String>> leerRegistros(Path archivo) throws IOException {
        String texto = Files.readString(archivo, StandardCharsets.UTF_8);
        List<List<String>> registros = new ArrayList<>();
        List<String> campos = new ArrayList<>();
        StringBuilder campo = new StringBuilder();
        boolean entreComillas = false;
        for (int i = 0; i < texto.length(); i++) {
            char caracter = texto.charAt(i);
            if (caracter == '"') {
                if (entreComillas && i + 1 < texto.length() && texto.charAt(i + 1) == '"') {
                    campo.append('"');
                    i++;
                } else {
                    entreComillas = !entreComillas;
                }
            } else if (caracter == ',' && !entreComillas) {
                campos.add(campo.toString());
                campo.setLength(0);
            } else if ((caracter == '\n' || caracter == '\r') && !entreComillas) {
                if (caracter == '\r' && i + 1 < texto.length() && texto.charAt(i + 1) == '\n') i++;
                campos.add(campo.toString());
                if (!(campos.size() == 1 && campos.get(0).isEmpty())) registros.add(campos);
                campos = new ArrayList<>();
                campo.setLength(0);
            } else {
                campo.append(caracter);
            }
        }
        if (entreComillas) throw new IOException("CSV con comillas sin cerrar: " + archivo);
        if (campo.length() > 0 || !campos.isEmpty()) {
            campos.add(campo.toString());
            registros.add(campos);
        }
        return registros;
    }

    private static String limpiarTerminadorAntiguo(String valor) {
        return valor.endsWith(";") ? valor.substring(0, valor.length() - 1) : valor;
    }

    private static void escribirRegistro(Path archivo, List<String> valores) throws IOException {
        List<String> escapados = new ArrayList<>();
        for (String valor : valores) {
            String seguro = valor.replace("\"", "\"\"");
            if (seguro.contains(",") || seguro.contains("\"") || seguro.contains("\n")
                    || seguro.contains("\r")) {
                seguro = "\"" + seguro + "\"";
            }
            escapados.add(seguro);
        }
        Files.writeString(archivo, String.join(",", escapados) + System.lineSeparator(),
                StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
