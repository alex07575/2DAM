
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Gasolinera {
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Pago> pagos = new ArrayList<>();
    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Gasolinera() throws IOException {
        cargarClientes();
        cargarPagos();
    }

    private void cargarClientes() throws IOException {
        for (String linea : Almacenamiento.leerClientes) {
            String[] datos = linea.split(";");
            Cliente cliente = new Cliente(Integer.parseInt(datos[0]), datos[1], datos[2], datos[3]);
            clientes.add(cliente);
        }
    }

    private void cargarPagos() throws IOException {
        for (String linea : Almacenamiento.leerPagos) {
            String[] datos = linea.split(";");
            Cliente cliente = new Cliente(Integer.parseInt(datos[0]), datos[1], datos[2], datos[3]);
            clientes.add(cliente);
        }
    }

    public int siguienteIdCliente() {
        int max = 0;
        for (Cliente c : clientes) {
            if (c.getId() > max) {
                max = c.getId();
            }
        }
        return max + 1;
    }

    public int siguienteIdPago() {
        int max = 0;
        for (Pago p : pagos) {
            if (p.getId() > max) {
                max = p.getId();
            }
        }
        return max + 1;
    }

    public boolean matriculaExiste(String matricula) {
        for (Cliente c : clientes) {
            if (c.getMatricula().equalsIgnoreCase(matricula)) {
                return true;
            }
        }
        return false;
    }

    public void altaCliente(String nombre, String telefono, String matricula)
            throws IOException {
        Cliente cliente = new Cliente(
                siguienteIdCliente(),
                nombre.trim(),
                telefono.trim(),
                matricula.trim().toUpperCase()
        );
        Almacenamiento.guardarCliente(cliente);
        clientes.add(cliente);
        System.out.println("Cliente registrado con ID " + cliente.getId() + ".");
    }

    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes.");
            return;
        }
        ArrayList<Cliente> lista = new ArrayList<>(clientes);
        System.out.println("ID NOMBRE TELEFONO MATRICULA");
        for (Cliente c : lista) {
            System.out.println(c);
        }
    }

    public void buscarClientes(String texto) {
        ArrayList<Cliente> encontrados = new ArrayList<>();
        for (Cliente c : clientes) {
            if (c.getNombre().toLowerCase().contains(texto.toLowerCase())
                    || c.getTelefono().toLowerCase().contains(texto.toLowerCase())
                    || c.getMatricula().toLowerCase().contains(texto.toLowerCase())) {

                encontrados.add(c);
            }
        }
        if (encontrados.isEmpty()) {
            System.out.println("No se han encontrado clientes.");
            return;
        }
        System.out.println("ID NOMBRE TELEFONO MATRICULA");
        for (Cliente c : encontrados) {
            System.out.println(c);
        }
    }

    public Cliente buscarCliente(int id) {
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public boolean hayClientes() {
        return !clientes.isEmpty();
    }

    public void mostrarClientes() {
        listarClientes();
    }

    public void registrarPago(int idCliente, LocalDate fecha, double importe,
                              double litros, String combustible) throws IOException {
        Pago pago = new Pago(
                siguienteIdPago(),
                idCliente,
                fecha,
                importe,
                litros,
                combustible.trim()
        );
        Almacenamiento.guardarPago(pago);
        pagos.add(pago);
        Cliente cliente = buscarCliente(idCliente);
        System.out.println("Pago " + pago.getId() + " registrado para " + cliente.getNombre() + ": " + pago.getImporte() + " €.");
    }

    public void listarPagos() {
        if (pagos.isEmpty()) {
            System.out.println("No hay pagos.");
            return;
        }
        ArrayList<Pago> lista = new ArrayList<>(pagos);
        System.out.println("ID | CLIENTE | FECHA | IMPORTE | LITROS | COMBUSTIBLE");

        for (Pago p : lista) {
            Cliente c = buscarCliente(p.getId_cliente());
            System.out.println(p.getId() + " | " +
                    c.getNombre() + " | " +
                    p.getFecha().format(formato) + " | " +
                    p.getImporte() + " € | " +
                    p.getLitros() + "L | " +
                    p.getCombustible());
        }
    }

    public LocalDate convertirFecha(String texto) {
        try {
            return LocalDate.parse(texto, formato);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean fechaValida(String texto) {
        return convertirFecha(texto) != null;
    }
}



