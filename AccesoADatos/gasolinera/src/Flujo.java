import java.nio.file.Path;
import java.nio.file.Files;

public class Flujo {
    private Path archivoCliente = Path.of("datos","clientes.csv");
    private Path archivoPago = Path.of("datos","pagos.csv");

    public void abrirFlujo(){
        if (Files.exists(archivoCliente)) {
            System.out.println("El archivo existe");
        }
    }

}
