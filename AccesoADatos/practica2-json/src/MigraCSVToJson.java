import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class MigraCSVToJson {
    private final Path ARCHIVO_CLIENTE_CSV;
    private final Path ARCHIVO_CLIENTE_JSON;
    private final Path ARCHIVO_PAGO_CSV;
    private final Path ARCHIVO_PAGO_JSON;
    private GestorArchivos gestor;
    private AlmacenamientoCSV  almacenamientoCsv;
    public MigraCSVToJson(Path origenCSV, Path destinoJSON) throws IOException {
        ARCHIVO_CLIENTE_CSV = origenCSV.resolve("clientes.csv");
        ARCHIVO_CLIENTE_JSON = destinoJSON.resolve("clientes.json");
        ARCHIVO_PAGO_CSV = origenCSV.resolve("pagos.csv");
        ARCHIVO_PAGO_JSON = destinoJSON.resolve("pagos.json");
    }

    public void migrarClientes() throws IOException {
        List<Cliente> clientes = almacenamientoCsv.leerClientes();
            for(Cliente c : clientes){
                gestor.guardarCliente(c);
            }
        System.out.println("Clientes migrados: " + clientes.size());
    }
    public void migrarPagos() throws IOException {
        List<Pago> pagos = almacenamientoCsv.leerPagos();
        for(Pago p : pagos){
            gestor.guardarPago(p);
        }
        System.out.println("Pagos migrados: " + pagos.size());
    }
    public void ejecutar() throws IOException {
        Scanner sc = new Scanner(System.in);
        if(!Files.exists(ARCHIVO_CLIENTE_JSON) && !Files.exists(ARCHIVO_PAGO_JSON)){
            System.out.println("Quieres migrar? ");
            String op = sc.nextLine().toLowerCase();
            if(op.equals("si")) {
                System.out.println("Iniciando migración CSV a JSON");
                gestor = new AlmacenamientoJSON();
                almacenamientoCsv = new AlmacenamientoCSV();
                migrarClientes();
                migrarPagos();
                System.out.println("Validación completada");

                System.out.println("Migracion completada");
            } else {
                System.out.println("Has indicado no o una opción invalida.");
            }
        } else {
            System.out.println("No se hace migración, ya existen ficheros JSON");
        }


    }
}

