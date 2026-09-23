import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class Gasolinera {
    private final Flujo ficheros;
    private final List<Cliente> clientes = new ArrayList<>();
    private final List<Pagos> pagos = new ArrayList<>();
    private int siguienteIdCliente = 1;
    private int siguienteIdPago = 1;

    public Gasolinera(Flujo ficheros) {
        this.ficheros = ficheros;
    }

    public void iniciar() throws IOException{
        ficheros.abrirFlujo();

        clientes.addAll(ficheros.cargarClientes());
        pagos.addAll(ficheros.cargarPagos());

        siguienteIdCliente = clientes.stream().mapToInt(Cliente::getId).max().orElse(0) + 1;
        siguienteIdPago = pagos.stream().mapToInt(Pagos::getId).max().orElse(0) + 1;

    }

    public Cliente darDeAltaCliente(String nombre, String telefono, String matricula) throws IOException {
        String matriculaNormalizada = matricula.trim().toUpperCase();
        boolean existe = clientes.stream().anyMatch(c -> c.getMatricula().equalsIgnoreCase(matriculaNormalizada));
        if (existe) {
            throw new IllegalStateException("Esa matrícula ya está registrada: " + matriculaNormalizada);
        }

        Cliente c = new Cliente(siguienteIdCliente, nombre, telefono, matriculaNormalizada);

        ficheros.guardarCliente(c);
        clientes.add(c);
        siguienteIdCliente++;

        return c;
    }

    public boolean hayClientes() {

        return !clientes.isEmpty();
    }


    public List<Cliente> listarClientes() {

        return clientes;
    }

    public List<Cliente> buscarClientes(int id_cliente) {

        return clientes;
    }

    public Pagos registrarPago(int idCliente, LocalDate fecha, BigDecimal importe,
                               BigDecimal litros, String combustible) throws IOException {
        if (buscarClientes(idCliente).isEmpty()) {
            throw new NoSuchElementException("No existe un cliente con el identificador " + idCliente + ".");
        }
        Pagos pago = new Pagos(siguienteIdPago, idCliente, fecha, importe, litros, combustible);

        ficheros.guardarPago(pago);
        pagos.add(pago);
        siguienteIdPago++;

        return pago;
    }

    public List<Pagos> listarPagos() {

        return pagos;
    }
}
