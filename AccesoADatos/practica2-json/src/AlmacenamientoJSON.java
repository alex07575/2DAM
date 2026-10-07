import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AlmacenamientoJSON implements GestorArchivos{
    public static final Path CARPETA = Path.of("datosJ");
    public static final Path CLIENTES = CARPETA.resolve("clientes.json");
    public static final Path PAGOS = CARPETA.resolve("pagos.json");
    public static final String JSON_OPEN = "{\n[\n";
    public static final String JSON_CLOSE = "\n]\n}";

    public AlmacenamientoJSON() throws  IOException{
        Files.deleteIfExists(CARPETA);
        Files.createDirectories(CARPETA);
        Files.deleteIfExists(CLIENTES);
        Files.createFile(CLIENTES);
        Files.deleteIfExists(PAGOS);
        Files.createFile(PAGOS);
    }

    private String ClienteJson(Cliente c){
        return "";
    }
    private String PagoJson(Pago p){
        return "";
    }

    @Override
    public List<Cliente> leerClientes() {
        return List.of();
    }
    @Override
    public List<Pago> leerPagos(){
        return List.of();
    }
    @Override
    public void guardarCliente(Cliente c){
        List<Cliente> clientes = leerClientes();
        clientes.add(c);
        try (BufferedWriter bw = Files.newBufferedWriter(CLIENTES, StandardCharsets.UTF_8, StandardOpenOption.CREATE)){
            bw.write(JSON_OPEN);
            String clientes_en_string = clientes.stream().map(this::ClienteJson).reduce
                    ((String s1, String s2) -> s1 + ",\n" + s2).orElse("");
            bw.write(clientes_en_string);
            bw.write(JSON_CLOSE);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    @Override
    public void guardarPago(Pago p){
        List<Pago> pagos = leerPagos();
        pagos.add(p);
        try (BufferedWriter bw = Files.newBufferedWriter(PAGOS, StandardCharsets.UTF_8, StandardOpenOption.CREATE)) {
            bw.write(JSON_OPEN);
            String pagos_en_string = pagos.stream().map(this::PagoJson).reduce
                    ((String s1, String s2) -> s1 + ",\n" + s2).orElse("");
            bw.write(pagos_en_string);
            bw.write(JSON_CLOSE);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
