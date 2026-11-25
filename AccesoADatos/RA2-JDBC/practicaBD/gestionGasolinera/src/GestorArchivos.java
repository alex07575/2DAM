import java.util.List;

public interface GestorArchivos {
    public List<Cliente> leerClientes();
    public List<Pago> leerPagos();
    public void guardarCliente(Cliente c);
    public void guardarPago(Pago p);
}
