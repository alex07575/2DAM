import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MigraCSVToJson implements GestorArchivos {
    private static final Path CARPETAJSON = Path.of("datosJ");
    private static final Path CLIENTESJSON = CARPETAJSON.resolve("clientes.json");
    private static final Path PAGOSJSON = CARPETAJSON.resolve("pagos.json");
    GestorArchivos g = new AlmacenamientoJSON();

    public MigraCSVToJson() throws IOException {
        Files.createDirectories(CARPETAJSON);
        if (Files.notExists(AlmacenamientoCSV.CLIENTES)) {
            Files.createFile(CLIENTESJSON);
        } else {
            leerClientes();

        }

        if (Files.notExists(AlmacenamientoCSV.PAGOS)) {
            Files.createFile(PAGOSJSON);
        } else {
            leerPagos();

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
