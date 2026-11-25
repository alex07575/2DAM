import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AlmacenamientoJSON implements GestorArchivos {
    public static final Path CARPETA = Path.of("datosJ");
    public static final Path CLIENTES = CARPETA.resolve("clientes.json");
    public static final Path PAGOS = CARPETA.resolve("pagos.json");

    public AlmacenamientoJSON() throws IOException {
        Files.createDirectories(CARPETA);
        if (Files.notExists(CLIENTES)) Files.writeString(CLIENTES, "[]", StandardCharsets.UTF_8);
        if (Files.notExists(PAGOS)) Files.writeString(PAGOS, "[]", StandardCharsets.UTF_8);
    }

    @Override
    public List<Cliente> leerClientes() throws IOException {
        List<Cliente> resultado = new ArrayList<>();
        for (Map<String, Object> fila : leerObjetos(CLIENTES)) {
            String matricula = sinTerminadorAntiguo(texto(fila, "matricula"));
            resultado.add(new Cliente(entero(fila, "id"), texto(fila, "nombre"),
                    texto(fila, "telefono"), matricula));
        }
        resultado.sort(null);
        return resultado;
    }

    @Override
    public List<Pago> leerPagos() throws IOException {
        List<Pago> resultado = new ArrayList<>();
        for (Map<String, Object> fila : leerObjetos(PAGOS)) {
            String combustible = sinTerminadorAntiguo(texto(fila, "combustible"));
            resultado.add(new Pago(entero(fila, "id"), entero(fila, "id_cliente"),
                    LocalDate.parse(texto(fila, "fecha")), decimal(fila, "importe").doubleValue(),
                    decimal(fila, "litros").doubleValue(), combustible));
        }
        resultado.sort(null);
        return resultado;
    }

    @Override
    public void guardarCliente(Cliente cliente) throws IOException {
        List<Cliente> clientes = leerClientes();
        clientes.add(cliente);
        List<Map<String, Object>> filas = new ArrayList<>();
        for (Cliente c : clientes) {
            Map<String, Object> fila = new LinkedHashMap<>();
            fila.put("id", c.getId());
            fila.put("nombre", c.getNombre());
            fila.put("telefono", c.getTelefono());
            fila.put("matricula", c.getMatricula());
            filas.add(fila);
        }
        guardarObjetos(CLIENTES, filas);
    }

    @Override
    public void guardarPago(Pago pago) throws IOException {
        List<Pago> pagos = leerPagos();
        pagos.add(pago);
        List<Map<String, Object>> filas = new ArrayList<>();
        for (Pago p : pagos) {
            Map<String, Object> fila = new LinkedHashMap<>();
            fila.put("id", p.getId());
            fila.put("id_cliente", p.getId_cliente());
            fila.put("fecha", p.getFecha().toString());
            fila.put("importe", BigDecimal.valueOf(p.getImporte()));
            fila.put("litros", BigDecimal.valueOf(p.getLitros()));
            fila.put("combustible", p.getCombustible());
            filas.add(fila);
        }
        guardarObjetos(PAGOS, filas);
    }

    private static List<Map<String, Object>> leerObjetos(Path archivo) throws IOException {
        Object json = JsonSimple.parse(Files.readString(archivo, StandardCharsets.UTF_8));
        if (!(json instanceof List<?>)) throw new IOException("Se esperaba un arreglo JSON en " + archivo);
        List<Map<String, Object>> resultado = new ArrayList<>();
        for (Object elemento : (List<?>) json) {
            if (!(elemento instanceof Map<?, ?>)) throw new IOException("Elemento JSON no es un objeto.");
            @SuppressWarnings("unchecked")
            Map<String, Object> fila = (Map<String, Object>) elemento;
            resultado.add(fila);
        }
        return resultado;
    }

    private static void guardarObjetos(Path archivo, List<Map<String, Object>> filas) throws IOException {
        List<String> objetos = new ArrayList<>();
        for (Map<String, Object> fila : filas) {
            List<String> campos = new ArrayList<>();
            for (Map.Entry<String, Object> campo : fila.entrySet()) {
                campos.add(JsonSimple.cadena(campo.getKey()) + ": " + valorJson(campo.getValue()));
            }
            objetos.add("  {" + String.join(", ", campos) + "}");
        }
        Files.writeString(archivo, "[\n" + String.join(",\n", objetos) + "\n]\n",
                StandardCharsets.UTF_8);
    }

    private static String valorJson(Object valor) {
        if (valor instanceof String) return JsonSimple.cadena((String) valor);
        if (valor instanceof BigDecimal) return ((BigDecimal) valor).toPlainString();
        return String.valueOf(valor);
    }

    private static String texto(Map<String, Object> fila, String clave) throws IOException {
        Object valor = fila.get(clave);
        if (!(valor instanceof String)) throw new IOException("Campo JSON de texto inválido: " + clave);
        return (String) valor;
    }

    private static int entero(Map<String, Object> fila, String clave) throws IOException {
        try {
            return decimal(fila, clave).intValueExact();
        } catch (ArithmeticException e) {
            throw new IOException("Campo JSON entero inválido: " + clave, e);
        }
    }

    private static BigDecimal decimal(Map<String, Object> fila, String clave) throws IOException {
        Object valor = fila.get(clave);
        if (!(valor instanceof BigDecimal)) throw new IOException("Campo JSON numérico inválido: " + clave);
        return (BigDecimal) valor;
    }

    private static String sinTerminadorAntiguo(String valor) {
        return valor.endsWith(";") ? valor.substring(0, valor.length() - 1) : valor;
    }
}
