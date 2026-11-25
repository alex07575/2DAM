import java.io.IOException;
import java.util.List;

public interface GestorArchivos {
    List<Cliente> leerClientes() throws IOException;
    List<Pago> leerPagos() throws IOException;
    void guardarCliente(Cliente c) throws IOException;
    void guardarPago(Pago p) throws IOException;
}
