import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Gasolinera {
    private final Flujo ficheros;
    private final List<Cliente> clientes = new ArrayList<>();
    private final List<Pagos> pagos = new ArrayList<>();
    private int siguienteIdCliente = 1;
    private int siguienteIdPago = 1;

    public Gasolinera(Flujo ficheros) {
        this.ficheros = ficheros;
    }

    public void iniciar(){

    }
}
