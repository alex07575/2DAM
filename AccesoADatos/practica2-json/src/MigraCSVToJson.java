import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MigraCSVToJson implements GestorArchivos{
    private static final Path CARPETAJSON = Path.of("datos");
    private static final Path CLIENTESJSON = CARPETAJSON.resolve("clientes.json");
    private static final Path PAGOSJSON = CARPETAJSON.resolve("pagos.json");

    public MigraCSVToJson() throws IOException{
        Files.createDirectories(CARPETAJSON);
        if (Files.notExists(CLIENTESJSON)) {
            Files.createFile(CLIENTESJSON);
        }

        if (Files.notExists(PAGOSJSON)) {
            Files.createFile(PAGOSJSON);
        }
    }

    @Override
    public List<Cliente> leerClientes() {
        return List.of();
    }

    @Override
    public List<Pago> leerPagos() {
        return List.of();
    }

    @Override
    public void guardarCliente(Cliente c) {

    }

    @Override
    public void guardarPago(Pago p) {

    }
}
