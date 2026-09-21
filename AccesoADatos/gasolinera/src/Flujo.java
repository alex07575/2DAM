import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Flujo {
    private Path archivoCliente = Path.of("datos","clientes.csv");
    private Path archivoPago = Path.of("datos","pagos.csv");

    public List<Cliente> cargarClientes() throws IOException {
        List<Cliente> clientes = new ArrayList<>();
        return clientes;
    }

    public List<Pagos> cargarPagos() throws IOException {
        List<Pagos> pagos = new ArrayList<>();
        return pagos;
    }

    public void abrirFlujo(){
        if (Files.exists(archivoCliente)) {
            System.out.println("El archivo existe");
        }
    }

}
