import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MigraCSVToJson {

    private final Path ARCHIVO_CLIENTE_CSV;
    private final Path ARCHIVO_CLIENTE_JSON;
    GestorArchivos g = new AlmacenamientoJSON();

    public MigraCSVToJson(Path origenCSV, Path destinoJSON) throws IOException {
        ARCHIVO_CLIENTE_CSV = origenCSV.resolve("clientes.csv");
        ARCHIVO_CLIENTE_JSON = destinoJSON.resolve("clientes.json");
    }


}
