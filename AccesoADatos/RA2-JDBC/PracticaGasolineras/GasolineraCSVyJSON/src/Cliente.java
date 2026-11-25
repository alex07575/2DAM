import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cliente implements Comparable<Cliente> {
    private final Integer id;
    private final String codigoCliente;
    private final String nombre;
    private final String telefono;
    private final String email;
    private final String matricula;
    private final List<InscripcionCampania> inscripciones = new ArrayList<>();
    private TarjetaFidelizacion tarjeta;

    public Cliente(int id, String nombre, String telefono, String matricula) {
        if (id <= 0) {
            throw new IllegalArgumentException("El id debe ser positivo.");
        }
        this.id = id;
        this.codigoCliente = null;
        this.nombre = Validacion.texto(nombre, "El nombre");
        this.telefono = Validacion.texto(telefono, "El teléfono");
        this.email = null;
        this.matricula = Validacion.texto(matricula, "La matrícula").toUpperCase();
    }

    public Cliente(Integer id, String codigoCliente, String nombre, String telefono,
                   String email, String matricula) {
        this.id = Validacion.idOpcional(id);
        this.codigoCliente = Validacion.texto(codigoCliente, "El código del cliente");
        this.nombre = Validacion.texto(nombre, "El nombre");
        this.telefono = textoOpcional(telefono);
        this.email = textoOpcional(email);
        this.matricula = textoOpcional(matricula);
        if (this.telefono == null && this.email == null) {
            throw new IllegalArgumentException("Debe facilitar teléfono o correo electrónico.");
        }
    }

    private String textoOpcional(String valor) {
        return valor == null || valor.trim().isEmpty() ? null : valor.trim();
    }

    void registrarTarjeta(TarjetaFidelizacion nuevaTarjeta) {
        if (tarjeta != null || nuevaTarjeta == null) {
            throw new IllegalStateException("El cliente ya tiene una tarjeta o la tarjeta no es válida.");
        }
        tarjeta = nuevaTarjeta;
    }

    void registrarInscripcion(InscripcionCampania inscripcion) {
        inscripciones.add(inscripcion);
    }

    boolean participaEn(Campania campania) {
        for (InscripcionCampania inscripcion : inscripciones) {
            if (inscripcion.getCampania() == campania) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "{" +
                "id: " + id +
                ", nombre: " + nombre +
                ", telefono: " + telefono +
                ", matricula: " + matricula + '}';
    }

    public int getId() {

        return id;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public String getNombre() {

        return nombre;
    }

    public String getTelefono() {

        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getMatricula() {

        return matricula;
    }

    public TarjetaFidelizacion getTarjeta() {
        return tarjeta;
    }

    public List<InscripcionCampania> getInscripciones() {
        return Collections.unmodifiableList(inscripciones);
    }

    @Override
    public int compareTo(Cliente o) {
        int resultado = this.nombre.compareTo(o.getNombre());
        if(resultado == 0){
            resultado = this.id-o.getId();
        }
        return resultado;
    }
}


