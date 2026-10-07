import java.io.*;
import java.nio.file.Path;

public class MigraCSVToJson {
    private final Path ARCHIVO_CLIENTE_CSV;
    private final Path ARCHIVO_CLIENTE_JSON;
    private final Path ARCHIVO_PAGO_CSV;
    private final Path ARCHIVO_PAGO_JSON;
    GestorArchivos g = new AlmacenamientoJSON();

    public MigraCSVToJson(Path origenCSV, Path destinoJSON) throws IOException {
        ARCHIVO_CLIENTE_CSV = origenCSV.resolve("clientes.csv");
        ARCHIVO_CLIENTE_JSON = destinoJSON.resolve("clientes.json");
        ARCHIVO_PAGO_CSV = destinoJSON.resolve("pagos.csv");
        ARCHIVO_PAGO_JSON = destinoJSON.resolve("pagos.json");
    }


}
